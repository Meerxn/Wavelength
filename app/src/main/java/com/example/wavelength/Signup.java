package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    private EditText usernamebox_su;
    private EditText passwordbox_su;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernamebox_su = (EditText) findViewById(R.id.username_signup);
        passwordbox_su = (EditText) findViewById(R.id.password_signup);


    }

    public void confirmSignUp(View view) {
        String username = usernamebox_su.getText().toString();
        String password = passwordbox_su.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username_su", username);
        intent.putExtra("password_su", password);
        startActivity(intent);
    }
}