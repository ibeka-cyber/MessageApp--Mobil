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

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder>{

    ArrayList<PersonModels> persons;
    Context context;

    public PersonsAdapter( Context context,ArrayList<PersonModels> persons) {
        this.persons = persons;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.person_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonsAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(persons.get(position).getImg()).into(holder.img);
        holder.name.setText(persons.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.pi_image);
            name = itemView.findViewById(R.id.pi_name);
        }
    }
}
