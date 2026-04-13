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
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.AuthServiceImpl;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.CallBackHandlerImpl;
import com.example.sportlife.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainActivity extends AppCompatActivity {
    AppCompatButton appCompatButton;
    EditText editTextName;
    EditText editTextPassword;
    TextView noneAcount;

    private ApiRepository apiRepository;
    private AuthServiceImpl authServiceImpl;
    private ErrorController errorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        noneAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });


        UIController uiController=new UIController(this,editTexts);
        errorController =new ErrorController();
        apiRepository = RetrofitClient.getApiRepository();
        authServiceImpl=new AuthServiceImpl(apiRepository,errorController);
        appCompatButton.setOnClickListener(v -> authServiceImpl.auth(editTextName.getText().toString(),editTextPassword.getText().toString(), new CallBackHandlerImpl(uiController)));


    }
}