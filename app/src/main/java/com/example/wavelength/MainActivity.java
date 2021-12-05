package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernamebox;
    EditText passwordbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Test

        usernamebox = (EditText) findViewById(R.id.username);
        passwordbox = (EditText) findViewById(R.id.password);

        Intent intent = getIntent();
        usernamebox.setText(intent.getStringExtra("username_su"));
        passwordbox.setText(intent.getStringExtra("password_su")); //Getting username and password from sign up page


    }

    public void toSignUp(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void toHomepage(View view) {

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        boolean success = dbHelper.onLogin(usernamebox.getText().toString(), passwordbox.getText().toString());

        if (!success) {
            Toast.makeText(this, "Unsuccessful signin", Toast.LENGTH_SHORT).show();
            Log.i("pass", "WE passed");
        } else {
            Intent intent = new Intent(this, HomepageActivity.class);
            intent.putExtra("username", usernamebox.getText().toString());
            startActivity(intent);
        }
    }
}