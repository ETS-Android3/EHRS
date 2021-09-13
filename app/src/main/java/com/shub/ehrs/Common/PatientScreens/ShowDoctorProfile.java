package com.shub.ehrs.Common.PatientScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shub.ehrs.R;

public class ShowDoctorProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_profile);
    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(),PatientDashboardScreen.class));
    }
}