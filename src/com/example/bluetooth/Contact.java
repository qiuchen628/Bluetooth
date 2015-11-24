package com.example.bluetooth;

public class Contact {
    
    //private variables
    int _id;
    String device_name;
    String RSSI;
    String time_stamp;
    String distance;
    
 
    
    // Empty constructor
    public Contact(){
         
    }
    
    
    
    // constructor
    public Contact(int id, String device_name, String RSSI, String distance, String time_stamp){
        this._id = id;
        this.device_name = device_name;
        this.RSSI = RSSI;
        this.time_stamp =time_stamp;
        this.distance=distance;
    }
     
    // constructor
    public Contact(String device_name, String RSSI, String distance, String time_stamp ){
        this.device_name = device_name;
        this.RSSI = RSSI;
        this.time_stamp = time_stamp;
        this.distance=distance;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getDeviceName(){
        return this.device_name;
    }
     
    // setting name
    public void setDeviceName(String device_name){
        this.device_name = device_name;
    }
     
    // getting phone number
    public String getRSSI(){
        return this.RSSI;
    }
     
    // setting phone number
    public void setRSSI(String RSSI){
        this.RSSI = RSSI;
    }
    
    public String getTime(){
        return this.time_stamp;
    }
     
    // setting name
    public void setTime(String time_stamp){
        this.time_stamp = time_stamp;
    }
     
    public String getDistance(){
        return this.distance;
    }
     
    // setting name
    public void setDistance(String distance){
        this.distance = distance;
    }
     
    
    @Override    
    public String toString() {    
        return "id=" + _id + ", device=" + device_name + ", RSSI=" + RSSI    
                + ", distance=" + distance +", time=" + time_stamp;    
    }    
    
    
}
