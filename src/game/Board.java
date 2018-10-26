package game;

import java.util.ArrayList;
import game.choice.ImageFinder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/* 
 * mythology - 20
 * geography - 22
 * animals - 27
 * sports - 21
 * 
 * */

public class Board extends Application{
	
//	static ArrayList<Card> handDealt = new ArrayList<Card>(); 
	ArrayList<CardComponent> handDealt = new ArrayList<CardComponent>();
	Point2D clickPoint;
	Stage window;
	
	final int dimensions = 20;
	final int scale = 50;
	
	ShapeComponent currentComponent;
	boolean inDragMode;
	Point2D lastPosition;
	double initialPositionX = 0;
	double initialPositionY = 0;
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new AnchorPane();
		Scene scene = new Scene(root, 1300, 900);
		
//		Adds the ImageView before anything else 
		ImageFinder image = new ImageFinder();
		ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));
		root.getChildren().add(imageView);
		
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

		for (int i = 0; i < 6; i++) {				
			Card card = question.getCard("27");
	
			System.out.println("Question: " + card.getQuestion());
			System.out.println("Choices: " + card.getChoices());
			System.out.println("Answer: " + card.getAnswer());
			System.out.println("Points: " + card.getPoints());
			System.out.println("Category:" + card.getCategory());
			
			CardComponent cardComponent = new CardComponent(new Point2D(x, y), 100, 150, card);
			root.getChildren().add(cardComponent.getRect());
			handDealt.add(cardComponent);
			x += 200;
		}

		window = primaryStage;
		window.setTitle("Agile Scrum Master");
		window.setScene(scene);
		window.show();
	}
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent mouseEvent) {
			
			clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			String eventName = mouseEvent.getEventType().getName();
			
			
			if (!inDragMode) {
				currentComponent = getCurrentComponent();
			}
			
			switch(eventName) {
			
				case("MOUSE_RELEASED"):
					if (currentComponent != null && currentComponent instanceof CardComponent) {
//						TODO: Still need to implement code here for when they release the card on a Player 
						
						
//						Puts the card back to where it was when it was initially created
						((CardComponent)currentComponent).getRect().setX(initialPositionX);
						((CardComponent)currentComponent).getRect().setY(initialPositionY);
					}
					else {
						currentComponent = null;
					}
					inDragMode = false;
					break;
				case("MOUSE_DRAGGED"):
					inDragMode = true;
					if (currentComponent != null && lastPosition != null) {
						
						((CardComponent) currentComponent).getRect().getX();
						((CardComponent) currentComponent).getRect().getY();
					
//						Moves the card based on where the mouse is dragged
						double delataX = clickPoint.getX()-lastPosition.getX();
						double delataY = clickPoint.getY()-lastPosition.getY();
						currentComponent.move(delataX, delataY);
						
					}
					break;
				case("MOUSE_PRESSED"):
					if (currentComponent != null) {
//						Determines the initial position of the card so that when the card is dropped somewhere
//						besides a Player it can return to its initial position.
						initialPositionX = ((CardComponent) currentComponent).getRect().getX();
						initialPositionY = ((CardComponent) currentComponent).getRect().getY();
					}
					break;
			}
			lastPosition = clickPoint;
		}
		
	};
	
//	Looks to see what component is at the clickPoint, if none return null
	private CardComponent getCurrentComponent() {
		CardComponent currentComponent = null;
		for (CardComponent comp : handDealt) {
			if (comp.contains(clickPoint)) {
				currentComponent = comp;
			}
		}
		return currentComponent;
	}
	
}
