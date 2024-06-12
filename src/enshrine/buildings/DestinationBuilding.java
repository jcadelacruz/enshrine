/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine.buildings;

import enshrine.Building;
import enshrine.Enshrine;
import enshrine.Entity;

/**
 *
 * @author dc_ca
 */
public class DestinationBuilding extends Building {
    public DestinationBuilding(int p, int w){
        super(Building.VOID, Enshrine.s(0,0,0), p, w);
        double res[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        effects = res;
    }
    public DestinationBuilding(int p, int w, double e[]){
        super(Building.USERSTATEFF, e, p, w);
    }
    
    @Override
    public void performBuildingFunction(Entity e){}
    public boolean attemptEnterBuilding(Entity e){
        if(e.getType().equals(Entity.DISCIPLE)){
            e.addStat(effects);
            if(e.getIncapacitated()&&e.getStats()[0]==e.getStats()[1]){
                e.setIncapacitated(false);
            }
        }
        return false;
    }
}
