package com.nacho91.ejercicioc.bank;

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

import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.DummyEjercicioCApi;
import com.nacho91.ejercicioc.bank.adapter.CardIssuerAdapter;
import com.nacho91.ejercicioc.model.CardIssuer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class BankActivity extends AppCompatActivity implements BankView{

    private CoordinatorLayout bankRoot;
    private RelativeLayout bankContainer;
    private ListView bankList;
    private Button nextButton;
    private ProgressBar progress;

    private BankPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        setTitle(R.string.bank_title);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        presenter = new BankPresenter(this, new ApiManager(new DummyEjercicioCApi()));

        initControls();
        setListeners();

        toggleVisiblityView(bankContainer, false);
    }

    private void initControls(){
        bankRoot = (CoordinatorLayout) findViewById(R.id.bank_root);
        bankContainer = (RelativeLayout) findViewById(R.id.bank_container);
        bankList = (ListView) findViewById(R.id.bank_list);
        nextButton = (Button) findViewById(R.id.bank_next_button);
        progress = (ProgressBar) findViewById(R.id.bank_progress);
    }

    private void setListeners(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.saveCardIssuer(bankList.getCheckedItemPosition());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.cardIssuers();
    }

    @Override
    public void onCardIssuerSuccess(List<CardIssuer> cardIssuers) {
        toggleVisiblityView(progress, false);
        toggleVisiblityView(bankContainer, true);

        bankList.setAdapter(new CardIssuerAdapter(cardIssuers));
    }

    @Override
    public void onCardIssuerError(String error) {
        Snackbar.make(bankRoot, error, BaseTransientBottomBar.LENGTH_SHORT)
                .setAction(R.string.bank_retry_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleVisiblityView(bankContainer, false);
                        toggleVisiblityView(progress, true);
                        presenter.cardIssuers();
                    }
                }).show();
    }

    @Override
    public void invalidCardIssuer() {
        Snackbar.make(bankRoot, R.string.bank_error, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    @Override
    public void goInstallmentScreen() {

    }

    private void toggleVisiblityView(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
