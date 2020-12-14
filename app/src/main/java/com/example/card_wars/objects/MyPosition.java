package com.example.card_wars.objects;

public class MyPosition {
    private double latitude;
    private double longitude;

    public MyPosition() {}

    public MyPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

} // MyPosition


