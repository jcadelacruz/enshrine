/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import java.util.ArrayList;

/**
 *
 * @author dc_ca
 */
public class Game {
    public final int BRANCHCOUNT=3;
    
    private int index;
    private ArrayList<Building> buildings;
    private ArrayList<ArrayList<Boolean>> obtainedUpgrades;
    private static ArrayList<Game> games = new ArrayList<>();
    
    public Game(int i){//blank new game
        index = i;
        buildings = new ArrayList<>();
        
        obtainedUpgrades = new ArrayList<>();
        for(int count = 0; count<BRANCHCOUNT; count++){
            obtainedUpgrades.add(new ArrayList<>());
        }
        
        games.add(this);
    }
    
    public int getIndex(){ return index;}
    public ArrayList<Building> getBuildings(){ return buildings;}
    public ArrayList<ArrayList<Boolean>> getObtainedUpgrades(){ return obtainedUpgrades;}
    public static ArrayList<Game> getGames(){ return games;}
    
}
