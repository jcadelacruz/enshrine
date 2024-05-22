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

    public static final int BRANCHCOUNT=3, INITIAL_DISCIPLE_COUNT = 8, GAME_SIZE = 3000, STEPDISTANCE=4;
    
    private int index;
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> obtainedUpgrades = new ArrayList<>();
    private ArrayList<Entity> population = new ArrayList<>();
    private static ArrayList<Game> games = new ArrayList<>();
    private static Game user;
    
    public Game(int i){//blank new game
        super(Entity.USER, 1.0, 1.0, 1.0, 1.0, 1.0, 1);
        index = i;
        
        for(int count = 0; count<BRANCHCOUNT; count++){
            obtainedUpgrades.add(new ArrayList<>());
        }
        
        for(int j = 0; j<INITIAL_DISCIPLE_COUNT; j++) population.add(new Entity(Entity.DISCIPLE, 1.0, 1.0, 1.0, 1.0, 1.0, 1));
        
        games.add(this);
    }
    
    //getters
    public int getIndex(){ return index;}
    public ArrayList<Building> getBuildings(){ return buildings;}
    public ArrayList<ArrayList<Boolean>> getObtainedUpgrades(){ return obtainedUpgrades;}
    public ArrayList<Entity> getPopulation(){ return population;}
        //static
    public static ArrayList<Game> getGames(){ return games;}
    public static Game getUser(){ return user;}
    
    //setters
    public static void setUser(int i) throws IndexOutOfBoundsException{ user = getGameByIndex(i);}
    public static void setUser(Game g){ user = g;}
    
    //methods
    public static Game getGameByIndex(int i) throws IndexOutOfBoundsException{
        for(Game g : games){
            if(g.index==i) return g;
        }
        throw new IndexOutOfBoundsException();
    }
}
