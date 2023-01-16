package com.example.messageapp3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messageapp3.Models.CreateMessageAdapter;
import com.example.messageapp3.Models.CreateMessageModels;
import com.example.messageapp3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class CreateMessageFragment extends Fragment {
    RecyclerView rv_message;

    ArrayList<CreateMessageModels> messages;
    CreateMessageAdapter cmAdapter;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_message, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        messages = new ArrayList<>();
        cmAdapter = new CreateMessageAdapter(getContext(),messages);
        rv_message = root.findViewById(R.id.cm_rv_messages);
        rv_message.setAdapter(cmAdapter);

        rv_message.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        getMessageToFirebase();
        return root;
    }

    private void getMessageToFirebase() {
        firebaseFirestore.collection("message").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot snapshot : task.getResult()){
                        Log.d("-------------------",snapshot.getData().toString());
                        CreateMessageModels cgm =snapshot.toObject(CreateMessageModels.class);
                        messages.add(cgm);
                        cmAdapter.notifyDataSetChanged();


                    }
                }
                else{
                    Log.d("-------------------","Veriyi getiremedik :(");
                }
            }
        });
    }

}