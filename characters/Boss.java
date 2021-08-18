package com.example.brassheroes.characters;

import com.example.brassheroes.gamemechanics.RNG;

public class Boss extends Enemy {
    private final int BOSS_LEVEL;

    public Boss(int bossLevel) {
        this.BOSS_LEVEL = bossLevel;
        setArmor(12);
        setBaseDamage(18);
        setName(RNG.randomBossName());
        setPortrait(RNG.randomBossPortrait());
        scaleLevel();
        System.out.println("boss stats: " + toString());
    }

    private void scaleLevel() {
        for (int i = 1; i < BOSS_LEVEL; i++) {
            levelUp();
        }
    }
}
