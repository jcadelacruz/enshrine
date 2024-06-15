/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import enshrine.Entity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class EntityMenuDisplayController implements Initializable {
    
    @FXML private Text nameText;
    
    private static EntityMenuDisplayController allEMDCs = null;
    
    public void displayEntity(Entity e){
        allEMDCs = this;
    }
    public static void closeAll(){
        Stage s = (Stage) allEMDCs.nameText.getScene().getWindow();
        s.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
