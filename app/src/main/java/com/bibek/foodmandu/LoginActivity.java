package com.bibek.foodmandu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bibek.foodmandu.bll.Loginbll;
import com.bibek.foodmandu.strictmode.StrictModeClass;
import com.bibek.foodmandu.bll.Loginbll;
import com.bibek.foodmandu.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {

    Button tvRegister;
    EditText etEmail,etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        tvRegister=findViewById(R.id.tvregister1);
        etEmail=findViewById(R.id.etemail);
        etPassword=findViewById(R.id.etpassword);
        btnLogin=findViewById(R.id.btnlogin);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login(){
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();

        Loginbll loginBLL=new Loginbll();

        StrictModeClass.strictmode();
        if (loginBLL.checkUser(email,password)){
            Intent intent=new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "email and password is incorrect", Toast.LENGTH_LONG).show();
            etEmail.requestFocus();
        }
    }
}
