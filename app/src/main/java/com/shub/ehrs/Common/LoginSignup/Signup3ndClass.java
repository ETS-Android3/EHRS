package com.shub.ehrs.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
;
import android.widget.Button;
import android.widget.ScrollView;


import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.shub.ehrs.Databases.UserHelperClass;
import com.shub.ehrs.R;

import java.util.ArrayList;

public class Signup3ndClass extends AppCompatActivity {

//    variable
    TextInputLayout phoneNumber, bloodGroup, weight;
    Button signUpLoginBtn;
    Button signUpNextButton;
    ScrollView scrollView;
    CountryCodePicker countryCodePicker;


    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3nd_class);

        //hooks
        phoneNumber = findViewById(R.id.phone_no);
        bloodGroup = findViewById(R.id.blood_group);
        weight = findViewById(R.id.weight);
        signUpLoginBtn = findViewById(R.id.signup_login_btn);
        signUpNextButton = findViewById(R.id.signup_next_btn);
        scrollView = findViewById(R.id.signup_3rd_screen_scrool_view);
        countryCodePicker = findViewById(R.id.code_country_picker);


    }

    public void addOnFirebase(View view) {


        //  Validation fields
        if(!validatePhoneNumber()){
            return;
        }//validation completed and now move to the next screen to verify phone no and save data


//        Get all values passed from previous screens using Intent
        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _username = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();// Get Phone no
        String _phoneNo = "+" +   "91"+ _getUserEnteredPhoneNumber;
        String _bloodGroup =  bloodGroup.getEditText().getText().toString();
        String _weight =  weight.getEditText().getText().toString();




        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);

        //Pass all fields to the next activity
        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _username);
        intent.putExtra("password", _password);
        intent.putExtra("date", _date);
        intent.putExtra("gender", _gender);
        intent.putExtra("phoneNo", _phoneNo);
        intent.putExtra("bloodGroup", _bloodGroup);
        intent.putExtra("weight", _weight);


      startActivity(intent);

    }


    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkPhoneNo = "^\\d{10}$" ;

        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        } else if (!val.matches(checkPhoneNo)) {
            phoneNumber.setError("Number should be 10 digit !");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }
}