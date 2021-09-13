package com.shub.ehrs.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shub.ehrs.R;

public class MakeSelection extends AppCompatActivity {

    Button viaSmsBtn, viaMailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);

//        Hooks
        viaMailBtn = findViewById(R.id.via_mail_btn);
        viaSmsBtn = findViewById(R.id.via_sms_btn);
    }

    public void goViaMailOption(View view) {
        startActivity(new Intent(getApplicationContext(), ForgetPassword.class));

    }

    public void goViaSmsOption(View view) {
        startActivity(new Intent(getApplicationContext(), VerifyOTP.class));

    }
}