/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enshrine;

import controllers.EntityMenuDisplayController;
import controllers.GameDisplayController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private int train = 0, gather = 0;//put this in Entity.java?
    //static
    private static GameDisplayController currentGDC;

    public EntityTaskDisplay(Entity e){
        entity = e;
        int b = e.getBuildingAttemptingToReach().getIndex();
        if(b==Enshrine.fightArea) colNum=GameDisplayController.fightCol;
        else if(Enshrine.trainStart<=b&&b<=Enshrine.trainEnd) colNum=GameDisplayController.trainCol;
        else if(b==Enshrine.craftTable) colNum=GameDisplayController.craftCol;
        else if(Enshrine.gatherStart<=b&&b<=Enshrine.gatherEnd) colNum=GameDisplayController.gatherCol;
        else System.out.println("error in EntityTaskDisplay constructor");
        
        this.setMaxHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);
        //functions
        this.setOnMouseExited(event -> {removeLightEffect(entity.getDisplayCharacter());});

        //buttons
        leftButton = new Button("<");
        leftButton.setMaxHeight(HEIGHT);
        leftButton.setMaxWidth(MOVE_BUTTON_WIDTH);
        leftButton.setMinHeight(HEIGHT);
        leftButton.setMinWidth(MOVE_BUTTON_WIDTH);
        leftButton.setPrefHeight(HEIGHT);
        leftButton.setPrefWidth(MOVE_BUTTON_WIDTH);
        
        rightButton = new Button(">");
        rightButton.setMaxHeight(HEIGHT);
        rightButton.setMaxWidth(MOVE_BUTTON_WIDTH);
        rightButton.setMinHeight(HEIGHT);
        rightButton.setMinWidth(MOVE_BUTTON_WIDTH);
        rightButton.setPrefHeight(HEIGHT);
        rightButton.setPrefWidth(MOVE_BUTTON_WIDTH);
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
        img.setOnMouseEntered(event -> {addLightEffects(img);});
        img.setOnMouseExited(event -> {removeLightEffect(img);});

        //innerVBox
        innerVBox = new VBox();
        innerVBox.setMaxHeight(HEIGHT);
        innerVBox.setMaxWidth(INNERVBOX_WIDTH);
        innerVBox.setMinHeight(HEIGHT);
        innerVBox.setMinWidth(INNERVBOX_WIDTH);
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
        innerVBox.setOnMouseEntered(event -> {
            if(colNum==GameDisplayController.fightCol) addLightEffect(entity.getDisplayCharacter());
            else addLightEffects(innerVBox);
        });
        innerVBox.setOnMouseExited(event -> {removeLightEffect(innerVBox);});

        this.getChildren().addAll(leftButton, img, innerVBox, rightButton);
        checkMoveButtonLimits();
        update();
    }
    
    //getters
    public int getColNum(){ return colNum;}
    //setters
    public static void setGDC(GameDisplayController t){ currentGDC = t;}

    //methods
    public void update(){
        double s[] = entity.getStats();
        nameText.setText(entity.getName());
        healthBar.setProgress(s[0]/s[1]);
        String des="";
        switch(colNum){
            case 0://fight
                des="Fighting";
                des+=" - str: "+entity.getStats()[2];
                break;
            case 1://train
                if(train==0) des= "Str: "+entity.getStats()[2];
                if(train==1) des= "IQ: "+entity.getStats()[7];
                break;
            case 2://craft
                des="Crafting ";
                try{ des+=entity.getItemAttemptingToCraft().getName();}
                catch(NullPointerException e){}
                break;
            case 3://gather
		//des="Gathering ";
                if(gather==0) des+= "Wood: "+entity.getMaterialCnts()[gather];
                if(gather==1) des+= "Iron: "+entity.getMaterialCnts()[gather];
                if(gather==2) des+= "Food: "+entity.getMaterialCnts()[gather];
                break;
            default:
        }
        descriptionText.setText(des);
    }
    private void moveEntityTaskDisplay(boolean goingRight){
        int change = -1;
        if(goingRight) change = 1;
        
        //add check here?
        
        moveEntityTaskDisplay(change);
    }
    public void moveEntityTaskDisplay(int change){
	//actual moving of etd
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
	if(colNum==COL_COUNT-1) rightButton.setDisable(true);
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
        System.out.println("open craft menu");
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
    private void addLightEffects(Node n) {
        addLightEffect(n);
        addLightEffect(entity.getDisplayCharacter());
    }
    private void addLightEffect(Node n) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.7); // Adjust the brightness
        
        n.setEffect(colorAdjust);
    }
    private void removeLightEffect(Node n) {
        n.setEffect(null);
    }
    
}
