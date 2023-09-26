package com.example.pip.network;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
            .baseUrl(Url.URL_DATA)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public API api=retrofit.create(API.class);
}
