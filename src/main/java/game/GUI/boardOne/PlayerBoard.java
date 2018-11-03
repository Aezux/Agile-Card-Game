package game.GUI.boardOne;

import game.backend.ImageFinder;
import game.backend.LabelMaker;
import game.interfaces.PlayerComponent;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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

    ArrayList<PlayerComponent> players;
    String playerChosen;
    LabelMaker labelMaker = new LabelMaker();

    /* Launches the GUI */
    public static void main(String[] args) {
        launch(args);
    }

    /* Starts the program */
    public void start(Stage playerStage) throws Exception {
    	playerStage.setTitle("Player Board");
    	playerStage.setScene(getScene());
    	playerStage.show();
        startScene();
    }

    public Scene getScene(){

        root = new AnchorPane();

        ImageFinder image = new ImageFinder();
        ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));
        
        Label boardLabel = labelMaker.createLabel("Draw Cards", Color.WHITE, 6, 6, 475, 50);

        root.getChildren().addAll(imageView, boardLabel);

        scene = new Scene(root, 1050, 900);

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
}
