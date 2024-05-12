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
public class Game extends Entity{

    public final int BRANCHCOUNT=3;
    
    private int index;
    private ArrayList<Building> buildings;
    private ArrayList<ArrayList<Boolean>> obtainedUpgrades;
    private static ArrayList<Game> games = new ArrayList<>();
    private static Game user;
    
    public Game(int i){//blank new game
        super(1.0, 1.0, 1.0, 1.0, 1.0, 1);
        index = i;
        buildings = new ArrayList<>();
        
        obtainedUpgrades = new ArrayList<>();
        for(int count = 0; count<BRANCHCOUNT; count++){
            obtainedUpgrades.add(new ArrayList<>());
        }
        
        games.add(this);
    }
    
    //getters
    public int getIndex(){ return index;}
    public ArrayList<Building> getBuildings(){ return buildings;}
    public ArrayList<ArrayList<Boolean>> getObtainedUpgrades(){ return obtainedUpgrades;}
        //static
    public static ArrayList<Game> getGames(){ return games;}
    public static Game getUser(){ return user;}
    
    //setters
    public static void setUser(int i){ user = games.get(i);}
    
    //methods
}
