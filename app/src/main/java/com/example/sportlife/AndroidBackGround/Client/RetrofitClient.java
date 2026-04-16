package com.example.sportlife.AndroidBackGround.Client;

import com.example.sportlife.AndroidBackGround.Security.SecurityInterceptor;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:49182/api/").addConverterFactory(GsonConverterFactory.create()).build();
    private static final OkHttpClient httpClient= new OkHttpClient.Builder().addInterceptor(new SecurityInterceptor()).build();
    public static ApiRepository getApiRepository(){
        return retrofit.create(ApiRepository.class);
    }
}
