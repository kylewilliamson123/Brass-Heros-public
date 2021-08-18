package com.example.brassheroes.characters;

import com.example.brassheroes.items.Equipment;

public class GameEntity {

    private final int HEALTH_PER_LV_INCREASE = 10;
    private final int EXP_NEEDED_INCREASE = 20;

    private String name;
    private String profession;
    private String damageType;
    private int baseDamage = 15;
    private int damageInc = 10;
    private int currentDamage;
    private int armor = 8;
    private int totalArmor;
    private int armorInc = 5;
    private int health = 100;
    private int maxHealth = 100;
    private int totalHealth;
    private int healthInc = 20;
    private int level = 1;
    private int exp = 0;
    private int expNeeded = 100;
    private int portrait;
    private int gameProgress = 0;

    private Equipment equippedArmor = null;
    private Equipment equippedWeapon = null;


    public GameEntity(
            String name,
            String profession,
            String damageType,
            int baseDamage,
            int damageInc,
            int currentDamage,
            int armor,
            int armorInc,
            int health,
            int maxHealth,
            int healthInc,
            int level,
            int exp,
            int expNeeded) {

        this.name = name;
        this.profession = profession;
        this.damageType = damageType;
        this.baseDamage = baseDamage;
        this.damageInc = damageInc;
        this.currentDamage = currentDamage;
        this.armor = armor;
        this.armorInc = armorInc;
        this.health = health;
        this.maxHealth = maxHealth;
        this.healthInc = healthInc;
        this.level = level;
        this.exp = exp;
        this.expNeeded = expNeeded;
    }


    public GameEntity() {
        this.currentDamage = getBaseDamage();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHealthInc() {
        return healthInc;
    }

    public void setHealthInc(int healthInc) {
        this.healthInc = healthInc;
    }

    public int getCurrentDamage() {
        return currentDamage;
    }

    public void setCurrentDamage(int currentDamage) {
        this.currentDamage = currentDamage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void levelUp() {
        //set damage
        setCurrentDamage(getCurrentDamage() + getDamageInc());
        // set exp needed for next level
        setExpNeeded(getExpNeeded() + EXP_NEEDED_INCREASE);
        //increase level by 1
        setLevel(getLevel() + 1);
        //set health
        setMaxHealth(getMaxHealth() + getHealthInc());
        //change health/level increase
        setHealthInc(getHealthInc() + HEALTH_PER_LV_INCREASE);
        //increase armor
        setArmor(getArmor() + getArmorInc());

    }

    //gain experience
    public void gainExp(int expAmt) {
        if (getExpNeeded() <= getExp()) {
            levelUp();
            setExp(0);
        } else {
            setExp(getExp() + expAmt);
            //check now if level up possible, if yes then level up
            if (getExpNeeded() <= getExp()) {
                levelUp();
                setExp(0);
            }
        }
    }

    public int getDamageInc() {
        return damageInc;
    }

    public void setDamageInc(int damageInc) {
        this.damageInc = damageInc;
    }

    public int getArmorInc() {
        return armorInc;
    }

    public void setArmorInc(int armorInc) {
        this.armorInc = armorInc;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\n\tProfession: " + getProfession() +
                "\n\tDamage Type: " + getDamageType() +
                "\n\tDamage: " + getCurrentDamage() +
                "\n\tArmor: " + getTotalArmor() +
                "\n\tHealth: " + getTotalHealth() +
                "\n\tLevel: " + getLevel()+
                "\n\tExp: "+getExp()+" / "+getExpNeeded()+
                "\n\tProgress: "+getGameProgress()+" / 100";
    }


    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }

    public int getGameProgress() {
        return gameProgress;
    }

    public void setGameProgress(int gameProgress) {
        this.gameProgress = gameProgress;
    }

    public Equipment getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Equipment equippedArmor) {
        this.equippedArmor = equippedArmor;
        setTotalHealth();
        setTotalArmor();
    }

    public boolean isArmorEquipped() {
        if (this.equippedArmor == null) {
            return false;
        } else return true;
    }

    public Equipment getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Equipment equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public boolean isWeaponEquipped() {
        if (this.equippedWeapon == null) {
            return false;
        } else return true;
    }

    public int getTotalHealth() {
        if (isArmorEquipped()){
            return totalHealth;
        }
        else return maxHealth;
    }

    public void setTotalHealth() {
        if (isArmorEquipped()) {
            this.totalHealth = maxHealth + equippedArmor.getHealthStat();
        } else this.totalHealth = maxHealth;
    }

    public int getTotalArmor() {
        if (isArmorEquipped()){
            return totalArmor;
        }else return armor;
    }

    public void setTotalArmor() {
        if (isArmorEquipped()){
            this.totalArmor = armor+equippedArmor.getArmorStat();
        }else this.totalArmor=armor;
    }
}
