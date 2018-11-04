package game.GUI.choice;

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
import game.backend.ImageFinder;
import game.backend.LabelMaker;

public class Choice extends Application{

    final int dimensions = 20;
    final int scale = 50;

    Pane root;
    Scene scene;

    ArrayList<Topic> topics;
    String topicChosen;
    LabelMaker labelMaker = new LabelMaker();

    /* Launches the GUI */
    public static void main(String[] args) {
        launch(args);
    }

    /* Starts the program */
    public void start(Stage cardStage) throws Exception {
    	cardStage.setTitle("Choice Scene");
		cardStage.setScene(getScene());
		cardStage.show();
		startScene();
		move();
    }

    public Scene getScene() {

        root = new AnchorPane();

        /* Background image */
        ImageFinder image = new ImageFinder();
        ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions+7));

        /* Sprint Planning label */
        Label sprintLabel = labelMaker.createLabel("Sprint Planning", Color.ORANGE, 6, 6, 475, 50);

        /* Backlog label */
        Label backlogLabel = labelMaker.createLabel("Pick a topic from the backlog", Color.WHITE, 3, 4, 426, 120);

        /* Button */
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
        Label animalsLabel = labelMaker.createLabel("Animals", Color.BLACK, 2, 2, 90, 430);
        root.getChildren().addAll(animals.getCard(), animalsLabel);
        topics.add(animals);
        
        /* Makes the mythology topic */
        Topic mythology = new Topic(new Point2D(270,300), "20");
        Label mythologyLabel = labelMaker.createLabel("Mythology", Color.BLACK, 2, 2, 350, 430);
        root.getChildren().addAll(mythology.getCard(), mythologyLabel);
        topics.add(mythology);
        
        /* Makes the geography topic */
        Topic geography = new Topic(new Point2D(530,300), "22");
        Label geographyLabel = labelMaker.createLabel("Geography", Color.BLACK, 2, 2, 610, 430);
        root.getChildren().addAll(geography.getCard(), geographyLabel);
        topics.add(geography);
        
        /* Makes the sports topic */
        Topic sports = new Topic(new Point2D(790,300), "21");
        Label sportsLabel = labelMaker.createLabel("Sports", Color.BLACK, 2, 2, 870, 430);
        root.getChildren().addAll(sports.getCard(), sportsLabel);
        topics.add(sports);
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
    
    /* Returns the topic number */
    public String getTopic() {
    	return topicChosen;
    }

}