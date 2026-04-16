package com.example.sportlife.AndroidBackGround.Security;

import androidx.annotation.NonNull;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Dto.Request.RefreshRequest;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
public class SecurityInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token=SecurityContext.createContext().getTokenAccess();
        Request request=chain.request().newBuilder().addHeader("Authorization","Bearer "+token).build();
        return chain.proceed(request);
    }
}
