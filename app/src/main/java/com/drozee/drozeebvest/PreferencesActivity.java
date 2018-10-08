package com.drozee.drozeebvest;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class PreferencesActivity extends AppCompatActivity {
    ArrayList<String> pref = new ArrayList<String>();
    RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this, pref);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.prefRV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(viewAdapter);

        Button submit = (Button)findViewById(R.id.submitBTN);
        final EditText prefET = (EditText)findViewById(R.id.prefET);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref.add(prefET.getText().toString());
                prefET.setText("");
                viewAdapter.notifyDataSetChanged();
            }
        });





        //Set Custom Font
        //TextView prefTV = (TextView) findViewById(R.id.prefTV);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "CaviarDreams_Bold.ttf");
        //prefTV.setTypeface(myCustomFont);
        //Find Submit Button
//        Button submitPref = (Button)findViewById(R.id.prefSubmitbtn);
//        final ArrayList<String> preferences = new ArrayList<String>();
//        submitPref.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    EditText editText1 = (EditText)findViewById(R.id.ET1);
//                    preferences.add(editText1.getText().toString());
//
//                    EditText editText2 = (EditText)findViewById(R.id.ET2);
//                    preferences.add(editText2.getText().toString());
//
//                    EditText editText3 = (EditText)findViewById(R.id.ET3);
//                    preferences.add(editText3.getText().toString());
//
//                    EditText editText4 = (EditText)findViewById(R.id.ET4);
//                    preferences.add(editText4.getText().toString());
//
//                    EditText editText5 = (EditText)findViewById(R.id.ET5);
//                    preferences.add(editText5.getText().toString());
//
//                    EditText editText6 = (EditText)findViewById(R.id.ET6);
//                    preferences.add(editText6.getText().toString());
//
//                    EditText editText7 = (EditText)findViewById(R.id.ET7);
//                    preferences.add(editText7.getText().toString());
//
//                    EditText editText8 = (EditText)findViewById(R.id.ET8);
//                    preferences.add(editText8.getText().toString());
//
//                    EditText editText9 = (EditText)findViewById(R.id.ET9);
//                    preferences.add(editText9.getText().toString());
//
//                    EditText editText10 = (EditText)findViewById(R.id.ET10);
//                    preferences.add(editText10.getText().toString());
//            }
//        });

    }
}
