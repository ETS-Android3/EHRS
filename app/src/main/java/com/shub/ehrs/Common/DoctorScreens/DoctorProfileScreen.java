package com.shub.ehrs.Common.DoctorScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shub.ehrs.Common.LoginSignup.Login;
import com.shub.ehrs.R;

public class DoctorProfileScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile_screen_activity);
    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(),DoctorDashboardScreen.class));
    }

    public void logOutFromProfile(View view) {
        finish();
        startActivity(new Intent(getApplicationContext(), Login.class));

    }
}