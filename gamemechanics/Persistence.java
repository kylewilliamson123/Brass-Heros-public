package com.example.brassheroes.gamemechanics;

import android.content.Context;

import com.example.brassheroes.characters.GameEntity;
import com.example.brassheroes.items.Equipment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Persistence {
    private Context mContext;
    //main save directories
    private File GENERAL_GAME_SAVE_DIR;
    private File GENERAL_INVENTORY_SAVE_DIR;

    private File GAME_SAVE_DIR, INVENTORY_SAVE_DIR;
    private File[] savedGameFiles, inventoryFiles;

    public Persistence(Context context) {
        this.mContext = context;
        createSavingDirs();
    }

    private void createSavingDirs() {
        //if directories exist list the files and get paths for saving
        try {
            GENERAL_GAME_SAVE_DIR = new File(mContext.getFilesDir(), "savedGames");
            GENERAL_INVENTORY_SAVE_DIR = new File(mContext.getFilesDir(), "savedInventory");
            //list files in main directories
            savedGameFiles = GENERAL_GAME_SAVE_DIR.listFiles();   //files in save dir
            inventoryFiles = GENERAL_INVENTORY_SAVE_DIR.listFiles();  // files inventory dir
            //corresponding files for saving the player and inventory
            // are the first files in the list in each general directory
            if (savedGameExists()) {
                GAME_SAVE_DIR = new File(GENERAL_GAME_SAVE_DIR, savedGameFiles[0].getName());
            }
            if (savedInventoryExists()) {
                INVENTORY_SAVE_DIR = new File(GENERAL_INVENTORY_SAVE_DIR, inventoryFiles[0].getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //overloaded functions for general data saving
    public void saveData(GameEntity player) {
        Gson gson = new Gson();
        String saveJson = gson.toJson(player);
        try {
            FileWriter fileWriter = new FileWriter(GAME_SAVE_DIR);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(saveJson);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData(ArrayList<Equipment> equipment) {
        Gson gson = new Gson();
        String saveJson = gson.toJson(equipment);
        try {
            FileWriter fileWriter = new FileWriter(INVENTORY_SAVE_DIR);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(saveJson);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //overloaded functions for saving data the first time game is started
    public void saveInitalData(GameEntity player) {
        Gson gson = new Gson();
        String saveJson = gson.toJson(player);
        try {
            File playerSave = new File(new File(mContext.getFilesDir(), "savedGames"), "Saved-" + player.getName());
            FileWriter fileWriter = new FileWriter(playerSave);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(saveJson);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveInitialData(ArrayList<Equipment> equipment, String username) {
        Gson gson = new Gson();
        String saveJson = gson.toJson(equipment);
        try {
            File inventorySave = new File(new File(mContext.getFilesDir(), "savedInventory"), "Inventory-" + username);
            FileWriter fileWriter = new FileWriter(inventorySave);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(saveJson);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Equipment> getSavedInventory() {
        try {
            FileReader fileReader = new FileReader(INVENTORY_SAVE_DIR);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = bufferedReader.readLine();
            //close readers
            bufferedReader.close();
            fileReader.close();

            Gson gson = new Gson();

            Type collectionType = new TypeToken<ArrayList<Equipment>>() {
            }.getType();

            return gson.fromJson(data, collectionType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GameEntity getSavedGame() {
        //start file reader  and buffered reader
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(GAME_SAVE_DIR);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //data from file to string
            String data = bufferedReader.readLine();
            //close readers
            bufferedReader.close();
            fileReader.close();
            return gson.fromJson(data, GameEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean areGeneralDirs() {
        if (GENERAL_GAME_SAVE_DIR.isDirectory() && GENERAL_INVENTORY_SAVE_DIR.isDirectory()) {
            return true;
        } else return false;
    }

    public boolean savesBothExist() {
        if (savedGameExists() && savedInventoryExists()) {
            return true;
        } else return false;
    }

    private boolean savedGameExists() {
        if (savedGameFiles.length != 0) {
            return true;
        } else return false;
    }

    private boolean savedInventoryExists() {
        if (inventoryFiles.length != 0) {
            return true;
        } else return false;
    }

    public void deleteSavedGames() {
        for (File tempFile : GENERAL_GAME_SAVE_DIR.listFiles()) {
            tempFile.delete();
        }
    }

    public void deleteSavedInventory() {
        for (File tempFile : GENERAL_INVENTORY_SAVE_DIR.listFiles()) {
            tempFile.delete();
        }
    }

    public void createGeneralDirs() {
        GENERAL_GAME_SAVE_DIR.mkdir();
        GENERAL_INVENTORY_SAVE_DIR.mkdir();
    }
}