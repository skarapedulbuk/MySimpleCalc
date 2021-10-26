package com.skarapedulbuk.mysimplecalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.skarapedulbuk.mysimplecalc.storage.ThemeStorage;

public class SettingsActivity extends AppCompatActivity {

    public static final String APP_THEME = "APP_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeStorage themeStorage = new ThemeStorage(this);
        setTheme(themeStorage.getAppTheme().getTheme());

        setContentView(R.layout.activity_settings);

        Intent launchIntent = getIntent();
        Theme launchTheme = (Theme) launchIntent.getSerializableExtra(APP_THEME);

        LinearLayout container = findViewById(R.id.theme_container);

        for (Theme theme : Theme.values()) {

            View itemView = getLayoutInflater().inflate(R.layout.item_theme, container, false);

            Button themeButton = itemView.findViewById(R.id.radio1);

            if (theme.equals(launchTheme)) themeButton.setEnabled(false);

            String txtValue = getString(theme.getTitle());
            themeButton.setText(txtValue);

            itemView.setOnClickListener(v -> {
                Intent data = new Intent();
                data.putExtra(APP_THEME, theme);
                setResult(Activity.RESULT_OK, data);
                finish();
                // themeStorage.setAppTheme(theme);
                // recreate();
            });

            container.addView(itemView);

        }
        findViewById(R.id.back_button).setOnClickListener(v -> finish());
    }
}