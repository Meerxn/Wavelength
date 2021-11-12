package com.example.wavelength;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * FROM users where username like '%s' and password like '%s",username,password),null);
        c.moveToFirst();
        //Test this to see empty record or not
        int icount = c.getCount();

        if(icount <= 0){
            c.close();
            sqLiteDatabase.close();
            return false;
        }
        else{
            c.close();
            sqLiteDatabase.close();
            return true;
        }

    }
}
