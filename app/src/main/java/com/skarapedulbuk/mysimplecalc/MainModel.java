package com.skarapedulbuk.mysimplecalc;

public class MainModel implements MainContract.Model {
    @Override
    public int doOperation(int arg1, int arg2, Operations operator) {
        switch (operator) {
            case PLUS:
                return arg1 + arg2;

            case MINUS:
                return arg1 - arg2;

            case MULTIPLY:
                return arg1 * arg2;

            case DIVIDE:
                return arg1 / arg2;

            default:
                return 0;
        }
    }
}
