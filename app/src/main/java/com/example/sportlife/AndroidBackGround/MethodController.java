package com.example.sportlife.AndroidBackGround;

import android.app.Activity;

import com.example.sportlife.AndroidBackGround.Dto.Response.ErrorResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;
@RequiredArgsConstructor
public class MethodController {
    private final Activity activity;
    public ErrorResponse parseError(Response<?> response) throws IOException {
        try {
            if (response.errorBody() == null) {
                return null;
            }
            String json = response.errorBody().string();
            Gson gson = new Gson();
            return gson.fromJson(json, ErrorResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public void ErrorAdvice(Map<String,String> tags, ErrorResponse error){
        editTexts.stream().map(e->{
            Map<String,String> errors=(Map<String,String>)error.getErrors().get("errors");
            String tag=e.getTag().toString();
            e.setError(errors != null ? errors.get(tag) : null);
            return null;
        });
    }
}
