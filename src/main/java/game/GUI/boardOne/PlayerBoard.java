package game.GUI.boardOne;

import game.GUI.CardComponent;
import game.backend.Card;
import game.backend.ImageFinder;
import game.backend.LabelMaker;
import game.backend.PointsKeeperSingleton;
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
    int numOfPlayers;
    ArrayList<PlayerComponent> players;
    String playerChosen;
    LabelMaker labelMaker = new LabelMaker();
    String topic;

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
        boardLabel.setTextFill(Color.ORANGE);
        boardLabel.setScaleX(6);
        boardLabel.setScaleY(6);
        boardLabel.setTranslateX(475);
        boardLabel.setTranslateY(50);

        root.getChildren().addAll(imageView, boardLabel);

        scene = new Scene(root, 1250, 900);

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
        	Card card = new Card();
        	//Only put this if and else case in here so that i can test this class without testing the Main in the Game package
        	if (topic != null) {
        		card = question.getCard(topic);
        	}
        	else { 
        		card = question.getCard("27");
        	}

            CardComponent cardComponent = new CardComponent(new Point2D(x, y), 100, 150, card);
            root.getChildren().add(cardComponent.getRect());
            handDealt.add(cardComponent);
            x += 200;
        }

        return scene;
    }

    public void startScene(){
        players = new ArrayList<PlayerComponent>();
        
        //This is is so that we can run this program on its own
        if (numOfPlayers == 0) numOfPlayers = 4;
        
        int positionX = 90;
        double posX = 30;
        for (int i = 0; i < this.numOfPlayers; i++) {
        	String playerName = PointsKeeperSingleton.getUniqueInstance().getTeams().get(0).get(i);
//        	String playerName = "Player " + (i+1);
        	PlayerComponent player = new PlayerComponent(new Point2D(posX,615), playerName);        	
        	Label label = labelMaker.createLabel(playerName, Color.BLACK, 2, 2, positionX, 745);
        	root.getChildren().addAll(player.getPlayerSpace(), label);
        	player.setLabel(label);
        	players.add(player);
        	positionX += 200;
        	posX += 200;
        }
        
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
                    			handDealt.remove(cardComp);
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
    
    //Return the ArrayList of players
    public ArrayList<PlayerComponent> getPlayers(){
    	return this.players;
    }

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
    
    //Sets topic
    public void setTopic(String topic) {
    	this.topic = topic;
    }
    
    //Gets topic
    public String getTopic() {
    	return topic;
    }
    
    //Returns handDealt
    public ArrayList<CardComponent> getHandDealt() {
    	return handDealt;
    }
    
    //sets number of players per team
    public void setNumOfPlayers(int num) {
    	this.numOfPlayers = num;
    }
    
    public int getNumOfPlayers() {
    	return this.numOfPlayers;
    }
    
}
