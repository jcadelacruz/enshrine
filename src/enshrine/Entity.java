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
public class Entity {
    public final static String ENEMY="HANNAH", DISCIPLE="LIKE_AND_SUBSCRIBE", USER="ITS_MAAM_ACTUALLY";
    public final static int WOOD=3006, IRON=1407, FOOD=4006;
    
    protected double hp, maxHP, str, def, moveSpd, actionSpd;
    protected String type;
    protected ArrayList<Item> inventory = new ArrayList<>();
    protected int woodCnt, ironCnt, foodCnt, iq;
    //disciple/monster specific stuff
    private Building targetBuilding = null;
    private Item itemToCraft = null;
    private boolean trainingFight = true, ;
    
    public Entity(String t, double h, double s, double d, double ms, double as, int i){
        type = t;
        
        hp = h;
        maxHP = h;
        str = s;
        def = d;
        moveSpd = ms;
        actionSpd = as;
        iq = i;
        
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
    public Building getTargetBuilding(){ return targetBuilding;}
    public boolean getTrainingFight(){ return trainingFight;}
    
    //setters
    public void setItemToCraft(Item i){ itemToCraft = i;}
    public void goInBuilding(Building b){ targetBuilding = b;}
    public void setTrainingFight(boolean b){ trainingFight = b;}
    
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
        //inventory
            //materials
    public void attemptAddMaterials(double[] m) throws IllegalArgumentException{
        attemptAddMaterials((int)m[0], WOOD); 
        attemptAddMaterials((int)m[1], IRON); 
        attemptAddMaterials((int)m[2], FOOD); 
    }
    public void attemptAddMaterials(int count, int index) throws IllegalArgumentException{
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
