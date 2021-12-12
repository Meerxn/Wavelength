package com.example.wavelength;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;


public class DBHelper {
    FirebaseAuth mauth;

    public DBHelper(FirebaseAuth author) {
        this.mauth = author;
    }
// Function to create the users table

//    public void onSignup(String username, String password){
//        createTable();
//        sqLiteDatabase.execSQL((String.format("INSERT INTO users (username,password) VALUES ('%s','%s')",
//                username,password)));
//    }
    // checks if user is valid or not\
    public void addData(String roomID, String startime, String endtime, String name){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("reservations").document("fardeen7210@gmail.com");
        Map<String,Object> playerMap=new HashMap<>();
        playerMap.put("roomID",roomID);
        playerMap.put("name",name);
        playerMap.put("start_time",startime);
        playerMap.put("end_time",endtime);
        docRef.update("Past Bookings", FieldValue.arrayUnion(playerMap));
    }
    public boolean onLogin(FirebaseAuth mauth){
        return false;

//        createTable();
//        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * FROM users where username like '%s' and password like '%s'",username,password),null);
//        try{c.moveToFirst();}
//        catch (NullPointerException e){
//            Log.i("errormsg", "NULL HERE");
//        }
//        //Test this to see empty record or not
//        int icount = c.getCount();
//        c.close();
//        sqLiteDatabase.close();
//        if(icount <= 0){
//            return false;
//        }
//        else{
//            return true;
//        }

    }
}
