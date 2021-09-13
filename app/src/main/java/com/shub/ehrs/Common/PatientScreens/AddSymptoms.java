package com.shub.ehrs.Common.PatientScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.shub.ehrs.R;

import java.sql.Timestamp;
import java.util.Date;

public class AddSymptoms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptoms);
    }

    public void addAndCallSymptomScreen(View view) {

        Date date = new Date();
        String timestamp = new Timestamp(date.getTime()).toString();
        Toast.makeText(getApplicationContext(),"symptom added on "+timestamp,Toast.LENGTH_SHORT).show();
    }
}