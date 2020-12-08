package com.example.card_wars.objects;

import java.util.LinkedList;

public class TopTen {

    private LinkedList<Record> records = new LinkedList<>();

    public TopTen() { }

    public TopTen(LinkedList<Record> records) {
        this.records = records;
    }

    public LinkedList<Record> getRecords() {
        return records;
    }

    public TopTen setRecords(LinkedList<Record> records) {
        this.records = records;
        return this;
    }
}
