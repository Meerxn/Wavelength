package com.example.wavelength;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper {
    SQLiteDatabase sqLiteDatabase;

    public DBHelper(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }
// Function to create the users table
    public void createTable() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users" +
                "(id INTEGER PRIMARY KEY,username TEXT, password TEXT)");
    }

    public void onSignup(String username, String password){
        createTable();
        sqLiteDatabase.execSQL((String.format("INSERT INTO users (username,password) VALUES ('%s','%s')",
                username,password)));
    }
    // checks if user is valid or not
    public boolean onLogin(String username,String password){
        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * FROM users where username like '%s' and password like '%s'",username,password),null);
        try{c.moveToFirst();}
        catch (NullPointerException e){
            Log.i("errormsg", "NULL HERE");
        }
        //Test this to see empty record or not
        int icount = c.getCount();
        c.close();
        sqLiteDatabase.close();
        if(icount <= 0){
            return false;
        }
        else{
            return true;
        }

    }
}
