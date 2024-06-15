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
    public DestinationBuilding(String n, int p, int w){
        super(n, Building.VOID, Enshrine.s(0,0,0), p, w);
        double res[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        effects = res;
        built = true;
    }
    
    @Override
    public void performBuildingFunction(Entity e){}

    @Override
    public String getEfficiency() {
        return "This is the "+this.getName();
    }
}
