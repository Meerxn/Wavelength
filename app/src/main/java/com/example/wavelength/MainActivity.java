package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernamebox;
    private EditText passwordbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.wavelength", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("username", "").equals("")){
            String storedname = sharedPreferences.getString("username", "");
            Intent intent = new Intent(this, Homepage.class);
            intent.putExtra("username", storedname);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }

        usernamebox = (EditText) findViewById(R.id.username);
        passwordbox = (EditText) findViewById(R.id.password);

        Intent intent = getIntent();
        usernamebox.setText(intent.getStringExtra("username_su"));
        passwordbox.setText(intent.getStringExtra("password_su")); //Data coming from signup page, check if this causes
        //with shared preferences
    }

    public void clickSignIn(View view) { //On click for Sign in button


        String username = usernamebox.getText().toString();
        String password = passwordbox.getText().toString();

        //if name not in username column in database
        if (false) {
            Toast.makeText(this, "Username not found, please sign up", Toast.LENGTH_SHORT).show();
        }

        //if password doesn't match with username
        if (false) {
            Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
        }

        //else: If successfully logged in, go to home page


        Intent intent = new Intent(this, Homepage.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }

    public void clickSignUp(View view) { //On click for sign up button
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}