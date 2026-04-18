package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import android.media.MediaDrm;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Request.ValidatedRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ValidatedResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiredArgsConstructor
public class ValidatedService {
    private final UIController uiController;
    private final ErrorController errorController;
    public Boolean validated(String tokenRefresh){
        ApiRepository apiRepository= RetrofitClient.getApiRepository();
        ValidatedRequest request=new ValidatedRequest(tokenRefresh);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController);
        Boolean validate=false;
        try {
            Response<ValidatedResponse> response=apiRepository.validated(request).execute();
            if(response.isSuccessful()&&response.body()!=null){
                validate=response.body().getValidatedToken();
            }else{
                ErrorResponse errorResponse=errorController.parseError(response);
                callBack.onError(errorResponse);
            }
        } catch (IOException e) {
            callBack.onNetworkError(e);
        }
        return validate;
    }
}
