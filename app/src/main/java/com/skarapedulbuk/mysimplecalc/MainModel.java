package com.skarapedulbuk.mysimplecalc;

public class MainModel implements MainContract.Model {
    @Override
    public double doOperation(double arg1, double arg2, Operations operator) {
        switch (operator) {
            case PLUS:
                return arg1 + arg2;

            case MINUS:
                return arg1 - arg2;

            case MULTIPLY:
                return arg1 * arg2;

            case DIVIDE:
                return arg1 / arg2;

            case EQUALS:
                return arg1;

            default:
                return 0;
        }
    }
}
