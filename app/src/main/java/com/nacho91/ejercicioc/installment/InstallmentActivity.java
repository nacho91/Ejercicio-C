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
import com.nacho91.ejercicioc.cache.CacheManager;
import com.nacho91.ejercicioc.installment.adapter.PayerCostAdapter;
import com.nacho91.ejercicioc.model.Installment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by Ignacio on 1/5/2017.
 */

public class InstallmentActivity extends AppCompatActivity implements InstallmentView{

    private CoordinatorLayout installmentRoot;
    private RelativeLayout installmentContainer;
    private ListView installmentList;
    private Button nextButton;
    private ProgressBar progress;

    private InstallmentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installment);

        setTitle(R.string.installemnt_title);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        EjercicioCApi api = ((EjercicioCApplication) getApplicationContext()).getApi();
        CacheManager cacheManager = ((EjercicioCApplication) getApplicationContext()).getCacheManager();

        presenter = new InstallmentPresenter(this, new ApiManager(api), cacheManager);

        initControls();
        setListeners();

        toggleVisiblityView(installmentContainer, false);
    }

    private void initControls(){
        installmentRoot = (CoordinatorLayout) findViewById(R.id.installment_root);
        installmentContainer = (RelativeLayout) findViewById(R.id.installment_container);
        installmentList = (ListView) findViewById(R.id.installment_list);
        nextButton = (Button) findViewById(R.id.installment_next_button);
        progress = (ProgressBar) findViewById(R.id.installment_progress);
    }

    private void setListeners(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.savePayerCost(installmentList.getCheckedItemPosition());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.installment();
    }

    @Override
    public void onInstallmentSuccess(Installment installment) {
        toggleVisiblityView(progress, false);
        toggleVisiblityView(installmentContainer, true);

        installmentList.setAdapter(new PayerCostAdapter(installment.getPayerCosts()));
    }

    @Override
    public void onInstallmentError(String error) {
        Snackbar.make(installmentRoot, error, BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction(R.string.installemnt_retry_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleVisiblityView(installmentContainer, false);
                        toggleVisiblityView(progress, true);
                        presenter.installment();
                    }
                }).show();
    }

    @Override
    public void invalidInstallment() {
        Snackbar.make(installmentRoot, R.string.installemnt_error, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    @Override
    public void finishProcess() {
        Intent intent = new Intent(this, AmountActivity.class);
        intent.putExtra(AmountActivity.COMPLETE_FLAG, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void toggleVisiblityView(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
