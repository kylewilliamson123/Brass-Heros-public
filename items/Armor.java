package com.example.brassheroes.items;

import com.example.brassheroes.R;

public class Armor extends Equipment {

    public Armor() {
    }

    public Armor(String name, int damageStat, int healthStat, int armorStat, int levelRequirement) {
        super(name, damageStat, healthStat, armorStat, levelRequirement);
        super.setType("armor");
    }

    public Armor addStartingArmor(){
        Armor temp = new  Armor("manly boxers",0,1,1,0);
        temp.setIcon(R.drawable.starting_armor);
        return temp;
    }
}
