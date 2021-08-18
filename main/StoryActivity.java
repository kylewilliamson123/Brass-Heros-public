package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.R;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.gamemechanics.Persistence;
import com.example.brassheroes.items.Equipment;

public class StoryActivity extends AppCompatActivity {

    TextView storyText, storyTitle;
    boolean isFightWon, wasBossFight;
    int expGained, drop;
    Equipment drop1, drop2;
    GameEntity player;
    Button skipStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initControls();
        setStory();
    }


    private String printDrop(int dropAmt) {
        if (dropAmt == 2) {
            return "\n1. " + drop1.toString() + "\n2. " + drop2.toString();
        } else if (dropAmt == 1) {
            return "\n1. " + drop1.toString();
        } else return "\n\t\t\tNo items found";
    }

    private void setStory() {
        if (isFightWon && !wasBossFight) {
            String s = getString(R.string.win_message, getString(R.string.fight), expGained, player.toString(), printDrop(drop));
            storyTitle.setText(R.string.win_title);
            storyText.setText(s);
            storyText.setMovementMethod(new ScrollingMovementMethod());
        }
        if (isFightWon && wasBossFight) {
            String s = getString(R.string.win_message, getString(R.string.boss_fight), expGained, player.toString(), printDrop(drop));
            storyTitle.setText(R.string.boss_win_title);
            storyText.setText(s);
            storyText.setMovementMethod(new ScrollingMovementMethod());
        }
        if (!isFightWon) {
            storyTitle.setText(R.string.lost_fight_title);
            storyText.setText(R.string.lost_fight_message);
        }
    }

    private void initControls() {
        Persistence persistence = new Persistence(this);
        player = persistence.getSavedGame();

        isFightWon = getIntent().getBooleanExtra("won", true);
        wasBossFight = getIntent().getBooleanExtra("isBossFight", false);
        expGained = getIntent().getIntExtra("expGained", 0);
        drop = getIntent().getIntExtra("drop", 0);
        if (drop != 0) {
            if (drop == 2) {
                drop1 = getIntent().getParcelableExtra("drop1");
                drop2 = getIntent().getParcelableExtra("drop2");
            } else if (drop == 1) {
                drop1 = getIntent().getParcelableExtra("drop1");
            }
        }

        storyText = findViewById(R.id.storyText);
        storyTitle = findViewById(R.id.storyTitle);

        skipStory = findViewById(R.id.skipStory);
        skipStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoryActivity.this, MapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}