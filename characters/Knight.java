package com.example.brassheroes.characters;

import com.example.brassheroes.R;

public class Knight extends GameEntity {

    public Knight() {
        setProfession("knight");
        setDamageInc(14);
        setArmor(10);
        setArmorInc(7);
        setMaxHealth(115);
        setDamageType("physical");
        setPortrait(R.drawable.knight);
    }
}
