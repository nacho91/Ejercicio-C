package com.nacho91.ejercicioc.amount;

import com.nacho91.ejercicioc.cache.CacheManager;
import com.nacho91.ejercicioc.model.PaymentInfo;

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

    public String getSelectedValues(){

        PaymentInfo info = cacheManager.getPaymentInfo();

        StringBuilder mesage = new StringBuilder();

        mesage.append("Monto: ").append(info.getAmount()).append("\n");
        mesage.append("Medio de pago: ").append(info.getPaymentMethod().getName()).append("\n");

        if(info.getPaymentMethod().isCreditCard()){
            mesage.append("Banco: ").append(info.getCardIssuer().getName()).append("\n");
            mesage.append("Cuotas: ").append(info.getPayerCost().getRecommendedMessage()).append("\n");
        }

        return mesage.toString();
    }
}
