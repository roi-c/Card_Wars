package com.example.card_wars.objects;

public class GameManager {
    private Player player1;
    private Player player2;
    private Player winner;
    private Deck deck;
    private int currentRoundNumber;
    private boolean isTie;

    public GameManager() { }

    public GameManager(String player1Name, String player2Name, boolean isRegularDeck) {
        player1 = new Player(player1Name);
        player2 = new Player(player1Name);
        deck = new Deck(isRegularDeck);
        currentRoundNumber = 0;
        isTie = false;
        winner = null;
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public boolean isTie() {
        return isTie;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean playRound() {
        if (deck.isEmpty()) {
            return false;
        }

        player1.setCurrentCard(deck.drawCard());
        player2.setCurrentCard(deck.drawCard());

        calculatePlayerScore(player1.getCurrentCard().getValue(), player2.getCurrentCard().getValue());

        currentRoundNumber++;

        if (deck.isEmpty()) {
            checkForWinner();
        }

        return true;
    } // playRound

    private void calculatePlayerScore(int cardValueOfPlayer1, int cardValueOfPlayer2) {
        if (cardValueOfPlayer1 > cardValueOfPlayer2) {
            player1.addPoint();
        } else if (cardValueOfPlayer1 < cardValueOfPlayer2) {
            player2.addPoint();
        } else { // its a tie
            player1.addPoint();
            player2.addPoint();
        }
    } // calculatePlayerScore

    private void checkForWinner() {
        int scoreOfPlayer1 = player1.getScore();
        int scoreOfPlayer2 = player2.getScore();

        if (scoreOfPlayer1 > scoreOfPlayer2) {
            winner = player1;
        } else if (scoreOfPlayer1 < scoreOfPlayer2) {
            winner = player2;
        } else { // its a tie
            deck = new Deck(false);
            isTie = true;
        }
    } // checkForWinner

}
