package com.nacho91.ejercicioc.api;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by ignacio on 26/05/16.
 */
public class ApiSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ApiException error = (ApiException) e;
        Log.v("ApiSubscriber", error.getKind().name());

        switch (error.getKind()){
            case NETWORK:
                onNetworkError();
                break;
            case UNEXPECTED:
                onUnknowError();
                break;
            case HTTP:
                onHttpError(error.getErrorBodyAs(ApiError.class));
                break;
            case NOT_FOUND:
                onNotFoundError();
                break;
        }
    }

    @Override
    public void onNext(T t) {

    }

    public void onNetworkError(){

    }

    public void onNotFoundError(){

    }

    public void onHttpError(ApiError error){

    }

    public void onUnknowError(){

    }
}
