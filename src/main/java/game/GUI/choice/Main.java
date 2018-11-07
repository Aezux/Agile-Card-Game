package game.GUI.choice;

import game.backend.Card;
import game.backend.Question;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Main extends Application{
	
	Topic topic;
	Choice choice;

	/* Launches the GUI */
	public static void main(String[] args) {
		launch(args);
	}

	/* Starts the program with the Choice scene */
	public void start(Stage cardStage) throws Exception {
		Choice choice = new Choice();
		cardStage.setTitle("Choice Scene");
		cardStage.setScene(choice.getScene());
		cardStage.show();
		cardStage.addEventHandler(ActionEvent.ACTION, actionHandler);
		choice.startScene();
		choice.move();
		System.out.println(choice);
		
	}
	EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			System.out.println(choice.getTopic());
			if (choice.getTopic() != null) {
				
				//window.setScene(choice.getScene());
				//choice.startScene();
				//choice.move();
				//window.show();
				
				if (choice.getTopic() != null) {
					Question question = new Question();
					Card card = question.getCard(choice.getTopic());
					//window.setScene(board2.getScene(card));
					//window.setTitle("Enter an Answer");
					//board2.startScene();
					//window.show();
				}
			}
			
		}
		
	};

}