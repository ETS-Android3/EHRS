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

import com.shub.ehrs.Common.DoctorScreens.PatientDetailsScreen;
import com.shub.ehrs.Common.ItemAnimation;
import com.shub.ehrs.HelperClasses.NotificationData;
import com.shub.ehrs.HelperClasses.PatientListData;
import com.shub.ehrs.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.RecyclerviewHolder>{


    //    variables
    Context context;
    List<NotificationData> NotificationDataList;
    List<NotificationData> filteredNotificationDataList;

    public NotificationsAdapter(Context context, List<NotificationData> NotificationDataList) {
        this.context = context;
        this.NotificationDataList = NotificationDataList;
        this.filteredNotificationDataList = NotificationDataList;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.noti_row_item, parent, false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, final int position) {
        String fullName = filteredNotificationDataList.get(position).getFullName();
        String sympTomName = filteredNotificationDataList.get(position).getSymptomName();
        String date = filteredNotificationDataList.get(position).getDate();
        String notification = fullName + " has added a new symptom " + sympTomName + " on " + date;
//        String notification = fullName + " has added a new symptom " + sympTomName + " on " ;
        holder.fullName.setText(notification);
//        holder.date.setText(filteredNotificationDataList.get(position).getDate());

        ItemAnimation.animateFadeIn(holder.itemView,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(context, PatientDetailsScreen.class);
//                intent.putExtra("fullName",filteredPatientDataList.get(position).getFullName());
//                intent.putExtra("date",filteredPatientDataList.get(position).getDate());
//                intent.putExtra("phoneNo",filteredPatientDataList.get(position).getPhoneNo());
//                intent.putExtra("email",filteredPatientDataList.get(position).getEmail());
//                intent.putExtra("gender",filteredPatientDataList.get(position).getGender());
//                intent.putExtra("weight",filteredPatientDataList.get(position).getWeight());
//                intent.putExtra("bloodGroup",filteredPatientDataList.get(position).getBloodGroup());
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {return filteredNotificationDataList.size(); }


    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder{

        //variables
        TextView fullName, date;


        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            //Hooks
            fullName = itemView.findViewById(R.id.tv_notification);
//            date = itemView.findViewById(R.id.date);

        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredNotificationDataList = NotificationDataList;
                }
                else{

                    List<NotificationData> lstFiltered = new ArrayList<>();
                    for(NotificationData row: NotificationDataList){
                        if(row.getFullName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredNotificationDataList = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredNotificationDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                filteredNotificationDataList = (List<NotificationData>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }
}
