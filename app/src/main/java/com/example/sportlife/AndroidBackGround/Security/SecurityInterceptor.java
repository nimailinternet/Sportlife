package com.example.sportlife.AndroidBackGround.Security;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
public class SecurityInterceptor implements Interceptor {
    private final Context context;

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        if (request.header("Authorization") != null) {
            return chain.proceed(request);
        }
        String path = request.url().encodedPath();
        if (path.contains("/auth")
                || path.contains("/refresh")
                || path.contains("/create")
                || path.contains("/top")
                || path.contains("/splash")) {
            return chain.proceed(request);
        }
        Log.d("dfdf",":int");
        SessionManager session = new SessionManager(context);

        String token = session.getAccessToken();
        if (token == null || token.trim().isEmpty()) {
            return chain.proceed(request);
        }
        Request newRequest = request.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(newRequest);
    }
}
