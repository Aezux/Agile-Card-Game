package game.GUI.boardOne;

import game.GUI.CardComponent;
import game.backend.Card;
import game.backend.ImageFinder;
import game.backend.Question;
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

    /* Launches the GUI */
    public static void main(String[] args) {
        launch(args);
    }

    /* Starts the program */
    public void start(Stage boardStage) throws Exception {
        PlayerBoard board = new PlayerBoard();
        boardStage.setTitle("Player Board");
        boardStage.setScene(board.getScene());
        boardStage.show();
        board.startScene();
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
        Question question = new Question();

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

        PlayerComponent playerOne = new PlayerComponent(new Point2D(30,615), "Player 1");
        players.add(playerOne);
        Label playerOneLabel = new Label("Player 1");
        playerOneLabel.setTextFill(Color.BLACK);
        playerOneLabel.setScaleX(2);
        playerOneLabel.setScaleY(2);
        playerOneLabel.setTranslateX(87);
        playerOneLabel.setTranslateY(745);
        root.getChildren().addAll(playerOne.getPlayerSpace(), playerOneLabel);

        PlayerComponent playerTwo = new PlayerComponent(new Point2D(300,615), "Player 2");
        players.add(playerTwo);
        Label playerTwoLabel = new Label("Player 2");
        playerTwoLabel.setTextFill(Color.BLACK);
        playerTwoLabel.setScaleX(2);
        playerTwoLabel.setScaleY(2);
        playerTwoLabel.setTranslateX(357);
        playerTwoLabel.setTranslateY(745);
        root.getChildren().addAll(playerTwo.getPlayerSpace(), playerTwoLabel);

        PlayerComponent playerThree = new PlayerComponent(new Point2D(560,615), "Player 3");
        players.add(playerThree);
        Label playerThreeLabel = new Label("Player 3");
        playerThreeLabel.setTextFill(Color.BLACK);
        playerThreeLabel.setScaleX(2);
        playerThreeLabel.setScaleY(2);
        playerThreeLabel.setTranslateX(617);
        playerThreeLabel.setTranslateY(745);
        root.getChildren().addAll(playerThree.getPlayerSpace(), playerThreeLabel);

        PlayerComponent playerFour = new PlayerComponent(new Point2D(820,615), "Player 4");
        players.add(playerFour);
        Label playerFourLabel = new Label("Player 4");
        playerFourLabel.setTextFill(Color.BLACK);
        playerFourLabel.setScaleX(2);
        playerFourLabel.setScaleY(2);
        playerFourLabel.setTranslateX(877);
        playerFourLabel.setTranslateY(745);
        root.getChildren().addAll(playerFour.getPlayerSpace(), playerFourLabel);
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
