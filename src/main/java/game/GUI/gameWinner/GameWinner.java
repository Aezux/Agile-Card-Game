package game.GUI.gameWinner;

import game.backend.ImageFinder;
import game.backend.PointsKeeperSingleton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

@SuppressWarnings("restriction")
public class GameWinner extends Application {
	
	private final int dimensions = 20;
	private final int scale = 50;
	private Pane root;
	private Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
//		primaryStage.setTitle("Congratulations");
//		primaryStage.setScene(getScene());
//		startScene();
//		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private String getWinner() {
		String winningTeam = PointsKeeperSingleton.getUniqueInstance().getWinner();
		int points = PointsKeeperSingleton.getUniqueInstance().getTeamScore(winningTeam);
		String result = winningTeam + " you have won the game with \n" + points + " story points.";
		return result;
	}
	
	public Scene getScene() {
		root = new AnchorPane();
		ImageFinder image = new ImageFinder();
		ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));
		
		// display the congratulations text
		Text congratsLabel = new Text("Congratulations");
		congratsLabel.setFill(Color.WHITE);
		congratsLabel.setFont(Font.font(40));
		congratsLabel.setTranslateX(120);
		congratsLabel.setTranslateY(150);
		
		// display the button to exit the game
		Button button = new Button("Exit");
		button.setFont(Font.font(20));
		button.setTranslateX(120);
		button.setTranslateY(410);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// TODO: end the game
			}
		});
		
		root.getChildren().addAll(imageView, congratsLabel, button);
		scene = new Scene(root, 1050, 900);
		return scene;
	}
	
	public void startScene() {
		// display the winning team message
		Text winnerLabel = new Text(getWinner());
		winnerLabel.setFill(Color.BLACK);
		winnerLabel.setFont(Font.font(30));
		winnerLabel.setTranslateX(120);
		winnerLabel.setTranslateY(220);
		root.getChildren().addAll(winnerLabel);
	}
}

