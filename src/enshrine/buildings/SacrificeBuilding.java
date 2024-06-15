/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine.buildings;

import enshrine.Building;
import enshrine.Entity;

/**
 *
 * @author dc_ca
 */
public class SacrificeBuilding extends Building{
    public SacrificeBuilding(String n, int p, int w, double e[]){
        super(n, Building.SPECIAL, e, p, w);
        built = true;
    }
    
    @Override
    public void performBuildingFunction(Entity e){}
    @Override
    public boolean collidesWith(Entity e){
        if(e.getType().equals(Entity.DISCIPLE)){
            e.addStat(effects);
            if(e.getIncapacitated()&&e.getStats()[0]==e.getStats()[1]){
                e.setIncapacitated(false);
            }
        }
        
        int p = e.getPos(), w = e.getWidth();
        if(pos<p && p<pos+width) return true;
        if(p<pos && pos<p+w) return true;
        return false;
    }

    @Override
    public String getEfficiency() {
        return "This is the "+this.getName();
    }
}
