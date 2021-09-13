package com.shub.ehrs.Common.DoctorScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.shub.ehrs.Common.DoctorScreens.adapter.RecyclerviewAdapter;

import com.shub.ehrs.HelperClasses.SymptomData;
import com.shub.ehrs.R;


import java.util.ArrayList;

import java.util.List;


public class ViewPatientSymptomsScreen extends AppCompatActivity {

    //    Variables
    RecyclerView userRecycler;
    RecyclerviewAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search = "";
    DatabaseReference database;
    String ref, phoneNo;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_view_patient_symptoms_activity);





        // Hooks
        searchView = findViewById(R.id.search_bar);
        phoneNo = getIntent().getStringExtra("phoneNo");
        ref = "users/" + phoneNo + "/Symptoms/";


        final List<SymptomData> userSymptomList = new ArrayList<>();
        setUserRecycler(userSymptomList);

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

                    SymptomData symptom = dataSnapshot.getValue(SymptomData.class);
                    userSymptomList.add(symptom);

                }

                recyclerviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }





    private void setUserRecycler(List<SymptomData> userSymptomList) {

        userRecycler = findViewById(R.id.userRecycler);

        database = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference(ref);
        userRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new RecyclerviewAdapter(this, userSymptomList);
        userRecycler.setAdapter(recyclerviewAdapter);
    }




    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(), PatientDetailsScreen.class));
    }
}





