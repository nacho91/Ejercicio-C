package com.nacho91.ejercicioc.api;

import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.Installment;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

/**
 * Created by Ignacio on 29/4/2017.
 */

public interface EjercicioCApi {

    List<PaymentMethod> paymentMethods(String publicKey);

    List<CardIssuer> cardIssuers(String publicKey, String paymentMethodId);

    Installment installment(String publicKey, String paymentMethodId, String issuerId, String amount);
}
