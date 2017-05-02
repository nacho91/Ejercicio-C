package com.nacho91.ejercicioc.payment;

import com.nacho91.ejercicioc.api.ApiError;
import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.api.ApiSubscriber;
import com.nacho91.ejercicioc.cache.CacheManager;
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

    private CacheManager cacheManager;

    private List<PaymentMethod> payments;

    public PaymentPresenter(PaymentView view, ApiManager apiManager, CacheManager cacheManager){
        this.view = view;
        this.apiManager = apiManager;
        this.cacheManager = cacheManager;
    }

    public void payments(){

        if(payments != null)
            return;

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

        cacheManager.savePaymentMethod(paymentMethod);

        if(paymentMethod.isCreditCard()){
            view.goBankScreen();
            return;
        }

        view.finishProcess();
    }
    
}
