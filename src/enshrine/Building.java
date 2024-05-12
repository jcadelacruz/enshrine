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
public class Building {
    public static final String USERSTATEFF="USER_STATS", DISCSTATEFF="DISCIPLE_STATS", USERINVENTORYEFF="USER_NON-MATERIAL_INVENTORY";
    public static final double USERSTAT=9876, USERINVENTORY=1234, TAKE=1473, GIVE=6173;
    
    private ArrayList<Entity> contents;
    private String type;
    private double[] effects = new double[8];//im not sure if 8 is the max possible amt
    
    public Building(String t, double[] ef){
        type = t;
        contents = new ArrayList<>();
        effects = ef;
    }
    public Building(double id, double[] ef){
        type = Building.USERSTATEFF;
        contents = new ArrayList<>();
        
        double[] newArray = new double[ef.length + 1];
        newArray[0] = id;
        System.arraycopy(ef, 0, newArray, 1, ef.length);

        effects = newArray;
    }
    
    public String getType(){ return type;}
    public ArrayList<Entity> getContents(){ return contents;}
    
    public void performBuildingFunction() throws IllegalArgumentException{
        if(!type.equals(Building.USERSTATEFF)) return;
        double[] effContent = new double[effects.length-1];
        System.arraycopy(effects, 1, effContent, 0, effContent.length);
        
        Game g = Game.getUser();
        if(effects[0]==Building.USERSTAT) g.addStat(effContent);
        else if(effects[0]==Building.USERINVENTORY) g.attemptAddMaterials(effContent);
    }
    public void performBuildingFunction(Entity d){
        if(!type.equals(Building.DISCSTATEFF)) return;
        d.addStat(effects);
    }
    public void performBuildingFunction(Item i){
        if(!type.equals(Building.USERINVENTORYEFF)) return;
        
        Game g = Game.getUser();
        if(effects[0]==Building.TAKE) g.takeItem(i);
        else if(effects[0]==Building.GIVE) g.addItem(i);
    }
}
