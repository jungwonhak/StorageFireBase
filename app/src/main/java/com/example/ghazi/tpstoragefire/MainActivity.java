package com.example.ghazi.tpstoragefire;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText first,last,born;
    private String TAG="donnee";
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd=(Button)findViewById(R.id.ajoutBtn);
        first=(EditText)findViewById(R.id.first);
        last=(EditText)findViewById(R.id.last);
        born=(EditText)findViewById(R.id.born);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> user = new HashMap<>();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference collection= db.collection("users");
                DocumentReference document=collection.document();
                user.put("first", first.getText().toString());
                user.put("last", last.getText().toString());
                user.put("born", born.getText().toString());

                document.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: success" );
                    }
                });

                Intent intent= new Intent(MainActivity.this,Success.class);
                startActivity(intent);
            }
        });


        //Get data from server
    /*    collection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Person p= new Person();
                                p= document.toObject(Person.class);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

*/

    // Listener on any changes
       /* document.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

*/
    }
}
