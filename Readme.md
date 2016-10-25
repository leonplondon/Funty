# Funty, a font manager for Android.

> Please forgive my English

This little Android library let you to manage your app fonts in an easy way.

You only have to put your custom fonts inside assets folder following the next rules:

1. Give your font a base name, without specifying the style.
2. Add a separator after base name (*A valid one, e.g: -, _*)
3. Write your style on the name, using only lowercase and using the previous defined separator.
    * bold
    * italic
    * regular
    * bold-italic
4. Don't forget to write the extension (*Only *.ttf files supported*)

If you have a file named `BungeeInline-Regular.ttf` applying above rules it should look now as `BungeeInline-regular.ttf`.

## Instructions

* In your application class, initialize the FontCache instance, it will be responsible to cache fonts to avoid loading each one every time you need it.
```java
public void onCreate() {
       super.onCreate();

       ...
       ...

       FontCache
            .getInstance()
            .init(this);
}
```

If you want, you can add some fonts in the same initialization:
```java
public void onCreate() {
       super.onCreate();

       ...
       ...

       FontCache
            .getInstance()
            .setSeparator("-")
            .addFont("raleway", null)
            .addFont("cabin", FontCache.ITALIC);
}
```

* `setSeparator(String separator)` is the defined token on step 2, used to separate font name form its style.
* `addFont(String baseName, String styleFont)` add a font to cache it is not already in it. `baseName` and `styleFont` are joint to form the filename in your assets so you don't have to write it completely.
* If you pass `null` as `styleFont` all styles will be loaded.

Now you are able to use custom fonts on your activities &/or fragments, you just write:
```java
FuntyText
        .onFields(T<? extends TextView> ... t)
        .fontName(String fontBaseName)
        .apply();
```

and let's magic happen.

### Note:
* If your font is not already in cache, it will be added automatically.
* You don't have to add all fonts on application class.
* If you have no added some font to your assets it will try to load `regular` font, if it does not exists, system font will be used.

## License
```
MIT License
Copyright (c) 2016 León Darío Peña

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```