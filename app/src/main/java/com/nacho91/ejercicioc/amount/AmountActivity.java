package com.nacho91.ejercicioc.amount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nacho91.ejercicioc.EjercicioCApplication;
import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.api.EjercicioCApi;
import com.nacho91.ejercicioc.cache.CacheManager;
import com.nacho91.ejercicioc.payment.PaymentActivity;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class AmountActivity extends AppCompatActivity implements AmountView{

    private EditText amountText;
    private Button nextButton;

    private AmountPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);

        CacheManager cacheManager = ((EjercicioCApplication) getApplicationContext()).getCacheManager();

        presenter = new AmountPresenter(this, cacheManager);

        initControls();
        setListeners();
    }

    private void initControls(){
        amountText = (EditText) findViewById(R.id.amount_text);
        nextButton = (Button) findViewById(R.id.amount_next_button);
    }

    private void setListeners(){

        amountText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    presenter.saveAmount(amountText.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.saveAmount(amountText.getText().toString());
            }
        });
    }

    @Override
    public void invalidAmount() {
        amountText.setError(getString(R.string.amount_error));
    }

    @Override
    public void goPaymentMethod() {
        startActivity(new Intent(this, PaymentActivity.class));
    }
}
