package com.example.card_wars.objects;

public class Player {
    private String name;
    private int score;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addPoint() {
        score += 1;
    }

} // Player
