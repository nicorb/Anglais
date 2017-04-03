package fr.menus;

import java.util.ArrayList;
import fr.main.Game;
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

public class AddingQuestion extends Application {

	public Image img,correct,wrong;
	private ArrayList<Text> texts;
	private ArrayList<TextField> searchBars;
	private static Stage primaryStage;

	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		img=new Image("file:sprites/waiting.png");
		correct=new Image("file:sprites/check.png");
		wrong=new Image("file:sprites/false.png");

		texts=new ArrayList<Text>();
		searchBars=new ArrayList<TextField>();
		Rectangle2D primaryScreenBounds = new Rectangle2D(Game.longueur*2/3, Game.hauteur*2/3,Game.longueur*2/3,  Game.hauteur*2/3);
		Group root = new Group();
		final VBox results = new VBox();
		results.setFillWidth(false);
		results.setSpacing(Screen.getPrimary().getVisualBounds().getHeight() * 1/12);
		results.setBorder(new Border(new BorderStroke[0]));
		Scene scene = new Scene(root, Game.longueur*2/3, Game.hauteur*2/3, Color.LIGHTGREY);

		Button button = new Button();
		final Text t = new Text();
		final Text t2 = new Text();
		final Text t3 = new Text();
		final Text t4 = new Text();
		final Text t5 = new Text();
		final Text t6 = new Text();
		final Text t7 = new Text();
		final Text t8 = new Text();
		final TextField question = new TextField();
		final TextField clue1 = new TextField();
		final TextField clue2 = new TextField();
		final TextField clue3 = new TextField();
		final TextField ans1 = new TextField();
		final TextField ans2 = new TextField();
		final TextField ans3 = new TextField();
		final TextField ans4 = new TextField();
		final TextField context = new TextField();
		final ListView<String> realAns = new ListView<String>();
		ObservableList<String> oui=FXCollections.observableArrayList ("1", "2", "3", "4");
		double barX=primaryScreenBounds.getWidth()*45/100;
		double barY=primaryScreenBounds.getHeight()*1/20;
		final double buttonSizeX = primaryScreenBounds.getWidth() * 1 / 5;
		final double buttonSizeY = primaryScreenBounds.getHeight() * 1 / 15; 

		realAns.setLayoutX(100);
		realAns.setLayoutY(primaryScreenBounds.getHeight()*9/10);
		realAns.setItems(oui);
		realAns.setPrefSize(100, 30);
		realAns.setOrientation(Orientation.HORIZONTAL);

		ImageView image=new ImageView();
		image.setImage(img);
		image.setLayoutY(primaryScreenBounds.getHeight()*85/100);
		image.setLayoutX(primaryScreenBounds.getWidth()/2+buttonSizeX);

		//Propertie of the texts written
		t.setText("Write your question here");
		t.setLayoutX(primaryScreenBounds.getWidth()*5/100);
		t.setLayoutY(primaryScreenBounds.getHeight()*1/10-5);


		t2.setText("Write your 3 clues here");
		t2.setLayoutX(primaryScreenBounds.getWidth()*5/100);
		t2.setLayoutY(primaryScreenBounds.getHeight()*3/10-5);

		t3.setText("Write the 4 possible answers");
		t3.setLayoutX(primaryScreenBounds.getWidth()*55/100);
		t3.setLayoutY(primaryScreenBounds.getHeight()*1/10-5);

		t4.setText("Choose the number of the correct answer");
		t4.setLayoutX(primaryScreenBounds.getWidth()*5/100);
		t4.setLayoutY(primaryScreenBounds.getHeight()*9/10-5);

		t5.setText("1:");
		t5.setLayoutX(primaryScreenBounds.getWidth()*55/100-10);
		t5.setLayoutY(primaryScreenBounds.getHeight()*1/10+barY*2/3);

		t6.setText("2:");
		t6.setLayoutX(primaryScreenBounds.getWidth()*55/100-10);
		t6.setLayoutY(primaryScreenBounds.getHeight()*3/10+barY*2/3);

		t7.setText("3:");
		t7.setLayoutX(primaryScreenBounds.getWidth()*55/100-10);
		t7.setLayoutY(primaryScreenBounds.getHeight()*5/10+barY*2/3);

		t8.setText("4:");
		t8.setLayoutX(primaryScreenBounds.getWidth()*55/100-10);
		t8.setLayoutY(primaryScreenBounds.getHeight()*7/10+barY*2/3);

		t8.setText("Justification:");
		t8.setLayoutX(primaryScreenBounds.getWidth()*12/100);
		t8.setLayoutY(primaryScreenBounds.getHeight()*84/100);



		//Property of searchbars and button



		question.setLayoutX(primaryScreenBounds.getWidth()*5/100);
		question.setLayoutY(primaryScreenBounds.getHeight()*1/10);
		question.setPrefSize(barX - 15, barY - 15);

		clue1.setLayoutX(primaryScreenBounds.getWidth()*5/100);
		clue1.setLayoutY(primaryScreenBounds.getHeight()*3/10);
		clue1.setPrefSize(barX - 15, barY - 15);

		clue2.setLayoutX(primaryScreenBounds.getWidth()*5/100);
		clue2.setLayoutY(primaryScreenBounds.getHeight()*5/10);
		clue2.setPrefSize(barX - 15, barY - 15);

		clue3.setLayoutX(primaryScreenBounds.getWidth()*5/100);
		clue3.setLayoutY(primaryScreenBounds.getHeight()*7/10);
		clue3.setPrefSize(barX - 15, barY - 15);

		ans1.setLayoutX(primaryScreenBounds.getWidth()*55/100);
		ans1.setLayoutY(primaryScreenBounds.getHeight()*1/10);
		ans1.setPrefSize(barX - 15, barY - 15);

		ans2.setLayoutX(primaryScreenBounds.getWidth()*55/100);
		ans2.setLayoutY(primaryScreenBounds.getHeight()*3/10);
		ans2.setPrefSize(barX - 15, barY - 15);

		ans3.setLayoutX(primaryScreenBounds.getWidth()*55/100);
		ans3.setLayoutY(primaryScreenBounds.getHeight()*5/10);
		ans3.setPrefSize(barX - 15, barY - 15);

		ans4.setLayoutX(primaryScreenBounds.getWidth()*55/100);
		ans4.setLayoutY(primaryScreenBounds.getHeight()*7/10);
		ans4.setPrefSize(barX - 15, barY - 15);

		context.setLayoutX(primaryScreenBounds.getWidth()*1/5);
		context.setLayoutY(primaryScreenBounds.getHeight()*8/10);
		context.setPrefSize( primaryScreenBounds.getWidth()*3/5, barY-15);


		button.setLayoutX(primaryScreenBounds.getWidth()/2-buttonSizeX/2);
		button.setLayoutY(primaryScreenBounds.getHeight()*9/10);
		button.setPrefSize(buttonSizeX - 15, buttonSizeY - 15);
		button.setText("Add");

		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				try{
					if(fr.database.SQLiteJDBC.addQuestion(question.getText(),Integer.parseInt(realAns.getSelectionModel().getSelectedItem()), clue1.getText(), clue2.getText(), clue3.getText(), ans1.getText(), ans2.getText(), ans3.getText(),ans4.getText(),context.getText())!=0){
						image.setImage(wrong);
					}else{
						image.setImage(correct);
					}
					System.out.println();
				}catch (Exception e){
					System.out.println("impossible de lancer la fonction");
					image.setImage(wrong);;
				}

			}
		});


		searchBars.add(ans4);
		searchBars.add(ans3);
		searchBars.add(ans2);
		searchBars.add(ans1);
		searchBars.add(clue1);
		searchBars.add(clue2);
		searchBars.add(clue3);
		searchBars.add(question);
		searchBars.add(context);

		texts.add(t4);
		texts.add(t);
		texts.add(t2);
		texts.add(t3);
		texts.add(t5);
		texts.add(t6);
		texts.add(t7);
		texts.add(t8);

		root.getChildren().add(button);
		root.getChildren().addAll(searchBars);
		root.getChildren().addAll(texts);
		root.getChildren().add(realAns);
		root.getChildren().add(image);
		Group contentGroup = new Group();
		contentGroup.getChildren().add(results);


		primaryStage.setScene(scene);
		//primaryStage.setMaximized(true);
		primaryStage.show();
	}

}