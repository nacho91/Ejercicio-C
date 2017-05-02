package com.nacho91.ejercicioc.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nacho91.ejercicioc.EjercicioCApplication;
import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.EjercicioCApi;
import com.nacho91.ejercicioc.bank.adapter.CardIssuerAdapter;
import com.nacho91.ejercicioc.base.BaseListActivity;
import com.nacho91.ejercicioc.cache.CacheManager;
import com.nacho91.ejercicioc.installment.InstallmentActivity;
import com.nacho91.ejercicioc.model.CardIssuer;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class BankActivity extends BaseListActivity implements BankView{

    private BankPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.bank_title);

        EjercicioCApi api = ((EjercicioCApplication) getApplicationContext()).getApi();
        CacheManager cacheManager = ((EjercicioCApplication) getApplicationContext()).getCacheManager();

        presenter = new BankPresenter(this, new ApiManager(api), cacheManager);

    }

    @Override
    public void onNextButtonPressed() {
        presenter.saveCardIssuer(getCheckedPosition());
    }

    @Override
    public void onRetryButtonPressed() {
        presenter.cardIssuers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.cardIssuers();
    }

    @Override
    public void onCardIssuerSuccess(List<CardIssuer> cardIssuers) {
        setListAdapter(new CardIssuerAdapter(cardIssuers));
    }

    @Override
    public void onCardIssuerError(String error) {
        showRetryError(error, getString(R.string.bank_retry_button));
    }

    @Override
    public void invalidCardIssuer() {
        showError(getString(R.string.bank_error));
    }

    @Override
    public void goInstallmentScreen() {
        startActivity(new Intent(this, InstallmentActivity.class));
    }
}
