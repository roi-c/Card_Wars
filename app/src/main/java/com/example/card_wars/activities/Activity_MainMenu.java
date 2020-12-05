package com.example.card_wars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.card_wars.R;

public class Activity_MainMenu extends AppCompatActivity {

    private Button menu_BTN_playGame;
    private Button menu_BTN_topTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activityLifeCycle", "onCreate: Activity_MainMenu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main_menu);

        findViews();

        menu_BTN_playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity(Activity_MainMenu.this);
            }
        });

        menu_BTN_topTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopTenActivity(Activity_MainMenu.this);
            }
        });

    } // onCreate

    private void findViews() {
        menu_BTN_playGame = findViewById(R.id.menu_BTN_playGame);
        menu_BTN_topTen = findViewById(R.id.menu_BTN_topTen);
    }

    private void openGameActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_Game.class);
        startActivity(myIntent);
    }

    private void openTopTenActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_TopTen.class);
        startActivity(myIntent);
    }

    @Override
    protected void onStart() {
        Log.d("activityLifeCycle", "onStart: Activity_MainMenu");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("activityLifeCycle", "onResume: Activity_MainMenu");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("activityLifeCycle", "onPause: Activity_MainMenu");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("activityLifeCycle", "onStop: Activity_MainMenu");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("activityLifeCycle", "onDestroy: Activity_MainMenu");
        super.onDestroy();
    }


} // Activity_MainMenu