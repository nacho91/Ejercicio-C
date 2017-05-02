package com.nacho91.ejercicioc.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ignacio on 29/4/2017.
 */

public class PayerCost {

    @SerializedName("recommended_message")
    private String recommendedMessage;

    public PayerCost(){}

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public void setRecommendedMessage(String recommendedMessage) {
        this.recommendedMessage = recommendedMessage;
    }
}
