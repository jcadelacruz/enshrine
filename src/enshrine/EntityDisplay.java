/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author dc_ca
 */
public class EntityDisplay extends VBox {
    public static int STANDARD_HEIGHT = 100;
    
    private ProgressBar healthBar;
    private ImageView iv;
    
    public EntityDisplay(Entity e){
        //Vbox
            this.setPrefWidth(e.getWidth());
            this.setPrefHeight(STANDARD_HEIGHT);
            this.setStyle("-fx-background-color: lightblue;"); // Set background color
            this.setAlignment(Pos.BOTTOM_CENTER);
        //image
            Image image;
            try{ image = new Image(e.getImageFileName());}
            catch(Exception exc){
                image = new Image(getClass().getResourceAsStream("/imgs/not.png"));
            }
        //image view
            iv = new ImageView();
            iv.setImage(image);
            iv.setFitWidth(e.getWidth());
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setLayoutX(e.getPos());
            iv.setLayoutY(Game.DEFAULT_Y_POS);
            //image view effect
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(0.5); // Adjust hue level
            colorAdjust.setBrightness(0.1); // Adjust brightness level
            switch(e.getType()){
                case Entity.ENEMY:
                    colorAdjust.setHue(0.8);
                    break;
            }
            iv.setEffect(colorAdjust);
        //progress bar
            healthBar = new ProgressBar();
            healthBar.setProgress(e.getStats()[0] / e.getStats()[1]);
        //add all
        this.getChildren().addAll(healthBar, iv);
    }
    public void update(Entity e){
        healthBar.setProgress(e.getStats()[0] / e.getStats()[1]);
    }
}
