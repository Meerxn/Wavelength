package com.example.wavelength;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

public class HomepageActivity extends AppCompatActivity {
    private NavigationBarView bottomNavigationView;
    private TextView welcome;
    private TextView roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        //adding logo to actionBar
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_logo, null);
        actionBar.setCustomView(view);

        bottomNavigationView = findViewById(R.id.bottomnav);
        //bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);


    }


    public void onRoomClick(View view){

        Log.i("HELLO", "YOU HAVE ARRIVED");
        roomName = (TextView) findViewById(R.id.textView9);
        Log.i("ROOM NAME", roomName.getText().toString());
        goToRoomActivity(roomName.getText().toString());

    }

    public void goToRoomActivity(String roomName) {
        //String message = String.valueOf(answer);
        Log.i("JUST ROOM", roomName.split(", ")[1]);
        Intent intent = new Intent(this, RoomActivity.class);
        intent.putExtra("libName", roomName);
        startActivity(intent);
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
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };



}