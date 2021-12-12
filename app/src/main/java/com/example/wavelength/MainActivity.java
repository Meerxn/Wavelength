package com.example.wavelength;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    public static ArrayList<Reservation> notes = new ArrayList<>();;

    public EditText usernamebox;
    public EditText passwordbox;
    private ActivityResultLauncher<String> mPermissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if(result) {
                        Log.e("perm", "onActivityResult: PERMISSION GRANTED");
                    } else {
                        Log.e("perm", "onActivityResult: PERMISSION DENIED");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.wavelength", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("username", "").equals("")){
            String storedname = sharedPreferences.getString("username", "");
            Intent intent = new Intent(this, HomepageActivity.class);
            intent.putExtra("username", storedname);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }

        usernamebox = (EditText) findViewById(R.id.username);
        passwordbox = (EditText) findViewById(R.id.password);

//        Intent intent = getIntent();
//        usernamebox.setText(intent.getStringExtra("username_su"));
//        passwordbox.setText(intent.getStringExtra("password_su")); //Getting username and password from sign up page


    }

    public void toSignUp(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
    public void fireBaseLogin(View view){
        String email = usernamebox.getText().toString();
        String password = passwordbox.getText().toString();



        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(mAuth,sqLiteDatabase);
        //dbHelper.libInit();
        //Log.d("data Here", " " + dbHelper.getOne());
//        dbHelper.onAddData(email,"Social", "001","5:00","5:30","2/10/2021");
//        dbHelper.onAddData(email,"Memorial Library", "001","5:00","5:30","2/10/2021");
//        notes = dbHelper.readNotes(email);
//        for (Reservation note : notes){
//            Log.d("here",note.getLibraryName());
//        }

        //dbHelper.addData("CL001","December 8th 12:20:00", "December 8th 12:50:00","College Library");




        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            toHomepage(view,1);
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            toHomepage(view,1);

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                        }
                    }
                });
    }
    public void toHomepage(View view, int status) {

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
        String email = usernamebox.getText().toString();
        String password = passwordbox.getText().toString();
        //boolean success = dbHelper.onLogin(usernamebox.getText().toString(), passwordbox.getText().toString());
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            success = true;
//
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                        } else {
//                            success = false;
//
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//
//                        }
//                    }
//                });

        if (status == 0) {
            Toast.makeText(this, "Unsuccessful signin", Toast.LENGTH_SHORT).show();
            Log.i("pass", "WE passed");
        } else {
            Intent intent = new Intent(this, HomepageActivity.class);
            intent.putExtra("username", usernamebox.getText().toString());
            SharedPreferences sharedPreferences = getSharedPreferences("com.example.wavelength", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("username",usernamebox.getText().toString()).apply();
            startActivity(intent);
        }
    }


}