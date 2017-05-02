package com.nacho91.ejercicioc.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ignacio on 29/4/2017.
 */

public class Installment {

    private CardIssuer cardIssuer;

    @SerializedName("payer_costs")
    private List<PayerCost> payerCosts;

    public Installment(){}

    public CardIssuer getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(CardIssuer cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public List<PayerCost> getPayerCosts() {
        return payerCosts;
    }

    public void setPayerCosts(List<PayerCost> payerCosts) {
        this.payerCosts = payerCosts;
    }
}
