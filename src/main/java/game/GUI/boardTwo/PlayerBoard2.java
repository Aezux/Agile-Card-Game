package game.GUI.boardTwo;

import java.util.ArrayList;

import game.GUI.SprintEnd.SprintEnd;
import game.backend.Card;
import game.backend.ImageFinder;
import game.backend.LabelMaker;
import game.backend.PointsKeeperSingleton;
import game.backend.Question;
import game.backend.Token;
import game.interfaces.PlayerComponent;
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
	private Stage primaryStage;
	private String team;
	private ArrayList<PlayerComponent> players;
	private ArrayList<Card> cards;
	private LabelMaker labelMaker = new LabelMaker();
	private PointsKeeperSingleton teams = PointsKeeperSingleton.getUniqueInstance();
	private SprintEnd sprintEnd = new SprintEnd();
	int numOfPlayers;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		teams.addTeam("team1");
		primaryStage.setTitle("Enter an Answer");
		primaryStage.setScene(getScene(players, "team1"));
		PlayerTurn(0);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	/* Get the current Card with the player's question */
	private Text getQuestion(Card card) {
		
		/* Makes the question fit inside the box */
		String question = "";
		String[] questionArray = card.getQuestion().split(" ");
		for (int i=0; i<questionArray.length; ++i) {
			question += questionArray[i] + " ";
			if (i % 5 == 0 && i != 0) {
				question += "\n";
			}
		}
		
		/* Makes the answer choices fit inside the box */
		String choices = "";		
		String[] choiceArray = card.getChoices().split(",");
		choiceArray[0] = "A) " + choiceArray[0];
		choiceArray[1] = "B)" + choiceArray[1];
		choiceArray[2] = "C)" + choiceArray[2];
		choiceArray[3] = "D)" + choiceArray[3];
		for (int i=0; i<choiceArray.length; ++i) {
			choices += choiceArray[i] + "\n";
		}
		
		/* Format the question */
		Text questionLabel = new Text(question + "\n\n" + choices);
		questionLabel.setFill(Color.BLACK);
		questionLabel.setFont(Font.font(30));
		questionLabel.setTranslateX(120);
		questionLabel.setTranslateY(150);
		
		return questionLabel;
	}
	
	/* Makes an input box */
	private TextField answerBox() {
		TextField answerField = new TextField();
		answerField.setPromptText("Answer:");
		answerField.setFont(Font.font(20));
		answerField.setPrefColumnCount(30);
		answerField.setTranslateX(120);
		answerField.setTranslateY(470);
		answerField.setAlignment(Pos.CENTER);
		return answerField;
	}
	
	/* Checks the answer */
	private boolean checkAnswer(String answer, Card card) {
		return answer.toLowerCase().equals(card.getAnswer().toLowerCase());
	}

	public Scene getScene(ArrayList<PlayerComponent> players, String team) {
		this.team = team;
		this.players = players;
		root = new AnchorPane();
		
		ImageFinder image = new ImageFinder();
        ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));
        root.getChildren().add(imageView);
        
		/* Checks if the players were passed in */
		if (players == null) {
			this.players = new ArrayList<PlayerComponent>();
			makePlayers();
		} else {
			for (int i=0; i<players.size(); i++) {
				root.getChildren().addAll(players.get(i).getPlayerSpace(), players.get(i).getLabel());
			}
		}
		
		scene = new Scene(root, 1250, 900);
		return scene;
	}
	
	public void startScene() {
		PlayerTurn(0);
	}
	
	/* Shows the scene for each player */
	private void PlayerTurn(int player) {
		
		if (player == players.size()) {
			primaryStage.setScene(sprintEnd.getScene(team));
			return;
		};
		
		if (player > 0) {
			players.get(player-1).setQuestion();
			players.get(player-1).changeColor();
		}
		
		players.get(player).setQuestion();
		players.get(player).changeColor();
		
		Card card = players.get(player).getCardsDealt().poll();
		
		Text questionLabel = getQuestion(card);
		TextField answerField = answerBox();
		
		// display the button to submit an answer
		Button button = new Button("Submit Answer");
		button.setFont(Font.font(20));
		button.setTranslateX(120);
		button.setTranslateY(520);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (answerField.getText() != null && !answerField.getText().isEmpty()) {
					boolean result = checkAnswer(answerField.getText(), card);
					if (result) teams.removePointsFromScore(team, card.getPoints());
					root.getChildren().removeAll(questionLabel, answerField, button);
					PlayerTurn(player+1);
				}
			}
		});
		
		root.getChildren().addAll(questionLabel, answerField, button);
	}
	
	private void makePlayers() {
		Question question = new Question(new Token().getToken());
		
		/* Player 1 */
        PlayerComponent playerOne = new PlayerComponent(new Point2D(30,615), "Player 1");
        Label playerOneLabel = labelMaker.createLabel("Player 1", Color.BLACK, 2, 2, 87, 745);
        root.getChildren().addAll(playerOne.getPlayerSpace(), playerOneLabel);
        playerOne.addCard(question.getCard("23"));
        playerOne.setLabel(playerOneLabel);
        players.add(playerOne);
        
        /* Player 2 */
        PlayerComponent playerTwo = new PlayerComponent(new Point2D(300,615), "Player 2");
        Label playerTwoLabel = labelMaker.createLabel("Player 2", Color.BLACK, 2, 2, 357, 745);
        root.getChildren().addAll(playerTwo.getPlayerSpace(), playerTwoLabel);
        playerTwo.addCard(question.getCard("23"));
        playerTwo.setLabel(playerTwoLabel);
        players.add(playerTwo);
        
        /* Player 3 */
        PlayerComponent playerThree = new PlayerComponent(new Point2D(560,615), "Player 3");        
        Label playerThreeLabel = labelMaker.createLabel("Player 3", Color.BLACK, 2, 2, 617, 745);
        root.getChildren().addAll(playerThree.getPlayerSpace(), playerThreeLabel);
        playerThree.addCard(question.getCard("23"));
        playerThree.setLabel(playerThreeLabel);
        players.add(playerThree);
        
        /* Player 4 */
        PlayerComponent playerFour = new PlayerComponent(new Point2D(820,615), "Player 4");
        Label playerFourLabel = labelMaker.createLabel("Player 4", Color.BLACK, 2, 2, 877, 745);
        root.getChildren().addAll(playerFour.getPlayerSpace(), playerFourLabel);
        playerFour.addCard(question.getCard("23"));
        playerFour.setLabel(playerFourLabel);
        players.add(playerFour);
	}
	
	//sets the list of players (this is used so we can pass the list from PlayerBoard1 to PlayerBoard2)
	public void setListofPlayers(ArrayList<PlayerComponent> players) {
		this.players = players;
	}
	
}

