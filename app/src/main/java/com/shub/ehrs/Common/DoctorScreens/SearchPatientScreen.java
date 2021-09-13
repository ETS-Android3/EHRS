package com.shub.ehrs.Common.DoctorScreens;

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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shub.ehrs.Common.DoctorScreens.adapter.PatientListAdapter;
import com.shub.ehrs.Common.PatientScreens.PrescriptionAdapter.RecyclerviewAdapter;
import com.shub.ehrs.HelperClasses.PatientListData;
import com.shub.ehrs.HelperClasses.PrescriptionData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class SearchPatientScreen extends AppCompatActivity {

//    variables

    RecyclerView userRecycler;
    PatientListAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search = "";
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_search_patient_screen_activity);

        // Hooks
        searchView = findViewById(R.id.search_bar);

        final List<PatientListData> userPatientList = new ArrayList<>();
        setUserRecycler(userPatientList);


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

                    PatientListData patient = dataSnapshot.getValue(PatientListData.class);
                    userPatientList.add(patient);

                }
                recyclerviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    private void setUserRecycler(List<PatientListData> userPatientList) {


        userRecycler = findViewById(R.id.userRecycler);
        database = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference("users");
        userRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new PatientListAdapter(this, userPatientList);
        userRecycler.setAdapter(recyclerviewAdapter);

    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(), DoctorDashboardScreen.class));
    }
}