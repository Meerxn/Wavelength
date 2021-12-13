package com.example.wavelength;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.google.firebase.auth.FirebaseAuth;
//import com.opencsv.CSVWriter;

public class RoomActivity extends AppCompatActivity {
    TextView libName;
    TextView roomName;
    TextView date;
    TextView startTime;
    TextView endTime;
    int startHour, startMinute;
    int endHour, endMinute;
    String libNameStr, roomNameStr;
    private DatePickerDialog datePickerDialog;
    CSVRead read = new CSVRead();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //adding logo to actionBar
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(view);

        //setting lib and room name
        libName = (TextView) findViewById(R.id.libName);
        roomName = (TextView) findViewById(R.id.roomName);
        Intent intent = getIntent();
        libNameStr = intent.getStringExtra("libName").split(", ")[0];
        roomNameStr = intent.getStringExtra("libName").split(", ")[1];
        libName.setText(libNameStr);
        roomName.setText("Room: " + roomNameStr);

        date = (TextView) findViewById(R.id.date_text);
        date.setText(todayDate());

        startTime = (TextView)findViewById(R.id.start_text);
        endTime = (TextView)findViewById(R.id.end_text);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE, null);
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        DBHelper dbHelper = new DBHelper(mauth,sqLiteDatabase);

        read.readCSV(dbHelper);
        read.updateReservations(roomNameStr);
        read.updateSchedule(findViewById(R.id.recyclerview), this);
    }

    public void showStartTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                startHour = selectedHour;
                startMinute = selectedMinute;
                fixStartMinute();
                startTime.setText(String.format(Locale.getDefault(), "%02d:%02d",startHour, startMinute));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, startHour, startMinute, true);
        timePickerDialog.setTitle("Select Start Time");
        timePickerDialog.show();
    }

    public void showEndTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                endHour = selectedHour;
                endMinute = selectedMinute;
                fixEndMinute();
                endTime.setText(String.format(Locale.getDefault(), "%02d:%02d", endHour, endMinute));
                addUpdatedInterval();


            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, endHour, endMinute, true);
        timePickerDialog.setTitle("Select End Time");
        timePickerDialog.show();
    }
    public void fixEndMinute(){
        Log.i("END MINUTE", String.valueOf(endMinute));
        if(endMinute >= 0 && endMinute < 15){
            endMinute = 0;
        }
        else if(endMinute >= 15 && endMinute < 45){
            endMinute = 30;
        }
        else if(endMinute >= 45){
            endMinute = 0;
            if(endHour == 23){
                endHour = 0;
            }
            else{
                endHour++;
            }
        }
    }

    public void fixStartMinute(){
        Log.i("START MINUTE", String.valueOf(startMinute));
        if(startMinute >= 0 && startMinute < 15){
            startMinute = 0;
        }
        else if(startMinute >= 15 && startMinute < 45){
            startMinute = 30;
        }
        else if(startMinute >= 45){
            startMinute = 0;
            if(startHour == 23){
                startHour = 0;
            }
            else{
                startHour++;
            }
        }
    }

    public void showDatePickerDialog(View view){
        Log.d("DATE PICKER", "HELLO DATE PICKER");
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String dayStr = month + "/" + day + "/" + year;
                date.setText(dayStr);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.show();

    }


    private String todayDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return month + "/" + day + "/" + year;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addUpdatedInterval() {
        String startStr = "";
        String endStr = "";

        if (startMinute == 0) {
            startStr = startHour + ":" + startMinute + "0";
        }
        else {
            startStr = startHour + ":" + startMinute;
        }

        if (endMinute == 0) {
            endStr = endHour + ":" + endMinute + "0";
        }
        else {
            endStr = endHour + ":" + endMinute;
        }
        if (startHour < 10) {
            startStr = "0" + startStr;
        }
        if (endHour < 10) {
            endStr = "0" + endStr;
        }

        // if the library is not open, throw an alert dialog
        if (startHour < 9 || startHour > 22 || endHour > 22 || endHour < 9) {
            TimeBoundDialog timeBoundDialog = new TimeBoundDialog();
            timeBoundDialog.show(getSupportFragmentManager(), "Alert Dialog");
        }
        Log.d("start", startStr);
        Log.d("end", endStr);
        List<String> l_times = read.getTimes();
        HashMap<Integer, String> hmap = read.getMap();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(startStr);
        List<String> updatedReserve = new ArrayList<>(); // intervals that the user has booked
        // this may or not be already filled. We need to check
        while (!lt.toString().equals(endStr)) {
            updatedReserve.add(lt.toString());
            lt = lt.plusMinutes(30);
        }
        int i = 0;
        boolean conflict = false; // is there a conflict in reservation
        for (String s : l_times) {
            // update the hashmap if there are no conflicts
            if (updatedReserve.contains(s) && hmap.get(i) == null) {
                hmap.put(i, "New");
            }
            // if room is already reserved, display an alert dialog box
            else if (updatedReserve.contains(s) && hmap.get(i) != null) {
                conflict = true;
                BoxDialog dialog = new BoxDialog();
                dialog.show(getSupportFragmentManager(), "Alert Dialog");
            }
            i++;
        }

        // this boolean variable prevents from reserving blocks that have a conflict
        if (!conflict) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            RecyclerView recyclerview = findViewById(R.id.recyclerview);
            recyclerview.setLayoutManager(layoutManager);
            DataAdapter adapter = new DataAdapter(read.getIntervals(), read.getTimes(), this, read.getMap());
            recyclerview.setAdapter(adapter);
        }
        else {
            for (Integer num : hmap.keySet()) {
                if (hmap.get(num) != null && hmap.get(num).equals("New")) {
                    hmap.put(num, null);
                }
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            RecyclerView recyclerview = findViewById(R.id.recyclerview);
            recyclerview.setLayoutManager(layoutManager);
            DataAdapter adapter = new DataAdapter(read.getIntervals(), read.getTimes(), this, read.getMap());
            recyclerview.setAdapter(adapter);
        }


        Log.d("yello", hmap.toString());
    }

    public void navConf(View view){
        writeCSV();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE, null);
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        DBHelper dbHelper = new DBHelper(mauth,sqLiteDatabase);
        String sTime = addZeroHour(startHour) + ":" + addZeroMin(startMinute);
        String eTime = addZeroHour(endHour) + ":" + addZeroMin(endMinute);
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        dbHelper.onAddData( email,libNameStr,roomNameStr,sTime,eTime,date.getText().toString());

        Intent intent = new Intent(this, ConfActivity.class);
        intent.putExtra("library", libNameStr + " - ");
        intent.putExtra("room", roomNameStr);
        intent.putExtra("startTime", addZeroHour(startHour) + ":" + addZeroMin(startMinute));
        intent.putExtra("endTime", addZeroHour(endHour) + ":" + addZeroMin(endMinute));
        Log.d("DATE FROM ROOM", date.getText().toString());

        intent.putExtra("dayStr", date.getText().toString());
        startActivity(intent);
    }

    public String addZeroHour(int hour){
        if(String.valueOf(hour).length() == 1){
            return "0" + hour;
        }
        return String.valueOf(hour);
    }

    public String addZeroMin(int min){
        if(min == 0){
            return "0" + min;
        }
        return String.valueOf(min);
    }

    public void writeCSV(){
        try {
            Context context = getApplicationContext();
            SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE, null);
            FirebaseAuth mauth = FirebaseAuth.getInstance();
            DBHelper dbHelper = new DBHelper(mauth,sqLiteDatabase);

            Log.d("Write csv", "write csv");
            String currRes = dbHelper.getResTime(roomNameStr);

            Log.d("curr res", currRes);
            currRes += "," + addZeroHour(startHour) + ":" + addZeroMin(startMinute) + "-" +
                    addZeroHour(endHour) + ":" + addZeroMin(endMinute);
            Log.d("new res", currRes);
            dbHelper.writeNewRes(currRes, roomNameStr);
            read.readCSV(dbHelper);

            /*read.getReservedTimes().set(index, curr);
            Log.d("reserved times", read.getReservedTimes().toString());*/
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}