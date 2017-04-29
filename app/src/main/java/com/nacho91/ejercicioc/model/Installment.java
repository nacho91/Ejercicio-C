package com.nacho91.ejercicioc.model;

import java.util.List;

/**
 * Created by Ignacio on 29/4/2017.
 */

public class Installment {

    private CardIssuer cardIssuer;

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
