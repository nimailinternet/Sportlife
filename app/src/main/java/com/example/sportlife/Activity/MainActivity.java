package com.example.sportlife.Activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sportlife.AndroidBackGround.CallBackHandler;
import com.example.sportlife.AndroidBackGround.MethodController;
import com.example.sportlife.AndroidBackGround.Service.ServiceImpl.AuthServiceImpl;
import com.example.sportlife.R;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainActivity extends AppCompatActivity {
    AppCompatButton appCompatButton;
    EditText editTextName;
    EditText editTextPassword;
    AuthServiceImpl authServiceImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        appCompatButton = findViewById(R.id.btn_register);
        editTextName = findViewById(R.id.et_name);
        editTextPassword = findViewById(R.id.et_password);
        Map<String,String> tags=new LinkedHashMap<>();
        tags.put(editTextName.getTag().toString(),editTextName.getText().toString());
        tags.put(editTextPassword.getTag().toString(),editTextPassword.getText().toString());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MethodController methodController = new MethodController(this);
        appCompatButton.setOnClickListener(v -> authServiceImpl.auth(tags, new CallBackHandler()));
    }
}