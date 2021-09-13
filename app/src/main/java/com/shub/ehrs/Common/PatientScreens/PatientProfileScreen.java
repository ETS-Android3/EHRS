package com.shub.ehrs.Common.PatientScreens;


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
import com.shub.ehrs.Common.LoginSignup.Login;
import com.shub.ehrs.Databases.SessionManager;
import com.shub.ehrs.R;


import java.util.HashMap;
import java.util.Objects;

public class PatientProfileScreen extends AppCompatActivity {

    //Variables
    private BottomSheetDialog bottomSheetDialog, bsDialogEditName,bsDialogEditEmail,bsDialogEditWeight;
    TextInputLayout edUserName, edUserEmail, edUserWeight;
    ImageButton lnEditName, lnEditEmail, lnEditWeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.patient_profile_screen_activity);

        //hooks for textviews

        TextView fullname = findViewById(R.id.tv_username);
        TextView email = findViewById(R.id.tv_email);
        TextView phoneNo = findViewById(R.id.tv_phone);
        TextView gender = findViewById(R.id.tv_gender);
        TextView date = findViewById(R.id.tv_age);
        TextView bloodgroup = findViewById(R.id.tv_bloodGroup);
        TextView weight = findViewById(R.id.tv_weight);



        SessionManager sessionManager = new SessionManager(this);
        HashMap<String ,String > userDetails = sessionManager.getUsersDetailsFromSession();

        String _fullname = userDetails.get(SessionManager.KEY_FULLNAME);
        String _email = userDetails.get(SessionManager.KEY_EMAIL);
        String _phoneNo = userDetails.get(SessionManager.KEY_PHONENUMBER);
        String _gender = userDetails.get(SessionManager.KEY_GENDER);
        String _date = userDetails.get(SessionManager.KEY_DATE);
        String _bloodgroup = userDetails.get(SessionManager.KEY_BLOODGROUP);
        String _weight = userDetails.get(SessionManager.KEY_WEIGHT);



        fullname.setText(_fullname);
        email.setText(_email);
        phoneNo.setText(_phoneNo);
        gender.setText(_gender);
        date.setText(_date);
        bloodgroup.setText(_bloodgroup);
        weight.setText(_weight);


        //hooks for editText
        lnEditName = findViewById(R.id.ln_edit_name_btn);
        lnEditEmail = findViewById(R.id.ln_edit_email_btn);
        lnEditWeight = findViewById(R.id.ln_edit_weight_btn);

        initActionClick();

    }

    private void initActionClick() {

        lnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetEditName();
            }
        });

        lnEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetEditEmail();
            }
        });

        lnEditWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetEditWeight();
            }
        });

    }

    private void showBottomSheetEditName(){
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_edit_name,null);

        ((View) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsDialogEditName.dismiss();
            }
        });

         edUserName = view.findViewById(R.id.ed_username);

        ((View) view.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edUserName.getEditText().getText().toString())){
                    Toast.makeText(getApplicationContext(),"Name can't be empty",Toast.LENGTH_SHORT).show();
                } else {
                    updateName(edUserName.getEditText().getText().toString());
                    bsDialogEditName.dismiss();
                }
            }
        });

        bsDialogEditName = new BottomSheetDialog(this);
        bsDialogEditName.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(bsDialogEditName.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bsDialogEditName.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bsDialogEditName = null;
            }
        });

        bsDialogEditName.show();
    }

    private void showBottomSheetEditEmail(){
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_edit_email,null);

        ((View) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsDialogEditEmail.dismiss();
            }
        });

        edUserEmail = view.findViewById(R.id.ed_email);

        ((View) view.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edUserEmail.getEditText().getText().toString())){
                    Toast.makeText(getApplicationContext(),"Email can't be empty",Toast.LENGTH_SHORT).show();
                } else {
                    updateEmail(edUserEmail.getEditText().getText().toString());
                    bsDialogEditEmail.dismiss();
                }
            }
        });

        bsDialogEditEmail = new BottomSheetDialog(this);
        bsDialogEditEmail.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(bsDialogEditEmail.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bsDialogEditEmail.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bsDialogEditEmail = null;
            }
        });

        bsDialogEditEmail.show();
    }

    private void showBottomSheetEditWeight(){
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_edit_weight,null);

        ((View) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsDialogEditWeight.dismiss();
            }
        });

        edUserWeight = view.findViewById(R.id.ed_weight);

        ((View) view.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edUserWeight.getEditText().getText().toString())){
                    Toast.makeText(getApplicationContext(),"Weight can't be empty",Toast.LENGTH_SHORT).show();
                } else {
                    updateWeight(edUserWeight.getEditText().getText().toString());
                    bsDialogEditWeight.dismiss();
                }
            }
        });

        bsDialogEditWeight = new BottomSheetDialog(this);
        bsDialogEditWeight.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(bsDialogEditWeight.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bsDialogEditWeight.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bsDialogEditWeight = null;
            }
        });

        bsDialogEditWeight.show();
    }


    private void updateName(String newName){

        Toast.makeText(getApplicationContext(),"Update Successful",Toast.LENGTH_SHORT).show();

    }

    private void updateEmail(String newEmail){

        Toast.makeText(getApplicationContext(),"Update Successful",Toast.LENGTH_SHORT).show();

    }

    private void updateWeight(String newWeight){

        Toast.makeText(getApplicationContext(),"Update Successful",Toast.LENGTH_SHORT).show();

    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(),PatientDashboardScreen.class));
    }

    public void logOutFromProfile(View view) {
        finish();
        startActivity(new Intent(getApplicationContext(), Login.class));

    }
}