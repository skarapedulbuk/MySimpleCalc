package com.skarapedulbuk.mysimplecalc;

public interface MainContract {
    interface View {
        void showResult(int result);

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
        int doOperation(int arg1, int arg2, Operations operator);
    }
}
