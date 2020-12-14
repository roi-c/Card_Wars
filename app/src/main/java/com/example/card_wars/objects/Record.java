package com.example.card_wars.objects;

public class Record {

    private String name = "";
    private long date = 0;
    private int score = 0;
    private MyPosition myPosition;

    public Record() { }

    public Record(String name, long date, int score) {
        this.name = name;
        this.date = date;
        this.score = score;

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

    public MyPosition getMyPosition() {
        return myPosition;

    }

    public Record setMyPosition(MyPosition myPosition) {
        this.myPosition = myPosition;
        return this;
    }
} // Record
