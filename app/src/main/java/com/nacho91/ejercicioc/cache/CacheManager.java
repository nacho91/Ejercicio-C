package com.nacho91.ejercicioc.cache;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.PayerCost;
import com.nacho91.ejercicioc.model.PaymentInfo;
import com.nacho91.ejercicioc.model.PaymentMethod;


/**
 * Created by Ignacio on 1/5/2017.
 */

public class CacheManager {

    static final String PAYMENT_INFO = "_PAYMENT_INFO_";

    private SharedPreferences preferences;

    private Gson gson;

    public CacheManager(SharedPreferences preferences){
        this.preferences = preferences;
        this.gson = new Gson();
    }

    public void saveAmount(double amount){

        PaymentInfo info = new PaymentInfo();
        info.setAmount(amount);

        savePaymentInfo(info);
    }

    public void savePaymentMethod(PaymentMethod paymentMethod){
        PaymentInfo info = getPaymentInfo();
        info.setPaymentMethod(paymentMethod);
        savePaymentInfo(info);
    }

    public void saveCardIssuer(CardIssuer cardIssuer){
        PaymentInfo info = getPaymentInfo();
        info.setCardIssuer(cardIssuer);
        savePaymentInfo(info);
    }

    public void savePayerCost(PayerCost payerCost){
        PaymentInfo info = getPaymentInfo();
        info.setPayerCost(payerCost);
        savePaymentInfo(info);
    }

    private void savePaymentInfo(PaymentInfo paymentInfo){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PAYMENT_INFO, gson.toJson(paymentInfo));
        editor.apply();
    }

    public PaymentInfo getPaymentInfo(){

        String paymentInfoJson = preferences.getString(PAYMENT_INFO, "");

        if(paymentInfoJson.isEmpty()){
            return new PaymentInfo();
        }

        return gson.fromJson(paymentInfoJson, PaymentInfo.class);

    }

    public void cleanPaymentInfo(){
        preferences.edit().clear().apply();
    }

}
