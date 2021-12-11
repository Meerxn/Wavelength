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
}
