package com.nacho91.ejercicioc.bank;

import com.nacho91.ejercicioc.api.ApiError;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.ApiSubscriber;
import com.nacho91.ejercicioc.cache.CacheManager;
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

    private CacheManager cacheManager;

    private List<CardIssuer> cardIssuers;

    public BankPresenter(BankView view, ApiManager apiManager, CacheManager cacheManager){
        this.view = view;
        this.apiManager = apiManager;
        this.cacheManager = cacheManager;
    }

    public void cardIssuers(){

        if(cardIssuers != null)
            return;

        PaymentInfo paymentInfo = cacheManager.getPaymentInfo();

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

                        if(issuers.isEmpty())
                            view.onCardIssuerError("No hay bancos disponibles");

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

        cacheManager.saveCardIssuer(cardIssuer);

        view.goInstallmentScreen();
    }
}
