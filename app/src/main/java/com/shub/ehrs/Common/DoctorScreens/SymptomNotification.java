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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shub.ehrs.Common.DoctorScreens.adapter.NotificationsAdapter;
import com.shub.ehrs.Common.DoctorScreens.adapter.PatientListAdapter;
import com.shub.ehrs.HelperClasses.NotificationData;
import com.shub.ehrs.HelperClasses.PatientListData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class SymptomNotification extends AppCompatActivity {

    //    variables

    RecyclerView userRecycler;
    NotificationsAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search = "";
    DatabaseReference database;
    String ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_notification);

        // Hooks
//        searchView = findViewById(R.id.search_bar);

        final List<NotificationData> notificationDataList = new ArrayList<>();
        setUserRecycler(notificationDataList);


//        searchView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//
//                recyclerviewAdapter.getFilter().filter(charSequence);
//                search = charSequence;
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    NotificationData noti = dataSnapshot.getValue(NotificationData.class);
                    notificationDataList.add(noti);

                }
                recyclerviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    private void setUserRecycler(List<NotificationData> notificationDataList) {

        ref = "doctor/" + "+919890931247" + "/notifications/";

        userRecycler = findViewById(R.id.userRecycler);
        database = FirebaseDatabase.getInstance("https://ehrs-83560-default-rtdb.firebaseio.com/").getReference(ref);
        userRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new NotificationsAdapter(this, notificationDataList);
        userRecycler.setAdapter(recyclerviewAdapter);

    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(),DoctorDashboardScreen.class));
    }
}