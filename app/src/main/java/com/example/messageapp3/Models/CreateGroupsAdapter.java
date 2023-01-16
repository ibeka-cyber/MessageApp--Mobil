package com.example.messageapp3.Models;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.messageapp3.R;

import java.util.ArrayList;

public class CreateGroupsAdapter extends RecyclerView.Adapter<CreateGroupsAdapter.ViewHolder>{
    private ArrayList<CreateGroupModels> createGroups;
    private Context context;


    public CreateGroupsAdapter( Context context,ArrayList<CreateGroupModels> createGroups) {
        this.createGroups = createGroups;
        this.context = context;
    }

    public CreateGroupsAdapter() {
    }

    @NonNull
    @Override
    public CreateGroupsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CreateGroupsAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(createGroups.get(position).getImg()).into(holder.c_groupsImage);
        holder.name.setText(createGroups.get(position).getName());
        holder.description.setText(createGroups.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return createGroups.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView c_groupsImage;
        TextView name,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            c_groupsImage = itemView.findViewById(R.id.gi_groups_image);
            name = itemView.findViewById(R.id.gi_groups_name);
            description = itemView.findViewById(R.id.gi_groups_desc);



        }
    }
}
