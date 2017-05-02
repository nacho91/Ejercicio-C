package com.nacho91.ejercicioc.installment;

import com.nacho91.ejercicioc.api.ApiError;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.ApiSubscriber;
import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.Installment;
import com.nacho91.ejercicioc.model.PayerCost;
import com.nacho91.ejercicioc.model.PaymentInfo;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class InstallmentPresenter {

    private InstallmentView view;

    private ApiManager apiManager;

    private Installment installment;

    public InstallmentPresenter(InstallmentView view, ApiManager apiManager){
        this.view = view;
        this.apiManager = apiManager;
    }

    public void installment(){

        PaymentInfo paymentInfo = getDummyPaymentInfo();

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

        PayerCost cardIssuer = installment.getPayerCosts().get(payerCostPos);

        view.finishProcess();
    }

    private PaymentInfo getDummyPaymentInfo(){
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount(200);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId("visa");
        paymentMethod.setTypeId("credit_card");

        paymentInfo.setPaymentMethod(paymentMethod);

        CardIssuer cardIssuer = new CardIssuer();
        cardIssuer.setId("303");
        cardIssuer.setName("Banco Galicia");

        paymentInfo.setCardIssuer(cardIssuer);

        return paymentInfo;
    }
}
