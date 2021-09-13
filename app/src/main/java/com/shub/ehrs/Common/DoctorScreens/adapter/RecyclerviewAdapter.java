package com.shub.ehrs.Common.DoctorScreens.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shub.ehrs.Common.DoctorScreens.ViewPatientSymptomDetails;

import com.shub.ehrs.Common.ItemAnimation;

import com.shub.ehrs.HelperClasses.PatientListData;
import com.shub.ehrs.HelperClasses.SymptomData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerviewHolder> {


    Context context;
    List<SymptomData> symptomDataList;
    List<SymptomData> filteredSymptomDataList;
    String phoneno;


    public RecyclerviewAdapter(Context context, List<SymptomData> symptomDataList) {
        this.context = context;
        this.symptomDataList = symptomDataList;
        this.filteredSymptomDataList = symptomDataList;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_item, parent, false);
        return new RecyclerviewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, final int position) {

        holder.symptomName.setText(filteredSymptomDataList.get(position).getSymptomName());
        holder.date.setText(filteredSymptomDataList.get(position).getDate());

        ItemAnimation.animateFadeIn(holder.itemView,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PatientListData data = new PatientListData();

                Intent intent = new Intent(context, ViewPatientSymptomDetails.class);
                intent.putExtra("symptomName",filteredSymptomDataList.get(position).getSymptomName());
                intent.putExtra("date",filteredSymptomDataList.get(position).getDate());
                intent.putExtra("phoneNo",filteredSymptomDataList.get(position).getPhoneNo());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { return filteredSymptomDataList.size(); }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder{

        //variables
        TextView symptomName, date;


        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            symptomName = itemView.findViewById(R.id.symptomName);
            date = itemView.findViewById(R.id.date);

        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredSymptomDataList = symptomDataList;
                }
                else{

                    List<SymptomData> lstFiltered = new ArrayList<>();
                    for(SymptomData row: symptomDataList){
                        if(row.getSymptomName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredSymptomDataList = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredSymptomDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                filteredSymptomDataList = (List<SymptomData>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }
}
