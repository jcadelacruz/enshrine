/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package enshrine;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dc_ca
 */
public class Enshrine extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/displays/MenuDisplay.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/displays/MenuDisplay.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /*public static FXMLLoader openFXML(String name, Event e, Class className) throws IOException{
        FXMLLoader loader = null;
        try{
            //get current display
            Node node = (Node) e.getSource();
            Scene currentScene = node.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            //get new display
            loader = new FXMLLoader(className.getResource("/displays/" + name + "Display.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //switch displays
            currentStage.hide();
            currentStage.setScene(scene);
            currentStage.show();
        }
        catch(IOException exception){
            //error
        }
        return loader;
    }
    public static FXMLLoader openFXML(String name, Node n, Class className) throws IOException{
        FXMLLoader loader = null;
        try{
            //get current display
            Scene currentScene = n.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            //get new display
            loader = new FXMLLoader(className.getResource("/displays/" + name + "Display.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //switch displays
            currentStage.hide();
            currentStage.setScene(scene);
            currentStage.show();
        }
        catch(IOException exception){
            //error
        }
        return loader;
    }*/
    public static void main(String[] args) {
        launch(args);
    }
    
}
