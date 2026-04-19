package com.example.sportlife.Activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportlife.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ⚠️ Тема ОБЯЗАНА применяться ДО super.onCreate(), иначе будет белая вспышка
        setTheme(R.style.Theme_SportLife_Splash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_splash);

    }

}
