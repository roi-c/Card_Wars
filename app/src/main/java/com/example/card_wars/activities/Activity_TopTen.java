package com.example.card_wars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.card_wars.R;
import com.example.card_wars.objects.Record;
import com.example.card_wars.objects.TopTen;
import com.example.card_wars.utils.SP;
import com.google.gson.Gson;

public class Activity_TopTen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activityLifeCycle", "onCreate: Activity_TopTen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        String ttJson = SP.getInstance().getString(SP.KEYS.KEY_TOP_TEN, "NA");
        TopTen topTen = new Gson().fromJson(ttJson, TopTen.class);

        for(int i = 0 ; i < topTen.getRecords().size(); i++) {
            Record record = topTen.getRecords().get(i);
            Log.d("testt", i + ")" +record.getName() +" " +record.getScore() + " " + record.getDate());
        }

        Log.d("testt", "------------------------------------");

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