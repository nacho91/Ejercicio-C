package com.nacho91.ejercicioc.model;

/**
 * Created by Ignacio on 29/4/2017.
 */

public class PaymentInfo {

    private double amount;

    private PaymentMethod paymentMethod;

    private CardIssuer cardIssuer;

    private PayerCost payerCost;

    public PaymentInfo(){}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CardIssuer getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(CardIssuer cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public PayerCost getPayerCost() {
        return payerCost;
    }

    public void setPayerCost(PayerCost payerCost) {
        this.payerCost = payerCost;
    }

}
