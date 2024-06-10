/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import enshrine.Building;
import enshrine.Entity;
import enshrine.Game;
import enshrine.MyTimerTask;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private int turn = 0;
    
    private static ArrayList<MapDisplayController> allMDCs = new ArrayList<>();
    public static int TICKRATE = 48, TURN_RESET_AT = TICKRATE*2, MOVEMODIFIER = 2, ACTIONMODIFIER;

    public void setGame(enshrine.Game g){
        loadedGame = g;
        Game.setCurrentGame(g);
        updatePlayArea();
    }
    public static void attemptUpdateAll() {
        MapDisplayController mdc = allMDCs.get(0);
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
    public void updatePlayArea(){
        //population
        for(Entity e : loadedGame.getPopulation()){
            Image image;
            try{
                image = new Image(e.getImageFileName());
            }
            catch(Exception exc){
                image = new Image(getClass().getResourceAsStream("/imgs/not.png"));
            }
            if(e.getDisplayCharacter()==null&&!e.getInsideBuilding()){
                //make image view
                ImageView iv = new ImageView();
                iv.setImage(image);
                iv.setFitWidth(e.getWidth());
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                iv.setLayoutX(e.getPos());
                iv.setLayoutY(Game.DEFAULT_Y_POS);
                //set in entity
                e.setDisplayCharacter(iv);
                //display in screen
                playAreaAnchorPane.getChildren().add(iv);
            }
            if(e.getDisplayCharacter()!=null){
                if(e.getInsideBuilding()){ e.getDisplayCharacter().setVisible(false);}
                else e.getDisplayCharacter().setVisible(true);
                ImageView iv = e.getDisplayCharacter();
                iv.setLayoutX(e.getPos());
                //change image state here
            }
        }
    }
    public void update(){
        for(Entity e : loadedGame.getPopulation()){
            e.update(turn);
        }
        updatePlayArea();
    }
    public void buildBuilding(Building b){
        b.build();
        updatePlayArea();
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
    private void initializeAll(){
        allMDCs.clear();
        allMDCs.add(this);
        initializeTaskList();
        initializeTimer();
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
