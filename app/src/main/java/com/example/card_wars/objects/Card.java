package com.example.card_wars.objects;

public class Card {
    public enum eType {Hearts, Diamonds, Spades, Clubs};
    public enum eName {Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King};

    private eType type;
    private eName name;

    public Card() {
    }

    public Card(eName name, eType type) {
        this.type = type;
        this.name = name;
    }

    public int getValue() {
        return name.ordinal() + 1;
    }

    public eType getType() {
        return type;
    }

    public eName getName() {
        return name;
    }

} // Card
