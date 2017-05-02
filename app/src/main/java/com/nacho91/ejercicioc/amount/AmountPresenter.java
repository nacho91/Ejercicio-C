package com.nacho91.ejercicioc.amount;

import com.nacho91.ejercicioc.cache.CacheManager;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class AmountPresenter {

    private AmountView view;

    private CacheManager cacheManager;

    public AmountPresenter(AmountView view, CacheManager cacheManager){
        this.view = view;
        this.cacheManager = cacheManager;
    }

    public void saveAmount(String amount){
        if(amount.trim().length() == 0){
            view.invalidAmount();
            return;
        }

        cacheManager.cleanPaymentInfo();
        cacheManager.saveAmount(Double.parseDouble(amount));

        view.goPaymentMethod();
    }
}
