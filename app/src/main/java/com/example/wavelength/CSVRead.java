package com.example.wavelength;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CSVRead {
    // the 0th element in all these lists are the column titles
    List<String> roomID = new ArrayList<>();
    List<String> libName = new ArrayList<>();;
    List<String> openTime = new ArrayList<>();;
    List<String> closeTime = new ArrayList<>();;
    List<String> reservedTimes = new ArrayList<>();;
    List<String> intervals = new ArrayList<>(26);
    List<String> times = new ArrayList<>(26);

    public List<String> getReservedTimes() {
        return reservedTimes;
    }

    public List<String> getTimes() {
        return times;
    }

    public void readCSV(InputStream stream) {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            String[] nextLine;

            // populate our lists which will contain info about each room
            while ((nextLine = reader.readNext()) != null) {
                roomID.add(nextLine[0]);
                libName.add(nextLine[1]);
                openTime.add(nextLine[2]);
                closeTime.add(nextLine[3]);
                reservedTimes.add(nextLine[4]);
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }






}
