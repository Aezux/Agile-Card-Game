package game;

import game.GUI.choice.Choice;
import game.GUI.welcome.Welcome;
import game.GUI.Board;
import game.GUI.SprintEnd.SprintEnd;
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
	SprintEnd sprintEnd;
	Stage window;
	PointsKeeperSingleton teams;

	static ArrayList<Card> handDealt = new ArrayList<Card>();
	Point2D clickPoint;
	boolean firstTime = true;
	
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
			if (welcome.getValidInput() && firstTime) {

				try {
					choice.start(window);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
			if (choice.getTopic() != null && firstTime) {
				try {
					board2.start(window);
					firstTime = false;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
	};

}
