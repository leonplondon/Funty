package com.soft.dracula.funty;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by lion on 10/25/16.
 */
public class FuntyText<T extends TextView> {

    private T[] textField;
    private String fontName;

    private FuntyText(T... textField) {
        this.textField = textField;
    }

    public static <T extends TextView> FuntyText onFields(T... textField) {
        return new FuntyText<>(textField);
    }

    public FuntyText apply() {
        for (T aux : textField) {
            resolveAndApplyTypeFace(aux);
        }

        return this;
    }

    public FuntyText fontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    private void resolveAndApplyTypeFace(T textField) {
        String style = FontCache.REGULAR;
        int currentStyle = textField.getTypeface().getStyle();

        switch (currentStyle) {
            case Typeface.NORMAL:
                style = FontCache.REGULAR;
                break;

            case Typeface.BOLD:
                style = FontCache.BOLD;
                break;

            case Typeface.ITALIC:
                style = FontCache.ITALIC;
                break;

            case Typeface.BOLD_ITALIC:
                style = FontCache.BOLD_ITALIC;
                break;
        }

        Typeface typeface =
                FontCache
                        .getInstance()
                        .getTypeFace(fontName, style);

        if (typeface != null) {
            textField.setTypeface(typeface);
        }
    }
}
