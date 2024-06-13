/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author dc_ca
 */
public class BuildingDisplay extends VBox{
    public static int STANDARD_HEIGHT = 225, EXPANDED_WIDTH = 150, EXPANDED_HEIGHT = 350, STANDARD_Y_POS = 25+Game.DEFAULT_Y_POS-(STANDARD_HEIGHT-EntityDisplay.STANDARD_HEIGHT);
    
    private ProgressBar healthBar;
    private ImageView iv;
    private HBox hbox;
    private Text nameTag;
    private Building building;
    
    public BuildingDisplay(Building b){
        //Vbox
            building = b;
            this.setPrefWidth(b.getWidth());
            this.setPrefHeight(STANDARD_HEIGHT);
            this.setMaxHeight(STANDARD_HEIGHT);
            this.setMinHeight(STANDARD_HEIGHT);
            this.setStyle("-fx-background-color: purple;"); // Set background color
            this.setAlignment(Pos.BOTTOM_CENTER);
            /*//function
            this.setOnMouseEntered(e -> {this.expand();});
            this.setOnMouseExited(e -> {this.compress();});*/
            this.setOnMousePressed(e -> {this.openBuildingMenu();});
        //image
            Image image;
            try{ image = new Image(b.getImageFileName());}
            catch(Exception exc){
                image = new Image(getClass().getResourceAsStream("/imgs/not.png"));
            }
        //image view
            iv = new ImageView();
            iv.setImage(image);
            iv.setFitWidth(b.getWidth());
            iv.setFitHeight(STANDARD_HEIGHT-0);
            //iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            //image view effect
            ColorAdjust colorAdjust = new ColorAdjust();
            //temp color effect
            //colorAdjust.setHue((b.getPos()*2)/Game.GAME_SIZE); // Adjust hue level
            colorAdjust.setBrightness(0.1); // Adjust brightness level
            
            iv.setEffect(colorAdjust);
        //nameTag
            nameTag = new Text(b.getName());
        //progress bar
            healthBar = new ProgressBar();
            //healthBar.setProgress(b.getStats()[0] / e.getStats()[1]);
            healthBar.setVisible(false);
         //hbox
            hbox = new HBox();
            hbox.setPrefHeight(EXPANDED_HEIGHT-STANDARD_HEIGHT);
            hbox.setMinHeight(EXPANDED_HEIGHT-STANDARD_HEIGHT);
            hbox.setMaxHeight(EXPANDED_HEIGHT-STANDARD_HEIGHT);
            hbox.setPrefWidth(EXPANDED_WIDTH);
            hbox.setVisible(false);
            //design
            hbox.setStyle("-fx-background-color: lightyellow;"); // Set background color
        //add all
        this.getChildren().addAll(hbox, nameTag, healthBar, iv);//top to bottom
    }
    //getters
    public Building getBuilding(){ return building;}
    
    //methods
    public void update(Entity e){
        healthBar.setProgress(e.getStats()[0] / e.getStats()[1]);
    }
    public void setBuiltImage(Boolean b){
        //iv.setImage(value);
    }
    public void expand(){
        //System.out.println("EXPANDING "+building.getName());
        this.setPrefWidth(EXPANDED_WIDTH);
        this.setMinWidth(EXPANDED_WIDTH);
        this.setPrefHeight(EXPANDED_HEIGHT);
        this.setMaxHeight(EXPANDED_HEIGHT);
        this.setLayoutY(this.getLayoutY()-25);//(EXPANDED_HEIGHT-STANDARD_HEIGHT));
        checkLayoutY();
        hbox.setVisible(true);
    }
    public void checkLayoutY(){
        if(this.getLayoutY()<0) this.setLayoutY(0);
    }
    public void compress(){
        this.setPrefWidth(building.getWidth());
        this.setMinWidth(0);
        this.setPrefHeight(STANDARD_HEIGHT);
        this.setMaxHeight(STANDARD_HEIGHT);
        this.setLayoutY(STANDARD_Y_POS);
        hbox.setVisible(false);
    }

    private void openBuildingMenu() {
        FXMLLoader loader = null;
        try{
            //get current display
            Scene currentScene = this.getScene();
            Stage newStage = new Stage();
            //get new display
            loader = new FXMLLoader(getClass().getResource("/displays/BuildingMenuDisplay.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //show display
            newStage.setScene(scene);
            newStage.show();
            //set building
            loader.getController();
        }
        catch(IOException exception){
            //error
        }
    }
}
