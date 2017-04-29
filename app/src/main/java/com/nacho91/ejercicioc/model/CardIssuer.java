package com.nacho91.ejercicioc.model;

/**
 * Created by Ignacio on 29/4/2017.
 */

public class CardIssuer {

    private String id;

    private String name;

    private String secureThumbnail;

    public CardIssuer(){}

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
}
