package com.shub.ehrs.Common.DoctorScreens.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shub.ehrs.Common.ItemAnimation;
import com.shub.ehrs.HelperClasses.DiagnosisData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class AddDiagnosisAdapter extends RecyclerView.Adapter<AddDiagnosisAdapter.RecyclerviewHolder> {

    Context context;
    List<DiagnosisData> diagnosisDataList;
    List<DiagnosisData> filteredDiagnosisDataList;

    public AddDiagnosisAdapter(Context context, List<DiagnosisData> diagnosisDataList) {
        this.context = context;
        this.diagnosisDataList = diagnosisDataList;
        this.filteredDiagnosisDataList = diagnosisDataList;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diagnosis_row_item, parent, false);
        return new AddDiagnosisAdapter.RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, int position) {

        holder.diagnosisName.setText(filteredDiagnosisDataList.get(position).getDiagnosisName());
        holder.date.setText(filteredDiagnosisDataList.get(position).getDate());


        ItemAnimation.animateFadeIn(holder.itemView,position);

    }

    @Override
    public int getItemCount() { return filteredDiagnosisDataList.size(); }


    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder{

        //variables
        TextView diagnosisName, date,  diagnosisDesc;


        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            diagnosisName = itemView.findViewById(R.id.diagnosis_name);
            date = itemView.findViewById(R.id.date);
            diagnosisDesc = itemView.findViewById(R.id.diagnosis_desc);

        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredDiagnosisDataList = diagnosisDataList;
                }
                else{

                    List<DiagnosisData> lstFiltered = new ArrayList<>();
                    for(DiagnosisData row: diagnosisDataList){
                        if(row.getDiagnosisName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredDiagnosisDataList = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredDiagnosisDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                filteredDiagnosisDataList = (List<DiagnosisData>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }
}


