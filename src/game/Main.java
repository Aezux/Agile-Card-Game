package game;

import java.util.ArrayList;

import game.choice.ImageFinder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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

//		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
		HBox hand = new HBox();
		for (int i = 0; i < 6; i++) {				
			Card card = question.getCard("27");
			System.out.println("Question: " + card.getQuestion());
			System.out.println("Choices: " + card.getChoices());
			System.out.println("Answer: " + card.getAnswer());
			System.out.println("Points: " + card.getPoints());
			System.out.println("Category:" + card.getCategory());
			
//			StackPane layout = new StackPane();
//			Text text = new Text(card.getQuestion());
//			int width = (int) text.getLayoutBounds().getWidth();
//			System.out.println("Width" + width);
//			
//			Making the cardComponent based on the size of the text (width+10 is used for padding)
//			CardComponent cardComponent = new CardComponent(new Point2D(x, y), 100, width+10, card);		
//			
//			layout.getChildren().addAll(cardComponent.getRect(), text);
//			layout.setLayoutX(x);
//			layout.setLayoutY(y);
//			layout.setPadding(new Insets (10));
//			hand.getChildren().add(layout);
			
			CardComponent cardComponent = new CardComponent(new Point2D(x, y), 100, 150, card);
			root.getChildren().add(cardComponent.getRect());
			handDealt.add(cardComponent);
			x += 200;
		}
		
//		hand.setLayoutX(20);
//		hand.setLayoutY(100);
//		root.getChildren().add(hand);
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
//						Still need to implement code here for when they release the card on a player 
						
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
						initialPositionX = ((CardComponent) currentComponent).getRect().getX();
						initialPositionY = ((CardComponent) currentComponent).getRect().getY();
						System.out.println("X: " + initialPositionX);
						System.out.println("Y: " + initialPositionY);
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
