package com.nacho91.ejercicioc.api;

import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.Installment;
import com.nacho91.ejercicioc.model.PayerCost;
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
        List<CardIssuer> cardIssuers = new ArrayList<>();

        CardIssuer tarjetaShopping = new CardIssuer();
        tarjetaShopping.setId("288");
        tarjetaShopping.setName("Tarjeta Shopping");
        tarjetaShopping.setSecureThumbnail("https://www.mercadopago.com/org-img/MP3/API/logos/288.gif");

        cardIssuers.add(tarjetaShopping);

        CardIssuer bancoGalicia = new CardIssuer();
        bancoGalicia.setId("279");
        bancoGalicia.setName("Banco Galicia");
        bancoGalicia.setSecureThumbnail("https://www.mercadopago.com/org-img/MP3/API/logos/279.gif");

        cardIssuers.add(bancoGalicia);

        CardIssuer pagoFacil = new CardIssuer();
        pagoFacil.setId("333");
        pagoFacil.setName("Nuevo Banco de Santa Fe");
        pagoFacil.setSecureThumbnail("https://www.mercadopago.com/org-img/MP3/API/logos/333.gif");

        cardIssuers.add(pagoFacil);

        return cardIssuers;
    }

    @Override
    public Installment installment(String publicKey, String paymentMethodId, String issuerId, String amount) {

        Installment installment = new Installment();

        List<PayerCost> payerCosts = new ArrayList<>();

        PayerCost payerCost = new PayerCost();
        payerCost.setRecommendedMessage("1 cuota de $ 1.340,00 ($ 1.340,00)");

        payerCosts.add(payerCost);

        installment.setPayerCosts(payerCosts);

        return installment;
    }
}
