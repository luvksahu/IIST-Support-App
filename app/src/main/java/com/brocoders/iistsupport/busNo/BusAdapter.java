package com.brocoders.iistsupport.busNo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brocoders.iistsupport.R;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.viewHolder>{
    ArrayList<BusModel> list;
    Context context;


    public BusAdapter(ArrayList<BusModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_select_bus , parent ,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BusModel model = list.get(position);
        holder.busNo.setText(model.getBusNo());
        holder.driver.setText(model.getDriver());
        holder.contact.setText(model.getContact());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder {
        TextView busNo, driver, contact;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            busNo = itemView.findViewById(R.id.busNo);
            driver = itemView.findViewById(R.id.driverName);
            contact = itemView.findViewById(R.id.contact);

        }
    }
}
