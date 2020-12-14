package com.example.card_wars.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.card_wars.Constants.SP_FILE;

public class SP {
    
    public interface KEYS {
        public static final String KEY_TOP_TEN = "KEY_TOP_TEN";
    }

    private static SP instance;
    private SharedPreferences prefs;

    public static SP getInstance() {
        return instance;
    }

    private SP(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SP(context);
        }
    }

    //// ---------------------------------------------------------- ////
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }

    public void removeKey(String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key).apply();
    }

} // SP
