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

import com.example.messageapp3.Models.CreateGroupModels;
import com.example.messageapp3.Models.CreateGroupsAdapter;
import com.example.messageapp3.Models.GroupCardsAdapeter;
import com.example.messageapp3.Models.PersonModels;
import com.example.messageapp3.Models.PersonsAdapter;
import com.example.messageapp3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AddGroupFragment extends Fragment {

    RecyclerView rv_groups,rv_persons;


    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    ArrayList<CreateGroupModels> createGroupModels;
    GroupCardsAdapeter groupCardsAdapeter;

    ArrayList<PersonModels> persons;
    PersonsAdapter personsAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_group, container, false);
        rv_groups = root.findViewById(R.id.ag_rv_groups);
        rv_persons=root.findViewById(R.id.ag_rv_person);

        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();

        createGroupModels = new ArrayList<>();
        groupCardsAdapeter= new GroupCardsAdapeter(createGroupModels, getActivity());
        rv_groups.setAdapter(groupCardsAdapeter);
        rv_groups.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        persons = new ArrayList<>();
        personsAdapter= new PersonsAdapter(getActivity(),persons);
        rv_persons.setAdapter(personsAdapter);
        rv_persons.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


        getGroupsToFirebase();

        return root;
    }

    private void getGroupsToFirebase() {
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
                        groupCardsAdapeter.notifyDataSetChanged();

                    }

                } else {
                    Log.d("---------------", "Verileri getiremedik...");
                }
            }
        });

        db.collection("persons").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        Log.d("---------------", doc.getData().toString());
                        PersonModels pm = doc.toObject(PersonModels.class);
                        persons.add(pm);
                        personsAdapter.notifyDataSetChanged();

                    }

                } else {
                    Log.d("---------------", "Verileri getiremedik...");
                }
            }
        });
    }


}