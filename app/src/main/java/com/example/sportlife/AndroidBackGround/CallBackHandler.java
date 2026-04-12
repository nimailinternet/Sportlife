package com.example.sportlife.AndroidBackGround;

import android.app.Activity;
import android.content.Intent;

import com.example.sportlife.Activity.ActivityLogin;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;

import java.util.Map;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class CallBackHandler {
    private final MethodController methodController;
    private final Activity activity;
    public void onSuccess(){
        Intent intent=new Intent(activity, ActivityLogin.class);
        activity.startActivity(intent);
        activity.finish();
    }
    public void onError(Map<String,String> tags, ErrorResponse error){
        methodController.ErrorAdvice(tags, error);
    }
    void onNetworkError(String message){

    }
}
