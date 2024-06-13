/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import enshrine.Building;
import enshrine.BuildingDisplay;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class BuildingMenuDisplayController implements Initializable {

    @FXML private Text level, name, efficiency;

    public void displayBuilding(BuildingDisplay bd){
        Building b = bd.getBuilding();
        level.setText(b.getLevel());
        name.setText(b.getName());
        efficiency.setText(b.getEfficiency());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
