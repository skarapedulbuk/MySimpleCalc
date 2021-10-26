package com.skarapedulbuk.mysimplecalc.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.skarapedulbuk.mysimplecalc.Theme;

public class ThemeStorage {
    private static final String APP_THEME = "APP_THEME";
    private final SharedPreferences sharedPreferences;

    public ThemeStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences("theme", Context.MODE_PRIVATE);
    }

    public void setAppTheme(Theme theme) {
        sharedPreferences.edit().putString(APP_THEME, theme.getKey())
                .apply();
    }

    public Theme getAppTheme() {
        String key = sharedPreferences.getString(APP_THEME, Theme.THEME_1.getKey());
        for (Theme theme : Theme.values()) {
            if (theme.getKey().equals(key)) {
                return theme;
            }
        }
        return Theme.THEME_1;
    }
}