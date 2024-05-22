/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

/**
 *
 * @author dc_ca
 */
public class Entity {
    public final static String ENEMY="HANNAH", DISCIPLE="LIKE_AND_SUBSCRIBE", USER="ITS_MAAM_ACTUALLY";
    public final static int WOOD=3006, IRON=1407, FOOD=4006;
    
    protected double hp, maxHP, str, def, moveSpd, actionSpd;
    protected String type;
    protected ArrayList<Item> inventory = new ArrayList<>();
    protected int woodCnt, ironCnt, foodCnt, iq;
    //disciple/monster specific stuff
    private Building buildingInsideOf = null, buildingAttemptingToReach = null;
    private Item itemToCraft = null;
    private boolean trainingFight = true, goingRight = true;
    private int materialToGather, targetPos = 5*Game.GAME_SIZE/6, pos;//pos is the BOTTOM LEFT corner of the hitbox
    private double width = 60.0;
    private ImageView displayCharacter = null;
    
    public Entity(String t, double h, double s, double d, double ms, double as, int i, int p){
        type = t;
        switch(type){
            case ENEMY:
                goingRight = true;
                break;
            case DISCIPLE:
                goingRight = false;
                break;
        }
        
        hp = h;
        maxHP = h;
        str = s;
        def = d;
        moveSpd = ms;
        actionSpd = as;
        iq = i;
        
        pos = p;
        
        woodCnt = 0;
        ironCnt = 0;
        foodCnt = 0;
    }
    
    //getters
    public String getType(){ return type;}
    public double[] getStats(){
        return Enshrine.s(hp, maxHP, str, def, moveSpd, actionSpd, (double)iq);
    }
    public Item getItemToCraft(){ return itemToCraft;}
    public Building getBuildingInsideOf(){ return buildingInsideOf;}
    public boolean getTrainingFight(){ return trainingFight;}
    public int getTargetPos(){ return targetPos;}
    public boolean getGoingRight(){ return goingRight;}
    public double getWidth(){ return width;}
    public int getPos(){ return pos;}
    public ImageView getDisplayCharacter(){ return displayCharacter;}
    public Building getBuildingAttemptingToReach(){ return buildingAttemptingToReach;}
    public String getImageFileName(){ return "/imgs/not.png";}
    
    //setters
    public void setItemToCraft(Item i){ itemToCraft = i;}
    public void setBuildingInsideOf(Building b){ buildingInsideOf = b;}
    public void goInBuilding(Building b){ buildingInsideOf = b;}
    public void setTrainingFight(boolean b){ trainingFight = b;}
    public void setTargetPos(int i){ targetPos = i;}
    public void setTargetPos(double i){
        if(i>1) i = 1;
        if(i<0) i = 0;
        targetPos = (int) i*Game.GAME_SIZE;
    }
    public void setGoingRight(boolean b){ goingRight = b;}
    public void setDisplayCharacter(ImageView iv){ displayCharacter = iv;}
    public void setBuildingAttemptingToReach(Building b){ buildingAttemptingToReach = b;}
    
    //methods
        //stats
    public void addStat(double[] d){
        this.addMaxHP(d[1]);
        this.addHP(d[0]);
        str = addUntilZero(str, d[2]);
        def = addUntilZero(def, d[3]);
        moveSpd = addUntilZero(moveSpd, d[4]);
        actionSpd = addUntilZero(actionSpd, d[5]);
        iq = (int) addUntilZero((double) iq, d[6]);
    }
    public void addHP(double h){
        double health = hp + h;
        if(health>maxHP) health = maxHP;
        if(health<0) health = 0;
        hp = health;
    }
    public void addMaxHP(double h){
        maxHP += h;
        addHP(0);
    }
    public double addUntilZero(double currVal, double add){
        double sum = currVal + add;
        if(sum<0) sum = 0;
        return sum;
    }
        //position
    public void move(int i){
        pos+=i;
    }
        //inventory
            //materials
    public void attemptAddMaterials(int[] m) throws IllegalArgumentException{
        int failAt = 0;
        try{
            for(int i = 0; i<3; i++){
                attemptAddMaterials(-(int)m[i], i);
                failAt++;
            }
        }
        catch(IllegalArgumentException e){
            for(int i = 0; i<=failAt; i++){
                attemptAddMaterials(-(int)m[i], i);
            }
        }
    }
    public void attemptAddMaterials(int count, int i) throws IllegalArgumentException{
        //convert
        int index = 0;
        switch(i){
            case 0:
                index = WOOD;
                break;
            case 1:
                index = IRON;
                break;
            case 2:
                index = FOOD;
                break;
            default:
                //
        }
        //add
        if(!canAfford(count, index)) throw new IllegalArgumentException();
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
            //items
    public void addItem(Item i){
        inventory.add(i);
    }
    public void takeItem(Item item){
        if (inventory.contains(item)) inventory.remove(item);
        else System.out.println("Error in Entity class, takeItem(): Item not found in inventory.");
    }
    
}
