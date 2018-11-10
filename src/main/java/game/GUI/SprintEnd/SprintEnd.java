package game.GUI.SprintEnd;

import java.util.Random;

import game.backend.ImageFinder;
import game.backend.LabelMaker;
import game.backend.PointsKeeperSingleton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SprintEnd extends Application {
	
	private final int dimensions = 20;
	private final int scale = 50;
	private Pane root;
	private Scene scene;
	private String team;
	private PointsKeeperSingleton teams = PointsKeeperSingleton.getUniqueInstance();
	private LabelMaker labelMaker = new LabelMaker();
	Random rand = new Random();

	@Override
	public void start(Stage primaryStage) throws Exception {
		teams.addTeam("team1");
		primaryStage.setTitle("Sprint Results");
		primaryStage.setScene(getScene("team1"));
		primaryStage.show();
	}
	
	/* Launches the program */
	public static void main(String[] args) {
		launch(args);
	}
	
	/* Product owner gives or takes points */
	private String getPoints() {
		int	points = getRandomNumber(16);
		if (getRandomNumber(2) == 1) {
			teams.removePointsFromScore(team, points);
			points *= -1;
		} else {
			teams.addPointsToScore(team, points);
		}
		
		return "The product owner\ngives you: " + points + " points";
	}
	
	/* Gets the scene */
	public Scene getScene(String team) {
		this.team = team;
		root = new AnchorPane();
		
		ImageFinder image = new ImageFinder();
		ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));
		
		Label sprint = labelMaker.createLabel("Sprint is Over", Color.ORANGE, 6, 6, 475, 50);
		Label results = labelMaker.createLabel(getPoints(), Color.WHITE, 6, 6, 475, 250);
		Label total = labelMaker.createLabel("total: " + teams.getTeamScore(team), Color.WHITE, 6, 6, 475, 400);
		
		Button button = new Button("CONTINUE");
		button.setScaleX(5);
        button.setScaleY(5);
        button.setTranslateX(475);
        button.setTranslateY(750);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("User clicked continue");
			}
		});
		
		root.getChildren().addAll(imageView, sprint, results, total, button);
		
		scene = new Scene(root, 1050, 900);
		return scene;
	}
	
	/* Generates a number from 0 to maxRandom */
	private int getRandomNumber(int maxRandom) {
		return rand.nextInt(maxRandom);
	}
	
}
