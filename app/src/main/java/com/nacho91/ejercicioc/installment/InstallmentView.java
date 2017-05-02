package com.nacho91.ejercicioc.installment;

import com.nacho91.ejercicioc.model.Installment;


/**
 * Created by Ignacio on 1/5/2017.
 */

public interface InstallmentView {

    void onInstallmentSuccess(Installment installment);
    void onInstallmentError(String error);
    void invalidInstallment();
    void finishProcess();
}
