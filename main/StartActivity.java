package com.example.brassheroes.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brassheroes.R;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.characters.Knight;
import com.example.brassheroes.characters.Paladin;
import com.example.brassheroes.characters.Wizard;
import com.example.brassheroes.gamemechanics.Persistence;
import com.example.brassheroes.items.Armor;
import com.example.brassheroes.items.Equipment;
import com.example.brassheroes.items.Weapon;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgClass1, imgClass2, imgClass3;

    private EditText usernameTxt;

    private Button btnClass1;
    private Button btnClass2;
    private Button btnClass3;

    private GameEntity player;

    private ArrayList<Equipment> inventory;

    private String username;

    private boolean classPicked = false;

    Persistence persistence;

    private final int WIZARD_IMG_ID = R.id.imgWizard, KNIGHT_IMG_ID = R.id.imgKnight,
            PALADIN_IMG_ID = R.id.imgPaladin, START_GAME_BTN_ID = R.id.startGameBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        initControls();
    }

    public void initControls() {
        persistence = new Persistence(this);

        usernameTxt = findViewById(R.id.username);

        imgClass1 = findViewById(KNIGHT_IMG_ID);
        imgClass2 = findViewById(PALADIN_IMG_ID);
        imgClass3 = findViewById(WIZARD_IMG_ID);

        btnClass1 = findViewById(R.id.btnClass1);
        btnClass2 = findViewById(R.id.btnClass2);
        btnClass3 = findViewById(R.id.btnClass3);
        Button btnStartGame = findViewById(START_GAME_BTN_ID);

        imgClass1.setOnClickListener(this);
        imgClass2.setOnClickListener(this);
        imgClass3.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);

        inventory = new ArrayList<>();
        inventory.add(new Armor().addStartingArmor());
        inventory.add(new Weapon().addStartingWeapon());

    }

    public boolean check() {
        if (usernameTxt.length() == 0) {
            usernameTxt.setError("Enter your name!");
            return false;
        }
        username = usernameTxt.getText().toString().trim();
        return true;
    }

    public void newGame() {
        boolean correctInput = check();

        if (correctInput && classPicked) {
            player.setName(username);

            if (persistence.areGeneralDirs()) {
                persistence.saveInitalData(player);
                persistence.saveInitialData(inventory, username);
                Intent intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case KNIGHT_IMG_ID:
                selectView(imgClass1);
                frameView(btnClass1);

                unselectView(imgClass2);
                unselectView(btnClass2);

                unselectView(imgClass3);
                unselectView(btnClass3);

                classPicked = true;
                player = new Knight();
                break;

            case PALADIN_IMG_ID:
                selectView(imgClass2);
                frameView(btnClass2);

                unselectView(imgClass3);
                unselectView(btnClass3);

                unselectView(imgClass1);
                unselectView(btnClass1);

                classPicked = true;
                player = new Paladin();

                break;

            case WIZARD_IMG_ID:
                selectView(imgClass3);
                frameView(btnClass3);

                unselectView(imgClass1);
                unselectView(btnClass1);

                unselectView(imgClass2);
                unselectView(btnClass2);

                classPicked = true;
                player = new Wizard();
                break;

            case START_GAME_BTN_ID:
                newGame();
        }
    }

    public void selectView(View v) {
        v.setBackgroundResource(R.drawable.gold_selected);
    }

    public void frameView(View v) {
        v.setBackgroundResource(R.drawable.frame_selected);
    }

    public void unselectView(View v) {
        v.setBackgroundResource(R.drawable.btn_gradient);
    }
}