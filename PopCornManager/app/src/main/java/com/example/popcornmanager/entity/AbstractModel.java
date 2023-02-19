package com.example.popcornmanager.entity;

public abstract class AbstractModel {

    private String id;
    private String popCornName;
    private String price;

    public AbstractModel() {
    }

    public AbstractModel(String id,String popCornName, String price) {
        this.popCornName = popCornName;
        this.price = price;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPopCornName() {
        return popCornName;
    }

    public void setPopCornName(String popCornName) {
        this.popCornName = popCornName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
