package game.GUI.boardOne;

import game.GUI.CardComponent;
import game.backend.Card;
import game.backend.ImageFinder;
import game.backend.LabelMaker;

import game.backend.Question;
import game.backend.Token;
import game.interfaces.PlayerComponent;
import game.interfaces.ShapeComponent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PlayerBoard extends Application {

    final int dimensions = 20;
    final int scale = 50;

    Pane root;
    Scene scene;

    ArrayList<CardComponent> handDealt = new ArrayList<CardComponent>();
    Point2D clickPoint;
    ShapeComponent currentComponent;
    boolean inDragMode;
    Point2D lastPosition;
    double initialPositionX = 0;
    double initialPositionY = 0;

    ArrayList<PlayerComponent> players;
    String playerChosen;
    LabelMaker labelMaker = new LabelMaker();

    /* Launches the GUI */
    public static void main(String[] args) {
        launch(args);
    }

    /* Starts the program */
    public void start(Stage boardStage) throws Exception {
        boardStage.setTitle("Player Board");
        boardStage.setScene(getScene());
        boardStage.show();
        startScene();
    }

    public Scene getScene(){

        root = new AnchorPane();

        ImageFinder image = new ImageFinder();
        ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));

        Label boardLabel = new Label("Draw From the Backlog");
        boardLabel.setTextFill(Color.WHITE);
        boardLabel.setScaleX(6);
        boardLabel.setScaleY(6);
        boardLabel.setTranslateX(475);
        boardLabel.setTranslateY(50);

        root.getChildren().addAll(imageView, boardLabel);

        scene = new Scene(root, 1050, 900);

        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        scene.setOnMouseEntered(mouseHandler);
        scene.setOnMouseExited(mouseHandler);
        scene.setOnMouseMoved(mouseHandler);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);

        int x = 50;
        int y = 200;
        Question question = new Question(new Token().getToken());

        for (int i = 0; i < 6; i++) {
            Card card = question.getCard("27");

//			System.out.println("Question: " + card.getQuestion());
//			System.out.println("Choices: " + card.getChoices());
//			System.out.println("Answer: " + card.getAnswer());
//			System.out.println("Points: " + card.getPoints());
//			System.out.println("Category:" + card.getCategory());

            CardComponent cardComponent = new CardComponent(new Point2D(x, y), 100, 150, card);
            root.getChildren().add(cardComponent.getRect());
            handDealt.add(cardComponent);
            x += 200;
        }

        return scene;
    }

    public void startScene(){
        players = new ArrayList<PlayerComponent>();

        /* Player 1 */
        PlayerComponent playerOne = new PlayerComponent(new Point2D(30,615), "Player 1");
        Label playerOneLabel = labelMaker.createLabel("Player 1", Color.BLACK, 2, 2, 87, 745);
        root.getChildren().addAll(playerOne.getPlayerSpace(), playerOneLabel);
        players.add(playerOne);
        
        /* Player 2 */
        PlayerComponent playerTwo = new PlayerComponent(new Point2D(300,615), "Player 2");
        Label playerTwoLabel = labelMaker.createLabel("Player 2", Color.BLACK, 2, 2, 357, 745);
        root.getChildren().addAll(playerTwo.getPlayerSpace(), playerTwoLabel);
        players.add(playerTwo);
        
        /* Player 3 */
        PlayerComponent playerThree = new PlayerComponent(new Point2D(560,615), "Player 3");        
        Label playerThreeLabel = labelMaker.createLabel("Player 3", Color.BLACK, 2, 2, 617, 745);
        root.getChildren().addAll(playerThree.getPlayerSpace(), playerThreeLabel);
        players.add(playerThree);
        
        /* Player 4 */
        PlayerComponent playerFour = new PlayerComponent(new Point2D(820,615), "Player 4");
        Label playerFourLabel = labelMaker.createLabel("Player 4", Color.BLACK, 2, 2, 877, 745);
        root.getChildren().addAll(playerFour.getPlayerSpace(), playerFourLabel);
        players.add(playerFour);
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
                    	
                    	//Adds card to the list of card the player has been dealt                    	
                    	for (PlayerComponent player : players) {
                    		if(player.contains(clickPoint.getX(), clickPoint.getY())) {
                    			//Casting the object as a CardComponent
                    			CardComponent cardComp = (CardComponent)currentComponent; 
                    			player.addCard(cardComp.getCard());
                    			//Removes the card from view 
                    			root.getChildren().remove(cardComp.getRect());
                    			break;
                    		}
                    	}

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
