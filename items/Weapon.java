package com.example.brassheroes.items;

import com.example.brassheroes.R;

public class Weapon extends Equipment {
    public Weapon() {
    }

    public Weapon(String name, int damageStat, int healthStat, int armorStat, int levelRequirement) {
        super(name, damageStat, healthStat, armorStat, levelRequirement);
        super.setType("weapon");
    }

    public Weapon addStartingWeapon(){
        Weapon temp = new Weapon("shit on a stick",1,0,0,0);
        temp.setIcon(R.drawable.starting_sword);
        return temp;
    }

}

