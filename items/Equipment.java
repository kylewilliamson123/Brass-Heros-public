package com.example.brassheroes.items;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipment implements Parcelable {
    private String name ;
    private String type;
    private int damageStat;
    private int healthStat;
    private int armorStat;
    private int levelRequirement;
    private int icon;
    //later rarity and unique modifiers

    public Equipment(String name, int damageStat, int healthStat, int armorStat, int levelRequirement) {
        this.name = name;
        this.damageStat = damageStat;
        this.healthStat = healthStat;
        this.armorStat = armorStat;
        this.levelRequirement = levelRequirement;
    }

    public Equipment() {
    }

    public Equipment(Parcel in) {
        name = in.readString();
        type = in.readString();
        damageStat = in.readInt();
        healthStat = in.readInt();
        armorStat = in.readInt();
        levelRequirement = in.readInt();
        icon = in.readInt();
    }

    public static final Creator<Equipment> CREATOR = new Creator<Equipment>() {
        @Override
        public Equipment createFromParcel(Parcel in) {
            return new Equipment(in);
        }

        @Override
        public Equipment[] newArray(int size) {
            return new Equipment[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamageStat() {
        return damageStat;
    }

    public void setDamageStat(int damageStat) {
        this.damageStat = damageStat;
    }

    public int getHealthStat() {
        return healthStat;
    }

    public void setHealthStat(int healthStat) {
        this.healthStat = healthStat;
    }

    public int getArmorStat() {
        return armorStat;
    }

    public void setArmorStat(int armorStat) {
        this.armorStat = armorStat;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "" + name +
                "\nDamage: " + damageStat +
                " Health: " + healthStat +
                "\nArmor: " + armorStat +
                " Req. level: " + levelRequirement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(damageStat);
        dest.writeInt(healthStat);
        dest.writeInt(armorStat);
        dest.writeInt(levelRequirement);
        dest.writeInt(icon);
    }
}
