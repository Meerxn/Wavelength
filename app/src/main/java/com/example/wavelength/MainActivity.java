package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Test
    }

    public void toSignUp(View view){
        Intent intent  = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void toHomepage(View view){
        Intent intent  = new Intent(this, HomepageActivity.class);
        startActivity(intent);
    }
}