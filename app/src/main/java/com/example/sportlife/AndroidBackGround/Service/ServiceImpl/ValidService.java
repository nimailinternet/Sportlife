package com.example.sportlife.AndroidBackGround.Service.ServiceImpl;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Request.ValidRequest;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.ValidResponse;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;

@RequiredArgsConstructor
public class ValidService {
    public final UIController uiController;
    public Boolean valid(String token,String type) {
        ApiRepository apiRepository = RetrofitClient.getApiRepository();
        ValidRequest request = new ValidRequest(token,type);
        Response<ValidResponse> response = null;
        Boolean tokenValid = false;
        CallBackHandler callBackHandler=new CallBackHandlerImpl(uiController);
        ErrorController errorController=new ErrorController();
        try {
            response = apiRepository.valid(request).execute();
            if(response.isSuccessful()&&response.body()!=null) {
                tokenValid = response.body().isTokenValid();
            }else{
                ErrorResponse errorResponse=new ErrorResponse();
                errorResponse=errorController.parseError(response);
                uiController.ErrorService(errorResponse.getErrors().get("result").toString());
            }
        } catch (IOException e) {
            callBackHandler.onNetworkError(e);
        }
        return tokenValid;
    }
}
