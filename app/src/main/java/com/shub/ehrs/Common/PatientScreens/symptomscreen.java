package com.shub.ehrs.Common.PatientScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shub.ehrs.Common.LoginSignup.Login;
import com.shub.ehrs.Common.PatientScreens.adapter.RecyclerviewAdapter;
import com.shub.ehrs.Databases.SessionManager;
import com.shub.ehrs.Databases.UserHelperClass;
import com.shub.ehrs.HelperClasses.NotificationData;
import com.shub.ehrs.HelperClasses.PatientListData;
import com.shub.ehrs.HelperClasses.SymptomData;
import com.shub.ehrs.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class symptomscreen extends AppCompatActivity {

    //    Variables
    RecyclerView userRecycler;
    RecyclerviewAdapter recyclerviewAdapter;
    DatabaseReference database;
    EditText searchView;
    CharSequence search = "";
    private BottomSheetDialog bsDialogSymptomName;
    TextInputLayout edSymptomName;
    ArrayList<SymptomData> list;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String phoneNo, fullName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_symptomscreen_activity);


        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> userDetails = sessionManager.getUsersDetailsFromSession();
        phoneNo = userDetails.get(SessionManager.KEY_PHONENUMBER);
        fullName = userDetails.get(SessionManager.KEY_FULLNAME);
        String ref = "users/" + phoneNo + "/Symptoms/";


//        users/+918552049130/Symptoms/

        // Hooks
        searchView = findViewById(R.id.search_bar);
        userRecycler = findViewById(R.id.userRecycler);
        database = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference(ref);
        userRecycler.setHasFixedSize(true);
        userRecycler.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        recyclerviewAdapter = new RecyclerviewAdapter(this, list);
        userRecycler.setAdapter(recyclerviewAdapter);


        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                recyclerviewAdapter.getFilter().filter(charSequence);
                search = charSequence;

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    SymptomData symptom = dataSnapshot.getValue(SymptomData.class);
                    list.add(symptom);

                }
                recyclerviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    public void callAddSymptomScreen(View view) {

//        Intent intent = new Intent(getApplicationContext(), AddSymptoms.class);

        //Pass all fields to the next activity
//        intent.putExtra("fullName", _fullName);

//        startActivity(new Intent(getApplicationContext(),AddSymptoms.class));

        showBottomSheetAddSymptom();

    }


    private void showBottomSheetAddSymptom() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_add_symptoms, null);

        ((View) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsDialogSymptomName.dismiss();
            }
        });

        edSymptomName = view.findViewById(R.id.ed_symptom_name);

        ((View) view.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edSymptomName.getEditText().getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Name can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    addSymptom(edSymptomName.getEditText().getText().toString());
                    bsDialogSymptomName.dismiss();
                }
            }
        });

        bsDialogSymptomName = new BottomSheetDialog(this);
        bsDialogSymptomName.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(bsDialogSymptomName.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bsDialogSymptomName.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bsDialogSymptomName = null;
            }
        });

        bsDialogSymptomName.show();
    }


    private void addSymptom(String newSymptom) {

        Date captureDate = new Date();
        String captureTimestamp = new Timestamp(captureDate.getTime()).toString();


        rootNode = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference();
        SymptomData addNewSymptom = new SymptomData(newSymptom, captureTimestamp,phoneNo);
        String refAdd = "users/" + phoneNo + "/";
        reference.child("users").child(phoneNo).child("Symptoms").child(newSymptom).setValue(addNewSymptom);

        Toast.makeText(getApplicationContext(), newSymptom + " added on " + captureTimestamp, Toast.LENGTH_SHORT).show();

        DatabaseReference  refDoctor = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference();
        NotificationData notify = new NotificationData(fullName, newSymptom,captureTimestamp);
//        String notification = fullName + " has added a new symptom " + newSymptom + " on " + captureTimestamp;
        refDoctor.child("doctor").child("+919890931247").child("notifications").push().setValue(notify);



    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(), PatientDashboardScreen.class));
    }


}