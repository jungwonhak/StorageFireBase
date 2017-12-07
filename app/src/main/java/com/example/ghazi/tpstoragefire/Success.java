package com.example.ghazi.tpstoragefire;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Success extends AppCompatActivity {

    private ListView lv;
    private String TAG="well";
    private ArrayList<Person> personList;
    private ArrayList<Person> personList1;
    private  ArrayAdapter<Person> arrayAdapter;
    private  ArrayAdapter<Person> arrayAdapter1;
    private int  k;
    private int m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        lv=(ListView)findViewById(R.id.listview);
        personList= new ArrayList<Person>();
        personList1= new ArrayList<Person>();
        //Get data from server
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference collection= db.collection("users");

        Query query = FirebaseFirestore.getInstance()
                .collection("users");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    // Handle error
                    //...
                    return;
                }

                // get the new updated list
                    personList1.clear();
                for (DocumentSnapshot document :snapshot.getDocuments() ) {

                    Log.d(TAG, document.getId() + " => " + document.getData());
                    Person p= new Person();

                    p= document.toObject(Person.class);
                    // set identifier of the person from the db
                    p.setId(document.getId());
                    personList1.add(p);
                }





                // Update UI
                arrayAdapter1 = new ArrayAdapter<Person>(
                        Success.this,
                        android.R.layout.simple_list_item_1,
                        personList1 );
                lv.setAdapter(arrayAdapter1);
            }
        });




       collection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Person p= new Person();

                                p= document.toObject(Person.class);
                                p.setId(document.getId());
                                personList.add(p);


                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                         arrayAdapter = new ArrayAdapter<Person>(
                                Success.this,
                                android.R.layout.simple_list_item_1,
                                personList );
                        lv.setAdapter(arrayAdapter);





                    }
                });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        m=i;
                        collection.document(""+personList.get(i).getId()).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                        personList.remove(m);
                                        arrayAdapter = new ArrayAdapter<Person>(
                                                Success.this,
                                                android.R.layout.simple_list_item_1,
                                                personList );
                                        lv.setAdapter(arrayAdapter);
                                        arrayAdapter.notifyDataSetChanged();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error deleting document", e);
                                    }
                                });


                    }
                });

    }


}
