package com.nacho91.ejercicioc.installment;

import com.nacho91.ejercicioc.api.ApiError;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.ApiSubscriber;
import com.nacho91.ejercicioc.cache.CacheManager;
import com.nacho91.ejercicioc.model.Installment;
import com.nacho91.ejercicioc.model.PayerCost;
import com.nacho91.ejercicioc.model.PaymentInfo;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class InstallmentPresenter {

    private InstallmentView view;

    private ApiManager apiManager;

    private CacheManager cacheManager;

    private Installment installment;

    public InstallmentPresenter(InstallmentView view, ApiManager apiManager, CacheManager cacheManager){
        this.view = view;
        this.apiManager = apiManager;
        this.cacheManager = cacheManager;
    }

    public void installment(){

        PaymentInfo paymentInfo = cacheManager.getPaymentInfo();

        apiManager.installment(paymentInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ApiSubscriber<List<Installment>>() {

                    @Override
                    public void onHttpError(ApiError error) {
                        super.onHttpError(error);
                        view.onInstallmentError(error.getCauseMessage());
                    }

                    @Override
                    public void onNext(List<Installment> installments) {
                        installment = installments.get(0);
                        view.onInstallmentSuccess(installment);
                    }
                });
    }

    public void savePayerCost(int payerCostPos){
        if(payerCostPos == -1){
            view.invalidInstallment();
            return;
        }

        PayerCost payerCost = installment.getPayerCosts().get(payerCostPos);

        cacheManager.savePayerCost(payerCost);

        view.finishProcess();
    }
}
