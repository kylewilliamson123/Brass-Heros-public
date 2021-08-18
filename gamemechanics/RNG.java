package com.example.brassheroes.gamemechanics;

import com.example.brassheroes.items.Armor;
import com.example.brassheroes.items.Equipment;
import com.example.brassheroes.items.Weapon;

import java.util.Random;

import static com.example.brassheroes.R.drawable.armor;
import static com.example.brassheroes.R.drawable.armor1;
import static com.example.brassheroes.R.drawable.armor2;
import static com.example.brassheroes.R.drawable.armor3;
import static com.example.brassheroes.R.drawable.armor5;
import static com.example.brassheroes.R.drawable.armor6;
import static com.example.brassheroes.R.drawable.armor7;
import static com.example.brassheroes.R.drawable.armor8;
import static com.example.brassheroes.R.drawable.chess_bishop;
import static com.example.brassheroes.R.drawable.chess_black_bishop;
import static com.example.brassheroes.R.drawable.chess_black_horse;
import static com.example.brassheroes.R.drawable.chess_black_king;
import static com.example.brassheroes.R.drawable.chess_black_queen;
import static com.example.brassheroes.R.drawable.chess_black_rook;
import static com.example.brassheroes.R.drawable.chess_horse;
import static com.example.brassheroes.R.drawable.chess_king;
import static com.example.brassheroes.R.drawable.chess_queen;
import static com.example.brassheroes.R.drawable.chess_rook;
import static com.example.brassheroes.R.drawable.enemy1;
import static com.example.brassheroes.R.drawable.enemy2;
import static com.example.brassheroes.R.drawable.enemy3;
import static com.example.brassheroes.R.drawable.enemy4;
import static com.example.brassheroes.R.drawable.enemy5;
import static com.example.brassheroes.R.drawable.map_0;
import static com.example.brassheroes.R.drawable.map_1;
import static com.example.brassheroes.R.drawable.map_10;
import static com.example.brassheroes.R.drawable.map_2;
import static com.example.brassheroes.R.drawable.map_3;
import static com.example.brassheroes.R.drawable.map_4;
import static com.example.brassheroes.R.drawable.map_5;
import static com.example.brassheroes.R.drawable.map_6;
import static com.example.brassheroes.R.drawable.map_7;
import static com.example.brassheroes.R.drawable.map_8;
import static com.example.brassheroes.R.drawable.map_9;
import static com.example.brassheroes.R.drawable.sword;
import static com.example.brassheroes.R.drawable.sword1;
import static com.example.brassheroes.R.drawable.sword2;
import static com.example.brassheroes.R.drawable.sword3;
import static com.example.brassheroes.R.drawable.sword4;
import static com.example.brassheroes.R.drawable.sword5;


public class RNG {
    private static final String[] damageTypes = {"magic", "mental", "physical"};

    private static final String[] firstNames =
            {"Erwin", "Niccolo", "Leo", "Fyodor", "Friedrich", "Arthur", "Wolfgang"};

    private static final String[] lastNames =
            {"Schroedinger", "Machiavelli", "Tolstoy", "Dostoyevsky",
                    "Nietzsche", "Schopenhauer", "von Goethe"};
    private static final String[] bossNames = {
            "Grandmaster Bobby Fischer", "Grandmaster Magnus Carlsen", "Grandmaster Garry Kasparov",
            "Grandmaster Jose Capablanca", "Grandmaster Hikaru Nakamura", "Grandmaster Boris Spassky",
            "Grandmaster Elizabeth Harmon", "Grandmaster Judith Polgar"
    };


    //list of available swords
    private static final Equipment[] weapons = {
            new Weapon("Longsword", 8, 0, 0, 2),
            new Weapon("Shortsword", 4, 0, 0, 1),
            new Weapon("Sword of fire", 13, 0, 5, 3),
            new Weapon("Infinity's Edge", 15, 0, 0, 4),
            new Weapon("Sword of the Night", 18, 0, 0, 5),
            new Weapon("Light's Bane", 22, 0, 0, 7),
            new Weapon("Lion Sin sword", 27, 0, 0, 8),
            new Weapon("Dragon Sin Sword", 35, 0, 0, 9),
            new Weapon("Golden Sword", 40, 0, 0, 10),
            new Weapon("Tungsten Sword", 55, 0, 0, 12),
            new Weapon("Cactus Sword", 60, 20, 15, 14),
            new Weapon("Magic Sword", 85, 0, 0, 17),
            new Weapon("Arcane Sword", 65, 0, 0, 12),
            new Weapon("Mythril Sword", 80, 0, 0, 15),
            new Weapon("Hallowed Sword", 90, 0, 0, 19),};

    //list of available armors
    private static final Equipment[] armors = {
            new Armor("Plate Armor", 0, 0, 10, 1),
            new Armor("Chainmail", 0, 5, 5, 1),

            new Armor("Magic Robe", 0, 0, 15, 3),
            new Armor("Spirit Visage", 0, 10, 10, 3),

            new Armor("Wooden Armor", 0, 5, 20, 5),
            new Armor("Gold Armor", 0, 15, 15, 5),

            new Armor("Silver Armor", 0, 10, 25, 7),
            new Armor("Titanium Armor", 0, 20, 20, 7),

            new Armor("Palladium Armor", 0, 15, 30, 9),
            new Armor("Tungsten Armor", 0, 25, 25, 9),

            new Armor("Cactus Armor", 0, 20, 35, 11),
            new Armor("Molten Armor", 0, 30, 30, 11),

            new Armor("Demonite Armor", 0, 25, 40, 13),
            new Armor("Cobalt Armor", 0, 35, 35, 13),

            new Armor("Mythril Armor", 0, 30, 45, 15),
            new Armor("Shroomite Armor", 0, 40, 40, 15),

            new Armor("Iron Armor", 0, 35, 50, 17),
            new Armor("Lead Armor", 0, 45, 45, 17),

            new Armor("Chlorophyte Armor", 0, 40, 55, 19),
            new Armor("Arcane Armor", 0, 50, 50, 19),

            new Armor("Dark Armor", 0, 45, 60, 21),
            new Armor("Light Armor", 0, 55, 55, 21)
    };


    private static final int[] enemyPictures = new int[]{enemy1, enemy2, enemy3, enemy4, enemy5};

    private static final int[] bossPictures = new int[]{chess_bishop, chess_horse, chess_king, chess_queen,
            chess_rook, chess_black_bishop, chess_black_horse, chess_black_king, chess_black_queen, chess_black_rook};

    private static final int[] weaponIcons = new int[]{sword, sword1, sword2, sword3, sword4, sword5};

    private static final int[] armorIcons = new int[]{armor, armor1, armor2, armor3, armor5, armor6, armor7, armor8};

    private static final int[] playerStoryMaps = new int[]{map_0, map_1, map_2, map_3, map_4, map_5, map_6, map_7, map_8, map_9, map_10};

    public static String randomName() {
        return firstNames[randomNumber(firstNames.length, 0)] + " "
                + lastNames[randomNumber(lastNames.length, 0)];
    }

    public static String randomBossName() {
        return bossNames[randomNumber(bossNames.length, 0)];
    }

    public static int randomNumber(int end, int start) {
        Random r = new Random();
        return r.nextInt(end - start) + start;
    }

    public static double randomNumber() {
        Random r = new Random();
        return r.nextDouble();
    }

    public static int getMap(int storyPoint) {
        System.out.println();
        return playerStoryMaps[storyPoint];
    }

    public static int randomEnemyPortrait() {
        return enemyPictures[randomNumber(enemyPictures.length, 0)];
    }

    public static int randomBossPortrait() {
        return bossPictures[randomNumber(bossPictures.length, 0)];
    }

    public static String randomDamageType() {
        return damageTypes[randomNumber(damageTypes.length, 0)];
    }

    public static Equipment randomWeapon(int playersLevel) {
        boolean shouldRepeat = true;
        Equipment temp = weapons[0];
        while (shouldRepeat) {
            temp = weapons[randomNumber(weapons.length, 0)];
            if (temp.getLevelRequirement() <= playersLevel) {
                shouldRepeat = false;
            }
        }
        temp.setIcon(randomWeaponIcon());
        return temp;
    }


    public static Equipment randomArmor(int playersLevel) {
        boolean shouldRepeat = true;
        Equipment temp = armors[0];
        while (shouldRepeat) {
            temp = armors[randomNumber(armors.length, 0)];
            if (temp.getLevelRequirement() <= playersLevel) {
                shouldRepeat = false;
            }
        }
        temp.setIcon(randomArmorIcon());
        return temp;
    }

    public static int randomArmorIcon() {
        return armorIcons[randomNumber(armorIcons.length, 0)];
    }

    public static int randomWeaponIcon() {
        return weaponIcons[randomNumber(weaponIcons.length, 0)];
    }


}
