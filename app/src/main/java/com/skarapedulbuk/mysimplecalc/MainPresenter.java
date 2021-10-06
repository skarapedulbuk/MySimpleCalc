package com.skarapedulbuk.mysimplecalc;

public class MainPresenter implements MainContract.Presenter {

    private static final int BASE = 10;

    private final MainModel model;
    private final MainContract.View view;

    private Integer arg1 = 0;
    private Integer arg2 = null;


    private Operations prevOperator;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.model = new MainModel();
    }

    @Override
    public void onDigitButtonClicked(int digit) {
        if (arg2 == null) {
            arg1 = arg1 * BASE + digit;
            view.showResult(arg1);
        } else {
            arg2 = arg2 * BASE + digit;
            view.showResult(arg2);
        }
    }

    @Override
    public void onOperatorButtonClicked(Operations operator) {
        if (prevOperator != null) {
            int result = model.doOperation(arg1, arg2, prevOperator);
            view.showResult(result);
            view.updateHistory(prevOperator.toString() + " " + arg2.toString());
            arg1 = result;
        } else {
            view.updateHistory(arg1.toString());
        }

        prevOperator = operator;
        arg2 = 0;

    }

    @Override
    public void onDotButtonClicked() {

    }

    @Override
    public void onCeButtonClicked() {
        view.clearHistory();
        view.showResult(0);
        prevOperator = null;
        arg2 = null;
        arg1 = 0;
    }

}
