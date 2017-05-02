package com.nacho91.ejercicioc.payment;

import com.nacho91.ejercicioc.api.ApiError;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.ApiSubscriber;
import com.nacho91.ejercicioc.model.PaymentMethod;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class PaymentPresenter {

    private PaymentView view;

    private ApiManager apiManager;

    private List<PaymentMethod> payments;

    public PaymentPresenter(PaymentView view, ApiManager apiManager){
        this.view = view;
        this.apiManager = apiManager;
    }

    public void payments(){
        apiManager.paymentMethods()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ApiSubscriber<List<PaymentMethod>>() {

                    @Override
                    public void onHttpError(ApiError error) {
                        super.onHttpError(error);
                        view.onPaymentError(error.getCauseMessage());
                    }

                    @Override
                    public void onNext(List<PaymentMethod> paymentMethods) {
                        payments = paymentMethods;
                        view.onPaymentSuccess(paymentMethods);
                    }
                });
    }

    public void savePaymentMethod(int paymentMethodPos){

        if(paymentMethodPos == -1){
            view.invalidPayment();
            return;
        }

        PaymentMethod paymentMethod = payments.get(paymentMethodPos);

        if(isCreditCardMethod(paymentMethod)){
            view.goBankScreen();
            return;
        }

        view.finishProcess();
    }

    private boolean isCreditCardMethod(PaymentMethod paymentMethod){
        return paymentMethod.getTypeId().equalsIgnoreCase("credit_card");
    }
}
