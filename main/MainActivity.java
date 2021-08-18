package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.R;
import com.example.brassheroes.gamemechanics.BackgroundSoundService;
import com.example.brassheroes.gamemechanics.Persistence;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button contGameBtn, newGameBtn;
    Persistence persistence;

    private final int CONTINUE_GAME_BTN_ID = R.id.continueBtn;
    private final int NEW_GAME_BTN_ID = R.id.newGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the immersive mode(complete fullscreen)
        setContentView(R.layout.activity_main);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        //start the app background music in a thread
        Intent svc = new Intent(this, BackgroundSoundService.class);
        startService(svc);
        initControls();
    }

    public void initControls() {
        persistence = new Persistence(this);

        contGameBtn = findViewById(CONTINUE_GAME_BTN_ID);
        newGameBtn = findViewById(NEW_GAME_BTN_ID);

        contGameBtn.setOnClickListener(this);
        newGameBtn.setOnClickListener(this);
    }

    public void startGame() {
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);
    }

    public void continueGame() {
        if (persistence.areGeneralDirs()) {
            if (persistence.savesBothExist()) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
                this.finish();
            } else Toast.makeText(MainActivity.this, "no saved game", Toast.LENGTH_LONG).show();
        } else Toast.makeText(MainActivity.this, "never started a game", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case NEW_GAME_BTN_ID:
                if (persistence.areGeneralDirs()) {
                    if (persistence.savesBothExist()) {
                        persistence.deleteSavedGames();
                        persistence.deleteSavedInventory();
                    }
                } else {
                    System.out.println("no directory found, creating");
                    persistence.createGeneralDirs();
                }
                startGame();
                break;

            case CONTINUE_GAME_BTN_ID:
                continueGame();
                break;
        }
    }
}

