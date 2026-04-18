package com.example.sportlife.AndroidBackGround.Security;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

@RequiredArgsConstructor
public class AuthenticatorRefresh implements Authenticator {
    private final RefreshService refreshService;
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NonNull Response response) throws IOException {
        String tokenRefresh=SecurityContext.createContext().getTokenRefresh();
        String tokenAccess=refreshService.refresh(tokenRefresh);
        SecurityContext.createContext().setTokenAccess(tokenAccess);
        return response.request().newBuilder().addHeader("Authorization", "Bearer " + tokenAccess).build();
    }
}
