package com.example.card_wars.objects;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck;
    private boolean isRegularDeck;

    public Deck() { }

    public Deck(boolean isRegularDeck) {
        deck = new Stack<Card>();

        if (isRegularDeck) {
            Card.eType[] allTypes = Card.eType.values();
            Card.eName[] allNames = Card.eName.values();
            for (Card.eName name : allNames) {
                for (Card.eType type : allTypes) {
                    deck.push(new Card(name, type));
                }
            }
        } else { // its a tie-breaker deck
            deck.push(new Card(Card.eName.King, Card.eType.Diamonds));
            deck.push(new Card(Card.eName.Ace, Card.eType.Clubs));
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public boolean isEmpty() {
       return deck.empty();
    }

    public Card drawCardFromTop() {
        return deck.pop();
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public boolean isRegularDeck() {
        return isRegularDeck;
    }

    public void setDeck(Stack<Card> deck) {
        this.deck = deck;
    }

    public void setRegularDeck(boolean regularDeck) {
        isRegularDeck = regularDeck;
    }
}
