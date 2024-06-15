/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import controllers.EntityMenuDisplayController;
import controllers.GameDisplayController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author dc_ca
 */
public class EntityTaskDisplay extends HBox{
    public static int HEIGHT=50, WIDTH=175, MOVE_BUTTON_WIDTH = 25, IMAGEVIEW_WIDTH = 30, INNERVBOX_WIDTH=WIDTH-((2*MOVE_BUTTON_WIDTH)+IMAGEVIEW_WIDTH);
    public static int COL_COUNT=4;
    
    //nodes
    private Button leftButton, rightButton;
    private ImageView img;
    private VBox innerVBox;
    private ProgressBar healthBar;
    private Text nameText, descriptionText;
    
    //stats
    private int colNum;
    private Entity entity;
    int train = 0, gather = 0;//put this in Entity.java?
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
    private void moveEntityTaskDisplay(boolean goingRight){
        int change = -1;
        if(goingRight) change = 1;
        
        //add check here?
        
	//actual moving
        currentGDC.removeEntityTaskList(entity, colNum);
        colNum += change;
        currentGDC.addEntityTaskList(entity);
	//whatever effect
	switch(colNum){
            case 0://fight
                entity.setTarget(Building.getByIndex(Enshrine.fightArea));
                break;
            case 1://train
		entity.setTarget(Building.getTrainingBuildings().get(train));
                break;
            case 2://craft
                entity.setTarget(Building.getByIndex(Enshrine.craftTable));
                break;
            case 3://gather
		entity.setTarget(Building.getGatheringBuildings().get(gather));
                break;
            default:
                
        }
        
	//check buttons
	checkMoveButtonLimits();
    }
    private void checkMoveButtonLimits(){
	leftButton.setDisable(false);
	rightButton.setDisable(false);
	if(colNum==0) leftButton.setDisable(true);
	if(colNum==COL_COUNT) rightButton.setDisable(true);
    }
    private void toggleActivity() {
        switch(colNum){
            case 0://fight
                break;
            case 1://train
		train++;
		if(train==2) train=0;
		entity.setTarget(Building.getTrainingBuildings().get(train));
                break;
            case 2://craft
		openCraftMenu();
                break;
            case 3://gather
		gather++;
		if(gather==3) gather=0;
		entity.setTarget(Building.getGatheringBuildings().get(gather));
                break;
            default:
                
        }
    }
    private void openCraftMenu(){
        
    }
    private void openEntityDisplay(){
        //close all
        EntityMenuDisplayController.closeAll();
        //open new
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
            //set entity
            EntityMenuDisplayController controller = loader.getController();
            controller.displayEntity(entity);
        }
        catch(IOException exception){
            //error
        }
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
