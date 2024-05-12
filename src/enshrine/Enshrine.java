/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package enshrine;

import controllers.MenuDisplayController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    public static void interpretLoadButton(Event e){
        int index = 0;
        try {
            String text = ((Button)e.getSource()).getText();
            String myChar = text.charAt(3)+"";
            char charIndex = text.charAt(0);
            index = Integer.parseInt(String.valueOf(charIndex));

            if(!myChar.equals("L")){
                //System.out.println("making: "+index);
                new Game(index);
                MenuDisplayController.refreshLoads();
            }
            else Enshrine.loadGame(index, e);//System.out.println("loading: "+index);
            
        }
        catch (NumberFormatException exc) {
            System.out.println("Cannot convert character to integer. Please make sure the character is a digit.");
        }
    }
    public static void loadGame(int i, Event e){
        //try{
        //    FXMLLoader loader = openFXML("")
        //}
    }
    public static double[] s(double a, double b, double c, double d, double e, double f, double g){
        double[] res = {a,b,c,d,e,f,g};
        return res;
    }
    public static FXMLLoader openFXML(String name, Event e, Class className) throws IOException{
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
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
