package com.drozee.drozeebvest;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreferencesActivity extends AppCompatActivity {
    ArrayList<String> pref = new ArrayList<String>();
    RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this, pref);
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mListReference;
    DatabaseReference mBooksReference;
    ChildEventListener mChildEventListener;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        String uid = user.getUid();
//        if(uid == null)
//        {
//            uid = "Anonymous";
//        }
        mListReference = mFirebaseDatabase.getReference().child("Books").child("Anonymous");
        mBooksReference = mListReference.child("Books").child("uid");
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.prefRV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(viewAdapter);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ArrayList<String> lst = new ArrayList<String>(); // Result will be holded Here
                        lst.add(String.valueOf(dataSnapshot.getValue()));
                        for(int z = 0; z < lst.size(); z++)
                        {
                                String[] atom = lst.get(z).split(",");
                                pref.add(atom[0] + "," + atom[1]);


                        }
                for (int a = 0; a < pref.size(); a++) {
                    Log.i("Preferences", Integer.toString(pref.get(0).length()));
                }
                viewAdapter.notifyDataSetChanged();}
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mListReference.addChildEventListener(mChildEventListener);
        Button add = (Button)findViewById(R.id.addBTN);
        final Button submit = (Button)findViewById(R.id.nextBTN);
        final EditText prefET = (EditText)findViewById(R.id.prefET);
        final EditText authET = (EditText)findViewById(R.id.authorET);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefET.getText().toString().equals("") || authET.getText().toString().equals("")) {
                    Toast.makeText(PreferencesActivity.this, "Either field cannot be blank", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    pref.add(prefET.getText().toString() + "," + authET.getText().toString());
                    prefET.setText("");
                    authET.setText("");
                    if(viewAdapter.getItemCount() >= 5)
                    {
                        if(viewAdapter.getItemCount() == 1)
                            mListReference.setValue(pref);
                        else
                            mListReference.setValue(pref);
                        Toast.makeText(PreferencesActivity.this, "Uploading Data", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });






        //Set Custom Font
        //Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "CaviarDreams_Bold.ttf");

    }
}
