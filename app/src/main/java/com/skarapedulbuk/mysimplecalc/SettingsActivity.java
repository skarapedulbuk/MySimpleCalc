package com.skarapedulbuk.mysimplecalc;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.skarapedulbuk.mysimplecalc.storage.ThemeStorage;

public class SettingsActivity extends AppCompatActivity {

    private static final String APP_THEME = "APP_THEME";
    //  private Theme selectedTheme;
    private ThemeStorage themeStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeStorage = new ThemeStorage(this);
        setTheme(themeStorage.getAppTheme().getTheme());

        setContentView(R.layout.activity_settings);
        RadioGroup container = findViewById(R.id.theme_container);

        showThemes(container);

        findViewById(R.id.ok_button).setOnClickListener(v -> {
            finish();
        });
    }

    public void showThemes(RadioGroup container) {
        for (Theme theme : Theme.values()) {

            View itemView = getLayoutInflater().inflate(R.layout.item_theme, container, false);
            container.addView(itemView);

            RadioButton radioButton = findViewById(R.id.radio1);
            radioButton.setText(theme.getTitle());
            itemView.setOnClickListener(v -> {
                // themeStorage.setAppTheme(theme);
                // selectedTheme = theme
                // recreate();
            });
        }
    }
}