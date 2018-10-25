package main.java.game.GUI.boardOne;

import main.java.game.GUI.choice.ImageFinder;
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

    /* Launches the GUI */
    public static void main(String[] args) {
        launch(args);
    }

    /* Starts the program */
    public void start(Stage oceanStage) throws Exception {
        // Nothing happens when you run this class
    }

    public Scene getScene(){

        root = new AnchorPane();

        ImageFinder image = new ImageFinder();
        ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));

        Label boardLabel = new Label("Draw Cards");
        boardLabel.setTextFill(Color.WHITE);
        boardLabel.setScaleX(6);
        boardLabel.setScaleY(6);
        boardLabel.setTranslateX(475);
        boardLabel.setTranslateY(50);

        root.getChildren().addAll(imageView, boardLabel);

        scene = new Scene(root, 1050, 900);

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
}
