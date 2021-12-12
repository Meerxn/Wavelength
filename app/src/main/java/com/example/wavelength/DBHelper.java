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
    public void libInit(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS libraries1" +
                "(roomID TEXT ,name TEXT, start_time TEXT,end_time TEXT,reserved_time Text)");
        String[][] libraries = {{"Mem379",	"Memorial Library"	,"9:00","22:00" , "09:00-10:30,12:00-14:00,14:00-16:00"},
                {"Mem477"	,"Memorial Library","9:00",	"22:00","10:00-13:00,13:00-14:00,17:00-18:00,20:00-22:00"},
                { "Mem479"	,"Memorial Library",	"9:00","22:00"	,"09:00-11:00,15:00-17:00"},
                {"Mem777"	, "Memorial Library",	"9:00"	,"22:00","10:00-12:00,12:30-14:00"},
                {"Mem779","Memorial Library", "9:00",	 "22:00", 	"09:30-12:00,14:00-16:00,16:00-18:00"},
                {"Col2203","College Library","7:30","0:00","09:00-10:00,10:00-13:00,16:00-17:15"},
                {"Col2205","College Library","7:30","0:00","09:00-11:00,15:00-17:00"},
                {"Col2217","College Library","7:30","0:00","09:30-10:00,12:00-14:15,17:00-20:00"},
                {"Col2258","College Library","7:30","0:00","10:00-12:00,13:00-15:30,17:00-19:00,19:00-22:00"},
                {"Col3203","College Library","7:30","0:00","10:00-12:00,12:30-14:00,14:00-17:15"},
                {"Ste101",	"Steenbock Library"	,"9:00",	"22:00" ,	"09:00-10:30,14:00-16:00"},
                {"Ste103",	 "Steenbock Library", "9:00"	,"22:00",	"08:00-10:00,10:00-13:00,16:00-17:15"},
                {"Ste108"	,"Steenbock Library",	"9:00",	"22:00"	,"10:00-13:00,13:00-14:00,17:00-18:00,20:00-22:00"},
                {"Ste111"	,"Steenbock Library",	"9:00",	"22:00",	"09:00-12:00,12:30-14:00,15:00-16:15"},
                {"Ste115","Steenbock Library",	"9:00",	"22:00",	"09:30-12:00,14:00-16:00,16:00-18:00"},
                {"Bus2111", "Business Library",	"8:00"	,"23:00","10:00-12:00,13:00-15:30,17:00-19:00,19:00-21:30"},
                {"Bus2135","Business Library","8:00","23:00","10:00-12:00,12:30-14:00,14:00-17:15"},
                {"Bus2210D","Business Library","8:00","23:00",	"09:00-10:30,14:00-16:00,17:00-18:30"},
                {"Bus3210C","Business Library","8:00","23:00","09:00-10:00,10:00-13:00,16:00-17:15"},
                {"Bus3210G","Business Library","8:00","23:00","10:00-13:00,13:00-14:00,15:00-17:30,17:30-19:00,20:00-22:00"},
                {"Soc142","Social Work Library","8:30","18:00","09:00-10:30,14:00-16:00,17:00-18:00"},
                {"Soc143","Social Work Library","8:30","18:00","09:30-10:00,10:30-13:30,13:30-16:00,16:00-17:15"},
        };
        for(int i = 0 ; i < libraries.length; i++){
            sqLiteDatabase.execSQL((String.format("INSERT INTO libraries1 (roomID,name,start_time,end_time,reserved_time) VALUES ('%s','%s','%s','%s','%s')",
                    libraries[i][0],libraries[i][1],libraries[i][2],libraries[i][3],libraries[i][3])));
        }


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
    public int getOne(){
        String date = "";
        int count = 0;
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * FROM libraries1"),null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            count = count + 1;
            c.moveToNext();



        }

        c.close();
        sqLiteDatabase.close();
        return count;

    }
    public void onAddData(String email,String name, String roomID, String startTime, String endTime, String date){
        createTable();
        sqLiteDatabase.execSQL((String.format("INSERT INTO reservations1 (email,name,roomid,end_time,start_time,date) VALUES ('%s','%s','%s','%s','%s','%s')",
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

//    Mem379	Memorial Library	9:00	22:00	09:00-10:30,12:00-14:00,14:00-16:00
//        Mem477	Memorial Library	9:00	22:00	10:00-13:00,13:00-14:00,17:00-18:00,20:00-22:00
//        Mem479	Memorial Library	9:00	22:00	09:00-11:00,15:00-17:00
//        Mem777	Memorial Library	9:00	22:00	10:00-12:00,12:30-14:00
//        Mem779	Memorial Library	9:00	22:00	09:30-12:00,14:00-16:00,16:00-18:00
//        Col2203	College Library	7:30	0:00	09:00-10:00,10:00-13:00,16:00-17:15
//        Col2205	College Library	7:30	0:00	09:00-11:00,15:00-17:00
//        Col2217	College Library	7:30	0:00	09:30-10:00,12:00-14:15,17:00-20:00
//        Col2258	College Library	7:30	0:00	10:00-12:00,13:00-15:30,17:00-19:00,19:00-22:00
//        Col3203	College Library	7:30	0:00	10:00-12:00,12:30-14:00,14:00-17:15
//        Ste101	Steenbock Library	9:00	22:00	09:00-10:30,14:00-16:00
//        Ste103	Steenbock Library	9:00	22:00	08:00-10:00,10:00-13:00,16:00-17:15
//        Ste108	Steenbock Library	9:00	22:00	10:00-13:00,13:00-14:00,17:00-18:00,20:00-22:00
//        Ste111	Steenbock Library	9:00	22:00	09:00-12:00,12:30-14:00,15:00-16:15
//        Ste115	Steenbock Library	9:00	22:00	09:30-12:00,14:00-16:00,16:00-18:00
//        Bus2111	Business Library	8:00	23:00	10:00-12:00,13:00-15:30,17:00-19:00,19:00-21:30
//        Bus2135	Business Library	8:00	23:00	10:00-12:00,12:30-14:00,14:00-17:15
//        Bus2210D	Business Library	8:00	23:00	09:00-10:30,14:00-16:00,17:00-18:30
//        Bus3210C	Business Library	8:00	23:00	09:00-10:00,10:00-13:00,16:00-17:15
//        Bus3210G	Business Library	8:00	23:00	10:00-13:00,13:00-14:00,15:00-17:30,17:30-19:00,20:00-22:00
//        Soc142	Social Work Library	8:30	18:00	09:00-10:30,14:00-16:00,17:00-18:00
//        Soc143	Social Work Library	8:30	18:00	09:30-10:00,10:30-13:30,13:30-16:00,16:00-17:15