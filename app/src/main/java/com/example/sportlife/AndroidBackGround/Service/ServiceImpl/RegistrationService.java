package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.Activity.ActivityLogin;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Dto.Request.RegistrationRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.RegistrationResponse;
import com.example.sportlife.AndroidBackGround.Security.SecurityContext;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;

@RequiredArgsConstructor
public class RegistrationService {
    private final ApiRepository apiRepository;
    private final ErrorController errorController;
    public void registration(String name, String password, CallBackHandler callBack){
        RegistrationRequest request=new RegistrationRequest(name,password);
        apiRepository.registration(request).enqueue(new retrofit2.Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    String tokenAccess =response.body().getTokenAccess();
                    String tokenRefresh=response.body().getTokenRefresh();
                    SecurityContext.createContext().setTokenAccess(tokenAccess);
                    SecurityContext.createContext().setTokenRefresh(tokenRefresh);
                    callBack.onSuccess(ActivityLogin.class);
                }else{
                    ErrorResponse errorResponse;
                    errorResponse=errorController.parseError(response);
                    callBack.onError(errorResponse);
                }
            }
            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                callBack.onNetworkError(t);
            }
        });
    ;}
}
