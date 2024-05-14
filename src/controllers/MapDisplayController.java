/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import enshrine.Building;
import enshrine.Entity;
import enshrine.Game;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class MapDisplayController implements Initializable {

    @FXML private VBox taskListCol1, taskListCol2, taskListCol3, taskListCol4;
    @FXML private Button pauseBtn;
    @FXML private AnchorPane playAreaAnchorPane;
    private ArrayList<VBox> taskListCols;
    
    private Game loadedGame;
    private boolean paused = true;
    
    private static ArrayList<MapDisplayController> allMDCs = new ArrayList<>();

    public void setGame(enshrine.Game g){
        loadedGame = g;
        Game.setUser(g);
    }
    public static void attemptUpdateAll() {
        MapDisplayController mdc = allMDCs.get(0);
        if(!mdc.paused){
            mdc.update();
        }
    }
    public void update(){
        for(Entity e : loadedGame.getPopulation()){
            //DISCIPLES
            if(e.getType().equals(Entity.DISCIPLE)){
                if(e.getTargetBuilding()!=null){//if in building
                    Building b = e.getTargetBuilding();
                    String type = b.getType();
                    switch(type){
                        case Building.USERSTATEFF:
                            b.performBuildingFunction();
                            break;
                        case Building.DISCSTATEFF:
                            b.performBuildingFunction(e, e.getTrainingFight());
                            break;
                        case Building.USERINVENTORYEFF:
                            b.performBuildingFunction(e.getItemToCraft());
                            break;
                    }
                }
                else{//if not in building
                    
                }
            }
            //ALL ENTITIES
            try{
                if(e.getTargetBuilding()==null||e.getTargetBuilding().getType()==Building.VOID){
                    
                }
            }
            catch(Exception exc){
                //vibes
            }
        }
    }
    private void initializeTaskList(){
        taskListCols = new ArrayList<>();
        taskListCols.add(taskListCol1);
        taskListCols.add(taskListCol2);
        taskListCols.add(taskListCol3);
        taskListCols.add(taskListCol4);
    }
    private void initializeAll(){
        allMDCs.set(0, this);
        initializeTaskList();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeAll();
    }    
    
}
