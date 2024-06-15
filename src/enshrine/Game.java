/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import static enshrine.Entity.FOOD;
import static enshrine.Entity.IRON;
import static enshrine.Entity.WOOD;
import exceptions.ItemNotInInventoryException;
import exceptions.NotAffordableException;
import exceptions.OutOfResourceCapacityBoundsException;
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
    //inventory
        //inv-items
    protected ArrayList<Item> inventory = new ArrayList<>();//Inventory contains all items possessed by the entity
    //private Item itemAttemptingToCraft = null, equippedArmor = null, equippedWeapon = null;//stats are added upon equip and de-equip
        //resources
    protected int woodCnt, ironCnt, foodCnt, capacity = 100;//STANDARD
    
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
            d.setTarget(Building.getByIndex(Enshrine.fightArea));
            population.add(d);
        }
        //TEST spawn opponent
        Entity d = new Entity(Entity.ENEMY, 1.0, 1.0, 1.0, 1, 1, 1, 1, 1000);
        d.setTarget(Building.getByIndex(Enshrine.campFir));
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
    //inventory
        //resources
    public int[] getMaterialCnts(){
        int[] res = {woodCnt, ironCnt, foodCnt};
        return res;
    }
    public int getCapacity(){ return capacity;}
    
        //static
    public static ArrayList<Game> getGames(){ return games;}
    public static Game getCurrentGame(){ return currentGame;}
    
    //setters
    public static void setCurrentGame(int i) throws IndexOutOfBoundsException{ currentGame = getGameByIndex(i);}
    public static void setCurrentGame(Game g){ currentGame = g;}
    
    //methods
        //stats
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
        //resources
    public void attemptAddMaterials(int[] m) throws NotAffordableException, OutOfResourceCapacityBoundsException{
        int failAt = 0;
        try{
            for(int i = 0; i<3; i++){
                attemptAddMaterials(m[i], i);
                failAt++;
            }
        }
        catch(NotAffordableException e){
            for(int i = 0; i<=failAt; i++){
                attemptAddMaterials(-m[i], i);
            }
            throw new NotAffordableException();
        }
        catch(OutOfResourceCapacityBoundsException e){
            for(int i = 0; i<=failAt; i++){
                attemptAddMaterials(-m[i], i);
            }
            throw new OutOfResourceCapacityBoundsException();
        }
    }
    public void attemptAddMaterials(int count, int i) throws NotAffordableException, OutOfResourceCapacityBoundsException{
        //convert
        int index = i;//convert section was made when public indices of resources wasn't 0, 1, and 2
        //check for exceptions
        if(!canAfford(count, index)) throw new NotAffordableException();
        if(!withinCapacity(count, index)) throw new OutOfResourceCapacityBoundsException();
        //add
        switch(index){
            case Entity.WOOD:
                woodCnt+=count;
                break;
            case Entity.IRON:
                ironCnt+=count;
                break;
            case Entity.FOOD:
                foodCnt+=count;
                break;
            default:
                System.out.println("Error in Entity class, attemptAddMaterials(): lol what even u tryna do"+WOOD+IRON+FOOD);
        }
    }
    public int[] addMaterialsUntilAble(int[] m){
        int res[] = new int[3];
        for(int i = 0; i<3; i++){
            res[i] = addMaterialsUntilAble(m[i], i);
        }
        return res;
    }
    public int addMaterialsUntilAble(int count, int index){
        int m=0;
        //add
        switch(index){
            case Entity.WOOD:
                woodCnt+=count;
                m = woodCnt-capacity;
                if(woodCnt>capacity) woodCnt=capacity;
                return m;
            case Entity.IRON:
                ironCnt+=count;
                m = ironCnt-capacity;
                if(ironCnt>capacity) ironCnt=capacity;
                return m;
            case Entity.FOOD:
                foodCnt+=count;
                m = foodCnt-capacity;
                if(foodCnt>capacity) foodCnt=capacity;
                return m;
            default:
                System.out.println("Error in Entity class, attemptAddMaterials(): lol what even u tryna do"+WOOD+IRON+FOOD);
        }
        return m;
    }
    public boolean canAfford(int count, int index){
        boolean affordable = false;
        switch(index){
            case Entity.WOOD:
                if(-count<=woodCnt) affordable = true;
                break;
            case Entity.IRON:
                if(-count<=ironCnt) affordable = true;
                break;
            case Entity.FOOD:
                if(-count<=foodCnt) affordable = true;
                break;
            default:
                System.out.println("Error in Entity class, canAfford(): helpme"+WOOD+IRON+FOOD);
        }
        return affordable;
    }
    public boolean withinCapacity(int count, int index){//in the future, i might add specific capacities to specific resources
        boolean within = false;
        switch(index){
            case Entity.WOOD:
                if(count+woodCnt<capacity) within = true;
                break;
            case Entity.IRON:
                if(count+ironCnt<capacity) within = true;
                break;
            case Entity.FOOD:
                if(count+foodCnt<capacity) within = true;
                break;
            default:
                System.out.println("Error in Entity class, canAfford(): helpme"+WOOD+IRON+FOOD);
        }
        return within;
    }
        //inventory-items
    public void addItem(Item i){ inventory.add(i);}
    public void takeItem(Item item) throws ItemNotInInventoryException{
        if (inventory.contains(item)) inventory.remove(item);
        else throw new ItemNotInInventoryException();
    }
        //static
    public static Game getGameByIndex(int i) throws IndexOutOfBoundsException{
        for(Game g : games){
            if(g.index==i) return g;
        }
        throw new IndexOutOfBoundsException();
    }

}
