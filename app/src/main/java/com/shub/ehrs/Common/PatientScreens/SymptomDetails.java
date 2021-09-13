package com.shub.ehrs.Common.PatientScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.shub.ehrs.R;

public class SymptomDetails extends AppCompatActivity {

    //variables
    TextView symptomName;
    String ref, phoneNo, name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_symptom_details);

        symptomName = findViewById(R.id.symptomName);

        String date = getIntent().getStringExtra("date");
        name = getIntent().getStringExtra("symptomName");
        phoneNo = getIntent().getStringExtra("phoneNo");
        ref = getIntent().getStringExtra("ref");

        symptomName.setText(name);

    }

    public void goToPriScriptionScreeen(View view) {

        Intent intent = new Intent(getApplicationContext(), PatientPrescriptionScreen.class);
        intent.putExtra("ref",ref);

        startActivity(intent);
    }

    public void goToDiagnosisScreeen(View view) {
        Intent intent = new Intent(getApplicationContext(), PatientDiagnosisScreen.class);
        intent.putExtra("ref",ref);

        startActivity(intent);
    }

    public void goToReportsScreeen(View view) {
        Intent intent = new Intent(getApplicationContext(), uploadimage.class);
        intent.putExtra("ref",ref);
        intent.putExtra("symptomName",name);
        intent.putExtra("phoneNo",phoneNo);

        startActivity(intent);
    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(),symptomscreen.class));
    }
}