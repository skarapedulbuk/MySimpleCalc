package com.skarapedulbuk.mysimplecalc;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

public enum Theme {
    THEME_1(R.string.theme_1, R.style.Theme_MySimpleCalc, "theme1"),
    THEME_2(R.string.theme_2, R.style.Theme_MySimpleCalc_V2, "theme2");

    @StringRes
    private final int title;
    @StyleRes
    private final int theme;

    private final String key;

    public String getKey() {
        return key;
    }

    Theme(int title, int theme, String key) {
        this.title = title;
        this.theme = theme;
        this.key = key;
    }

    public int getTitle() {
        return title;
    }

    public int getTheme() {
        return theme;
    }
}