package com.example.messageapp3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messageapp3.Models.AddMeesageAdapter;
import com.example.messageapp3.Models.CreateGroupModels;
import com.example.messageapp3.Models.CreateGroupsAdapter;
import com.example.messageapp3.Models.CreateMessageModels;
import com.example.messageapp3.Models.MessageAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class SendMessage extends Fragment {
    RecyclerView rv_groups,rv_messages;

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;

    ArrayList<CreateGroupModels> groups;
    AddMeesageAdapter groupAdapter;

    ArrayList<CreateMessageModels> message;
    MessageAdapter messageAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nav_send_message, container, false);
        rv_groups = root.findViewById(R.id.sm_rv_groupNames);
        rv_messages=root.findViewById(R.id.sm_rv_messages);

        db=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        groups = new ArrayList<>();
        groupAdapter= new AddMeesageAdapter(groups, getActivity());
        rv_groups.setAdapter(groupAdapter);
        rv_groups.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        message = new ArrayList<>();
        messageAdapter= new MessageAdapter(message, getActivity());
        rv_messages.setAdapter(messageAdapter);
        rv_messages.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        getDataToFirebase();
        return root;
    }


    private void getDataToFirebase() {

        db.collection("groups").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        Log.d("---------------", doc.getData().toString());
                        CreateGroupModels cgm = doc.toObject(CreateGroupModels.class);
                        groups.add(cgm);
                        groupAdapter.notifyDataSetChanged();

                    }

                } else {
                    Log.d("---------------", "Verileri getiremedik...");
                }
            }
        });

        db.collection("message").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        Log.d("---------------", doc.getData().toString());
                        CreateMessageModels cgm = doc.toObject(CreateMessageModels.class);
                        message.add(cgm);
                        messageAdapter.notifyDataSetChanged();

                    }

                } else {
                    Log.d("---------------", "Verileri getiremedik...");
                }
            }
        });
    }
}