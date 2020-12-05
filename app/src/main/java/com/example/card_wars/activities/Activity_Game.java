package com.example.card_wars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.card_wars.R;
import com.example.card_wars.objects.GameManager;
import com.example.card_wars.objects.Card;
import com.example.card_wars.objects.Player;


public class Activity_Game extends AppCompatActivity {
    public static final int DELAY = 2500; // in ms

    private GameManager game;

    private TextView game_LBL_title;
    private TextView game_LBL_score1;
    private TextView game_LBL_score2;
    private ImageView game_IMG_card1;
    private ImageView game_IMG_card2;
    private ImageView game_IMG_play;
    private ProgressBar game_DPB_determinateBar;
    private ImageView game_IMG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activityLifeCycle", "onCreate: Activity_Game");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new GameManager("Skier", "Snowboarder", true);

        findViews();

        Glide
                .with(this)
                .load(R.drawable.img_game_background)
                .into(game_IMG_background);

        game_IMG_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!game.playRound()) {
                    return;
                }

                displayRound();
            }
        });

    } // onCreate

    private void findViews() {
        game_LBL_title = findViewById(R.id.game_LBL_title);
        game_LBL_score1 = findViewById(R.id.game_LBL_score1);
        game_LBL_score2 = findViewById(R.id.game_LBL_score2);
        game_IMG_card1 = findViewById(R.id.game_IMG_card1);
        game_IMG_card2 = findViewById(R.id.game_IMG_card2);
        game_IMG_play = findViewById(R.id.game_IMG_play);
        game_DPB_determinateBar = findViewById(R.id.game_DPB_determinateBar);
        game_IMG_background = findViewById(R.id.game_IMG_background);
    }

    private void displayRound() {
        showCard(game.getPlayer1().getCurrentCard(), game_IMG_card1);
        showCard(game.getPlayer2().getCurrentCard(), game_IMG_card2);
        game_LBL_score1.setText("" + game.getPlayer1().getScore());
        game_LBL_score2.setText("" + game.getPlayer2().getScore());

        if (!game.isTie()) {
            game_LBL_title.setText("Round " + game.getCurrentRoundNumber());
            game_DPB_determinateBar.setProgress((int)(game.getProgress() * 100));

        } else {
            game_LBL_title.setText("TIE BREAKER");
            game_LBL_title.setTextColor(getColor(R.color.red));
        }

        if (game.getWinner() != null) {
            openWinnerActivity(Activity_Game.this, game.getWinner());
        }

    } // displayRound

    private void showCard(Card card, ImageView img) {
        String cardDrawableName = getCardIconName(card);
        int resourceId = this.getResources().getIdentifier(cardDrawableName, "drawable", this.getPackageName());
        img.setImageResource(resourceId);
    }

    private String getCardIconName(Card card) {
        return "ic_" + card.getName().name().toLowerCase() + "_" + card.getType().name().toLowerCase();
    }

    private void openWinnerActivity(Activity activity, Player winner) {
        game_IMG_play.setVisibility(View.GONE);
        Toast.makeText(this, "Game Finished!", Toast.LENGTH_SHORT).show();
        
        String name = winner.getName();
        int score = winner.getScore();

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
        }, DELAY);
    } // openWinnerActivity

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