package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.R;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.gamemechanics.PlayerManager;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private GameEntity player;

    private Button btnInventory, btnFight;

    private TextView playerName;

    private ImageView mapImageView;

    private ProgressBar playerExpBar, playerProgressBar;

    private PlayerManager playerManager;

    private final int INVENTORY_BUTTON_ID = R.id.btnInventory;
    private final int FIGHT_BUTTON_ID = R.id.btnFight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initDisplay();
        updateMap();
    }

    //function to extract saved data
    private void initDisplay() {
        playerManager = new PlayerManager(this);
        initPlayerElements();
        initButtons();
    }

    private void updateMap() {
        mapImageView = findViewById(R.id.mapImageView);
        mapImageView.setImageResource(playerManager.updateMap());
    }

    private void initPlayerElements() {
        playerExpBar = findViewById(R.id.mapPlayerExp);
        playerProgressBar = findViewById(R.id.mapPlayerProgress);

        playerExpBar.setMax(playerManager.getExpNeeded());
        playerExpBar.setProgress(playerManager.getExp());

        playerProgressBar.setMax(playerManager.getMaxGameProgress());
        playerProgressBar.setProgress(playerManager.getGameProgress());

        playerName = findViewById(R.id.playerNameMap);
        playerName.setText(playerManager.getName());
    }

    private void initButtons() {
        btnFight = findViewById(R.id.btnFight);
        btnFight.setOnClickListener(this);

        btnInventory = findViewById(R.id.btnInventory);
        btnInventory.setOnClickListener(this);
    }

    public void openInventory() {

        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void startFight() {
        Intent intent = new Intent(this, FightActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case INVENTORY_BUTTON_ID:
                openInventory();
                break;
            case FIGHT_BUTTON_ID:
                startFight();
                break;
        }
    }
}