/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import enshrine.BuildingDisplay;
import enshrine.Building;
import enshrine.Entity;
import enshrine.EntityDisplay;
import enshrine.EntityTaskDisplay;
import enshrine.Game;
import enshrine.MyTimerTask;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class GameDisplayController implements Initializable {

    @FXML private VBox taskListCol1, taskListCol2, taskListCol3, taskListCol4;
    @FXML private Button pauseBtn;
    @FXML private AnchorPane playAreaAnchorPane;
    private ArrayList<VBox> taskListCols;
    
    private Game loadedGame;
    private boolean paused = true;
    private int turn = 0;
    
    private static ArrayList<GameDisplayController> allMDCs = new ArrayList<>();
    public static int TICKRATE = 24, TURN_RESET_AT = TICKRATE*2, MOVEMODIFIER = 2, ACTIONMODIFIER;
    public static int fightCol=0, trainCol=1, craftCol=2, gatherCol=3;

    public void setGame(enshrine.Game g){
        loadedGame = g;
        Game.setCurrentGame(g);
        updatePlayArea();
        setOnClose();
        initializeEntityTaskDisplays();
    }
    public static void attemptUpdateAll() {
        GameDisplayController mdc = allMDCs.get(0);
        if(!mdc.paused){
            //System.out.println(mdc.turn);
            mdc.updateTurn();
            mdc.update();
        }
    }
    public void updateTurn(){
        turn++;
        if(turn>=TURN_RESET_AT) turn = 0;
    }
    @FXML private void togglePause(ActionEvent e){
        paused = !paused;
    }
    public void update(){
        try{for(Entity e : loadedGame.getPopulation()){
            e.update(turn);
            //System.out.println(e.getType()+": going to "+e.getBuildingAttemptingToReach().getName());
            //check if dead
            if(e.getStats()[0]==0){
                switch(e.getType()){
                    case Entity.DISCIPLE:
                        e.setIncapacitated(true);
                        break;
                    case Entity.ENEMY:
                        loadedGame.getPopulation().remove(e);
                        e.getDisplayCharacter().setVisible(false);
                        break;
                }
            }
        }}
        catch(java.util.ConcurrentModificationException exc){
            //System.out.println("Error");//i have no idea how to fix this exception from not occurring
        }
        updatePlayArea();
    }
    public void updatePlayArea(){
        //population
        displayEntities(loadedGame.getPopulation());
    }
    public void displayEntities(ArrayList<Entity> e){
        for(Entity c:e){
            displayEntity(c);
        }
    }
    public void addEntityTaskList(Entity e){
        addEntityTaskList(e, e.getTaskDisplay().getColNum());
    }
    public void addEntityTaskList(Entity e, int colNum){
        EntityTaskDisplay t = e.getTaskDisplay();
        VBox v = taskListCols.get(colNum);
        v.getChildren().add(t);
    }
    public void removeEntityTaskList(Entity e, int colNum){
        EntityTaskDisplay t = e.getTaskDisplay();
        VBox v = taskListCols.get(colNum);
        v.getChildren().remove(t);
    }
    public void displayEntity(Entity e){
        if(e.getDisplayCharacter()==null&&!e.getInsideBuilding()){
            //make entityDisplay - set in entity
            EntityDisplay iv = new EntityDisplay(e);
            e.setDisplayCharacter(iv);
            //display in screen
            playAreaAnchorPane.getChildren().add(e.getDisplayCharacter());
        }
        if(e.getDisplayCharacter()!=null){
            EntityDisplay iv = e.getDisplayCharacter();
            //change visibility of character
            if(e.getInsideBuilding()){ iv.setVisible(false);}
            else iv.setVisible(true);
            //set location of character display
            iv.setLayoutX(e.getPos());
            iv.setLayoutY(Game.DEFAULT_Y_POS);
            //change image state here
        }
    }
    public void displayBuildings(ArrayList<Building> b){
        for(Building c:b){displayBuildings(c);}
    }
    public void displayBuildings(Building b){
        if(b.getDisplay()==null){
            //make buildingDisplay - set in Building
            BuildingDisplay iv = new BuildingDisplay(b);
            b.setDisplay(iv);
            //display in screen
            playAreaAnchorPane.getChildren().add(b.getDisplay());
        }
        if(b.getDisplay()!=null){
            BuildingDisplay iv = b.getDisplay();
            //set location of buildingdisplay
            iv.setLayoutX(b.getPos());
            iv.setLayoutY(BuildingDisplay.STANDARD_Y_POS);
            //change image state here
                //change built-ness of building
            if(b.getBuilt()){ iv.setBuiltImage(true);}
            else iv.setBuiltImage(false);
        }
    }
    private void initializeEntityTaskDisplays(){
        for(Entity e:Game.getCurrentGame().getPopulation()){
            if(e.getType().equals(Entity.DISCIPLE)){
                e.setTaskDisplay(new EntityTaskDisplay(e));
                addEntityTaskList(e);
            }
        }
    }
    private void initializeBuildings(){
        //set array of buildings to make
        ArrayList<Building> buildingsToInitialize = Building.getAllBuildings();
        //display all
        displayBuildings(buildingsToInitialize);
    }
    private void initializeEntityTaskDisplayRelation(){
        EntityTaskDisplay.setGDC(this);
    }
    public void initializeTimer(){
        Timer timer = new Timer("delay", true);
        timer.scheduleAtFixedRate(new MyTimerTask(), 100, 1000/TICKRATE);
    }
    private void initializeTaskList(){
        taskListCols = new ArrayList<>();
        taskListCols.add(taskListCol1);
        taskListCols.add(taskListCol2);
        taskListCols.add(taskListCol3);
        taskListCols.add(taskListCol4);
    }
    public void setOnClose(){
        Scene currentScene = pauseBtn.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.setOnCloseRequest(event -> {
            BuildingMenuDisplayController.closeAll();
            EntityMenuDisplayController.closeAll();
        });
    }
    private void initializeAll(){
        allMDCs.clear();
        allMDCs.add(this);
        initializeTaskList();
        initializeTimer();
        initializeBuildings();
        initializeEntityTaskDisplayRelation();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeAll();
        /*playAreaAnchorPane.getChildren().clear();
        Text t = new Text("waho");
        playAreaAnchorPane.getChildren().add(t);
        t.setLayoutX(2500);
        t.setLayoutY(200);*/
        
    }    
    
}
