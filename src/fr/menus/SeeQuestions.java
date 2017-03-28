package fr.menus;

import java.util.ArrayList;
import fr.main.Game;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SeeQuestions extends Application {
	
	public Image img,correct,wrong;
	private ArrayList<Text> texts;

    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	texts=new ArrayList<Text>();
    	Rectangle2D primaryScreenBounds = new Rectangle2D(Game.longueur*2/3, Game.hauteur*2/3,Game.longueur*2/3,  Game.hauteur*2/3);
    	Group root = new Group();
        final VBox results = new VBox();
        results.setFillWidth(false);
        results.setSpacing(Screen.getPrimary().getVisualBounds().getHeight() * 1/12);
        results.setBorder(new Border(new BorderStroke[0]));
        Scene scene = new Scene(root, Game.longueur*2/3, Game.hauteur*2/3, Color.LIGHTGREY);	

        Button button = new Button();
        final Text question1 = new Text();
        final Text clue11 = new Text();
        final Text clue12 = new Text();
        final Text clue13 = new Text();
        final Text ans11 = new Text();
        final Text ans12 = new Text();
        final Text ans13 = new Text();
        final Text ans14 = new Text();
        final double buttonSizeX = primaryScreenBounds.getWidth() * 1 / 5;
        final double buttonSizeY = primaryScreenBounds.getHeight() * 1 / 15; 
        double barX=primaryScreenBounds.getWidth()*45/100;
        double barY=primaryScreenBounds.getHeight()*1/20;
        
        ImageView image=new ImageView();
    	image.setImage(img);
    	image.setLayoutY(primaryScreenBounds.getHeight()*85/100);
    	image.setLayoutX(primaryScreenBounds.getWidth()/2+buttonSizeX);
        
        //Propertie of the texts written
        question1.setText("what?");
        question1.setLayoutX(primaryScreenBounds.getWidth()/2-question1.getStrokeWidth()/2);
        question1.setLayoutY(primaryScreenBounds.getHeight()*1/10);
        
        
        clue11.setText("clue1");
        clue11.setLayoutX(primaryScreenBounds.getWidth()/2-clue11.getStrokeWidth()/2);
        clue11.setLayoutY(primaryScreenBounds.getHeight()*15/100);
        
        clue12.setText("clue2");
        clue12.setLayoutX(primaryScreenBounds.getWidth()/2-clue11.getStrokeWidth()/2);
        clue12.setLayoutY(primaryScreenBounds.getHeight()*20/100);
        
        clue13.setText("clue3");
        clue13.setLayoutX(primaryScreenBounds.getWidth()/2-clue11.getStrokeWidth()/2);
        clue13.setLayoutY(primaryScreenBounds.getHeight()*25/100);
        
        ans11.setText("1:");
        ans11.setLayoutX(primaryScreenBounds.getWidth()*10/100);
        ans11.setLayoutY(primaryScreenBounds.getHeight()*30/100);
        
        ans12.setText("2:");
        ans12.setLayoutX(primaryScreenBounds.getWidth()*30/100);
        ans12.setLayoutY(primaryScreenBounds.getHeight()*30/100);
        
        ans13.setText("3:");
        ans13.setLayoutX(primaryScreenBounds.getWidth()*50/100);
        ans13.setLayoutY(primaryScreenBounds.getHeight()*30/100);
        
        ans14.setText("4:");
        ans14.setLayoutX(primaryScreenBounds.getWidth()*7/10);
        ans14.setLayoutY(primaryScreenBounds.getHeight()*30/100);
        
        
        button.setLayoutX(primaryScreenBounds.getWidth()/2-buttonSizeX/2);
        button.setLayoutY(primaryScreenBounds.getHeight()*9/10);
        button.setPrefSize(buttonSizeX - 15, buttonSizeY - 15);
        button.setText("Add");

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

  
            }
        });

        
        texts.add(question1);
        texts.add(clue11);
        texts.add(clue12);
        texts.add(clue13);
        texts.add(ans11);
        texts.add(ans12);
        texts.add(ans13);
        texts.add(ans14);
        
        root.getChildren().add(button);
        root.getChildren().addAll(texts);
        root.getChildren().add(image);
        Group contentGroup = new Group();
        contentGroup.getChildren().add(results);
        
        
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }}