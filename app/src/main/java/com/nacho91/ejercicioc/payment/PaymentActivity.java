package com.nacho91.ejercicioc.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nacho91.ejercicioc.EjercicioCApplication;
import com.nacho91.ejercicioc.amount.AmountActivity;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.api.EjercicioCApi;
import com.nacho91.ejercicioc.bank.BankActivity;
import com.nacho91.ejercicioc.base.BaseListActivity;
import com.nacho91.ejercicioc.cache.CacheManager;
import com.nacho91.ejercicioc.model.PaymentMethod;
import com.nacho91.ejercicioc.payment.adapter.PaymentMethodAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class PaymentActivity extends BaseListActivity implements PaymentView{

    private PaymentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.payment_title);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        EjercicioCApi api = ((EjercicioCApplication) getApplicationContext()).getApi();
        CacheManager cacheManager = ((EjercicioCApplication) getApplicationContext()).getCacheManager();

        presenter = new PaymentPresenter(this, new ApiManager(api), cacheManager);
    }


    @Override
    public void onNextButtonPressed() {
        presenter.savePaymentMethod(getCheckedPosition());
    }

    @Override
    public void onRetryButtonPressed() {
        presenter.payments();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.payments();
    }

    @Override
    public void onPaymentSuccess(List<PaymentMethod> payments) {
        setListAdapter(new PaymentMethodAdapter(payments));
    }

    @Override
    public void onPaymentError(String error) {
        showRetryError(error, getString(R.string.payment_retry_button));
    }

    @Override
    public void invalidPayment() {
        showError(getString(R.string.amount_error));
    }

    @Override
    public void finishProcess() {
        Intent intent = new Intent(this, AmountActivity.class);
        intent.putExtra(AmountActivity.COMPLETE_FLAG, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void goBankScreen() {
        startActivity(new Intent(this, BankActivity.class));
    }

}
