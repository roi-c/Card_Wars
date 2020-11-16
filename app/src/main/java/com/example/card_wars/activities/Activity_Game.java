package com.example.card_wars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.card_wars.R;
import com.example.card_wars.objects.Card;
import com.example.card_wars.objects.Player;

import java.util.Collections;
import java.util.Stack;

public class Activity_Game extends AppCompatActivity {

    private Player player1;
    private Player player2;
    private Stack<Card> deck;
    private int currentRoundNumber;
    private boolean isTie;

    private TextView game_LBL_title;
    private TextView game_LBL_roundNumber;
    private TextView game_LBL_score1;
    private TextView game_LBL_score2;
    private ImageView game_IMG_card1;
    private ImageView game_IMG_card2;
    private ImageView game_IMG_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activityLifeCycle", "onCreate: Activity_Game");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1 = new Player("Skier");
        player2 = new Player("Snowboarder");
        deck = createNewDeck(true);
        currentRoundNumber = 0;
        isTie = false;

        game_LBL_title = findViewById(R.id.game_LBL_title);
        game_LBL_roundNumber = findViewById(R.id.game_LBL_roundNumber);
        game_LBL_score1 = findViewById(R.id.game_LBL_score1);
        game_LBL_score2 = findViewById(R.id.game_LBL_score2);
        game_IMG_card1 = findViewById(R.id.game_IMG_card1);
        game_IMG_card2 = findViewById(R.id.game_IMG_card2);
        game_IMG_play = findViewById(R.id.game_IMG_play);

        game_IMG_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRound();
            }
        });

    } // onCreate

    private void playRound() {
        Card cardOfPlayer1, cardOfPlayer2;
        int cardValueOfPlayer1, cardValueOfPlayer2;

        if (deck.empty()) {
            return;
        }

        cardOfPlayer1 = deck.pop();
        cardValueOfPlayer1 = cardOfPlayer1.getValue();
        showCard(cardOfPlayer1, game_IMG_card1);

        cardOfPlayer2 = deck.pop();
        cardValueOfPlayer2 = cardOfPlayer2.getValue();
        showCard(cardOfPlayer2, game_IMG_card2);

        calculatePlayerScore(cardValueOfPlayer1, cardValueOfPlayer2);

        currentRoundNumber++;

        if (!isTie) {
            game_LBL_roundNumber.setText("" + currentRoundNumber);
        }

        if (deck.empty()) {
            checkForWinner();
        }

    } // playRound

    private void showCard(Card card, ImageView img) {
        String cardDrawableName = getCardIconName(card);
        int resourceId = this.getResources().getIdentifier(cardDrawableName, "drawable", this.getPackageName());
        img.setImageResource(resourceId);
    }

    private String getCardIconName(Card card) {
        return "ic_" + card.getName().name().toLowerCase() + "_" + card.getType().name().toLowerCase();
    }

    private void calculatePlayerScore(int cardValueOfPlayer1, int cardValueOfPlayer2) {
        if (cardValueOfPlayer1 > cardValueOfPlayer2) {
            player1.addPoint();
            game_LBL_score1.setText("" + player1.getScore());
        } else if (cardValueOfPlayer1 < cardValueOfPlayer2) {
            player2.addPoint();
            game_LBL_score2.setText("" + player2.getScore());
        } else { // its a tie
            player1.addPoint();
            game_LBL_score1.setText("" + player1.getScore());
            player2.addPoint();
            game_LBL_score2.setText("" + player2.getScore());
        }
    } // calculatePlayerScore

    private void checkForWinner() {
        int scoreOfPlayer1 = player1.getScore();
        int scoreOfPlayer2 = player2.getScore();

        if (scoreOfPlayer1 > scoreOfPlayer2) {
            openWinnerActivity(Activity_Game.this, player1);
        } else if (scoreOfPlayer1 < scoreOfPlayer2) {
            openWinnerActivity(Activity_Game.this, player2);
        } else { // its a tie
            deck = createNewDeck(false);
            game_LBL_title.setText("TIE BREAKER");
            game_LBL_title.setTextColor(getColor(R.color.red));
            game_LBL_roundNumber.setText("");
            isTie = true;
        }
    } // checkForWinner

    private void openWinnerActivity(Activity activity, Player winner) {
        game_IMG_play.setVisibility(View.GONE);
        Toast.makeText(this, "Game Finished!", Toast.LENGTH_SHORT).show();
        
        String name = winner.getName();
        int score = winner.getScore();
        int delayInMs = 2500;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(activity, Activity_Winner.class);
                myIntent.putExtra(Activity_Winner.EXTRA_KEY_NAME, name);
                myIntent.putExtra(Activity_Winner.EXTRA_KEY_SCORE, score);
                startActivity(myIntent);
                finish();
            }
        }, delayInMs);
    }

    private Stack<Card> createNewDeck(boolean isRegularDeck) {
        Stack<Card> deck = new Stack<Card>();

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
        Collections.shuffle(deck);
        return deck;
    } // createNewDeck

    @Override
    protected void onStart() {
        Log.d("activityLifeCycle", "onStart: Activity_Game");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("activityLifeCycle", "onResume: Activity_Game");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("activityLifeCycle", "onPause: Activity_Game");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("activityLifeCycle", "onStop: Activity_Game");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("activityLifeCycle", "onDestroy: Activity_Game");
        super.onDestroy();
    }

} // Activity_Game