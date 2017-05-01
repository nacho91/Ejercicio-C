package com.nacho91.ejercicioc.amount;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class AmountPresenter {

    private AmountView view;

    public AmountPresenter(AmountView view){
        this.view = view;
    }

    public void saveAmount(String amount){
        if(amount.trim().length() == 0){
            view.invalidAmount();
            return;
        }

        view.goPaymentMethod();
    }
}
