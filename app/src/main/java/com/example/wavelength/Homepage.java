package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    TextView welcomemsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        welcomemsg = (TextView) findViewById(R.id.welcomemsg);
        Intent intent = getIntent();
        String message = intent.getStringExtra("username");
        welcomemsg.setText("Welcome "+message+"!");
    }
}