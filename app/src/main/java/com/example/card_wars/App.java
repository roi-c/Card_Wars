package com.example.card_wars;

import android.app.Application;

import com.example.card_wars.utils.SP;
import com.example.card_wars.utils.Signals;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SP.init(this);
        Signals.init(this);

    }

} // App
