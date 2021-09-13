package com.shub.ehrs.Common.DoctorScreens.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shub.ehrs.Common.DoctorScreens.PatientDetailsScreen;
import com.shub.ehrs.Common.ItemAnimation;
import com.shub.ehrs.Common.PatientScreens.SymptomDetails;
import com.shub.ehrs.HelperClasses.PatientListData;

import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.RecyclerviewHolder> {

    //    variables
    Context context;
    List<PatientListData> PatientDataList;
    List<PatientListData> filteredPatientDataList;

    public PatientListAdapter(Context context, List<PatientListData> PatientDataList) {
        this.context = context;
        this.PatientDataList = PatientDataList;
        this.filteredPatientDataList = PatientDataList;
    }



    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.patient_list_row_item, parent, false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, final int position) {


        holder.fullName.setText(filteredPatientDataList.get(position).getFullName());
        holder.date.setText(filteredPatientDataList.get(position).getDate());

        ItemAnimation.animateFadeIn(holder.itemView,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PatientDetailsScreen.class);
                intent.putExtra("fullName",filteredPatientDataList.get(position).getFullName());
                intent.putExtra("date",filteredPatientDataList.get(position).getDate());
                intent.putExtra("phoneNo",filteredPatientDataList.get(position).getPhoneNo());
                intent.putExtra("email",filteredPatientDataList.get(position).getEmail());
                intent.putExtra("gender",filteredPatientDataList.get(position).getGender());
                intent.putExtra("weight",filteredPatientDataList.get(position).getWeight());
                intent.putExtra("bloodGroup",filteredPatientDataList.get(position).getBloodGroup());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() { return filteredPatientDataList.size(); }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder{

        //variables
        TextView fullName, date;


        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            //Hooks
            fullName = itemView.findViewById(R.id.patient_name);
            date = itemView.findViewById(R.id.date);

        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredPatientDataList = PatientDataList;
                }
                else{

                    List<PatientListData> lstFiltered = new ArrayList<>();
                    for(PatientListData row: PatientDataList){
                        if(row.getFullName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredPatientDataList = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredPatientDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                filteredPatientDataList = (List<PatientListData>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }

}
