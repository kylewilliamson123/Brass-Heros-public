package com.example.brassheroes.gamemechanics;

import android.content.Context;

import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.items.Equipment;

import java.util.ArrayList;

public class PlayerManager {
    private GameEntity mPlayer;
    private Persistence persistence;
    private ArrayList<Equipment> mInventory;
    private final int MAX_GAME_PROGRESS = 100;
    private final int STORY_INCREMENT =10;

    public PlayerManager(Context context) {
        persistence = new Persistence(context);
        this.mPlayer = persistence.getSavedGame();
        this.mInventory = persistence.getSavedInventory();
    }

    public int getTotalHealth(){
        return this.mPlayer.getTotalHealth();
    }

    public ArrayList<Equipment> getInventory() {
        return this.mInventory;
    }

    public String printPlayer() {
        return mPlayer.toString();
    }

    public int updateMap() {
        int storyPoint = mPlayer.getGameProgress() / STORY_INCREMENT;
        return RNG.getMap(storyPoint);
    }

    public int getMaxGameProgress() {
        return MAX_GAME_PROGRESS;
    }

    public int getGameProgress() {
        return mPlayer.getGameProgress();
    }

    public int getExpNeeded() {
        return mPlayer.getExpNeeded();
    }

    public int getExp() {
        return mPlayer.getExp();
    }

    public String getName() {
        return mPlayer.getName();
    }

    public int getPortrait() {
        return mPlayer.getPortrait();
    }

    public void equip(Equipment equipment) {
        String s = equipment.getType();
        //case armor was clicked
        if (s.equals("armor")) {
            if (mPlayer.isArmorEquipped()) {
                mPlayer.setEquippedArmor(null);
            }
            mPlayer.setEquippedArmor(equipment);
            persistence.saveData(mPlayer);
        }
        //case weapon was clicked
        if (s.equals("weapon")) {
            if (mPlayer.isWeaponEquipped()) {
                mPlayer.setEquippedWeapon(null);
            }
            mPlayer.setEquippedWeapon(equipment);
            persistence.saveData(mPlayer);

        }

    }

}
