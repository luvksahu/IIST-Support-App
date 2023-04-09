package com.brocoders.iistsupport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brocoders.iistsupport.FacultyActivity;
import com.brocoders.iistsupport.Models.FacultyModel;
import com.brocoders.iistsupport.R;
import com.brocoders.iistsupport.busNo.BusModel;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.myViewHolder>{
    ArrayList<FacultyModel> list;
    Context context;

    public FacultyAdapter(ArrayList<FacultyModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_faculty_details,parent,false);
        return new myViewHolder(view);  // this is for show main item in recycler view
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        FacultyModel model = list.get(position);
        holder.email.setText(model.getEmail());
        holder.expertise.setText((CharSequence) model.getExpertise());
        holder.name.setText(model.getName());
        holder.role.setText(model.getRole());

        Glide.with(holder.furl.getContext())
                .load(model.getFurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.furl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView furl;
        TextView email,expertise,name,role;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            furl = itemView.findViewById(R.id.fimg);
            email =itemView.findViewById(R.id.femail);
            expertise = itemView.findViewById(R.id.fexpertise);
            name = itemView.findViewById(R.id.fname);
            role = itemView.findViewById(R.id.frole);
            name = itemView.findViewById(R.id.fname);
        }
    }


}
