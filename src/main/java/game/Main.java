package game;

import game.GUI.choice.Choice;
import game.GUI.welcome.Welcome;
import game.GUI.Board;
import game.GUI.SprintEnd.SprintEnd;
import game.GUI.boardOne.PlayerBoard;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application{
	
	Welcome welcome;
	Board board;
	Choice choice;
	PlayerBoard board1;
	PlayerBoard2 board2;	
	SprintEnd sprintEnd;
	Stage window;
	PointsKeeperSingleton teams;
	int sceneCounter = 0;

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
		board1 = new PlayerBoard();
		board2 = new PlayerBoard2();
                
                		
		
		window.addEventHandler(ActionEvent.ACTION, actionHandler);
		window.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseHandler);
				
		window.setScene(welcome.getScene());
		window.show();
	}
	
	EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			if (welcome.getValidInput() && sceneCounter == 0) {

				try {
					
					choice.start(window);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
			//Set the topic and change to the next board
			if (choice.getTopic() != null && sceneCounter == 0) {
				try {
					int numPlayerPerTeam = welcome.getNumPlayersPerTeam();
					String topic = choice.getTopic();
					board1.setNumOfPlayers(numPlayerPerTeam);
					board1.setTopic(topic);
					board1.start(window);
					sceneCounter++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
	};
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			String eventName = mouseEvent.getEventType().getName();
			
			switch(eventName) {
				case("MOUSE_RELEASED"):
					//If there are no cards left to be dealt to the players, and this isn't their first time then continue 
					if (board1.getHandDealt().isEmpty() && sceneCounter == 1) {
						try {
							if (board1.getPlayers() == null) {
								System.out.println("RETURNING a NULL Value");
							} else System.out.println("returning playes");
							board2.setListofPlayers(board1.getPlayers());
							board2.start(window);
							sceneCounter++;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				default:
					break;
			}
		}
		
	};

}
