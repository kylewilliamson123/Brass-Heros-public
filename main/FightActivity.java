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
import com.example.brassheroes.characters.Enemy;
import com.example.brassheroes.gamemechanics.FightEngine;
import com.example.brassheroes.gamemechanics.PlayerManager;

public class FightActivity extends AppCompatActivity implements View.OnClickListener {

    final int ATTACK_BUTTON_ID = R.id.attackBtn;
    final int REST_BUTTON_ID = R.id.restBtn;
    final int RUN_BUTTON_ID = R.id.runBtn;

    Button btnAttack, btnFlee, btnRest;

    TextView playerMessage, enemyName, enemyClass;

    PlayerManager playerManager;

    ProgressBar enemyHealth, playerHealth;

    ImageView enemyPortrait;

    FightEngine fightEngine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initControls();
        initDisplay();
    }

    private void attackMove() {
        //players turn
        fightEngine.attackMove(true);
        enemyHealth.setProgress(fightEngine.getHealthReturnValue(), true);
        //enemys turn
        fightEngine.attackMove(false);
        playerHealth.setProgress(fightEngine.getHealthReturnValue(), true);
    }

    private void rest() {
        fightEngine.attackMove(false);
        playerHealth.setProgress(fightEngine.getHealthReturnValue(), true);
    }

    private void initControls() {
        //create player manager
        playerManager = new PlayerManager(this);
        //create fight engine object
        fightEngine = new FightEngine(this);
    }

    //all the model fields
    private void initDisplay() {
        btnRest = findViewById(REST_BUTTON_ID);
        btnAttack = findViewById(ATTACK_BUTTON_ID);
        btnFlee = findViewById(RUN_BUTTON_ID);

        btnRest.setOnClickListener(this);
        btnFlee.setOnClickListener(this);
        btnAttack.setOnClickListener(this);

        enemyHealth = findViewById(R.id.enemyHealth);
        enemyHealth.setMax(fightEngine.getEnemyTotalHealth());
        enemyHealth.setProgress(fightEngine.getEnemyTotalHealth());

        playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setMax(playerManager.getTotalHealth());
        playerHealth.setProgress(playerManager.getTotalHealth());

        playerMessage = findViewById(R.id.playerMessage);
        playerMessage.setText(getString(R.string.fight_player_move, playerManager.getName()));

        enemyPortrait = findViewById(R.id.enemyPortrait);
        enemyPortrait.setImageResource(fightEngine.getEnemyPortrait());

        enemyName = findViewById(R.id.enemyName);
        enemyName.setText(fightEngine.getEnemyName());

        enemyClass = findViewById(R.id.enemyClass);
        enemyClass.setText(fightEngine.printEnemyClass());
    }

    //leave the fight
    private void run() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case ATTACK_BUTTON_ID:
                attackMove();
                break;
            case RUN_BUTTON_ID:
                run();
                break;
            case REST_BUTTON_ID:
                rest();
                break;
        }
    }
}