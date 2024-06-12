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
public abstract class Building {
    public static final String USERSTATEFF="USER_STATS", DISCSTATEFF="DISCIPLE_STATS", USERINVENTORYEFF="USER_NON-MATERIAL_INVENTORY", VOID="HOMERSIMPSON";
    public static final double USERSTAT=9876, USERINVENTORY=1234, TAKE=1473, GIVE=6173, CRAFT=37421, FIGHT=91641, LEARN=73425;
    
    private String type;
    protected double[] effects = new double[8];//im not sure if 8 is the max possible amt
    private boolean built = false;
    private int pos, width;//see Entity class for details

    public static ArrayList<Building> allBuildings = new ArrayList<>();
    
    public Building(String t, double[] ef, int p, int w){
        type = t;
        effects = ef;
        pos = p;
        width = w;
        allBuildings.add(this);
    }
    /*public Building(double id, double[] ef){
        type = Building.USERSTATEFF;
        
        double[] newArray = new double[ef.length + 1];
        newArray[0] = id;
        System.arraycopy(ef, 0, newArray, 1, ef.length);

        effects = newArray;
        allBuildings.add(this);
    }*/
    
    //getters
    public int getPos(){ return pos;}
    public int getWidth(){ return width;}
    public String getType(){ return type;}
    public boolean getBuilt(){ return built;}
        //static
    public static ArrayList<Building> getAllBuildings(){ return allBuildings;}
    public static Building getByIndex(int i){ return allBuildings.get(i);}
    
    //setters
    public void build(){ built = true;}
    
    //methods
    public abstract void performBuildingFunction(Entity e);
    public boolean collidesWith(Entity e){
        int p = e.getPos(), w = e.getWidth();
        if(pos<p && p<pos+width) return true;
        if(p<pos && pos<p+w) return true;
        return false;
    }
    public abstract boolean attemptEnterBuilding(Entity e);
}