package com.soft.dracula.sample;

import android.app.Application;

import com.soft.dracula.funty.FontCache;

/**
 * Created by lion on 10/25/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FontCache
                .getInstance()
                .init(this)
                .setSeparator("_")
                .addFont("raleway", null)
                .addFont("cabin", FontCache.ITALIC);
    }
}
