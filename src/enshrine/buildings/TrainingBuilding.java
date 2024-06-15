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
public class TrainingBuilding extends Building {
    int specialty;//0 str, 1 iq

    public TrainingBuilding(String n, double[] ef, int p, int w, int i) {
        super(n, Building.TRAINING, ef, p, w);
        specialty = i;
    }

    @Override
    public String getEfficiency() {
        String s="";
        switch(specialty){
            case 0: s="Strength";break;
            case 1: s="I.Q.";break;
        }
        s= "This is a Training institution for Disciple's "+s;
        String eff = s+"\n";
        for(double d : effects){
            eff+= (d+", ");
        }
        return eff;
    }

    @Override
    public void performBuildingFunction(Entity e) {
        e.addStat(effects);
    }
    
}
