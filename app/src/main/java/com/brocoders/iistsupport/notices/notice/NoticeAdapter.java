package com.brocoders.iistsupport.notices.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brocoders.iistsupport.R;
import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.viewHolder> {

    ArrayList<NoticeModel> list;

    Context context;

    public NoticeAdapter(ArrayList<NoticeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_notice , parent ,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.viewHolder holder, int position) {
        NoticeModel model = list.get(position);
        holder.message.setText(model.getMessage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder {
        TextView message;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.noticeMessage);
        }
    }








}
