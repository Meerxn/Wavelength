package com.example.wavelength;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class HomepageActivity extends AppCompatActivity {
    private NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);
    }

    private NavigationBarView.OnItemSelectedListener bottomnavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };
}