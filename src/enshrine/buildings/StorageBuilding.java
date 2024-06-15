/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine.buildings;

import enshrine.Building;
import enshrine.Enshrine;
import enshrine.Entity;
import enshrine.Game;
import exceptions.NotAffordableException;
import exceptions.OutOfResourceCapacityBoundsException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dc_ca
 */
public class StorageBuilding extends Building {
    private int produce;//0 wood, 1 iron, 2 food

    public StorageBuilding(String n, int p, int w, int i) {
        super(n, Building.STORAGE, Enshrine.s(0,0,0), p, w);
        produce = i;
    }

    @Override
    public String getEfficiency() {
        String s="";
        switch(produce){
            case 0: s="Wood";break;
            case 1: s="Iron";break;
            case 2: s="Food";break;
        }
        return "This is a storage silo for "+s;
    }

    @Override
    public void performBuildingFunction(Entity e) {
        int i[] = e.getMaterialCnts();
        //add to user
        try{ Game.getCurrentGame().attemptAddMaterials(i);}
        catch (NotAffordableException ex) {//shouldnt be possible
        } catch (OutOfResourceCapacityBoundsException ex) {
            Game.getCurrentGame().addMaterialsUntilAble(i);
            //alert about capacity
        }
        //remove from entity
        for(int j=0; j<i.length; j++){ i[j] = -i[j];}
        try { e.attemptAddMaterials(i);}
        catch (NotAffordableException ex) {//shouldnt be possible
        } catch (OutOfResourceCapacityBoundsException ex) {//shouldnt be possible
        }
        //change target
        e.setTarget(Building.getGatheringBuildings().get(produce));
    }
    
}
