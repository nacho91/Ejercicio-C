package com.nacho91.ejercicioc.payment;

import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class PaymentPresenter {

    private PaymentView view;

    private ApiManager apiManager;

    private List<PaymentMethod> payments;

    public PaymentPresenter(PaymentView view, ApiManager apiManager){
        this.view = view;
        this.apiManager = apiManager;
    }

    public void payments(){
        payments = apiManager.paymentMethods();

        if(payments == null){
            view.onPaymentError("");
        }

        view.onPaymentSuccess(payments);
    }

    public void savePaymentMethod(int paymentMethodPos){

        if(paymentMethodPos == -1){
            view.invalidPayment();
            return;
        }

        PaymentMethod paymentMethod = payments.get(paymentMethodPos);

        if(isCreditCardMethod(paymentMethod)){
            view.goBankScreen();
            return;
        }

        view.finishProcess();
    }

    private boolean isCreditCardMethod(PaymentMethod paymentMethod){
        return paymentMethod.getTypeId().equalsIgnoreCase("credit_card");
    }
}
