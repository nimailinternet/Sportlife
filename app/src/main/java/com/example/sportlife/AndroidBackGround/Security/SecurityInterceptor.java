package com.example.sportlife.AndroidBackGround.Security;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SecurityInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token=SecurityContext.createContext().getToken();
        Request request=chain.request().newBuilder().addHeader("Authorization","Bearer "+token).build();
        return chain.proceed(request);
    }
}
