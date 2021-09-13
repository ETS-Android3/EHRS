package com.shub.ehrs.Common.DoctorScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shub.ehrs.Common.LoginSignup.Login;
import com.shub.ehrs.R;

public class DoctorDashboardScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard_screen);
    }

    public void goToSearchPatientScreen(View view) {
        startActivity(new Intent(getApplicationContext(),SearchPatientScreen.class));
    }

    public void ViewDoctorProfile(View view) {
        startActivity(new Intent(getApplicationContext(),DoctorProfileScreen.class));
    }

    public void LogOutofAccount(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));

    }

    public void goToNotifiactionScreen(View view) {
        startActivity(new Intent(getApplicationContext(), SymptomNotification.class));

    }
}