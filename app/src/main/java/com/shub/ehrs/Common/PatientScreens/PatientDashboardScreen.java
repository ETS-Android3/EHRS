package com.shub.ehrs.Common.PatientScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.shub.ehrs.Databases.SessionManager;
import com.shub.ehrs.R;

import java.util.HashMap;

public class PatientDashboardScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard_screen);

        TextView textView = findViewById(R.id.txid);

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String ,String > userDetails = sessionManager.getUsersDetailsFromSession();

        String fullname = userDetails.get(SessionManager.KEY_FULLNAME);

        textView.setText(fullname);
    }

    public void goToSymptoms(View view) {
        startActivity(new Intent(getApplicationContext(),symptomscreen.class));
    }

    public void goToMedications(View view) {
        startActivity(new Intent(getApplicationContext(),Medications.class));
        }

    public void goToProfile(View view) {
        startActivity(new Intent(getApplicationContext(),PatientProfileScreen.class));
    }

    public void showDoctorProfile(View view) {
        startActivity(new Intent(getApplicationContext(),ShowDoctorProfile.class));
    }
}