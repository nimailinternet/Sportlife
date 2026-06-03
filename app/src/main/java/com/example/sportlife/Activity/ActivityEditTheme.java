package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.UiContext;

import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.R;

public class ActivityEditTheme extends CreateActivity{
    @Override
    protected int getIdLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected int getIdView() {
        return R.id.activitySettings;
    }
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Button back=findViewById(R.id.btnBack);
        Button editTheme=findViewById(R.id.btnToggleTheme);

        UIController uiController=new UIController(this,null);

        back.setOnClickListener(v->{});

    }
}
