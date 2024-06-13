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
        ArrayList<Integer> taken = new ArrayList<>();
        for(int j = 0; j<INITIAL_DISCIPLE_COUNT; j++){
            boolean picked = false;
            int randomNumber = 0, WIDTH = 75, wah = INITIAL_DISCIPLE_COUNT*WIDTH*3;
            while(!picked){
                picked = true;
                randomNumber = new Random().nextInt(wah);
                //System.out.println("attempting rand num: "+randomNumber);
                for(int l : taken){
                    //System.out.println(" checking: "+randomNumber+" with: "+l);
                    if(l<randomNumber && randomNumber<l+WIDTH) picked = false;
                    if(randomNumber<l && l<randomNumber+WIDTH) picked = false;
                }
            }
            taken.add(randomNumber);
            //System.out.println("rand num: "+randomNumber);
            Entity d = new Entity(Entity.DISCIPLE, 1.0, 1.0, 1.0, 1, 1, 1, 1, (Game.GAME_SIZE-(wah+150))+randomNumber);
            d.setTarget(Building.getByIndex(0));
            population.add(d);
        }
        //TEST spawn opponent
        Entity d = new Entity(Entity.ENEMY, 1.0, 1.0, 1.0, 1, 1, 1, 1, 1000);
        d.setTarget(Building.getByIndex(1));
        population.add(d);
        
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
    public void addHP(int h){
        int health = hp + h;
        if(health>maxHP) health = maxHP;
        if(health<0) health = 0;
        hp = health;
    }
    public void addMaxHP(int h){
        maxHP += h;
        addHP(0);
    }
    public static Game getGameByIndex(int i) throws IndexOutOfBoundsException{
        for(Game g : games){
            if(g.index==i) return g;
        }
        throw new IndexOutOfBoundsException();
    }
}
