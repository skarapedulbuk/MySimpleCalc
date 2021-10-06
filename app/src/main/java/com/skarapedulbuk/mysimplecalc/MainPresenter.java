package com.skarapedulbuk.mysimplecalc;

import android.content.Context;
import android.widget.Toast;

public class MainPresenter implements MainContract.Presenter {

    private static final int BASE = 10;

    private final MainModel model;
    private final MainContract.View view;

    private Double arg1 = 0.0;
    private Double arg2 = null;

    private Operations prevOperator;

    private boolean isDotPressed;
    private int divider;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.model = new MainModel();
    }

    @Override
    public void onDigitButtonClicked(int digit) {
        if (arg2 == null) {
            if (isDotPressed) {
                arg1 = arg1 + digit / (double) divider;
                divider *= BASE;
            } else {
                arg1 = arg1 * BASE + digit;
            }
            view.showResult(arg1);
        } else {
            if (isDotPressed) {
                arg2 = arg2 + digit / (double) divider;
                divider *= BASE;
            } else {
                arg2 = arg2 * BASE + digit;
            }
            view.showResult(arg2);
        }
    }

    @Override
    public void onOperatorButtonClicked(Operations operator) {
        if (prevOperator != null) {
            double result = model.doOperation(arg1, arg2, prevOperator);
            view.showResult(result);
            if (prevOperator != Operations.EQUALS) {
                view.updateHistory(arg2.toString());
            }
            arg1 = result;
        } else {

            view.updateHistory(arg1.toString());
        }

        view.updateHistory(operator.toString());

        if (operator == Operations.EQUALS) {
            prevOperator = null;
            arg2 = null;
        } else {
            prevOperator = operator;
            arg2 = 0.0;
        }
        isDotPressed = false;
    }

    @Override
    public void onDotButtonClicked() {
        if (isDotPressed) {
            Toast.makeText((Context) view, "Точка уже была нажата!", Toast.LENGTH_SHORT).show();
        } else {
            isDotPressed = true;
            divider = BASE;
        }
    }

    @Override
    public void onCeButtonClicked() {
        view.clearHistory();
        view.showResult(0);
        prevOperator = null;
        arg2 = null;
        arg1 = 0.0;
        isDotPressed = false;
    }

}
