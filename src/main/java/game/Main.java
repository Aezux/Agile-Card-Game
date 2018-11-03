package game;

import game.GUI.choice.Choice;
import game.GUI.welcome.Welcome;
import game.GUI.Board;
import game.GUI.boardTwo.PlayerBoard2;

import java.util.ArrayList;

import game.backend.Card;
import game.backend.PointsKeeperSingleton;
import game.backend.Question;
import game.backend.Token;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.stage.Stage;

public class Main extends Application{
	
	Welcome welcome;
	Board board;
	Choice choice;
	PlayerBoard2 board2;
	Stage window;
	PointsKeeperSingleton teams;

	static ArrayList<Card> handDealt = new ArrayList<Card>();
	Point2D clickPoint;
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		this.window = window;
		window.setTitle("Agile Card Game");
		
		/* Adds both teams */
		teams = PointsKeeperSingleton.getUniqueInstance();
		teams.addTeam("team1");
		teams.addTeam("team2");
		
		welcome = new Welcome();
		board = new Board();
		choice = new Choice();
		board2 = new PlayerBoard2();
		
		
		window.addEventHandler(ActionEvent.ACTION, actionHandler);
				
		window.setScene(welcome.getScene());
		window.show();
	}
	
	EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			if (welcome.getValidInput()) {
				
				window.setScene(choice.getScene());
				window.setTitle("Enter your topic choice");
				choice.startScene();
				choice.move();
				window.show();
				
				if (choice.getTopic() != null) {
					Question question = new Question(new Token().getToken());
					ArrayList<Card> cards = new ArrayList<Card>();
					
					/* Add 4 cards to the ArrayList */
					for (int i=0; i<4; ++i) {
						cards.add(question.getCard(choice.getTopic()));
					}
					
					window.setScene(board2.getScene(cards, "team1"));
					window.setTitle("Enter an Answer");
					board2.startScene();
					window.show();
				}
			}
			
		}
		
	};

}
