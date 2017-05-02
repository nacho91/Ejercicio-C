package com.nacho91.ejercicioc.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ignacio on 29/4/2017.
 */

public class PaymentMethod {

    private String id;

    private String name;

    @SerializedName("secure_thumbnail")
    private String secureThumbnail;

    @SerializedName("payment_type_id")
    private String typeId;

    public PaymentMethod(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public boolean isCreditCard(){
        return typeId.equalsIgnoreCase("credit_card");
    }
}
