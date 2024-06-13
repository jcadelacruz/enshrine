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
public class EntityEffectBuilding extends Building{

    public EntityEffectBuilding(String n, double[] ef, int p, int w) {
        super(n, Building.DISCSTATEFF, ef, p, w);
    }
    @Override
    public void performBuildingFunction(Entity e) {
        e.addStat(this.effects);
    }
    
}
