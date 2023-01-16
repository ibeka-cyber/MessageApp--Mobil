package com.example.messageapp3.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.messageapp3.R;

import java.util.ArrayList;

public class GroupCardsAdapeter extends RecyclerView.Adapter<GroupCardsAdapeter.ViewHolder> {
    ArrayList<CreateGroupModels> createGroupModels;
    Context context;

    public GroupCardsAdapeter(ArrayList<CreateGroupModels> createGroupModels, Context context) {
        this.createGroupModels = createGroupModels;
        this.context = context;
    }

    public GroupCardsAdapeter() {
    }

    @NonNull
    @Override
    public GroupCardsAdapeter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupCardsAdapeter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.groups_cards,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupCardsAdapeter.ViewHolder holder, int position) {
        Glide.with(context).load(createGroupModels.get(position).getImg()).into(holder.c_groupsImage);
        holder.name.setText(createGroupModels.get(position).getName());
        holder.description.setText(createGroupModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return createGroupModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView c_groupsImage;
        TextView name,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            c_groupsImage = itemView.findViewById(R.id.gc_group_image);
            name = itemView.findViewById(R.id.gc_group_name);
            description = itemView.findViewById(R.id.gc_group_desc);
        }
    }
}
