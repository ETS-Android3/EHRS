package com.shub.ehrs.Common.DoctorScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shub.ehrs.Common.PatientScreens.ShowActivity;
import com.shub.ehrs.Common.PatientScreens.uploadimage;
import com.shub.ehrs.R;

public class ViewPatientSymptomDetails extends AppCompatActivity {
    String symptomName,date,phoneNo;
    TextView name, symptomDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity_patient_symptom_details);


        //hooks
        name = findViewById(R.id.symptom_name);
        symptomDate = findViewById(R.id.symptom_date);

        symptomName = getIntent().getStringExtra("symptomName");
        date = getIntent().getStringExtra("date");
        phoneNo = getIntent().getStringExtra("phoneNo");
        name.setText(symptomName);
        symptomDate.setText(date);
    }

    public void goToAddDiagnosisScreeen(View view) {
        Intent intent = new Intent(getApplicationContext(),AddDiagnosisScreen.class);
        intent.putExtra("symptomName",symptomName);
        intent.putExtra("phoneNo",phoneNo);
        startActivity(intent);
    }

    public void goToAddPrescriptionScreen(View view) {
        Intent intent = new Intent(getApplicationContext(),AddPrescriptionScreen.class);
        intent.putExtra("symptomName",symptomName);
        intent.putExtra("phoneNo",phoneNo);
        startActivity(intent);
    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(),ViewPatientSymptomsScreen.class));
    }

    public void goToViewReportScreeen(View view) {
        Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
        intent.putExtra("symptomName",symptomName);
        intent.putExtra("phoneNo",phoneNo);
        startActivity(intent);

    }

    public void gotoPatientProfileScreen(View view) {
        startActivity(new Intent(getApplicationContext(),PatientDetailsScreen.class));

    }
}