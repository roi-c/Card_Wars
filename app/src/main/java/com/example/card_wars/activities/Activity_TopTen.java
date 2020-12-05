package com.example.card_wars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.card_wars.R;

public class Activity_TopTen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activityLifeCycle", "onCreate: Activity_TopTen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);
    } // onCreate


    @Override
    protected void onStart() {
        Log.d("activityLifeCycle", "onStart: Activity_TopTen");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("activityLifeCycle", "onResume: Activity_TopTen");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("activityLifeCycle", "onPause: Activity_TopTen");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("activityLifeCycle", "onStop: Activity_TopTen");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("activityLifeCycle", "onDestroy: Activity_TopTen");
        super.onDestroy();
    }

} // Activity_TopTen