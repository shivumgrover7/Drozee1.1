package com.drozee.drozeebvest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.imageButton)
    Button imageButton;
    FirebaseAuth mAuth;
    FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Madeleina Sans.otf");
        mStorage = FirebaseStorage.getInstance();
        imageButton.setTypeface(typeface);
        mAuth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.imageButton)
    public void onViewClicked() {
        //startActivity(new Intent(this, PreferencesActivity.class));
        loginuser();

    }

    private void loginuser() {final String emailstring = editText.getText ().toString ().trim ();

        // to get Password from user and store it in variable called Password
        final String PassWord = editText4.getText ().toString ().trim ();
        if(emailstring.isEmpty ())
        {
            //set an error
            editText.setError ("Email is required");
            //and highlight that box
            editText.requestFocus ();
            return;
        }
        if(PassWord.isEmpty ())
        {
            //set an error
            editText4.setError ("Password is required");
            //it will focus on password
            editText4.requestFocus ();
            return;
        }
        if(PassWord.length ()<6)
        {
            editText4.setError ("Minimum length of password required is 6");
            editText4.requestFocus ();
            return;
        }
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(emailstring).matches())
        {
            mAuth.signInWithEmailAndPassword (emailstring,PassWord).addOnCompleteListener (new OnCompleteListener<AuthResult>( ) {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
//                    progressBar.setVisibility (View.GONE);
                    if (task.isSuccessful ())
                    {
                        //if login is successful then
                        Intent intent = new Intent (LoginActivity.this, IDLogin.class);
                        intent .addFlags (intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity (intent);
                        Toast.makeText (getApplicationContext (),"log in",Toast.LENGTH_SHORT).show ();

                    }else
                    {
                        //else
                        Toast.makeText (getApplicationContext (), task.getException ().getMessage (),Toast.LENGTH_SHORT).show ();
                    }
                }
            });}
    }
}
