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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHelper {
    FirebaseAuth mauth;
    SQLiteDatabase sqLiteDatabase;



    public DBHelper(FirebaseAuth author,SQLiteDatabase sqLiteDatabase) {
        this.mauth = author;
        this.sqLiteDatabase = sqLiteDatabase;

    }
    public void createTable() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS reservations1" +
                "(email TEXT ,name TEXT, roomid TEXT,end_time TEXT, start_time TEXT, date TEXT)");
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
    public void onAddData(String email,String name, String roomID, String startTime, String endTime, String date){
        createTable();
        sqLiteDatabase.execSQL((String.format("INSERT INTO reservations_table (email,name,roomid,end_time,start_time,date) VALUES ('%s','%s','%s','%s','%s','%s')",
                email,name,roomID,endTime,startTime,date)));
    }
    public void getData(String email,String name, String roomID, String startTime, String endTime, String date){
        createTable();
        sqLiteDatabase.execSQL((String.format(
                email,name,roomID,endTime,startTime,date)));
    }
    public ArrayList<Reservation> readNotes(String email){
        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * FROM reservations1 where email like '%s'",email),null);
        int nameIndex = c.getColumnIndex("name");
        int roomIdIndex = c.getColumnIndex("roomid");
        int end_timeIndex = c.getColumnIndex("end_time");
        int start_timeIndex = c.getColumnIndex("start_time");
        int dateIndex = c.getColumnIndex("date");
        c.moveToFirst();
        ArrayList<Reservation> notesList = new ArrayList<>();
        while(!c.isAfterLast()){
            String name = c.getString(nameIndex);
            String roomID = c.getString(roomIdIndex);
            String endTime = c.getString(end_timeIndex);
            String startTime = c.getString(start_timeIndex);
            String date = c.getString(dateIndex);
            Reservation note = new Reservation(email,name,roomID,endTime,startTime,date);
            notesList.add(note);
            c.moveToNext();


        }
        c.close();
        sqLiteDatabase.close();
        return notesList;


    }
//    public String checkValue(){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference docRef = db.collection("reservations").document("fardeen7210@gmail.com");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            String currHash;
//
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("Here", "DocumentSnapshot data: " + document.getData());
//                        currHash = document.getData().toString();
//                    } else {
//                        Log.d("bye", "No such document");
//                    }
//                } else {
//                    Log.d("jj", "get failed with ", task.getException());
//                }
//            }
//        });
//
////        createTable();
////        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * FROM users where username like '%s' and password like '%s'",username,password),null);
////        try{c.moveToFirst();}
////        catch (NullPointerException e){
////            Log.i("errormsg", "NULL HERE");
////        }
////        //Test this to see empty record or not
////        int icount = c.getCount();
////        c.close();
////        sqLiteDatabase.close();
////        if(icount <= 0){
////            return false;
////        }
////        else{
////            return true;
////        }
//        String[] name;
//        //name = currHash.toString().split("}");
//        return currHash;
//    }
}
