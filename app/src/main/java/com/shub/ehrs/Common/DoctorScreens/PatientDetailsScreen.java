package com.shub.ehrs.Common.DoctorScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shub.ehrs.HelperClasses.PatientListData;
import com.shub.ehrs.R;

public class PatientDetailsScreen extends AppCompatActivity {

//    variables
    TextView fullName,age;
    String name,date,phoneNo,email,gender,weight,bloodGroup;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_details_screen_activity);

        fullName = findViewById(R.id.fullName);
        age = findViewById(R.id.age);

        name = getIntent().getStringExtra("fullName");
        date = getIntent().getStringExtra("date");
        phoneNo = getIntent().getStringExtra("phoneNo");
        email = getIntent().getStringExtra("email");
        gender = getIntent().getStringExtra("gender");
        weight = getIntent().getStringExtra("weight");
        bloodGroup = getIntent().getStringExtra("bloodGroup");


        fullName.setText(name);
        age.setText(gender);
    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(),SearchPatientScreen.class));
    }

    public void goToPatientProfile(View view) {
        Intent intent = new Intent(getApplicationContext(),ViewPatientProfile.class);
//        intent.putExtra("phoneNo",phoneNo);

        intent.putExtra("fullName",name);
        intent.putExtra("date",date);
        intent.putExtra("phoneNo",phoneNo);
        intent.putExtra("email",email);
        intent.putExtra("gender",gender);
        intent.putExtra("weight",weight);
        intent.putExtra("bloodGroup",bloodGroup);
        startActivity(intent);
    }

    public void goToSymptomDetails(View view) {
        Intent intent = new Intent(getApplicationContext(),ViewPatientSymptomsScreen.class);
        intent.putExtra("phoneNo",phoneNo);
        startActivity(intent);
    }
}