package com.shub.ehrs.Common.PatientScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shub.ehrs.Common.PatientScreens.adapter.DiagnosisAdapter;
import com.shub.ehrs.HelperClasses.DiagnosisData;

import com.shub.ehrs.HelperClasses.PrescriptionData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class PatientDiagnosisScreen extends AppCompatActivity {


//    variables

    RecyclerView userRecycler;
    DiagnosisAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search = "";
    DatabaseReference database;
    String ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_diagnosis_screen_activity);

        // Hooks
        searchView = findViewById(R.id.search_bar);

        final List<DiagnosisData> userDiagnosisList = new ArrayList<>();

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

    private void setUserRecycler(List<DiagnosisData> userDiagnosisList) {

        String ref1 = getIntent().getStringExtra("ref");
        ref = ref1 + "/Diagnosis/";

//        String ref = "users/" + "+918552049130" + "/Symptoms/" + "cough" + "/Diagnosis/";

        userRecycler = findViewById(R.id.userRecycler);
        database = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference(ref);
        userRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new DiagnosisAdapter(this, userDiagnosisList);
        userRecycler.setAdapter(recyclerviewAdapter);
        ;
    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(), SymptomDetails.class));
    }
}