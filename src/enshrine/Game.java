/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import java.util.ArrayList;
import java.util.Random;

/**
 * Game represents the game state, so all the game data should be accessible here
 * 
 * @author dc_ca
 */
public class Game{

    public static final int BRANCHCOUNT=3, INITIAL_DISCIPLE_COUNT = 3, GAME_SIZE = 3000, STEPDISTANCE=20, DEFAULT_Y_POS = 200;
    
    //stats
    private int hp, maxHP;
    //saves
    private int index;
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> obtainedUpgrades = new ArrayList<>();
    private ArrayList<Entity> population = new ArrayList<>();
    //static
    private static ArrayList<Game> games = new ArrayList<>();
    private static Game currentGame;
    
    public Game(int i){//blank new game
        //stats
        hp = 5;
        maxHP = 5;
        
        index = i;
        
        for(int count = 0; count<BRANCHCOUNT; count++){
            obtainedUpgrades.add(new ArrayList<>());
        }
        
        //spawn disciples at random locs
        for(int j = 0; j<INITIAL_DISCIPLE_COUNT; j++){
            ArrayList<Integer> taken = new ArrayList<>();
            //taken.add(0);
            boolean picked = false;
            int randomNumber = 0, WIDTH = 75, wah = INITIAL_DISCIPLE_COUNT*WIDTH*3;
            while(!picked){
                picked = true;
                randomNumber = new Random().nextInt(wah) - wah/2;
                for(Integer l : taken){
                    if(l-WIDTH<randomNumber && randomNumber<l+WIDTH) picked = false;
                }
            }
            taken.add(randomNumber);
            population.add(new Entity(Entity.DISCIPLE, 1.0, 1.0, 1.0, 1, 1, 1, 1, (2950-(wah/2))+randomNumber));
        }
        
        games.add(this);
    }
    
    //getters
        //stats
    public int getHP(){ return hp;}
    public int getMaxHP(){ return maxHP;}
        //saves
    public int getIndex(){ return index;}
    public ArrayList<Building> getBuildings(){ return buildings;}
    public ArrayList<ArrayList<Boolean>> getObtainedUpgrades(){ return obtainedUpgrades;}
    public ArrayList<Entity> getPopulation(){ return population;}
        //static
    public static ArrayList<Game> getGames(){ return games;}
    public static Game getCurrentGame(){ return currentGame;}
    
    //setters
    public static void setCurrentGame(int i) throws IndexOutOfBoundsException{ currentGame = getGameByIndex(i);}
    public static void setCurrentGame(Game g){ currentGame = g;}
    
    //methods
    public static Game getGameByIndex(int i) throws IndexOutOfBoundsException{
        for(Game g : games){
            if(g.index==i) return g;
        }
        throw new IndexOutOfBoundsException();
    }
}
