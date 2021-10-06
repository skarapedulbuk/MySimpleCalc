package com.skarapedulbuk.mysimplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private TextView currentTextView;
    private TextView historyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        currentTextView = findViewById(R.id.text_view_current);
        historyTextView = findViewById(R.id.text_view_history);

        findViewById(R.id.key_0).setOnClickListener(v -> presenter.onDigitButtonClicked(0));
        findViewById(R.id.key_1).setOnClickListener(v -> presenter.onDigitButtonClicked(1));
        findViewById(R.id.key_2).setOnClickListener(v -> presenter.onDigitButtonClicked(2));
        findViewById(R.id.key_3).setOnClickListener(v -> presenter.onDigitButtonClicked(3));
        findViewById(R.id.key_4).setOnClickListener(v -> presenter.onDigitButtonClicked(4));
        findViewById(R.id.key_5).setOnClickListener(v -> presenter.onDigitButtonClicked(5));
        findViewById(R.id.key_6).setOnClickListener(v -> presenter.onDigitButtonClicked(6));
        findViewById(R.id.key_7).setOnClickListener(v -> presenter.onDigitButtonClicked(7));
        findViewById(R.id.key_8).setOnClickListener(v -> presenter.onDigitButtonClicked(8));
        findViewById(R.id.key_9).setOnClickListener(v -> presenter.onDigitButtonClicked(9));

        findViewById(R.id.key_minus).setOnClickListener(v -> presenter.onOperatorButtonClicked(Operations.MINUS));
        findViewById(R.id.key_plus).setOnClickListener(v -> presenter.onOperatorButtonClicked(Operations.PLUS));
        findViewById(R.id.key_mul).setOnClickListener(v -> presenter.onOperatorButtonClicked(Operations.MULTIPLY));
        findViewById(R.id.key_div).setOnClickListener(v -> presenter.onOperatorButtonClicked(Operations.DIVIDE));
        findViewById(R.id.key_eq).setOnClickListener(v -> presenter.onOperatorButtonClicked(Operations.EQUALS));

        findViewById(R.id.key_c).setOnClickListener(v -> presenter.onCeButtonClicked());
        findViewById(R.id.key_dot).setOnClickListener(v -> presenter.onDotButtonClicked());
    }

    @Override
    public void showResult(int result) {
        currentTextView.setText(String.valueOf(result));
    }

    @Override
    public void updateHistory(String text) {
        String history = historyTextView.getText().toString() + " " + text;
        historyTextView.setText(history);
    }

    @Override
    public void clearHistory() {
        historyTextView.setText(null);
    }
}