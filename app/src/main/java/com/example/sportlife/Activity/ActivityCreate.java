package com.example.sportlife.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sportlife.AndroidBackGround.Security.SessionManager;
import com.example.sportlife.R;

import java.util.Locale;

public abstract class ActivityCreate extends AppCompatActivity {
    protected abstract  int getIdLayout();
    protected abstract int getIdView();

    @Override
    protected void onCreate(Bundle bundle) {
        SessionManager session=new SessionManager(getApplicationContext());
        if(session.getTheme().equals("Dark")){
            setTheme(R.style.BaseTheme);
        }else{
            setTheme(R.style.LightTheme);
        }
        Locale locale=new Locale(session.getLanguage());
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        super.onCreate(bundle);
        EdgeToEdge.enable(this);
        setContentView(getIdLayout());
        View view = findViewById(getIdView());
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
