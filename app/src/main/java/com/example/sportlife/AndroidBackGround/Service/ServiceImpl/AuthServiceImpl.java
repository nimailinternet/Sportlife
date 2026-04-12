package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.MethodController;
import com.example.sportlife.AndroidBackGround.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Dto.Request.AuthRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.AuthResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;

@RequiredArgsConstructor
public class AuthServiceImpl {
    private final ApiRepository apiRepository;
    private final MethodController methodController;
    public void auth(Map<String,String> tags, CallBackHandler callback){
        AuthRequest authRequest=new AuthRequest(tags.get("name"),tags.get("password"));
        apiRepository.auth(authRequest).enqueue(new retrofit2.Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    String token=response.body().getToken();
                    callback.onSuccess();
                }else{
                    ErrorResponse errorResponse= null;
                    try {
                        errorResponse = methodController.parseError(response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    callback.onError(tags,errorResponse);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Map<String,Object> errors=new LinkedHashMap<>();
                errors.put("message",t.getMessage());
                ErrorResponse error=new ErrorResponse("500",errors);
                callback.onError(tags,error);
            }
        });
    }
}
