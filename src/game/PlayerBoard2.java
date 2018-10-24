package game;

import java.util.ArrayList;

import game.choice.ImageFinder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerBoard2 extends Application {

	private final int dimensions = 20;
	private final int scale = 50;
	private Pane root;
	private Scene scene;
	private ArrayList<PlayerComponent> players;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Enter an Answer");
		primaryStage.setScene(getScene());
		startScene();
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private String getQuestion() {
		
		// get the current Card with the player's question here:
		return "This is the current question. Who played the role?";
	}
	
	private boolean checkAnswer(String answer) {
		// TODO: check the answer
		return true;
	}

	public Scene getScene() {
		
		root = new AnchorPane();
		ImageFinder image = new ImageFinder();
		ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));
		
		// display the question text
		Text questionLabel = new Text(getQuestion());
		questionLabel.setFill(Color.BLACK);
		questionLabel.setFont(Font.font(30));
		questionLabel.setTranslateX(120);
		questionLabel.setTranslateY(150);
		
		// display the answer input box
		TextField answerField = new TextField();
		answerField.setPromptText("Answer:");
		answerField.setFont(Font.font(20));
		answerField.setPrefColumnCount(30);
		answerField.setTranslateX(120);
		answerField.setTranslateY(320);
		answerField.setAlignment(Pos.CENTER);
		
		// display the answer text results label
		Text actionTargetText = new Text();
		actionTargetText.setFont(Font.font(18));
		actionTargetText.setFill(Color.FIREBRICK);
		actionTargetText.setTranslateX(120);
		actionTargetText.setTranslateY(380);
		
		// display the button to submit an answer
		Button button = new Button("Submit Answer");
		button.setFont(Font.font(20));
		button.setTranslateX(120);
		button.setTranslateY(410);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (answerField.getText() != null && !answerField.getText().isEmpty()) {
					// TODO: check the answer in input field
					boolean result = checkAnswer(answerField.getText());
					if (result) actionTargetText.setText("Correct!");
					else actionTargetText.setText("Incorrect answer.");
				}
			}
		});
		
		root.getChildren().addAll(imageView, questionLabel, answerField, 
				actionTargetText, button);
		scene = new Scene(root, 1050, 900);
		return scene;
	}

	public void startScene() {
		
		players = new ArrayList<PlayerComponent>();
		
		PlayerComponent playerOne = new PlayerComponent(new Point2D(30,615), "Player 1");
		players.add(playerOne);
		Label playerOneLabel = new Label("Player 1");
		playerOneLabel.setTextFill(Color.BLACK);
		playerOneLabel.setScaleX(2);
		playerOneLabel.setScaleY(2);
		playerOneLabel.setTranslateX(87);
		playerOneLabel.setTranslateY(745);
		root.getChildren().addAll(playerOne.getPlayerSpace(), playerOneLabel);
		
		PlayerComponent playerTwo = new PlayerComponent(new Point2D(300,615), "Player 2");
		players.add(playerTwo);
		Label playerTwoLabel = new Label("Player 2");
		playerTwoLabel.setTextFill(Color.BLACK);
		playerTwoLabel.setScaleX(2);
		playerTwoLabel.setScaleY(2);
		playerTwoLabel.setTranslateX(357);
		playerTwoLabel.setTranslateY(745);
		root.getChildren().addAll(playerTwo.getPlayerSpace(), playerTwoLabel);
		
		PlayerComponent playerThree = new PlayerComponent(new Point2D(560,615), "Player 3");
		players.add(playerThree);
		Label playerThreeLabel = new Label("Player 3");
		playerThreeLabel.setTextFill(Color.BLACK);
		playerThreeLabel.setScaleX(2);
		playerThreeLabel.setScaleY(2);
		playerThreeLabel.setTranslateX(617);
		playerThreeLabel.setTranslateY(745);
		root.getChildren().addAll(playerThree.getPlayerSpace(), playerThreeLabel);
		
		PlayerComponent playerFour = new PlayerComponent(new Point2D(820,615), "Player 4");
		players.add(playerFour);
		Label playerFourLabel = new Label("Player 4");
		playerFourLabel.setTextFill(Color.BLACK);
		playerFourLabel.setScaleX(2);
		playerFourLabel.setScaleY(2);
		playerFourLabel.setTranslateX(877);
		playerFourLabel.setTranslateY(745);
		root.getChildren().addAll(playerFour.getPlayerSpace(), playerFourLabel);
	}
}

