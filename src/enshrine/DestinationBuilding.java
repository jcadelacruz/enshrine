/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

/**
 *
 * @author dc_ca
 */
public class DestinationBuilding extends Building {
    DestinationBuilding(int p, int w){
        super(Building.VOID, Enshrine.s(0,0,0), p, w);
    }
    
    public void performBuildingFunction(Entity e){}
    @Override
    public boolean collidesWith(int p){ return false;}
}
