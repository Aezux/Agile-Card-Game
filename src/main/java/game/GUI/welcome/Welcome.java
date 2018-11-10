package game.GUI.welcome;

import java.util.ArrayList;

import game.backend.ImageFinder;
import game.backend.PointsKeeperSingleton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Welcome extends Application {

	private final String WELCOME_MESSAGE = "The Agile Trivia Card Game";
	private final String INPUT_PROMPT = "Enter Number of Players per Team:";
	private final int dimensions = 20;
	private final int scale = 50;
	private Pane root;
	private Scene scene;
	private boolean validInput = false;
	private int numPlayersPerTeam;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome");
		primaryStage.setScene(getScene());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void initializeTeams(int players) {
		PointsKeeperSingleton.getUniqueInstance().addTeam("Team 1");
		PointsKeeperSingleton.getUniqueInstance().addTeam("Team 2");
		// TODO: integrate the number of players to the game
	}
	
	
	public boolean getValidInput() {
		return validInput;
	}

	public Scene getScene() {
		root = new AnchorPane();
		ImageFinder image = new ImageFinder();
		ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));

		// display the welcome text
		Text welcomeLabel = new Text(WELCOME_MESSAGE);
		welcomeLabel.setFill(Color.BLACK);
		welcomeLabel.setFont(Font.font(30));
		welcomeLabel.setTranslateX(120);
		welcomeLabel.setTranslateY(150);
		
		// display the input prompt text
		Text inputLabel = new Text(INPUT_PROMPT);
		inputLabel.setFill(Color.BLACK);
		inputLabel.setFont(Font.font(30));
		inputLabel.setTranslateX(120);
		inputLabel.setTranslateY(250);

		// display the answer input box
		TextField answerField = new TextField();
		answerField.setFont(Font.font(20));
		answerField.setPrefColumnCount(10);
		answerField.setTranslateX(120);
		answerField.setTranslateY(320);
		answerField.setAlignment(Pos.CENTER);

		// display the error on input label
		Text actionTargetText = new Text();
		actionTargetText.setFont(Font.font(18));
		actionTargetText.setFill(Color.FIREBRICK);
		actionTargetText.setTranslateX(120);
		actionTargetText.setTranslateY(380);

		// display the button to submit an answer
		Button button = new Button("Submit");
		button.setFont(Font.font(20));
		button.setTranslateX(120);
		button.setTranslateY(410);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int result = 0;
				if (answerField.getText() != null && !answerField.getText().isEmpty()) {
					try {
						actionTargetText.setText("");
						result = Integer.parseInt(answerField.getText());
						if (result >=4 && result <= 6) {
							initializeTeams(result);
							validInput = true;
							System.out.println(validInput);
							numPlayersPerTeam = result;
							PointsKeeperSingleton.getUniqueInstance().setPlayersPerTeam(numPlayersPerTeam);
							//initializing the list of teams with empty teams
							PointsKeeperSingleton.getUniqueInstance().getTeams().add(new ArrayList<String>());
							PointsKeeperSingleton.getUniqueInstance().getTeams().add(new ArrayList<String>());
						}else {
							actionTargetText.setText("Please enter a number between 4 and 6");
						}
						
					} catch (Exception exception) {
						actionTargetText.setText("Please enter a number between 4 and 6.");
					}
				}
			}
		});
		
		root.getChildren().addAll(imageView, welcomeLabel, inputLabel, 
				answerField, actionTargetText, button);
		scene = new Scene(root, 1250, 900);
		return scene;
	}
	
	// returns the number of players per team
	public int getNumPlayersPerTeam() {
		return this.numPlayersPerTeam;
	}
}

