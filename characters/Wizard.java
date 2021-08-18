package com.example.brassheroes.characters;

import com.example.brassheroes.R;

public class Wizard extends GameEntity {

    public Wizard() {
        setProfession("wizard");
        setBaseDamage(20);
        setDamageInc(20);
        setHealthInc(15);
        setDamageType("magic");
        setPortrait(R.drawable.wizard);
    }
}
