package com.example.popcornmanager.entity;

import java.io.Serializable;

public class PopCornModel extends AbstractModel implements Serializable {
    private String popCornType;

    public PopCornModel(String id, String popCornName, String price,String popCornType) {
        super(id,popCornName, price);
        this.popCornType=popCornType;
    }

    public PopCornModel() {

    }

    public String getPopCornType() {
        return popCornType;
    }

    public void setPopCornType(String popCornType) {
        this.popCornType = popCornType;
    }
}
