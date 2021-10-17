package com.skarapedulbuk.mysimplecalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;
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

        findViewById(R.id.key_settings).setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
        });
    }

    @Override
    public void showResult(double result) {
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

    @Override
    protected void onResume() {
        super.onResume();
        currentTextView.setText(String.valueOf(presenter.getArg1()));
        //  Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        historyTextView.setText(savedInstanceState.getString(getString(R.string.ARG_HISTORY)));
        presenter.setArg1(savedInstanceState.getDouble(getString(R.string.ARG_1)));
        presenter.setArg2((Double) savedInstanceState.getSerializable(getString(R.string.ARG_2)));
        presenter.setPrevOperator((Operations) savedInstanceState.getSerializable(getString(R.string.ARG_OPER)));
        presenter.setDivider(savedInstanceState.getInt(getString(R.string.ARG_DIV)));
        presenter.setDotPressed(savedInstanceState.getBoolean(getString(R.string.ARG_DOT)));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString(getString(R.string.ARG_HISTORY), historyTextView.getText().toString());
        outState.putDouble(getString(R.string.ARG_1), presenter.getArg1());
        outState.putSerializable(getString(R.string.ARG_2), presenter.getArg2());
        outState.putInt(getString(R.string.ARG_DIV), presenter.getDivider());
        outState.putBoolean(getString(R.string.ARG_DOT), presenter.isDotPressed());
        outState.putSerializable(getString(R.string.ARG_OPER), presenter.getPrevOperator());

        super.onSaveInstanceState(outState);
    }

}