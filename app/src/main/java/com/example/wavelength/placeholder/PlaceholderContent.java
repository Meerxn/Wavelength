package com.example.wavelength.placeholder;

import com.example.wavelength.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<Reservation> ITEMS = new ArrayList<Reservation>();



    private static final int COUNT = 25;

//    static {
//        addItem(new PlaceholderItem("GR1", "123", "456", "Grainger", "12/12/21"));
//        addItem(new PlaceholderItem("BUS1", "123", "756", "Business", "12/12/21"));
//        addItem(new PlaceholderItem("SOC1", "23", "456", "Soc", "12/12/21"));
//    }

    public static void addItem(Reservation item) {
        ITEMS.add(item);
//        ITEM_MAP.put(item.id, item);
    }

//    private static PlaceholderItem createPlaceholderItem(int position) {
//        return new PlaceholderItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String roomid;
        public final String starttime;
        public final String endtime;
        public final String name;
        public final String date;

        public PlaceholderItem(String roomid, String starttime, String endtime, String name, String date) {
            this.roomid = roomid;
            this.starttime = starttime;
            this.endtime = endtime;
            this.name = name;
            this.date = date;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}