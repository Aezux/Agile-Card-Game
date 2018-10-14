package game;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/* 
 * mythology - 20
 * geography - 22
 * animals - 27
 * sports - 21
 * 
 * */

public class Main extends Application{
	
	static ArrayList<Card> handDealt = new ArrayList<Card>(); 
	Point2D clickPoint;
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Agile Scrum Master");
		
		scene.setOnMouseClicked(mouseHandler);
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseEntered(mouseHandler);
		scene.setOnMouseExited(mouseHandler);
		scene.setOnMouseMoved(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Question question = new Question();
		for (int i = 0; i < 6; i ++) {
			Card card = question.getCard("27");
			System.out.println("Question: " + card.getQuestion());
			System.out.println("Choices: " + card.getChoices());
			System.out.println("Answer: " + card.getAnswer());
			System.out.println("Points: " + card.getPoints());
			handDealt.add(card);
		}
	}
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			
			clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			String eventName = mouseEvent.getEventType().getName();
			
			switch(eventName) {
			
				case("MOUSE_RELEASED"):
					
					break;
				case("MOUSE_DRAGGED"):
					
					break;
				case("MOUSE_PRESSED"):
					
					break;
			}
		}
		
	};
}
