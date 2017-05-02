package com.nacho91.ejercicioc.installment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.nacho91.ejercicioc.EjercicioCApplication;
import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.amount.AmountActivity;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.EjercicioCApi;
import com.nacho91.ejercicioc.base.BaseListActivity;
import com.nacho91.ejercicioc.cache.CacheManager;
import com.nacho91.ejercicioc.installment.adapter.PayerCostAdapter;
import com.nacho91.ejercicioc.model.Installment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by Ignacio on 1/5/2017.
 */

public class InstallmentActivity extends BaseListActivity implements InstallmentView{

    private InstallmentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.installemnt_title);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        EjercicioCApi api = ((EjercicioCApplication) getApplicationContext()).getApi();
        CacheManager cacheManager = ((EjercicioCApplication) getApplicationContext()).getCacheManager();

        presenter = new InstallmentPresenter(this, new ApiManager(api), cacheManager);

    }

    @Override
    public void onNextButtonPressed() {
        presenter.savePayerCost(getCheckedPosition());
    }

    @Override
    public void onRetryButtonPressed() {
        presenter.installment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.installment();
    }

    @Override
    public void onInstallmentSuccess(Installment installment) {
       setListAdapter(new PayerCostAdapter(installment.getPayerCosts()));
    }

    @Override
    public void onInstallmentError(String error) {
        showRetryError(error, getString(R.string.installemnt_retry_button));
    }

    @Override
    public void invalidInstallment() {
        showError(getString(R.string.installemnt_error));
    }

    @Override
    public void finishProcess() {
        Intent intent = new Intent(this, AmountActivity.class);
        intent.putExtra(AmountActivity.COMPLETE_FLAG, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
