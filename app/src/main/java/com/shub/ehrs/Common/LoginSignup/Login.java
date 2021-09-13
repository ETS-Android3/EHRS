package com.shub.ehrs.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.shub.ehrs.Common.DoctorScreens.DoctorDashboardScreen;
import com.shub.ehrs.Common.PatientScreens.PatientDashboardScreen;
import com.shub.ehrs.Databases.SessionManager;
import com.shub.ehrs.R;

public class Login extends AppCompatActivity {

    //    variables
    Button forgetPasswordBtn, createAccountbtn;
    TextInputLayout phoneNumber, password;
    CountryCodePicker countryCodePicker;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hooks
        forgetPasswordBtn = findViewById(R.id.login_forget_password);
        createAccountbtn = findViewById(R.id.login_create_acc_btn);
        phoneNumber = findViewById(R.id.login_phone_no);
        password = findViewById(R.id.login_password);
        countryCodePicker = findViewById(R.id.login_country_code_picker);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String _userPhoneNo = phoneNumber.getEditText().getText().toString();
                String _password = password.getEditText().getText().toString();
                if (_userPhoneNo.equals("9898931247") && _password.equals("abc@123")){
                    startActivity(new Intent(getApplicationContext(), DoctorDashboardScreen.class));
                }else{
                    letTheUserLoggedIn();
                }

            }
        });
    }



    public void letTheUserLoggedIn(){

//        if (!isConnected(this)){showCustomDialog();}

        //validate username and password

        if(!validateFields()){ return; }

        //get data
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        final String _password = password.getEditText().getText().toString().trim();
        if (_phoneNumber.charAt(0) == '0'){ _phoneNumber = _phoneNumber.substring(1); }
//        final String _completePhoneNumber = "+" + countryCodePicker.getFullNumber() + _phoneNumber;
        final String _completePhoneNumber = "+" + "91" + _phoneNumber;

        //database
        Query checkUser = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference("users").orderByChild("phoneNo").equalTo(_completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = dataSnapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                    if(systemPassword.equals(_password)){
                        password.setError(null);
                        password.setErrorEnabled(false);

                        //get users data from firebase
                        String _fullname = dataSnapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
                        String _username = dataSnapshot.child(_completePhoneNumber).child("username").getValue(String.class);
                        String _email = dataSnapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _phoneNo = dataSnapshot.child(_completePhoneNumber).child("phoneNo").getValue(String.class);
                        String _password = dataSnapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                        String _dateofBirth = dataSnapshot.child(_completePhoneNumber).child("date").getValue(String.class);
                        String _gender = dataSnapshot.child(_completePhoneNumber).child("gender").getValue(String.class);
                        String _bloodGroup = dataSnapshot.child(_completePhoneNumber).child("bloodGroup").getValue(String.class);
                        String _weight = dataSnapshot.child(_completePhoneNumber).child("weight").getValue(String.class);

                        //create a session

                        SessionManager sessionManager = new SessionManager(Login.this);
                        sessionManager.createLoginSession(_fullname,_username,_email,_phoneNo,_password,_dateofBirth,_gender, _bloodGroup,_weight);

                        startActivity(new Intent(getApplicationContext(),PatientDashboardScreen.class));

//                        Toast.makeText(Login.this, _fullname + "/n" + _email + "/n" + _phoneNo + "/n" + _dateofBirth + "/n"
//                                , Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Login.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Login.this, "No such user exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setMessage("Please connect to the internet to proceed further").setCancelable(false).setPositiveButton("connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
    }


    //check the internet connection
    private boolean isConnected(Login login) {
        ConnectivityManager connectivityManager = (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())){
            return  true;
        } else{
            return false;
        }
    }


    private  boolean validateFields(){

        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if (_phoneNumber.isEmpty()){
            phoneNumber.setError("Phone Number cannot be empty");
            phoneNumber.requestFocus();
            return false;
        }
        else if(_password.isEmpty()){
            password.setError("Phone Number cannot be empty");
            password.requestFocus();
            return false;
        }
        else {
            password.setError(null);
            password.setError(null);
            phoneNumber.setErrorEnabled(false);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }

    public void callMakeSelectionScreen(View view){
        startActivity(new Intent(getApplicationContext(), MakeSelection.class));
    }

    public void callCreateAccountScreen(View view) {
        startActivity(new Intent(getApplicationContext(), Signup.class));
    }

    public void loginToProfile(View view) {
        String _userPhoneNo = phoneNumber.getEditText().getText().toString();
        String _password = password.getEditText().getText().toString();

        if (_userPhoneNo.equals("9876543210") && _password.equals("abc@123")){
            startActivity(new Intent(getApplicationContext(), DoctorDashboardScreen.class));
        }else{
            startActivity(new Intent(getApplicationContext(), PatientDashboardScreen.class));
        }

    }
}