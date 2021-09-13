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
import com.shub.ehrs.Common.DoctorScreens.adapter.AddDiagnosisAdapter;
import com.shub.ehrs.Common.DoctorScreens.adapter.AddPrescriptionAdapter;
import com.shub.ehrs.Common.PatientScreens.SymptomDetails;
import com.shub.ehrs.Common.PatientScreens.adapter.DiagnosisAdapter;
import com.shub.ehrs.HelperClasses.DiagnosisData;
import com.shub.ehrs.HelperClasses.PrescriptionData;
import com.shub.ehrs.HelperClasses.SymptomData;
import com.shub.ehrs.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AddDiagnosisScreen extends AppCompatActivity {

    //    variables

    RecyclerView userRecycler;
    AddDiagnosisAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search="";
    DatabaseReference database;
    String ref, phoneNo, symptomName;
    private BottomSheetDialog bsDialogDiagnosisName;
    TextInputLayout edDiagnosisName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doxtor_activity_add_diagnosis_screen);
        // Hooks
        searchView = findViewById(R.id.search_bar);

        symptomName = getIntent().getStringExtra("symptomName");
        phoneNo = getIntent().getStringExtra("phoneNo");

        ref = "users/" + phoneNo + "/Symptoms/" + symptomName + "/Diagnosis/";


        final List<DiagnosisData> userDiagnosisList = new ArrayList<>();

//
        setUserRecycler(userDiagnosisList);

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

                userDiagnosisList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    DiagnosisData diagnosis = dataSnapshot.getValue(DiagnosisData.class);
                    userDiagnosisList.add(diagnosis);

                }
                recyclerviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    private void setUserRecycler(List<DiagnosisData> userDiagnosisList){

        userRecycler = findViewById(R.id.userRecycler);
        database = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference(ref);
        userRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new AddDiagnosisAdapter(this,userDiagnosisList);
        userRecycler.setAdapter(recyclerviewAdapter);
    }

    public void callAddDiagnosisBottomSheet(View view) {
        showBottomSheetAddDiagnosis();
    }

    private void showBottomSheetAddDiagnosis() {

        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_add_diagnosis,null);

        ((View) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsDialogDiagnosisName.dismiss();
            }
        });

        edDiagnosisName = view.findViewById(R.id.ed_diagnosis_name);

        ((View) view.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edDiagnosisName.getEditText().getText().toString())){
                    Toast.makeText(getApplicationContext(),"diagnosis can't be empty",Toast.LENGTH_SHORT).show();
                } else {
                    addDiagnosis(edDiagnosisName.getEditText().getText().toString());
                    bsDialogDiagnosisName.dismiss();
                }
            }
        });

        bsDialogDiagnosisName = new BottomSheetDialog(this);
        bsDialogDiagnosisName.setContentView(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(bsDialogDiagnosisName.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bsDialogDiagnosisName.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bsDialogDiagnosisName = null;
            }
        });

        bsDialogDiagnosisName.show();
    }

    public void addDiagnosis(String newDiagnosis){

        Date captureDate = new Date();
        String captureTimestamp = new Timestamp(captureDate.getTime()).toString();

        FirebaseDatabase rootNode;
        DatabaseReference reference;

        rootNode = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference();
        DiagnosisData addNewDiagnosis = new DiagnosisData(newDiagnosis, captureTimestamp);
        String refAdd = "users/" + phoneNo + "/";
        reference.child("users").child(phoneNo).child("Symptoms").child(symptomName).child("Diagnosis").child(newDiagnosis).setValue(addNewDiagnosis);
//        reference.child("users").child(phoneNo).child("Symptoms").child(symptomName).child("Diagnosis").child(newDiagnosis).setValue(newDiagnosis);


        Toast.makeText(getApplicationContext(), newDiagnosis + " added on " + captureTimestamp, Toast.LENGTH_SHORT).show();

    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(), SymptomDetails.class));
    }
}