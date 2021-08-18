package com.example.brassheroes.gamemechanics;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.brassheroes.R;
import com.example.brassheroes.characters.Boss;
import com.example.brassheroes.characters.Enemy;
import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.items.Equipment;
import com.example.brassheroes.main.StoryActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class FightEngine {
    private GameEntity mPlayer;
    private ArrayList<Equipment> mInventory;
    private Enemy mEnemy;
    private Context mContext;
    private Persistence persistence;
    private boolean isBossFight = false;
    Equipment[] drops;

    private final double WEAPON_DROP_CHANCE = 0.35;
    private final double ARMOR_DROP_CHANCE = 0.35;
    private final double BOSS_WEAPON_CHANCE = 0.45;
    private final double BOSS_ARMOR_CHANCE = 0.45;
    private final int LOW_BOUND_EXP = 30;
    private final int HIGH_BOUND_EXP = 60;
    private final int GAME_PROGRESS_AMOUNT = 10;
    private final int BOSS_LEVEL_ADVANTAGE = 1;

    private int bossLevel, healthReturnValue;


    public FightEngine(Context context) {
        //1. get context
        this.mContext = context;
        //2.create playerManager object
        persistence = new Persistence(mContext);
        //3. get saved data
        this.mPlayer = persistence.getSavedGame();
        this.mInventory = persistence.getSavedInventory();
        this.bossLevel = mPlayer.getLevel() + BOSS_LEVEL_ADVANTAGE;
        //4. spawn the enemy
        spawnEnemy();

        //5. initialize the health to max values before fight
        mPlayer.setHealth(mPlayer.getTotalHealth());
        mEnemy.setHealth(mEnemy.getTotalHealth());
    }

    private void spawnEnemy() {
        if (mPlayer.getGameProgress() >= 100) {
            isBossFight = true;
            mPlayer.setGameProgress(0);
            spawnBoss();
        } else {
            this.mEnemy = new Enemy();
            for (int i = 1; i < mPlayer.getLevel(); i++) {
                mEnemy.gainExp(mEnemy.getExpNeeded());
            }
        }
    }

    private void spawnBoss() {
        this.mEnemy = new Boss(bossLevel);
        if (RNG.randomNumber() <= BOSS_ARMOR_CHANCE) {
            mEnemy.setEquippedArmor(RNG.randomArmor(bossLevel));
        }
        if (RNG.randomNumber() <= BOSS_WEAPON_CHANCE) {
            mEnemy.setEquippedArmor(RNG.randomWeapon(bossLevel));
        }
    }

    public void attackMove(boolean isPlayer) {
        //check who makes the attack move
        if (isPlayer) {
            playerAttack();
        } else if (!isPlayer) {
            enemyAttack();
        }
    }

    //player attacks, return health of enemy
    private void playerAttack() {
        receiveDamage(mEnemy, mPlayer);
        healthReturnValue = mEnemy.getHealth();
        //check if enemy has 0 health
        if (mEnemy.getHealth() <= 0) {
            winResult();
        }
    }

    //enemy attacks, return health of player after attack
    private void enemyAttack() {
        receiveDamage(mPlayer, mEnemy);
        healthReturnValue = mPlayer.getHealth();
        if (mPlayer.getHealth() <= 0) {
            Toast.makeText(mContext, "you lost!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, StoryActivity.class);
            intent.putExtra("won", false);
            mContext.startActivity(intent);
        }
    }

    public int getHealthReturnValue() {
        return healthReturnValue;
    }

    //results of the fight
    public void winResult() {
        //gain experience
        int expGained = RNG.randomNumber(HIGH_BOUND_EXP, LOW_BOUND_EXP);
        mPlayer.gainExp(expGained);
        //restore health to full after the fight
        mPlayer.setHealth(mPlayer.getTotalHealth());
        //roll item drop
        int drop = rollItemDrop();
        System.out.println("drops" + Arrays.toString(drops));
        //set story progress
        mPlayer.setGameProgress(mPlayer.getGameProgress() + GAME_PROGRESS_AMOUNT);
        //save the data
        persistence.saveData(mPlayer);
        persistence.saveData(mInventory);

        Toast.makeText(mContext, mContext.getString(R.string.win_title), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, StoryActivity.class);
        if (drop == 2) {
            intent.putExtra("drop", 2);
            intent.putExtra("drop1", drops[0]);
            intent.putExtra("drop2", drops[1]);
        } else if (drop == 1) {
            intent.putExtra("drop", 1);
            intent.putExtra("drop1", drops[0]);
        } else intent.putExtra("drop", 0);

        if (isBossFight) {
            intent.putExtra("isBossFight", true);
        }

        intent.putExtra("won", true);
        intent.putExtra("expGained", expGained);


        mContext.startActivity(intent);
    }

    public int rollItemDrop() {
        drops = new Equipment[2];
        boolean first = rollWeapon(WEAPON_DROP_CHANCE);
        boolean second = rollArmor(ARMOR_DROP_CHANCE);
        if (first && second) {
            drops[0] = mInventory.get(mInventory.size() - 1);
            drops[1] = mInventory.get(mInventory.size() - 2);
            return 2;
        } else if (first || second) {
            drops[0] = mInventory.get(mInventory.size() - 1);
            return 1;
        } else return 0;
    }

    private boolean rollWeapon(double chance) {
        if (RNG.randomNumber() <= chance) {
            mInventory.add(RNG.randomWeapon(mPlayer.getLevel()));
            return true;
        }
        return false;
    }

    private boolean rollArmor(double chance) {
        if (RNG.randomNumber() <= chance) {
            mInventory.add(RNG.randomArmor(mPlayer.getLevel()));
            return true;
        }
        return false;
    }

    public int getEnemyPortrait() {
        return mEnemy.getPortrait();
    }

    public String getEnemyName() {
        return mEnemy.getName();
    }

    public int getEnemyTotalHealth() {
        return mEnemy.getTotalHealth();
    }

    public String printEnemyClass() {
        return mEnemy.printClass();
    }

    //calculate the damage taken
    private int damageCalculation(int enemyDamage, int targetArmor) {
        //if damage - armor would be  lower or equal  10, damage will be 70% effective
        // eg. damage: 100, armor 90, health 100
        //100-90<= 10 so --> 100*0.7=70 ---> 70-90=20 >10 so  damage dealt will be 70 instead of 100
        if ((enemyDamage - targetArmor <= 10)) {
            int damageResult = (int) (enemyDamage * 0.7);
            return damageResult;
        } else {
            //if damage-armor> 10
            int damageResult = (enemyDamage - targetArmor);
            return damageResult;
        }

    }

    public void receiveDamage(GameEntity target, GameEntity attacker) {
        //case attacker has weapon target has armor equipped
        if (target.isArmorEquipped() && attacker.isWeaponEquipped()) {
            //calculate total values
            int totalDamage = attacker.getCurrentDamage() + attacker.getEquippedWeapon().getDamageStat();
            int totalArmor = target.getTotalArmor();
            int totalHealth = target.getHealth();
            // System.out.println("damage target has armor and attacker has weapon: " + damageCalculation(totalDamage, totalArmor));
            target.setHealth(totalHealth - damageCalculation(totalDamage, totalArmor));
        } else
            //case attacker has weapon
            if (attacker.isWeaponEquipped()) {
                int totalDamage = attacker.getCurrentDamage() + attacker.getEquippedWeapon().getDamageStat();
                int totalArmor = target.getTotalArmor();
                int totalHealth = target.getHealth();
                target.setHealth(totalHealth - damageCalculation(totalDamage, totalArmor));
                //System.out.println("damage attacker weapon equipped: " + damageCalculation(totalDamage, totalArmor));
            } else {
                //other cases
                int totalDamage = attacker.getCurrentDamage();
                int totalArmor = target.getTotalArmor();
                int totalHealth = target.getHealth();
                target.setHealth(totalHealth - damageCalculation(totalDamage, totalArmor));
                //System.out.println("damage none equipped: " + damageCalculation(totalDamage, totalArmor));
            }
    }
}
