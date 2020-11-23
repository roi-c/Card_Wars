package com.example.card_wars.objects;

public class Player {
    private String name;
    private int score;
    private Card currentCard;

    public Player() { }

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.currentCard = null;
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

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

} // Player
