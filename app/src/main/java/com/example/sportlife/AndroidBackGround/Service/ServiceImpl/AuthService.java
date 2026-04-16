package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.Activity.ActivityLogin;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Dto.Request.AuthRequest;
import com.example.sportlife.AndroidBackGround.Security.SecurityContext;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.AuthResponse;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;

@RequiredArgsConstructor
public class AuthService {
    private final ApiRepository apiRepository;
    private final ErrorController errorController;
    public void auth(String name,String password, CallBackHandler callback){
        AuthRequest authRequest=new AuthRequest(name,password);
        apiRepository.auth(authRequest).enqueue(new retrofit2.Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    String token=response.body().getTokenAccess();
                    String tokenRefresh=response.body().getTokenRefresh();
                    SecurityContext.createContext().setTokenAccess(token);
                    SecurityContext.createContext().setTokenRefresh(tokenRefresh);
                    callback.onSuccess(ActivityLogin.class);
                }else{
                    ErrorResponse errorResponse;
                    try {
                        errorResponse = errorController.parseError(response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    callback.onError(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                callback.onNetworkError(t);
            }
        });
    }
}
