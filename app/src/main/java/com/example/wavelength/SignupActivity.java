package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    EditText usernamebox;
    EditText passwordbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        usernamebox = (EditText) findViewById(R.id.username_su);
        passwordbox = (EditText) findViewById(R.id.password_su);
    }

    public void toSignIn(View view){

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

       dbHelper.onSignup(usernamebox.getText().toString(), passwordbox.getText().toString());

        Intent intent  = new Intent(this, MainActivity.class);
        intent.putExtra("username_su", usernamebox.getText().toString());
        intent.putExtra("password_su", passwordbox.getText().toString());
        startActivity(intent);
    }
}