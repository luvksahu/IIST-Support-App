package com.brocoders.iistsupport.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brocoders.iistsupport.Models.FacultyModel;
import com.brocoders.iistsupport.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyAdapter extends FirebaseRecyclerAdapter<FacultyModel,FacultyAdapter.myViewHolder>{

    public FacultyAdapter(@NonNull FirebaseRecyclerOptions<FacultyModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull FacultyModel model) {
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

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_faculty_details,parent,false);
        return new myViewHolder(view);  // this is for show main item in recycler view
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
