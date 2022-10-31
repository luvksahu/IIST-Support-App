package com.brocoders.iistsupport.route;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brocoders.iistsupport.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RouteAdapter extends FirebaseRecyclerAdapter<RouteModel, RouteAdapter.myViewHolder>{

    public RouteAdapter(@NonNull FirebaseRecyclerOptions<RouteModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RouteAdapter.myViewHolder holder, int position, @NonNull RouteModel model) {
        holder.location.setText(model.getLocation());
        holder.sign.setText(model.getSign());
        holder.time.setText(model.getTime());
    }

    @NonNull
    @Override
    public RouteAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bus_route,parent,false);
        return new RouteAdapter.myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView location, sign, time;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.stopName);
            sign = itemView.findViewById(R.id.stopSymbol);
            time = itemView.findViewById(R.id.routeTime);
        }
    }



}
