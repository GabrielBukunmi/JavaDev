package com.example.brainbell.javadev.api;

import com.example.brainbell.javadev.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BRAINBELL on 12-Sep-17.
 */
public interface Service {
    @GET("/search/users?q=location:lagos+type:user+language:java")
    Call<ItemResponse> getItems();

}
