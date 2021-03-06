package com.nacho91.ejercicioc.api;

import com.nacho91.ejercicioc.BuildConfig;
import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.Installment;
import com.nacho91.ejercicioc.model.PaymentInfo;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

import rx.Observable;

/**
 * Created by Ignacio on 29/4/2017.
 */

public class ApiManager {

    private EjercicioCApi ejercicioCApi;

    public ApiManager(EjercicioCApi ejercicioCApi) {
        this.ejercicioCApi = ejercicioCApi;
    }

    public Observable<List<PaymentMethod>> paymentMethods(){
        return ejercicioCApi.paymentMethods(BuildConfig.PUBLIC_KEY);
    }

    public Observable<List<CardIssuer>> cardIssuers(PaymentInfo info){
        return ejercicioCApi.cardIssuers(BuildConfig.PUBLIC_KEY,
                info.getPaymentMethod().getId());
    }

    public Observable<List<Installment>> installment(PaymentInfo info){
        return ejercicioCApi.installment(BuildConfig.PUBLIC_KEY,
                info.getPaymentMethod().getId(),
                info.getCardIssuer().getId(),
                String.valueOf(info.getAmount()));
    }
}
