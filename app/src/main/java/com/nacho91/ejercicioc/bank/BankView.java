package com.nacho91.ejercicioc.bank;

import com.nacho91.ejercicioc.model.CardIssuer;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public interface BankView {

    void onCardIssuerSuccess(List<CardIssuer> cardIssuers);
    void onCardIssuerError(String error);
    void invalidCardIssuer();
    void goInstallmentScreen();
}
