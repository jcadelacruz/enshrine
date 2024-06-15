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
    public static final String VOID="HOMERSIMPSON", GATHERING="BARKBARK", STORAGE="SILOS", TRAINING="WOOF", CRAFTING="MIBECRAFT", SPECIAL="CAMPFIRES_ARE_ANNOYING";
    public static final double USERSTAT=9876, USERINVENTORY=1234, TAKE=1473, GIVE=6173, CRAFT=37421, FIGHT=91641, LEARN=73425;

    
    private String name, type;
    protected double[] effects = new double[8];//im not sure if 8 is the max possible amt
    protected boolean built = false;
    protected int pos, width;//see Entity class for details
    private int level;
    private BuildingDisplay display;
    private String imageFileName;

    public static ArrayList<Building> allBuildings = new ArrayList<>();
    
    public Building(String n, String t, double[] ef, int p, int w){
        name = n;
        imageFileName = n;
        type = t;
        effects = ef;
        pos = p;
        width = w;
        level = 1;
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
    public int getLevel(){ return level;}
    public String getType(){ return type;}
    public String getName(){ return name;}
    public abstract String getEfficiency();
    public boolean getBuilt(){ return built;}
    public BuildingDisplay getDisplay(){ return display;}
    public String getImageFileName(){ return imageFileName;}
        //static
    public static ArrayList<Building> getAllBuildings(){ return allBuildings;}
    public static ArrayList<Building> getGatheringBuildings() { return getBuildings(Enshrine.gatherStart,Enshrine.gatherEnd);}
    public static ArrayList<Building> getStorageBuildings() { return getBuildings(Enshrine.siloStart,Enshrine.siloEnd);}
    public static ArrayList<Building> getTrainingBuildings() { return getBuildings(Enshrine.trainStart,Enshrine.trainEnd);}
    public static Building getByIndex(int i){ return allBuildings.get(i);}
    public static ArrayList<Building> getBuildings(int start, int end) {
        ArrayList<Building> b = new ArrayList<>();
        for(int i=start; i<=end; i++){ b.add(allBuildings.get(i));}
        return b;
    }
    
    //setters
    public void setBuilt(Boolean b){ built = b;}
    public void setDisplay(BuildingDisplay b){ display = b;}
    
    //methods
    public abstract void performBuildingFunction(Entity e);
    public boolean collidesWith(Entity e){
        int p = e.getPos(), w = e.getWidth();
        if(pos<p && p<pos+width) return true;
        if(p<pos && pos<p+w) return true;
        return false;
    }
    public boolean attemptEnterBuilding(Entity e){
        if(built) return true;
        else return false;
    }
}