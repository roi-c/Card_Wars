package com.example.card_wars.objects;

public class Record {

    private String name = "";
    private long date = 0;
    private int score = 0;
//    private double longitude = 0;
//    private double latitude  = 0;

    public Record() { }

    public Record(String name, long date, int score/*, double longitude, double latitude*/) {
        this.name = name;
        this.date = date;
        this.score = score;
//        this.longitude = longitude;
//        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public long getDate() {
        return date;
    }

    public Record setDate(long date) {
        this.date = date;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }

//    public double getLongitude() {
//        return longitude;
//    }
//
//    public Record setLongitude(double longitude) {
//        this.longitude = longitude;
//        return this;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public Record setLatitude(double latitude) {
//        this.latitude = latitude;
//        return this;
//    }
} // Record
