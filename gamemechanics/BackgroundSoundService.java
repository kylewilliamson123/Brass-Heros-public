package com.example.brassheroes.gamemechanics;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.brassheroes.R;

public class BackgroundSoundService extends Service{
    private static final String TAG = null;
    private final int GAME_MUSIC_THEME = R.raw.theme;
    MediaPlayer player;

    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, GAME_MUSIC_THEME);
        player.setLooping(true); // Set looping
        player.setVolume(30, 30);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();

        return START_STICKY;
    }

    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    @Override
    public void onDestroy() {
        player.release();
    }

    @Override
    public void onLowMemory() {

    }


}
