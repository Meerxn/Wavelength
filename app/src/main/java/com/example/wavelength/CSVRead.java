package com.example.wavelength;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    HashMap<Integer, String> map = new HashMap<>();

    public HashMap<Integer, String> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, String> map) {
        this.map = map;
    }

    public List<String> getTimes() {
        return times;
    }

    public List<String> getIntervals() {
        return intervals;
    }

    public List<String> getReservedTimes(){ return reservedTimes;}

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

            // populate all the times
            times.add("09:00");times.add("09:30"); times.add("10:00");
            times.add("10:30"); times.add("11:00");times.add("11:30"); times.add("12:00");
            times.add("12:30"); times.add("13:00");times.add("13:30"); times.add("14:00");
            times.add("14:30"); times.add("15:00");times.add("15:30");times.add("16:00");
            times.add("16:30");times.add("17:00");times.add("17:30"); times.add("18:00");
            times.add("18:30");times.add("19:00"); times.add("19:30");times.add("20:00");
            times.add("20:30"); times.add("21:00");times.add("21:30");times.add("22:00");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSchedule(RecyclerView id, Context context) {
        Log.d("HELLO", "updateSchedule: called.");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerview = id;
        recyclerview.setLayoutManager(layoutManager);
        DataAdapter adapter = new DataAdapter(intervals, times, context, map);
        recyclerview.setAdapter(adapter);
    }

    public int getCSVRow(String room) {
        int index = 0;
        for (int i = 0; i < roomID.size(); i++) {
            if (room.equals(roomID.get(i))) {
                index = i;
            }
        }
        return index;
    }

    /*
This method goes through all the time intervals and creates a list of
all 30 minute slots that are reserved. These 30 minute time slots that are
in the list will be blocked in red color.
 */
    public void updateReservations(String room) {
        int index = getCSVRow(room);
        String[] arr = reservedTimes.get(index).split(","); // all reserved ranges for one room
        List<String> allBookedPositions = new ArrayList<>();
        for (String str : arr) {
            String[] curr = str.split("-");
            String start = curr[0];
            String end = curr[1];
            DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime lt = LocalTime.parse(start);
            while (!lt.toString().equals(end)) {
                allBookedPositions.add(lt.toString());
                lt = lt.plusMinutes(30);
            }
        }

        int i = 0;
        for (String str : times) {
            if (allBookedPositions.contains(str)) {
                map.put(i, str);
            }
            else {
                map.put(i, null);
            }
            i++;
        }
        Log.d("MAP1", map.toString());
    }






}
