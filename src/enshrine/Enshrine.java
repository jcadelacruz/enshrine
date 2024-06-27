/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package enshrine;

import enshrine.buildings.*;
import controllers.GameDisplayController;
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
    
    public static int fightArea=0, gatherStart=1, gatherEnd=3, siloStart=4, siloEnd=6, trainStart=7, trainEnd = 8, craftTable=9, campFir=10;//change craft table
    
    @Override
    public void start(Stage stage) throws Exception {
        Building fightingArea = new DestinationBuilding("Fighting Area", 500, 250);
        
        double wood[] = {1.0,0,0}, iron[]={0,1.0,0}, food[]={0,0,1.0};
        Building forest = new GatheringBuilding("Forestry", wood, 1200, 200, 0);
        Building weBackInThe = new GatheringBuilding("Mines", iron, 1430, 150, 1);
        Building coop = new GatheringBuilding("Chiken", food, 1600, 175, 2);
        
        Building siloW = new StorageBuilding("Wood", 1800, 100, 0);
        Building siloI = new StorageBuilding("Iron", 1910, 100, 1);
        Building siloF = new StorageBuilding("Food", 2020, 100, 2);
        
        double str[] = {0.0,0.0,0.05,0.0,0.0,0.0,0.0,0.0}, iq[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,1};
        Building cubao = new TrainingBuilding("Train Station", str, 2140, 150, 0);
        Building shb2f = new TrainingBuilding("Library", iq, 2300, 140, 0);
        double res[] = {1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Building craftingTable = new DestinationBuilding("Crafting Table", 2450, 200);
        Building campfire = new SacrificeBuilding("Campfire", 2700, 250, res);
        /*Building gatheringPlot = new Building(Building.USERINVENTORY, s(0.0,0.0,1.0));
        Building trainStation = new Building(Building.DISCSTATEFF, s(0.0,0.0,0.0));
        Building craftingTable = new Building(Building.USERINVENTORYEFF, s(Building.CRAFT,0.0,0.0));
        
        DESTINATION no eff 0
            fighting area
        GATHERING discres 1-3
            wood
            iron
            food
        SILOS -discres, userres 4-6
            wood
            iron
            food    
        TRAINING discstat 7-8
            str training
            library iq
        CRAFTING userinv, -userres 9
            open menu
        SPECIAL/SACRIFICE userstat, -userinv 10
            open menu + can enter
        
        */
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/displays/MenuDisplay.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/displays/MenuDisplay.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void interpretLoadButton(Event e, Class c){
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
            else Enshrine.loadGame(index, e, c);//System.out.println("loading: "+index);
            
        }
        catch (NumberFormatException exc) {
            System.out.println("Cannot convert character to integer. Please make sure the character is a digit.");
        }
    }
    public static void loadGame(int i, Event e, Class c){
        try{
            FXMLLoader loader = openFXML("Map", e, c);
            GameDisplayController mdc = loader.getController();
            Game g = Game.getGameByIndex(i);
            mdc.setGame(g);
            Entity.setCurrentGame(g);
            Game.setCurrentGame(g);
        }
        catch(IndexOutOfBoundsException exc){
            System.out.println("game not found at index "+ i);
        }
        catch(IOException exc){
            System.out.println("cannot load map display");
        }
    }
    public static double[] s(double a, double b, double c, double d, double e, double f, double g){
        double[] res = {a,b,c,d,e,f,g};
        return res;
    }
    public static double[] s(double a, double b, double c){
        double[] res = {a,b,c};
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
