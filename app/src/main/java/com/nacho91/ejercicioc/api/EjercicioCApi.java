package com.nacho91.ejercicioc.api;

import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.Installment;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ignacio on 29/4/2017.
 */

public interface EjercicioCApi {

    @GET("./")
    Observable<List<PaymentMethod>> paymentMethods(@Query("public_key") String publicKey);

    @GET("card_issuers")
    Observable<List<CardIssuer>> cardIssuers(@Query("public_key")String publicKey,
                                 @Query("payment_method_id") String paymentMethodId);

    @GET("installments")
    Observable<List<Installment>> installment(@Query("public_key")String publicKey,
                            @Query("payment_method_id")String paymentMethodId,
                            @Query("issuer.id")String issuerId,
                            @Query("amount")String amount);
}
