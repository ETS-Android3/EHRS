package com.shub.ehrs.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shub.ehrs.R;

public class ForgetPasswordSuccessMessage extends AppCompatActivity {

    Button backtoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_success_message);

        backtoLogin = findViewById(R.id.back_to_login_btn);
    }

    public void backToLogin(View view) {

        startActivity(new Intent(getApplicationContext(), Login.class));
    }
}