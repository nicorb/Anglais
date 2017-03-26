package fr.menus;

import java.io.File;
import java.util.ArrayList;
import fr.main.Game;
import fr.utils.Score;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ScorePopUp extends Application {

	public Image img,correct,wrong;
	private ArrayList<Text> texts;
	private ArrayList<TextField> searchBars;
	private static int score;

	public static void main(int a,String[] args) {
		score=a;
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		texts=new ArrayList<Text>();
		searchBars=new ArrayList<TextField>();
		Rectangle2D primaryScreenBounds = new Rectangle2D(Game.longueur*1/3, Game.hauteur*1/6,Game.longueur*1/3, Game.hauteur*1/6);
		Group root = new Group();
		final VBox results = new VBox();
		results.setFillWidth(false);
		results.setSpacing(Screen.getPrimary().getVisualBounds().getHeight() * 1/12);
		results.setBorder(new Border(new BorderStroke[0]));
		Scene scene = new Scene(root, Game.longueur*1/4, Game.hauteur*1/6, Color.LIGHTGREY);

		Button button = new Button();
		final Text t = new Text();
		final TextField name = new TextField();
		double barX=primaryScreenBounds.getWidth()*60/100;
		double barY=primaryScreenBounds.getHeight()*1/10;
		final double buttonSizeX = primaryScreenBounds.getWidth() * 3/ 10;
		final double buttonSizeY = primaryScreenBounds.getHeight() * 1 / 15; 



		//Propertie of the texts written
		t.setText("Enter your name:");
		t.setLayoutX(primaryScreenBounds.getWidth()*15/100);
		t.setLayoutY(primaryScreenBounds.getHeight()*1/4-5);

		//Property of searchbars and button
		name.setLayoutX(primaryScreenBounds.getWidth()*1/10);
		name.setLayoutY(primaryScreenBounds.getHeight()*1/4);
		name.setPrefSize(barX-15, barY-15);


		button.setLayoutX(primaryScreenBounds.getWidth()*2/5);
		button.setLayoutY(primaryScreenBounds.getHeight()*6/10);
		button.setPrefSize(buttonSizeX - 15, buttonSizeY - 15);
		button.setText("Submitt");

		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ArrayList<Score> scores = fr.database.SQLiteJDBC.getScore();
				if (scores.size()==10){
					if(score>scores.get(9).getScore()){
						for(int i=1;i<10;i++){
							if(score<scores.get(9-i).getScore()){
								fr.database.SQLiteJDBC.addScore(score, name.getText());
							}
						}
					}
				}else{
					fr.database.SQLiteJDBC.addScore(score, name.getText());
				}
				fr.menus.MainMenu.getGame().enterState(ScoreMenu.ID);
				
				primaryStage.close();
			}
		});

		searchBars.add(name);

		texts.add(t);

		root.getChildren().add(button);
		root.getChildren().addAll(searchBars);
		root.getChildren().addAll(texts);
		Group contentGroup = new Group();
		contentGroup.getChildren().add(results);


		primaryStage.setScene(scene);
		//primaryStage.setMaximized(true);
		primaryStage.show();
	}}