package com.example.brassheroes.characters;

import com.example.brassheroes.gamemechanics.RNG;

public class Enemy extends GameEntity {
    public Enemy() {
        setDamageType(RNG.randomDamageType());
        setName(RNG.randomName());
        setPortrait(RNG.randomEnemyPortrait());
        setArmor(8);
       // System.out.println("enemy starting armor:"+getArmor());
        setBaseDamage(14);
       // System.out.println("enemy starting damage:"+getBaseDamage());
        if (getDamageType().equals("mental")){
            setProfession("Paladin");
        }
        if (getDamageType().equals("magic")){
            setProfession("Wizard");
        }
        if (getDamageType().equals("physical")){
            setProfession("Knight");
        }
    }


    public String printClass(){
        return "the Fallen "+getProfession();
    }
}
