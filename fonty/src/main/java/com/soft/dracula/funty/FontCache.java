package com.soft.dracula.funty;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by lion on 10/25/16.
 */

public class FontCache {

    private static HashMap<String, Typeface> fontsHashMap;
    private static FontCache fontCache;
    private WeakReference<Context> context;
    private String standardSeparator = "-";

    public static final String BOLD = "bold";
    public static final String BOLD_ITALIC = "bold-italic";
    public static final String REGULAR = "regular";
    public static final String ITALIC = "italic";

    private String allStyles[] = {
            BOLD,
            BOLD_ITALIC,
            REGULAR,
            ITALIC
    };

    private FontCache() {
        fontsHashMap = new HashMap<>(5);
    }

    public static FontCache getInstance() {
        if (fontCache == null)
            fontCache = new FontCache();

        return fontCache;
    }

    public FontCache init(Context context) {
        this.context = new WeakReference<>(context);
        return this;
    }

    public FontCache setSeparator(String separator) {
        this.standardSeparator = separator;
        return this;
    }

    public Typeface getTypeFace(String fontName, String style) {
        String fontKey = joinName(standardSeparator, fontName, style);
        if (context.get() == null)
            throw new IllegalStateException("Context has been not initialized");

        Typeface typeface = fontsHashMap.get(fontKey);

        if (typeface == null) {
            if (isNotCachedButOnAssets(fontKey)) {
                addFont(fontName, style);
                typeface = fontsHashMap.get(fontKey);
            } else {
                typeface = fontsHashMap.get(joinName(standardSeparator, fontName, REGULAR));
            }
        }

        return typeface;
    }

    public FontCache addFont(String baseName, String style) {
        if (style != null) {
            String fontName = joinName(standardSeparator, baseName, style);
            insertFont(fontName);
        } else {
            for (String aux : allStyles) {
                String fontName = joinName(standardSeparator, baseName, aux);
                insertFont(fontName);
            }
        }

        return this;
    }

    private void insertFont(String fontName) {
        if (!fontsHashMap.containsKey(fontName)) {
            Typeface typeface = loadFont(fontName);

            if (typeface != null)
                fontsHashMap.put(fontName, typeface);
        }
    }

    private Typeface loadFont(String fontName) {
        Typeface typeface = null;

        try {
            typeface = Typeface.createFromAsset(context.get().getAssets(), fontName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return typeface;
    }

    private String joinName(String separator, String... pieces) {
        StringBuffer nameBuffer = new StringBuffer();
        String ttfExtension = ".ttf";

        if (pieces.length == 0) {
            throw new IllegalStateException("No base font's name provided");
        }

        for (int i = 0; i < pieces.length - 1; i++) {
            nameBuffer =
                    nameBuffer
                            .append(pieces[i])
                            .append(separator);
        }

        nameBuffer =
                nameBuffer
                        .append(pieces[pieces.length - 1])
                        .append(ttfExtension);

        return nameBuffer.toString();
    }

    public boolean isNotCachedButOnAssets(String fontKey) {
        try {
            return (Typeface.createFromAsset(context.get().getAssets(), fontKey)) != null;
        } catch (Exception ex) {
            return false;
        }
    }
}
