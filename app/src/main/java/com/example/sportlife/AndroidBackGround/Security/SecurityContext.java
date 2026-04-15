package com.example.sportlife.AndroidBackGround.Security;

import lombok.Data;

@Data
public class SecurityContext {
    private String token;
    private static SecurityContext context;
    public static SecurityContext createContext(){
        if(context==null){
            context=new SecurityContext();
        }
        return context;
    }
}
