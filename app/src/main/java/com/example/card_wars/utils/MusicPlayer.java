package com.example.card_wars.utils;

import android.content.Context;
import android.media.MediaPlayer;


public class MusicPlayer {

    private MediaPlayer mp;
    private boolean isMpFinished = false;
    private Context mContext;

    public MusicPlayer(Context context) {
        mContext = context;
    }

    public void playSound(int rawSound) {
        isMpFinished = false;

        mp = android.media.MediaPlayer.create(mContext, rawSound);

        mp.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(android.media.MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                isMpFinished = true;
                mp = null;
            }
        });

        mp.start();
    }

    public void start() {
        if (!isMpFinished) {
            mp.start();
        }

    }

    public void pause() {
        if (!isMpFinished) {
            mp.pause();
        }
    }

    public void releaseIfNotFinished() {
        if (mp != null) {
            mp.release();
        }
    }

} // MusicPlayer


