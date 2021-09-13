package com.shub.ehrs.Common.DoctorScreens;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;


import com.google.android.material.textfield.TextInputLayout;
import com.shub.ehrs.Common.PatientScreens.PatientDashboardScreen;
import com.shub.ehrs.Databases.SessionManager;

//setContentView(R.layout.doctor_view_patient_profile_activity);

import java.util.HashMap;
import java.util.Objects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shub.ehrs.R;

public class ViewPatientProfile extends AppCompatActivity {

    //Variables
    private BottomSheetDialog bottomSheetDialog, bsDialogEditName, bsDialogEditEmail, bsDialogEditWeight;
    TextInputLayout edUserName, edUserEmail, edUserWeight;
    ImageButton lnEditName, lnEditEmail, lnEditWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_view_patient_profile_activity);

        //hooks for textviews

        TextView fullname = findViewById(R.id.tv_username);
        TextView email = findViewById(R.id.tv_email);
        TextView phoneNo = findViewById(R.id.tv_phone);
        TextView gender = findViewById(R.id.gender_value);
        TextView date = findViewById(R.id.age_value);
        TextView bloodgroup = findViewById(R.id.blood_group_value);
        TextView weight = findViewById(R.id.weight_value);

//
//        SessionManager sessionManager = new SessionManager(this);
//        HashMap<String, String> userDetails = sessionManager.getUsersDetailsFromSession();
//
//        String _fullname = userDetails.get(SessionManager.KEY_FULLNAME);
//        String _email = userDetails.get(SessionManager.KEY_EMAIL);
//        String _phoneNo = userDetails.get(SessionManager.KEY_PHONENUMBER);
//        String _gender = userDetails.get(SessionManager.KEY_GENDER);
//        String _date = userDetails.get(SessionManager.KEY_DATE);
//        String _bloodgroup = userDetails.get(SessionManager.KEY_BLOODGROUP);
//        String _weight = userDetails.get(SessionManager.KEY_WEIGHT);

        String _fullname = getIntent().getStringExtra("fullName");
        String _date = getIntent().getStringExtra("date");
        String _phoneNo = getIntent().getStringExtra("phoneNo");
        String _email = getIntent().getStringExtra("email");
        String _gender = getIntent().getStringExtra("gender");
        String _weight = getIntent().getStringExtra("weight");
        String _bloodGroup = getIntent().getStringExtra("bloodGroup");



        fullname.setText(_fullname);
        email.setText(_email);
        phoneNo.setText(_phoneNo);
        gender.setText(_gender);
        date.setText(_date);
        bloodgroup.setText(_bloodGroup);
        weight.setText(_weight);




    }



    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(), PatientDashboardScreen.class));
    }

}