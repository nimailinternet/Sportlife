package com.example.sportlife.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sportlife.AndroidBackGround.Client.ApiRepository;
import com.example.sportlife.AndroidBackGround.Client.RetrofitClient;
import com.example.sportlife.AndroidBackGround.Controller.ErrorController;
import com.example.sportlife.AndroidBackGround.Controller.UIController;
import com.example.sportlife.AndroidBackGround.Security.SecurityContext;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandler;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.AuthService;
import com.example.sportlife.AndroidBackGround.Service.CallBackHandlerImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.ValidService;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
public class MainActivity extends AppCompatActivity {
    AppCompatButton appCompatButton;
    EditText editTextName;
    EditText editTextPassword;
    TextView noneAcount;

    private  ApiRepository apiRepository;
    private  AuthService authService;
    private  ErrorController errorController;
    private  ValidService validService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String refresh = SecurityContext.createContext().getTokenRefresh();
        if(refresh!=null&&validService.valid(refresh,"Refresh")){
            Intent intent=new Intent(this, ActivityLogin.class);
            this.startActivity(intent);
            this.finish();
            return;
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        appCompatButton = findViewById(R.id.btn_login);
        editTextName = findViewById(R.id.et_name);
        editTextPassword = findViewById(R.id.et_password);
        noneAcount = findViewById(R.id.none_account);
        List<EditText> editTexts=new ArrayList<>();
        editTexts.add(editTextPassword);
        editTexts.add(editTextName);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        noneAcount = findViewById(R.id.none_account);
        UIController uiController=new UIController(this,editTexts);
        errorController =new ErrorController();
        apiRepository = RetrofitClient.getApiRepository();
        authService =new AuthService(apiRepository,errorController);
        CallBackHandler callBack=new CallBackHandlerImpl(uiController);
        noneAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSuccess(ActivityLogin.class);
            }
        });
        appCompatButton.setOnClickListener(v -> authService.auth(editTextName.getText().toString(),editTextPassword.getText().toString(),callBack));


    }
}