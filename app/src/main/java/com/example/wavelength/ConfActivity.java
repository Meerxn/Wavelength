package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ConfActivity extends AppCompatActivity {

    String libNameStr, roomNameStr, startTime, endTime, dayStr;
    TextView library, room, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf);

        //adding logo to actionBar
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(view);

        library = (TextView) findViewById(R.id.library);
        room = (TextView) findViewById(R.id.room);
        date = (TextView) findViewById(R.id.confDate);
        time = (TextView) findViewById(R.id.time);

        Intent intent = getIntent();
        libNameStr = intent.getStringExtra("library");
        roomNameStr = intent.getStringExtra("room");
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("endTime");
        dayStr = intent.getStringExtra("dayStr");
        Log.d("date", dayStr);
        library.setText(libNameStr);
        room.setText(roomNameStr);
        date.setText(dayStr);
        time.setText(startTime + " - " + endTime);



    }

    public void backToHome(View view){
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
    }
}