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
    public final static String ENEMY="HANNAH", DCPL="LIKE_AND_SUBSCRIBE", USER="ITS_MAAM_ACTUALLY";
    public final static int WOOD=3006, IRON=1407, FOOD=4006;
    
    protected double hp, maxHP, str, def, moveSpd, actionSpd;
    protected String type;
    protected ArrayList<Item> inventory;
    protected int woodCnt, ironCnt, foodCnt, iq;
    
    public Entity(double h, double s, double d, double ms, double as, int i){
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
        
        inventory = new ArrayList<>();
    }
    
    //getters
    public double[] getStats(){
        return Enshrine.s(hp, maxHP, str, def, moveSpd, actionSpd, (double)iq);
    }
    
    //setters
    
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
        switch(index){
            case Entity.WOOD:
                if(-count>woodCnt) throw new IllegalArgumentException();
                else woodCnt+=count;
                break;
            case Entity.IRON:
                if(-count>ironCnt) throw new IllegalArgumentException();
                else ironCnt+=count;
                break;
            case Entity.FOOD:
                if(-count>foodCnt) throw new IllegalArgumentException();
                else foodCnt+=count;
                break;
            default:
                System.out.println("Error in Entity class, attemptAddMaterials(): lol what even u tryna do"+WOOD+IRON+FOOD);
        }
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
