package com.example.wavelength;

public class Reservation {
    private String email;
    private String name;
    private String roomID;
    private String startTime;
    private String endTime;
    private String date;


    public Reservation(String email, String name, String roomID, String startTime, String endTime, String date){
        this.email = email;
        this.name = name;
        this.roomID = roomID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public String getEmail(){
        return email;
    }
    public String getLibraryName(){
        return name;
    }
    public String getRoomID(){
        return roomID;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }


}