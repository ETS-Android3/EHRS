package com.shub.ehrs.Common.DoctorScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.shub.ehrs.Common.DoctorScreens.adapter.ReportAdapter;
import com.shub.ehrs.HelperClasses.ReportData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class ViewReportScreen extends AppCompatActivity {

    //    variables

    RecyclerView userRecycler;
    ReportAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_view_report_screen_activity);

        // Hooks
        searchView = findViewById(R.id.search_bar);

        List<ReportData> userReportList = new ArrayList<>();

        userReportList.add(new ReportData("MRI Scans","7/03/2021",""));
        userReportList.add(new ReportData("Corona Report ","9/03/2021","+ve"));
        userReportList.add(new ReportData("CT Scans","10/03/2021",""));
        userReportList.add(new ReportData(" blood analysis","11/03/2021",""));
        userReportList.add(new ReportData("MRI Scans","7/03/2021",""));
        userReportList.add(new ReportData("Corona Report ","9/03/2021","+ve"));
        userReportList.add(new ReportData("CT Scans","10/03/2021",""));
        userReportList.add(new ReportData(" blood analysis","11/03/2021",""));
        userReportList.add(new ReportData("MRI Scans","7/03/2021",""));
        userReportList.add(new ReportData("Corona Report ","9/03/2021","-vee"));
        userReportList.add(new ReportData("CT Scans","10/03/2021",""));
        userReportList.add(new ReportData(" blood analysis","11/03/2021",""));
        userReportList.add(new ReportData(" blood analysis","11/03/2021",""));
        userReportList.add(new ReportData("MRI Scans","7/03/2021",""));
        userReportList.add(new ReportData("Corona Report ","9/03/2021","-vee"));
        userReportList.add(new ReportData("CT Scans","10/03/2021",""));
        userReportList.add(new ReportData(" blood analysis","11/03/2021",""));


        setUserRecycler(userReportList);


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

    }

    private void setUserRecycler(List<ReportData> userReportList){

        userRecycler = findViewById(R.id.userRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new ReportAdapter(this,userReportList);
        userRecycler.setAdapter(recyclerviewAdapter);
    }

    public void gotoPreviousActivity(View view) {
        startActivity(new Intent(getApplicationContext(), ViewPatientSymptomDetails.class));
    }
}