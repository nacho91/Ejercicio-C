package com.nacho91.ejercicioc.installment;

import com.nacho91.ejercicioc.api.ApiManager;
import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.Installment;
import com.nacho91.ejercicioc.model.PayerCost;
import com.nacho91.ejercicioc.model.PaymentInfo;
import com.nacho91.ejercicioc.model.PaymentMethod;

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

        installment = apiManager.installment(paymentInfo);

        if(installment == null){
            view.onInstallmentError("");
        }

        view.onInstallmentSuccess(installment);
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
        paymentMethod.setId("vidsa");
        paymentMethod.setTypeId("credit_card");

        paymentInfo.setPaymentMethod(paymentMethod);

        CardIssuer cardIssuer = new CardIssuer();
        cardIssuer.setId("233");
        cardIssuer.setName("Banco Galicia");

        paymentInfo.setCardIssuer(cardIssuer);

        return paymentInfo;
    }
}
