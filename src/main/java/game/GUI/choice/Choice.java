package main.java.game.GUI.choice;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import main.java.game.backend.ImageFinder;

public class Choice extends Application{

    final int dimensions = 20;
    final int scale = 50;

    Pane root;
    Scene scene;

    ArrayList<Topic> topics;
    String topicChosen;

    /* Launches the GUI */
    public static void main(String[] args) {
        launch(args);
    }

    /* Starts the program */
    public void start(Stage oceanStage) throws Exception {
        // Nothing happens when you run this class
    }

    public Scene getScene() {

        root = new AnchorPane();

        /* Background image */
        ImageFinder image = new ImageFinder();
        ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));

        /* Sprint Planning label */
        Label sprintLabel = new Label("Sprint Planning");
        sprintLabel.setTextFill(Color.WHITE);
        sprintLabel.setScaleX(6);
        sprintLabel.setScaleY(6);
        sprintLabel.setTranslateX(475);
        sprintLabel.setTranslateY(50);

        /* Backlog label */
        Label backlogLabel = new Label("Pick a topic from the backlog");
        backlogLabel.setTextFill(Color.WHITE);
        backlogLabel.setScaleX(3);
        backlogLabel.setScaleY(4);
        backlogLabel.setTranslateX(426);
        backlogLabel.setTranslateY(120);

        /* Button label */
        Button button = new Button("Select choice");
        button.setScaleX(5);
        button.setScaleY(5);
        button.setTranslateX(475);
        button.setTranslateY(750);

        /* User clicks the button */
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                boolean validTopic = validateInput();
                if (validTopic) {
                    //TODO: Move to the next view
                    System.out.println("The topic you chose is: " + topicChosen);
                }
            }
        });

        root.getChildren().addAll(imageView, sprintLabel, backlogLabel, button);

        /* Box to hold everything in it */
        scene = new Scene(root, 1050, 900);

        return scene;
    }

    public void startScene() {
        topics = new ArrayList<Topic>();

        /* Makes the animals topic */
        Topic animals = new Topic(new Point2D(10,300), "27");
        topics.add(animals);
        Label animalsLabel = new Label("Animals");
        animalsLabel.setTextFill(Color.BLACK);
        animalsLabel.setScaleX(2);
        animalsLabel.setScaleY(2);
        animalsLabel.setTranslateX(90);
        animalsLabel.setTranslateY(430);
        root.getChildren().addAll(animals.getCard(), animalsLabel);

        /* Makes the mythology topic */
        Topic mythology = new Topic(new Point2D(270,300), "20");
        topics.add(mythology);
        Label mythologyLabel = new Label("Mythology");
        mythologyLabel.setTextFill(Color.BLACK);
        mythologyLabel.setScaleX(2);
        mythologyLabel.setScaleY(2);
        mythologyLabel.setTranslateX(350);
        mythologyLabel.setTranslateY(430);
        root.getChildren().addAll(mythology.getCard(), mythologyLabel);

        /* Makes the geography topic */
        Topic geography = new Topic(new Point2D(530,300), "22");
        topics.add(geography);
        Label geographyLabel = new Label("Geography");
        geographyLabel.setTextFill(Color.BLACK);
        geographyLabel.setScaleX(2);
        geographyLabel.setScaleY(2);
        geographyLabel.setTranslateX(610);
        geographyLabel.setTranslateY(430);
        root.getChildren().addAll(geography.getCard(), geographyLabel);

        /* Makes the sports topic */
        Topic sports = new Topic(new Point2D(790,300), "21");
        topics.add(sports);
        Label sportsLabel = new Label("Sports");
        sportsLabel.setTextFill(Color.BLACK);
        sportsLabel.setScaleX(2);
        sportsLabel.setScaleY(2);
        sportsLabel.setTranslateX(870);
        sportsLabel.setTranslateY(430);
        root.getChildren().addAll(sports.getCard(), sportsLabel);
    }

    /* Allows you to move the dots and containers */
    public void move() {
        EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                Point2D clickPoint = new Point2D(event.getX(), event.getY());
                String eventName = event.getEventType().getName();

                switch(eventName) {
                    case("MOUSE_PRESSED"):
                        for (Topic topic : topics) {
                            if (topic.contains(clickPoint.getX(), clickPoint.getY())) {
                                topic.clicked();
                                topic.changeColor();
                            }
                        }
                        break;
                }
            }
        };

        /* Update the scene */
        scene.setOnMousePressed(mouseHandler);
    }

    /* Makes sure the user selects one topic */
    private boolean validateInput() {
        int count = 0;
        /* Counts how many cards were clicked */
        for (Topic topic : topics) {
            if (topic.checkClicked()) {
                topicChosen = topic.getTopic();
                count++;
            }
        }
        return count == 1;
    }

}