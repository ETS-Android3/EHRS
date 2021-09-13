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
import com.shub.ehrs.HelperClasses.PrescriptionData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class AddPrescriptionAdapter extends RecyclerView.Adapter<AddPrescriptionAdapter.RecyclerviewHolder>{

    Context context;
    List<PrescriptionData> prescriptionDataList;
    List<PrescriptionData> filteredPrescriptionDataList;

    public AddPrescriptionAdapter(Context context, List<PrescriptionData> prescriptionDataList) {
        this.context = context;
        this.prescriptionDataList = prescriptionDataList;
        this.filteredPrescriptionDataList = prescriptionDataList;
    }


    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.prescription_row_item, parent, false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, int position) {

        holder.prescriptionName.setText(filteredPrescriptionDataList.get(position).getPrescriptionName());
        holder.prescriptionDesc.setText(filteredPrescriptionDataList.get(position).getPrescriptionDesc());
        holder.date.setText(filteredPrescriptionDataList.get(position).getDate());

        ItemAnimation.animateFadeIn(holder.itemView,position);
    }

    @Override
    public int getItemCount() { return filteredPrescriptionDataList.size(); }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder{

        //variables
        TextView prescriptionName,prescriptionDesc, date;

        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            prescriptionName = itemView.findViewById(R.id.prescription_name);
            date = itemView.findViewById(R.id.prescription_date);
            prescriptionDesc =itemView.findViewById(R.id.prescription_desc);

        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredPrescriptionDataList = prescriptionDataList;
                }
                else{

                    List<PrescriptionData> lstFiltered = new ArrayList<>();
                    for(PrescriptionData row: prescriptionDataList){
                        if(row.getPrescriptionName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredPrescriptionDataList = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredPrescriptionDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                filteredPrescriptionDataList = (List<PrescriptionData>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }

}
