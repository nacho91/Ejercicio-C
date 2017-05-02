package com.nacho91.ejercicioc.payment;

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
import com.nacho91.ejercicioc.amount.AmountActivity;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.api.EjercicioCApi;
import com.nacho91.ejercicioc.bank.BankActivity;
import com.nacho91.ejercicioc.cache.CacheManager;
import com.nacho91.ejercicioc.model.PaymentMethod;
import com.nacho91.ejercicioc.payment.adapter.PaymentMethodAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class PaymentActivity extends AppCompatActivity implements PaymentView{

    private CoordinatorLayout paymentRoot;
    private RelativeLayout paymentContainer;
    private ListView paymentList;
    private Button nextButton;
    private ProgressBar progress;

    private PaymentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        setTitle(R.string.payment_title);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        EjercicioCApi api = ((EjercicioCApplication) getApplicationContext()).getApi();
        CacheManager cacheManager = ((EjercicioCApplication) getApplicationContext()).getCacheManager();

        presenter = new PaymentPresenter(this, new ApiManager(api), cacheManager);

        initControls();
        setListeners();

        toggleVisiblityView(paymentContainer, false);
    }

    private void initControls(){
        paymentRoot = (CoordinatorLayout) findViewById(R.id.payment_root);
        paymentContainer = (RelativeLayout) findViewById(R.id.payment_container);
        paymentList = (ListView) findViewById(R.id.payment_list);
        nextButton = (Button) findViewById(R.id.payment_next_button);
        progress = (ProgressBar) findViewById(R.id.payment_progress);
    }

    private void setListeners(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.savePaymentMethod(paymentList.getCheckedItemPosition());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.payments();
    }

    @Override
    public void onPaymentSuccess(List<PaymentMethod> payments) {
        toggleVisiblityView(progress, false);
        toggleVisiblityView(paymentContainer, true);

        paymentList.setAdapter(new PaymentMethodAdapter(payments));
    }

    @Override
    public void onPaymentError(String error) {
        Snackbar.make(paymentRoot, error, BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction(R.string.payment_retry_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleVisiblityView(paymentContainer, false);
                        toggleVisiblityView(progress, true);
                        presenter.payments();
                    }
                }).show();
    }

    @Override
    public void invalidPayment() {
        Snackbar.make(paymentRoot, R.string.payment_error, BaseTransientBottomBar.LENGTH_SHORT).show();
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

    private void toggleVisiblityView(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
