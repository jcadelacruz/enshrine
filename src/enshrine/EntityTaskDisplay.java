/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import controllers.GameDisplayController;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author dc_ca
 */
public class EntityTaskDisplay extends HBox{
    public static int HEIGHT=50, WIDTH=175, MOVE_BUTTON_WIDTH = 25, IMAGEVIEW_WIDTH = 30, INNERVBOX_WIDTH=WIDTH-((2*MOVE_BUTTON_WIDTH)+IMAGEVIEW_WIDTH);
    
    //nodes
    private Button leftButton, rightButton;
    private ImageView img;
    private VBox innerVBox;
    private ProgressBar healthBar;
    private Text nameText, descriptionText;
    
    //stats
    private int colNum;
    private Entity entity;
    //static
    private static GameDisplayController currentGDC;

    public EntityTaskDisplay(Entity e){
        entity = e;
        
        this.setMaxHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);
        //functions
        this.setOnMouseDragEntered(event -> {addLightEffect();});
        this.setOnMouseDragExited(event -> {removeLightEffect();});

        //buttons
        leftButton = new Button("<");
        leftButton.setMaxHeight(HEIGHT);
        leftButton.setMaxWidth(WIDTH);
        leftButton.setMinHeight(HEIGHT);
        leftButton.setMinWidth(WIDTH);
        leftButton.setPrefHeight(HEIGHT);
        leftButton.setPrefWidth(WIDTH);
        
        rightButton = new Button(">");
        rightButton.setPrefHeight(HEIGHT);
        rightButton.setPrefWidth(WIDTH);
            //functions
        leftButton.setOnAction(event->{moveEntityTaskDisplay(false);});
        rightButton.setOnAction(event->{moveEntityTaskDisplay(true);});

        //image
        img = new ImageView();
        img.setFitHeight(HEIGHT);
        img.setFitWidth(IMAGEVIEW_WIDTH);
        img.setPickOnBounds(true);
        //img.setPreserveRatio(true);
        img.setImage(new Image(getClass().getResourceAsStream(e.getImageFileName())));
        //function
        img.setOnMouseClicked(event->{ openEntityDisplay();});

        //innerVBox
        innerVBox = new VBox();
        /*innerVBox.setMaxHeight(Double.MAX_VALUE);
        innerVBox.setMaxWidth(Double.MAX_VALUE);
        innerVBox.setMinHeight(0);
        innerVBox.setMinWidth(0);*/
        innerVBox.setPrefHeight(HEIGHT);
        innerVBox.setPrefWidth(INNERVBOX_WIDTH);

            nameText = new Text("NAME");
            nameText.setFont(Font.font(10));

            healthBar = new ProgressBar();
            healthBar.setMaxHeight(10);
            healthBar.setMaxWidth(INNERVBOX_WIDTH);
            healthBar.setMinHeight(10);
            healthBar.setMinWidth(INNERVBOX_WIDTH);
            healthBar.setPrefHeight(10);
            healthBar.setPrefWidth(INNERVBOX_WIDTH);
            healthBar.setProgress(e.getStats()[0]/e.getStats()[1]);

            descriptionText = new Text("Text");

        innerVBox.getChildren().addAll(nameText, healthBar, descriptionText);
        //function
        innerVBox.setOnMouseClicked(event -> {toggleActivity();});

        this.getChildren().addAll(leftButton, img, innerVBox, rightButton);
    }
    
    //getters
    public int getColNum(){ return colNum;}
    //setters
    public static void setGDC(GameDisplayController t){ currentGDC = t;}

    //methods
    private void toggleActivity() {
        switch(colNum){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                
        }
    }
    private void openEntityDisplay(){
        //open display
    }
    private void moveEntityTaskDisplay(boolean goingRight){
        int change = -1;
        if(goingRight) change = 1;
        
        //add check here?
        
        currentGDC.removeEntityTaskList(entity, colNum);
        colNum += change;
        currentGDC.addEntityTaskList(entity);
    }

        //aesthetic
    private void addLightEffect() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(1.2); // Adjust the brightness

        this.setEffect(colorAdjust);
    }

    private void removeLightEffect() {
        this.setEffect(null);
    }
    
}
