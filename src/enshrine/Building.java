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
    public static final String USERSTATEFF="USER_STATS", DISCSTATEFF="DISCIPLE_STATS", USERINVENTORYEFF="USER_NON-MATERIAL_INVENTORY", VOID="HOMERSIMPSON";
    public static final double USERSTAT=9876, USERINVENTORY=1234, TAKE=1473, GIVE=6173, CRAFT=37421, FIGHT=91641, LEARN=73425;
    
    private String type;
    private double[] effects = new double[8];//im not sure if 8 is the max possible amt
    private boolean built = false;

    private static ArrayList<Building> allBuildings = new ArrayList<>();
    
    public Building(String t, double[] ef){
        type = t;
        effects = ef;
        allBuildings.add(this);
    }
    public Building(double id, double[] ef){
        type = Building.USERSTATEFF;
        
        double[] newArray = new double[ef.length + 1];
        newArray[0] = id;
        System.arraycopy(ef, 0, newArray, 1, ef.length);

        effects = newArray;
        allBuildings.add(this);
    }
    
    //getters
    public String getType(){ return type;}
    public boolean getBuilt(){ return built;}
    
    //setters
    public void build(){ built = true;}
    
    //methods
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
    public void performBuildingFunction(Entity d, boolean fight){
        if(!type.equals(Building.DISCSTATEFF)) return;
        if(effects[0]==FIGHT){
            double[] add_str = {0.0,0.0,1.0,0.0,0.0,0.0,0.0};
            d.addStat(add_str);
        }
        else if(effects[0]==LEARN){
            double[] add_iq = {0.0,0.0,0.0,0.0,0.0,0.0,1.0};
            d.addStat(add_iq);
        }
    }
    /*public void performBuildingFunction(Item i){
        if(!type.equals(Building.USERINVENTORYEFF)) return;
        
        Game g = Game.getUser();
        if(effects[0]==Building.TAKE) g.takeItem(i);
        else if(effects[0]==Building.GIVE) g.addItem(i);
    }*/
    public void performBuildingFunction(Item i){
        if(!type.equals(Building.USERINVENTORYEFF)) return;
        
        Game g = Game.getUser();
        if(effects[0]==Building.CRAFT){
            try{
                g.attemptAddMaterials(i.getRequirements());
                g.addItem(i);
            }
            catch(IllegalArgumentException exc){
                System.out.println("ur poor; this shouldnt be reached since there should be a checker in the controller");
            }
        }
    }
}
