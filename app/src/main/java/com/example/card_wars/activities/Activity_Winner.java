package com.example.card_wars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.card_wars.R;
import com.example.card_wars.objects.Player;
import com.google.gson.Gson;


public class Activity_Winner extends AppCompatActivity {
    public static final String EXTRA_KEY_WINNER = "EXTRA_KEY_WINNER";

    private TextView winner_LBL_name;
    private TextView winner_LBL_score;
    private Button winner_BTN_topTen;
    private Button winner_BTN_replay;
    private Button winner_BTN_close;
    private MediaPlayer mp;
    private boolean isMpFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activityLifeCycle", "onCreate: Activity_Winner");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        findViews();

        playSound(R.raw.snd_win_game);

        String winnerJsonFromGameActivity = getIntent().getStringExtra(EXTRA_KEY_WINNER);
        Player winner = new Gson().fromJson(winnerJsonFromGameActivity, Player.class);
        winner_LBL_name.setText(winner.getName());
        winner_LBL_score.setText("" + winner.getScore());

        winner_BTN_topTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopTenActivity(Activity_Winner.this);
            }
        });

        winner_BTN_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity(Activity_Winner.this);
            }
        });

        winner_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

    } // onCreate

    private void findViews() {
        winner_LBL_name = findViewById(R.id.winner_LBL_name);
        winner_LBL_score = findViewById(R.id.winner_LBL_score);
        winner_BTN_replay = findViewById(R.id.winner_BTN_replay);
        winner_BTN_close = findViewById(R.id.winner_BTN_close);
        winner_BTN_topTen = findViewById(R.id.winner_BTN_topTen);
    }

    private void openTopTenActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_TopTen.class);
        startActivity(myIntent);
        finish();
    }

    private void openGameActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_Game.class);
        startActivity(myIntent);
        finish();
    }

    private void closeActivity() {
        finish();
    }

    @Override
    protected void onStart() {
        Log.d("activityLifeCycle", "onStart: Activity_Winner");
        super.onStart();
        start();
    }

    private void playSound(int rawSound) {
        mp = MediaPlayer.create(this, rawSound);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                isMpFinished = true;
                mp = null;
            }
        });

        mp.start();
    }

    private void start() {
        if (!isMpFinished) {
            mp.start();
        }

    }

    private void stop() {
        if (!isMpFinished) {
            mp.pause();
        }
    }

    @Override
    protected void onResume() {
        Log.d("activityLifeCycle", "onResume: Activity_Winner");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("activityLifeCycle", "onPause: Activity_Winner");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("activityLifeCycle", "onStop: Activity_Winner");
        super.onStop();
        stop();
    }

    @Override
    protected void onDestroy() {
        Log.d("activityLifeCycle", "onDestroy: Activity_Winner");
        super.onDestroy();
        if (mp != null) {
            mp.release(); }
    }

} // Activity_Winner