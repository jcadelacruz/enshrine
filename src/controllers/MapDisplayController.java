/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

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

    public void setGame(enshrine.Game g){
        loadedGame = g;
    }
    private void initializeTaskList(){
        taskListCols = new ArrayList<>();
        taskListCols.add(taskListCol1);
        taskListCols.add(taskListCol2);
        taskListCols.add(taskListCol3);
        taskListCols.add(taskListCol4);
    }
    private void initializeAll(){
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
