package com.example.wavelength;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class RoomActvitiy extends AppCompatActivity {
    TextView libName;
    TextView roomName;
    TextView date;
    TextView startTime;
    TextView endTime;
    int startHour, startMinute;
    int endHour, endMinute;
    private DatePickerDialog datePickerDialog;



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
        String libNameStr = intent.getStringExtra("libName").split(", ")[0];
        String roomNameStr = intent.getStringExtra("libName").split(", ")[1];
        libName.setText(libNameStr);
        roomName.setText("Room: " + roomNameStr);

        date = (TextView) findViewById(R.id.date_text);
        date.setText(todayDate());

        startTime = (TextView)findViewById(R.id.start_text);
        endTime = (TextView)findViewById(R.id.end_text);

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
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                endHour = selectedHour;
                endMinute = selectedMinute;
                fixEndMinute();
                endTime.setText(String.format(Locale.getDefault(), "%02d:%02d", endHour, endMinute));
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
}