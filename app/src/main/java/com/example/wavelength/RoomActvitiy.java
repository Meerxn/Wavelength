package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class RoomActvitiy extends AppCompatActivity {
    TextView libName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //adding logo to actionBar
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(view);

        libName = (TextView) findViewById(R.id.libName);
        Intent intent = getIntent();
        String libNameStr = intent.getStringExtra("libName").split(", ")[0];
        String roomNameStr = intent.getStringExtra("libName").split(", ")[1];
        libName.setText(libNameStr);
    }
}