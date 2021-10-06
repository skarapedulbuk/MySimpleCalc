package com.skarapedulbuk.mysimplecalc;

public interface MainContract {
    interface View {
        void showResult(double result);

        void updateHistory(String history);

        void clearHistory();
    }

    interface Presenter {

        void onDigitButtonClicked(int digit);

        void onOperatorButtonClicked(Operations operator);

        void onCeButtonClicked();

        void onDotButtonClicked();
    }

    interface Model {
        double doOperation(double arg1, double arg2, Operations operator);
    }
}
