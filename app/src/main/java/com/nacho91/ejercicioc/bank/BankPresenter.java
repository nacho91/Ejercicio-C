package com.nacho91.ejercicioc.bank;

import com.nacho91.ejercicioc.api.ApiError;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.ApiSubscriber;
import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.PaymentInfo;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        apiManager.cardIssuers(paymentInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ApiSubscriber<List<CardIssuer>>() {

                    @Override
                    public void onHttpError(ApiError error) {
                        super.onHttpError(error);
                        view.onCardIssuerError(error.getCauseMessage());
                    }

                    @Override
                    public void onNext(List<CardIssuer> issuers) {
                        cardIssuers = issuers;
                        view.onCardIssuerSuccess(cardIssuers);
                    }
                });
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
        paymentMethod.setId("visa");
        paymentMethod.setTypeId("credit_card");

        paymentInfo.setPaymentMethod(paymentMethod);

        return paymentInfo;
    }
}
