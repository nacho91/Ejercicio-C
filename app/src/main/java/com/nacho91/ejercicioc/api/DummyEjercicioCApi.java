package com.nacho91.ejercicioc.api;

import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.Installment;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class DummyEjercicioCApi implements EjercicioCApi {

    @Override
    public List<PaymentMethod> paymentMethods(String publicKey) {

        List<PaymentMethod> payments = new ArrayList<>();

        PaymentMethod visa = new PaymentMethod();
        visa.setId("visa");
        visa.setName("Visa");
        visa.setTypeId("credit_card");
        visa.setSecureThumbnail("https://www.mercadopago.com/org-img/MP3/API/logos/visa.gif");

        payments.add(visa);

        PaymentMethod master = new PaymentMethod();
        master.setId("master");
        master.setName("Mastercard");
        master.setTypeId("credit_card");
        master.setSecureThumbnail("https://www.mercadopago.com/org-img/MP3/API/logos/master.gif");

        payments.add(master);

        PaymentMethod pagoFacil = new PaymentMethod();
        pagoFacil.setId("pagofacil");
        pagoFacil.setName("Pago Fácil");
        pagoFacil.setTypeId("ticket");
        pagoFacil.setSecureThumbnail("https://www.mercadopago.com/org-img/MP3/API/logos/pagofacil.gif");

        payments.add(pagoFacil);

        return payments;
    }

    @Override
    public List<CardIssuer> cardIssuers(String publicKey, String paymentMethodId) {
        return null;
    }

    @Override
    public Installment installment(String publicKey, String paymentMethodId, String issuerId, String amount) {
        return null;
    }
}
