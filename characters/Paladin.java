package com.example.brassheroes.characters;

import com.example.brassheroes.R;

public class Paladin extends GameEntity {

    public Paladin() {
        setProfession("paladin");
        setMaxHealth(125);
        setHealthInc(25);
        setArmor(15);
        setArmorInc(9);
        setDamageType("mental");
        setPortrait(R.drawable.paladin);

    }
}
