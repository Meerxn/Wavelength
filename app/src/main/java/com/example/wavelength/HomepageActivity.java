package com.example.wavelength;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

public class HomepageActivity extends AppCompatActivity {
    private NavigationBarView bottomNavigationView;
    private TextView welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);

//        welcome = (TextView) findViewById(R.id.header_homepage);
//        Intent intent = getIntent();
//        String message = intent.getStringExtra("username");
//        welcome.setText("Hello "+ message+ "!");

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        Log.v("Created menu", "Menu created");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v("Method called", "Called options selected");
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.logoutopt:
                Intent intent = new Intent(this, MainActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.wavelength", MODE_PRIVATE);
                sharedPreferences.edit().remove("username").apply();
                Log.v("here", "Before intent");
                startActivity(intent);
                Log.v("here 2", "After intent");
            default:
                return super.onContextItemSelected(item);
        }
    }

    private NavigationBarView.OnItemSelectedListener bottomnavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.maps:
                    fragment = new MapsFragment();
                    break;
                case R.id.reservation:
                    fragment = new ReservationFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };
}