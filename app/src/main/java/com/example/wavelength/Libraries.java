package com.example.wavelength;


public class Libraries {
    private String name;
    private String roomID;
    private String startTime;
    private String endTime;
    private String reservationTime;


    public Libraries(String roomID, String name, String startTime, String endTime, String reservationTime){
        this.name = name;
        this.roomID = roomID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservationTime = reservationTime;
    }
    public String getReservationTime(){
        return reservationTime;
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