package com.nacho91.ejercicioc.payment;

import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public interface PaymentView {

    void onPaymentSuccess(List<PaymentMethod> payments);
    void onPaymentError(String error);
    void invalidPayment();
    void finishProcess();
    void goBankScreen();
}
