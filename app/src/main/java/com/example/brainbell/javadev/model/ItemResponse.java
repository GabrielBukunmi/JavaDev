package com.example.brainbell.javadev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BRAINBELL on 12-Sep-17.
 */
public class ItemResponse {
    //this method is to get the response of the {@Link Service} Service
    //items is the array in which the details needed are stored in the json
    @SerializedName("items")
    @Expose
    //and Item is a class in the package model created
    private List<Item> items;

    public List<Item> getItems(){
        return items;
    }

    public void setItems(List<Item> items){
        this.items=items;
    }
}
