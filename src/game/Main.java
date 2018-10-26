package game;

import game.choice.Choice;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application{
	
	Welcome welcome;
	Board board;
	Choice choice;
	Stage window;

	@Override
	public void start(Stage window) throws Exception {
		this.window = window;
		window.setTitle("Agile Scrum Master");
		welcome = new Welcome();
		board = new Board();
		choice = new Choice();
		
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
			}
			
		}
		
	};
	
	public static void main(String args[]) {
		launch(args);
	}
}
