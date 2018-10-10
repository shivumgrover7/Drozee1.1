package com.drozee.drozeebvest;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PreferencesActivity extends AppCompatActivity {
    ArrayList<String> pref = new ArrayList<String>();
    RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this, pref);
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mListReference;
    FirebaseAuth mAuth;
    String user;
    ListView listViewl;
    List<Books> booklist;
    public EditText prefET;

    public EditText authET;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        mAuth =  FirebaseAuth.getInstance();
        listViewl = (ListView) findViewById (R.id.lisst);
        booklist = new ArrayList<> ();


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mListReference = mFirebaseDatabase.getReference("Books").child(mAuth.getCurrentUser().getUid());
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.prefRV);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(viewAdapter);
        user = mAuth.getUid();
        final Button add = (Button)findViewById(R.id.addBTN);
//        final Button submit = (Button)findViewById(R.id.submitBTN);
          prefET = (EditText)findViewById(R.id.prefET);
        authET = (EditText)findViewById(R.id.authorET);
//        submit.setEnabled(false);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addbook();
//                if(authET.getText().toString()!= "" && prefET.getText().toString()!= ""){
//                pref.add(prefET.getText().toString() + "," + authET.getText());
//                prefET.setText("");
//                authET.setText("");
//                viewAdapter.notifyDataSetChanged();
//                if(viewAdapter.getItemCount() >= 5)
//                {
////                    submit.setBackgroundResource(R.drawable.edittext);
//                    submit.setEnabled(true);
//                if (prefET.getText().toString().equals("") || authET.getText().toString().equals("")) {
//                    Toast.makeText(PreferencesActivity.this, "Either field cannot be blank", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    pref.add(prefET.getText().toString() + "," + authET.getText().toString());
//                    prefET.setText("");
//                    authET.setText("");
//                    viewAdapter.notifyDataSetChanged();
//                    if (viewAdapter.getItemCount() >= 5)
//                    {
//                        submit.setBackgroundResource(R.drawable.edittext);
//                    }
//                }
//            }}
        }});
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(viewAdapter.getItemCount() >= 5)
//                {
//              if(viewAdapter.getItemCount() == 1)
//                        mListReference.child("Books").child(user).push().setValue(pref);
//                    else
//                        mListReference.child("Books").child(user).setValue(pref);
//                    Toast.makeText(PreferencesActivity.this, "Uploading Data", Toast.LENGTH_SHORT).show();
//
//                }
//                else
//                {
//                    Toast.makeText(PreferencesActivity.this, "Please enter at least 5 books", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });





        //Set Custom Font
        //Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "CaviarDreams_Bold.ttf");

    }


    @Override
    protected void onStart() {
        super.onStart ( );
        mListReference.addValueEventListener (new ValueEventListener( ) {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                booklist.clear ();
                for (DataSnapshot applianceSnapshot : dataSnapshot.getChildren ()){

                    Books bookslista = applianceSnapshot.getValue (Books.class);
                    booklist.add (bookslista);
                }
                BooksListAdapter adapter = new BooksListAdapter (PreferencesActivity.this, booklist);
                listViewl.setAdapter (adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




//    private void addbook() {
//        String bookname = prefET.getText().toString();
//        String author = authET.getText().toString();
//
//        Books books = new Books(bookname,author);
//        mListReference.child(bookname).setValue(books);
//        Toast.makeText (this, "Book added",Toast.LENGTH_LONG).show ();

    private void addbook() {
        String bookname = prefET.getText().toString();
        String author = authET.getText().toString();
        if(prefET.getText().toString().equals("") || authET.getText().toString().equals("")) {
            Toast.makeText(PreferencesActivity.this, "Book or Author name cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            prefET.setText("");
            authET.setText("");
            Books books = new Books(bookname, author);
            mListReference.child(bookname).setValue(books);
            Toast.makeText(this, "Book added", Toast.LENGTH_LONG).show();

        }
    }
}
