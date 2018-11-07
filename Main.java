package game;

import game.GUI.choice.Choice;
import game.GUI.welcome.Welcome;
import game.GUI.Board;
import game.GUI.boardOne.PlayerBoard;
import game.GUI.boardTwo.PlayerBoard2;

import java.util.ArrayList;

import game.backend.Card;
import game.backend.Question;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.stage.Stage;

public class Main extends Application{
	
	Welcome welcome;
	PlayerBoard board;
	Choice choice;
	PlayerBoard2 board2;
	Stage window;

	static ArrayList<Card> handDealt = new ArrayList<Card>();
	Point2D clickPoint;
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		this.window = window;
		window.setTitle("Agile Scrum Master");
		
		
		welcome = new Welcome();
		board = new PlayerBoard();
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
				choice.startScene();
				choice.move();
				window.show();
				
				if (choice.getTopic() != null) {
					Question question = new Question();
					Card card = question.getCard(choice.getTopic());
					window.setScene(board.getScene());
					window.setTitle("Pick Card");
					board.startScene();
					window.show();
				}
			}
			
		}
		
	};
	
}
