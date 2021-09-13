package com.shub.ehrs.Common.DoctorScreens.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shub.ehrs.Common.ItemAnimation;
import com.shub.ehrs.HelperClasses.ReportData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.RecyclerviewHolder> {

    //    variables
    Context context;
    List<ReportData> reportDataList;
    List<ReportData> filteredReportDataList;

    public ReportAdapter(Context context, List<ReportData> reportDataList) {
        this.context = context;
        this.reportDataList = reportDataList;
        this.filteredReportDataList = reportDataList;
    }


    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reports_row_item, parent, false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, int position) {


        holder.reportName.setText(filteredReportDataList.get(position).getReportName());
        holder.date.setText(filteredReportDataList.get(position).getDate());
        holder.reportDesc.setText(filteredReportDataList.get(position).getReportUri());

        ItemAnimation.animateFadeIn(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return filteredReportDataList.size();
    }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder{

        //variables
        TextView reportName, date, reportDesc;


        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            reportName = itemView.findViewById(R.id.report_name);
            date = itemView.findViewById(R.id.report_date);
            reportDesc = itemView.findViewById(R.id.report_desc);

        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredReportDataList = reportDataList;
                }
                else{

                    List<ReportData> lstFiltered = new ArrayList<>();
                    for(ReportData row: reportDataList){
                        if(row.getReportName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredReportDataList = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredReportDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                filteredReportDataList = (List<ReportData>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }
}
