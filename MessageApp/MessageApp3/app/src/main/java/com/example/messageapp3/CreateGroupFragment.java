package com.example.messageapp3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.messageapp3.Models.CreateGroupModels;
import com.example.messageapp3.Models.CreateGroupsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CreateGroupFragment extends Fragment {
    EditText groupName,groupDescription;
    ImageView groupImage;
    Button createGroup;
    RecyclerView rv_groups;
    Uri filePath;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    ArrayList<CreateGroupModels> createGroupModels;
    CreateGroupsAdapter createGroupsAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_creare_group, container, false);
        groupName = root.findViewById(R.id.cg_et_groupName);
        groupDescription=root.findViewById(R.id.cg_et_groupDesc);
        groupImage=root.findViewById(R.id.cg_iv_groupImage);
        createGroup=root.findViewById(R.id.cg_btn_createGroup);
        rv_groups = root.findViewById(R.id.cg_rv_groups);

        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();

        createGroupModels = new ArrayList<>();
        createGroupsAdapter= new CreateGroupsAdapter(getActivity(),createGroupModels);
        rv_groups.setAdapter(createGroupsAdapter);
        rv_groups.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(),
                        result->{
                            if(result.getResultCode()==getActivity().RESULT_OK){
                                Intent id =result.getData();
                                filePath = id.getData();
                                groupImage.setImageURI(filePath);

                            }
                        });

        groupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                activityResultLauncher.launch(i);
            }
        });

       getGroupsFirestore();
        return root;
    }
    private void getGroupsFirestore() {
        db.collection("groups").
                //işlem tamamlandığında çağırılacak fonk.
                        get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //eğer veriler geldiyse
                if (task.isComplete()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        Log.d("---------------", doc.getData().toString());
                        //CreateGroupModels sınıfından bir nesne oluşturuyor ve o nesnenin referansını bmye atıyor.
                        //böylelikle tek tek eşleştirmeye gerek kalmadan verileri alıyor.
                        CreateGroupModels cgm = doc.toObject(CreateGroupModels.class);
                        //şimdi aldığımız verileri bir dizinin içine koymamız gerekiyor.
                        //verileri koyduğumuzda model değişmiş demektir.
                        createGroupModels.add(cgm);
                        //model değiştiyse adaptör de değişmiş demektir.
                        createGroupsAdapter.notifyDataSetChanged();

                    }

                } else {
                    Log.d("---------------", "Verileri getiremedik...");
                }
            }
        });
    }
}