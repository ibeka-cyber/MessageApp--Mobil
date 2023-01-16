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

public class AddMeesageAdapter extends RecyclerView.Adapter<AddMeesageAdapter.ViewHolder> {
    ArrayList<CreateGroupModels> groups;
    Context context;

    public AddMeesageAdapter(ArrayList<CreateGroupModels> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    @NonNull
    @Override
    public AddMeesageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.groups_cards,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddMeesageAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(groups.get(position).getImg()).into(holder.c_groupsImage);
        holder.name.setText(groups.get(position).getName());
        holder.description.setText(groups.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView c_groupsImage;
        TextView name,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            c_groupsImage=itemView.findViewById(R.id.gc_group_image);
            name=itemView.findViewById(R.id.gc_group_name);
            description=itemView.findViewById(R.id.gc_group_desc);
        }
    }
}
