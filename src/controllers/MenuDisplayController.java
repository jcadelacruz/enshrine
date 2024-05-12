/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import enshrine.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author dc_ca
 */
public class MenuDisplayController implements Initializable {

    @FXML private Button startBtn, settingsBtn, creditsBtn;
    @FXML private BorderPane bp;
    boolean creditsOpen = false;
    String creditsText;
    private static ArrayList<MenuDisplayController> allMenuDisplayControllers = new ArrayList<>();
    
    public static void refreshLoads(){
        for(MenuDisplayController m : allMenuDisplayControllers){
            m.openLoads();
        }
    }
    @FXML private void openLoads() {
        //make vbox
        VBox loadBoxes = new VBox();
        loadBoxes.setAlignment(Pos.CENTER);
        bp.setRight(loadBoxes);
        //make buttons
        for(int i = 1; i<=5; i++){
            String state = " [EMPTY]";
            for(Game game : Game.getGames()){
                if(game.getIndex()==i) state = " [LOADED]";
            }
            
            String str = i+state;
            Button b = new Button();
            b.setText(str);
            b.setOnAction(e -> {
                Enshrine.interpretLoadButton(e);
            });
            
            loadBoxes.getChildren().add(b);
        }
    }

    @FXML private void openSettings(ActionEvent event) {
    }

    @FXML private void viewCredits(ActionEvent event) {
        if(creditsOpen){
            creditsBtn.setText("Credits");
            creditsBtn.setPrefHeight(0);
            creditsBtn.setPrefWidth(130);
        }
        else{
            creditsBtn.setText(creditsText);
            creditsBtn.setTextAlignment(TextAlignment.CENTER);
            creditsBtn.setPrefHeight(300);
            creditsBtn.setPrefWidth(400);
        }
        creditsOpen = !creditsOpen;
    }
    private void initializeCreditsText(){
        creditsText = "Developed by [Your Name]\n" +
"Game Design: [Lead Game Designer]\n" +
"Programming: [Lead Programmer]\n" +
"Art and Animation: [Lead Artist]\n" +
"Music and Sound Effects: [Composer]\n" +
"Quality Assurance: [Lead QA Tester]\n" +
"Special Thanks: [Names of individuals]\n\n" +
"Thank you for playing \"Enshrined\"!";
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allMenuDisplayControllers.clear();
        allMenuDisplayControllers.add(this);
        
        initializeCreditsText();
    } 
    
}
