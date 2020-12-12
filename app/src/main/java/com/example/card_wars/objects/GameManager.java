package com.example.card_wars.objects;

import com.example.card_wars.utils.SP;
import com.google.gson.Gson;

import java.util.ArrayList;

public class GameManager {
    public final static int MAX_NUM_OF_ROUNDS = (Card.eName.values().length * Card.eType.values().length) / 2;

    private Player player1;
    private Player player2;
    private Player winner;
    private Deck deck;
    private int currentRoundNumber;
    private boolean isTie;
    private float progress;
    private TopTen topTen;

    public GameManager() {
    }

    public GameManager(String player1Name, String player2Name, boolean isRegularDeck) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        deck = new Deck(isRegularDeck);
        currentRoundNumber = 0;
        isTie = false;
        winner = null;
        topTen = new TopTen();
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

    public float getProgress() {
        return progress;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setCurrentRoundNumber(int currentRoundNumber) {
        this.currentRoundNumber = currentRoundNumber;
    }

    public void setTie(boolean tie) {
        isTie = tie;
    }

    public boolean playRound() {
        if (deck.isEmpty()) {
            return false;
        }

        player1.setCurrentCard(deck.drawCardFromTop());
        player2.setCurrentCard(deck.drawCardFromTop());

        calculatePlayerScore(player1.getCurrentCard().getValue(), player2.getCurrentCard().getValue());

        currentRoundNumber++;
        progress = (float) currentRoundNumber / MAX_NUM_OF_ROUNDS;

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

        if (winner != null) {
            addToTopTen(winner);
        }

    } // checkForWinner

    private void addToTopTen(Player winner) {
        Gson gson = new Gson();
        SP sp = SP.getInstance();

        String topTenJson = sp.getString(SP.KEYS.KEY_TOP_TEN, "NA");
        if (topTenJson.equals("NA")) { // TopTen list is empty
            addRecordToTopTen(winner, 0);
        } else { // TopTen list is not empty
            topTen = gson.fromJson(topTenJson, TopTen.class);
            ArrayList<Record> records = topTen.getRecords();
            int winnerScore = winner.getScore();
            int i = 0;
            if (winnerScore > records.get(records.size() - 1).getScore() || records.size() < TopTen.MAX_IN_LIST) {
                do {
                    int currentScore = records.get(i).getScore();
                    if (winnerScore > currentScore && i < TopTen.MAX_IN_LIST) {
                        if (records.size() == TopTen.MAX_IN_LIST) { // if list if full remove last
                            records.remove(records.size() - 1);
                        }
                        addRecordToTopTen(winner, i);
                        break;
                    }
                    if (i == records.size() - 1 && i < TopTen.MAX_IN_LIST) { // winner score is the lowest, and there is room in the list
                        addRecordToTopTen(winner, i + 1);
                        break;
                    }
                    i++;
                } while (i < records.size());
            }
        }

        sp.putString(SP.KEYS.KEY_TOP_TEN, gson.toJson(topTen));
    } // addToTopTen

    private void addRecordToTopTen(Player winner, int index) {
        Record record = new Record()
                .setDate(System.currentTimeMillis())
                .setName(winner.getName())
                .setScore(winner.getScore());

        topTen.getRecords().add(index, record);
    }

} // GameManager
