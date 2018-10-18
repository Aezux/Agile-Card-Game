package game.choice;

import java.awt.Point;
import java.util.ArrayList;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



import javafx.geometry.Point2D;


public class Choice extends Application{


	final int dimensions = 20;
	final int scale = 50;

	/* Launches the GUI */
	public static void main(String[] args) {
		launch(args);
	}

	/* Starts the program */
	public void start(Stage oceanStage) throws Exception {
		// Nothing happens when you run this class
	}
	
	public Scene getScene() {
		
		Pane screen = new AnchorPane();
		
		Label backlogLabel = new Label("Sprint Planning");
		Label backlogLabel2 = new Label("Pick a topic from the backlog");
		
		ImageFinder image = new ImageFinder();
		ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));
		
		
		backlogLabel.setTextFill(Color.WHITE);
		backlogLabel.setScaleX(6);
		backlogLabel.setScaleY(6);
		backlogLabel.setTranslateX(460);
		backlogLabel.setTranslateY(50);
		
		backlogLabel2.setTextFill(Color.WHITE);
		backlogLabel2.setScaleX(3);
		backlogLabel2.setScaleY(4);
		backlogLabel2.setTranslateX(400);
		backlogLabel2.setTranslateY(120);
		
		
		
		/* Box to hold everything in it */
		Scene newinput = new Scene(screen, scale*dimensions, scale*dimensions);
		
		
//		Button button = new Button("Select choice");
		
		screen.getChildren().addAll(imageView, backlogLabel, backlogLabel2);
		
		return newinput;
	}

}
