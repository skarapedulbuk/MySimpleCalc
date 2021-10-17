package com.skarapedulbuk.mysimplecalc;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.widget.Toast;

public class MainPresenter implements MainContract.Presenter {

    private static final int BASE = 10;

    private final MainModel model;
    private final MainActivity view;

    private Double arg1 = 0.0;
    private Double arg2 = null;

    private Operations prevOperator;

    private boolean isDotPressed;
    private int divider;

    public MainPresenter(MainActivity view) {
        this.view = view;
        this.model = new MainModel();
    }


    public Double getArg1() {
        return arg1;
    }

    public void setArg1(Double arg1) {
        this.arg1 = arg1;
    }

    public void setArg2(Double arg2) {
        this.arg2 = arg2;
    }

    public void setPrevOperator(Operations prevOperator) {
        this.prevOperator = prevOperator;
    }

    public void setDotPressed(boolean dotPressed) {
        isDotPressed = dotPressed;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    public Double getArg2() {
        return arg2;
    }

    public Operations getPrevOperator() {
        return prevOperator;
    }

    public boolean isDotPressed() {
        return isDotPressed;
    }

    public int getDivider() {
        return divider;
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
            displayResult(arg1);
        } else {
            if (isDotPressed) {
                arg2 = arg2 + digit / (double) divider;
                divider *= BASE;
            } else {
                arg2 = arg2 * BASE + digit;
            }
            displayResult(arg2);
        }
    }

    public  void displayResult(double result){
        view.showResult(result);
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
            Toast.makeText(view, R.string.dot_is_pressed, Toast.LENGTH_SHORT).show();
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
