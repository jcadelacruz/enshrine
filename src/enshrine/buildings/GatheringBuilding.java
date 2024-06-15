/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine.buildings;

import enshrine.Building;
import enshrine.Entity;
import exceptions.NotAffordableException;
import exceptions.OutOfResourceCapacityBoundsException;

/**
 *
 * @author dc_ca
 */
public class GatheringBuilding extends Building{
    int produce=0;//0 wood, 1 iron, 2 food

    public GatheringBuilding(String n, double[] ef, int p, int w, int i) {
        super(n, Building.GATHERING, ef, p, w);
        produce = 0;
    }
    @Override
    public void performBuildingFunction(Entity e) {
        int ef[] = new int[8];
        for(int i=0; i<effects.length; i++){
            ef[i] = (int) effects[i];
            if(ef[i]<0) System.out.println("error in GatheringBuilding: performBuildingFunction(); effects should be greater than 0");
        }
        try {
            e.attemptAddMaterials(ef);
        } catch (NotAffordableException ex) {
            //effects should be greater than 0
        } catch (OutOfResourceCapacityBoundsException ex) {
            //PUT OUT OF CAPACITY HERE
            outOfCapacity(e);
        }
    }
    private void outOfCapacity(Entity e){
        e.setTarget(Building.getStorageBuildings().get(produce));
    }

    @Override
    public String getEfficiency() {
        String eff = "";
        for(double d : effects){
            eff+= (d+", ");
        }
        return eff;
    }
    
}
