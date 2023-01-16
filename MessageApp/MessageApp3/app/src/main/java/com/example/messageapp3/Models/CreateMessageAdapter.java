package com.example.messageapp3.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messageapp3.R;

import java.util.ArrayList;

public class CreateMessageAdapter extends RecyclerView.Adapter<CreateMessageAdapter.ViewHolder> {
    ArrayList<CreateMessageModels> createMessageModels;
    Context context;

    public CreateMessageAdapter( Context context,ArrayList<CreateMessageModels> createMessageModels) {
        this.createMessageModels = createMessageModels;
        this.context = context;
    }

    @NonNull
    @Override
    public CreateMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.message_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CreateMessageAdapter.ViewHolder holder, int position) {
        holder.title.setText(createMessageModels.get(position).getTitle());
        holder.message.setText(createMessageModels.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return createMessageModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mi_message_name);
            message = itemView.findViewById(R.id.mi_message_desc);
        }
    }
}
