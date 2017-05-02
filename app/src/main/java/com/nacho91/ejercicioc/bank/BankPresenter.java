package com.nacho91.ejercicioc.bank;

import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.PaymentInfo;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class BankPresenter {

    private BankView view;

    private ApiManager apiManager;

    private List<CardIssuer> cardIssuers;

    public BankPresenter(BankView view, ApiManager apiManager){
        this.view = view;
        this.apiManager = apiManager;
    }

    public void cardIssuers(){

        PaymentInfo paymentInfo = getDummyPaymentInfo();

        cardIssuers = apiManager.cardIssuers(paymentInfo);

        if(cardIssuers == null){
            view.onCardIssuerError("");
        }

        view.onCardIssuerSuccess(cardIssuers);
    }

    public void saveCardIssuer(int cardIssuerPos){
        if(cardIssuerPos == -1){
            view.invalidCardIssuer();
            return;
        }

        CardIssuer cardIssuer = cardIssuers.get(cardIssuerPos);

        view.goInstallmentScreen();
    }

    private PaymentInfo getDummyPaymentInfo(){
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount(200);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId("vidsa");
        paymentMethod.setTypeId("credit_card");

        paymentInfo.setPaymentMethod(paymentMethod);

        return paymentInfo;
    }
}
