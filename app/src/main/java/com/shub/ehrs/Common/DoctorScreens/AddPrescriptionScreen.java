package com.shub.ehrs.Common.DoctorScreens;

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
import com.shub.ehrs.Common.DoctorScreens.adapter.AddPrescriptionAdapter;

import com.shub.ehrs.Common.PatientScreens.PrescriptionAdapter.RecyclerviewAdapter;
import com.shub.ehrs.Databases.SessionManager;
import com.shub.ehrs.HelperClasses.DiagnosisData;
import com.shub.ehrs.HelperClasses.PrescriptionData;
import com.shub.ehrs.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AddPrescriptionScreen extends AppCompatActivity {

    //    Variables
    RecyclerView userRecycler;
    AddPrescriptionAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search="";
    DatabaseReference database;
    String ref, phoneNo, symptomName;
    private BottomSheetDialog bsDialogPrescriptionName;
    TextInputLayout edPrescriptionName, edPrescriptionDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_add_prescription_screen_activity);

        // Hooks
        searchView = findViewById(R.id.search_bar);
         symptomName = getIntent().getStringExtra("symptomName");
        phoneNo = getIntent().getStringExtra("phoneNo");

        ref = "users/" + phoneNo + "/Symptoms/" + symptomName + "/Prescription/";

//         ref = "users/" + phoneNo + "/Symptoms/";

        final List<PrescriptionData> userPrescriptionList = new ArrayList<>();

        setUserRecycler(userPrescriptionList);


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

                userPrescriptionList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    PrescriptionData prescription = dataSnapshot.getValue(PrescriptionData.class);
                    userPrescriptionList.add(prescription);

                }
                recyclerviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void setUserRecycler(List<PrescriptionData> userPrescriptionList){

//        String ref = "users/" + phoneNo + "/Symptoms/" +  + "/Prescription/";

        userRecycler = findViewById(R.id.userRecycler);
        database = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference(ref);
        userRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new AddPrescriptionAdapter(this,userPrescriptionList);
        userRecycler.setAdapter(recyclerviewAdapter);
    }

    public void callAddPrescriptionBottomSheet(View view) {
        showBottomSheetAddPrescription();
    }

    private void showBottomSheetAddPrescription() {

        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_screen_add_prescription,null);

        ((View) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsDialogPrescriptionName.dismiss();
            }
        });

        edPrescriptionName = view.findViewById(R.id.ed_prescription_name);
        edPrescriptionDesc = view.findViewById(R.id.ed_prescription_desc);

        ((View) view.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edPrescriptionName.getEditText().getText().toString())){
                    Toast.makeText(getApplicationContext(),"prescription can't be empty",Toast.LENGTH_SHORT).show();
                } else {
                    addPrescription(edPrescriptionName.getEditText().getText().toString(),edPrescriptionDesc.getEditText().getText().toString());
                    bsDialogPrescriptionName.dismiss();
                }
            }
        });

        bsDialogPrescriptionName = new BottomSheetDialog(this);
        bsDialogPrescriptionName.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(bsDialogPrescriptionName.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bsDialogPrescriptionName.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bsDialogPrescriptionName = null;
            }
        });

        bsDialogPrescriptionName.show();
    }

    private void addPrescription(String newPrescription, String newprescriptionDesc){

        Date captureDate = new Date();
        String captureTimestamp = new Timestamp(captureDate.getTime()).toString();

        FirebaseDatabase rootNode;
        DatabaseReference reference;

        rootNode = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference();
        PrescriptionData addNewPrescription = new PrescriptionData(newPrescription, captureTimestamp, newprescriptionDesc);
        String refAdd = "users/" + phoneNo + "/";
        reference.child("users").child(phoneNo).child("Symptoms").child(symptomName).child("Prescription").child(newPrescription).setValue(addNewPrescription);
//


        Toast.makeText(getApplicationContext(), newPrescription + " added on " + captureTimestamp, Toast.LENGTH_SHORT).show();

    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(), ViewPatientSymptomDetails.class));
    }


}