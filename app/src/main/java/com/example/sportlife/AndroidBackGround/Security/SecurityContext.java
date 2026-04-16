package com.example.sportlife.AndroidBackGround.Security;

import com.example.sportlife.AndroidBackGround.Dto.Request.ValidRequest;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.RefreshService;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ValidService;

import java.io.IOException;

import lombok.Data;

@Data
public class SecurityContext {
    private static String tokenAccess;
    private static String tokenRefresh;
    private static SecurityContext context;
    private static RefreshService refreshService;
    private static ValidService validService;
    public static SecurityContext createContext() {
        if(context==null){
            context=new SecurityContext();
        }
        if(!validService.valid(context.getTokenAccess(),"Access")){
            String tokenAccess=refreshService.refresh(context.getTokenRefresh());
            context.setTokenAccess(tokenAccess);
        }
        return context;
    }

    public void setTokenAccess(String tokenAccess) {
        this.tokenAccess = tokenAccess;
    }

    public String getTokenAccess() {
        return tokenAccess;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }
}
