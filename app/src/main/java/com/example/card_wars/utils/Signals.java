package com.example.card_wars.utils;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

public class Signals {

    private static Signals instance;
    private Context appContext;

    public static Signals getInstance() {
        return instance;
    }

    private Signals(Context appContext) {
        this.appContext = appContext.getApplicationContext();
    }

    public static void init(Context appContext) {
        if (instance == null) {
            instance = new Signals(appContext);
        }
    }

    public void vibrate() {
        Vibrator v = (Vibrator) appContext.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    public void toast(String message) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
    }

} // Signals
