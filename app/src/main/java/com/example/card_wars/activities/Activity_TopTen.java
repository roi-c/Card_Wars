package com.example.card_wars.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.card_wars.R;
import com.example.card_wars.callbacks.CallBack_Top;
import com.example.card_wars.fragments.Fragment_List;
import com.example.card_wars.fragments.Fragment_Map;
import com.example.card_wars.objects.TopTen;
import com.example.card_wars.utils.SP;
import com.google.gson.Gson;

public class Activity_TopTen extends AppCompatActivity {

    private FrameLayout topTen_LAY_list;
    private FrameLayout topTen_LAY_map;

    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activityLifeCycle", "onCreate: Activity_TopTen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        findViews();
//        initViews();
        TopTen topTen = null;
        String ttJson = SP.getInstance().getString(SP.KEYS.KEY_TOP_TEN, "NA");
        if (!ttJson.equals("NA")) {
            topTen = new Gson().fromJson(ttJson, TopTen.class);
        }

        fragment_list = new Fragment_List(this, topTen);
        fragment_list.setCallBack_top(callBack_top);
        getSupportFragmentManager().beginTransaction().add(R.id.topTen_LAY_list, fragment_list).commit();

        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.topTen_LAY_map, fragment_map).commit();


    } // onCreate

    private void findViews() {
        topTen_LAY_list = findViewById(R.id.topTen_LAY_list);
        topTen_LAY_map = findViewById(R.id.topTen_LAY_map);
    }

    private CallBack_Top callBack_top = new CallBack_Top() {
        @Override
        public void addMarkerToMap(double lat, double lon) {
            fragment_map.addMarker(lat, lon);
        }
    };


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