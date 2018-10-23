package game;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/* 
 * mythology - 20
 * geography - 22
 * animals - 27
 * sports - 21
 * 
 * */

public class Main extends Application{
	
//	static ArrayList<Card> handDealt = new ArrayList<Card>(); 
	ArrayList<CardComponent> handDealt = new ArrayList<CardComponent>();
	Point2D clickPoint;
	Stage window;
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
//		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Pane root = new AnchorPane();
		Scene scene = new Scene(root, 1050, 900);
		
		
		
		scene.setOnMouseClicked(mouseHandler);
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseEntered(mouseHandler);
		scene.setOnMouseExited(mouseHandler);
		scene.setOnMouseMoved(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
		
				
		int x = 50;
		int y = 200;
		Question question = new Question();
		HBox hand = new HBox();
		for (int i = 0; i < 6; i ++) {				
			Card card = question.getCard("27");
			System.out.println("Question: " + card.getQuestion());
			System.out.println("Choices: " + card.getChoices());
			System.out.println("Answer: " + card.getAnswer());
			System.out.println("Points: " + card.getPoints());
			System.out.println("Category:" + card.getCategory());
			StackPane layout = new StackPane();
			Text text = new Text(card.getQuestion());
			text.setLayoutX(200);
			int width = (int) text.getLayoutBounds().getWidth();
			System.out.println("Width" + width);
			CardComponent cardComponent = new CardComponent(new Point2D(x, y), 100, width, card);		
			
			layout.getChildren().addAll(cardComponent.getRect(), text);
			layout.setLayoutX(x);
			layout.setLayoutY(y);
			layout.setPadding(new Insets (10));
			hand.getChildren().add(layout);
			
//			root.getChildren().add(layout);
			handDealt.add(cardComponent);
			x += 300;
		}
		
		hand.setLayoutX(20);
		hand.setLayoutY(100);
		root.getChildren().add(hand);
		window = primaryStage;
		window.setTitle("Agile Scrum Master");
		window.setScene(scene);
		window.show();
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
