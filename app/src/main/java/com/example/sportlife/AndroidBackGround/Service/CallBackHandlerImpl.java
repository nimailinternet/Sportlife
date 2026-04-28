package com.example.sportlife.AndroidBackGround.Service;

import android.app.Activity;

import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.example.sportlife.AndroidBackGround.Dto.Response.FindTopResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CallBackHandlerImpl implements CallBackHandler {
    private final UIController uiController;

    @Override
    public void onSuccess(Class<? extends Activity> activity) {// осуществляет переход на другой экран!
        uiController.openNextScreen(activity);
    }

    @Override
    public void onError(ErrorResponse error) {//выводит ошибку, как красную подсветку с текстом !
        uiController.ErrorAdvice(error);
    }

    @Override
    public void onNetworkError(String t) {//выводит ошибку на экран!
        uiController.errorService(t);
    }

    @Override
    public void findTop(FindTopResponse response) {
        uiController.findTop(response);
    }
}
