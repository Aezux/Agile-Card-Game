package game.GUI.welcome;

import game.backend.ImageFinder;
import game.backend.PointsKeeperSingleton;
import game.backend.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class chooseTeam extends Application {

    private final String HEADER = "The Agile Trivia Card Game";
    private final String NAME_PROMPT = "Enter your name: ";
    private final String TEAM_PROMPT = "Select which team to join (1 or 2): ";
    private final int dimensions = 20;
    private final int scale = 50;
    private Pane root;
    private Scene scene;
    private boolean validInput = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Choose Team");
        primaryStage.setScene(getScene());
        primaryStage.show();
    }

    public Scene getScene() {
        root = new AnchorPane();
        ImageFinder image = new ImageFinder();
        ImageView imageView = new ImageView(image.getImage("background.jpg", scale, dimensions + 7));

        // display the welcome text
        Text promptLabel = new Text(HEADER);
        promptLabel.setFill(Color.BLACK);
        promptLabel.setFont(Font.font(30));
        promptLabel.setTranslateX(120);
        promptLabel.setTranslateY(150);

        // display the name prompt
        Text inputLabel = new Text(NAME_PROMPT);
        inputLabel.setFill(Color.BLACK);
        inputLabel.setFont(Font.font(30));
        inputLabel.setTranslateX(120);
        inputLabel.setTranslateY(250);

        // display the name input box
        TextField nameField = new TextField();
        nameField.setFont(Font.font(20));
        nameField.setPrefColumnCount(10);
        nameField.setTranslateX(120);
        nameField.setTranslateY(305);
        nameField.setAlignment(Pos.CENTER);

        // display the team prompt text
        Text inputLabelTwo = new Text(TEAM_PROMPT);
        inputLabelTwo.setFill(Color.BLACK);
        inputLabelTwo.setFont(Font.font(30));
        inputLabelTwo.setTranslateX(120);
        inputLabelTwo.setTranslateY(410);

        // display the team answer box
        TextField teamField = new TextField();
        teamField.setFont(Font.font(20));
        teamField.setPrefColumnCount(10);
        teamField.setTranslateX(120);
        teamField.setTranslateY(460);
        teamField.setAlignment(Pos.CENTER);

        // display the error on input label
        Text actionTargetText = new Text();
        actionTargetText.setFont(Font.font(18));
        actionTargetText.setFill(Color.FIREBRICK);
        actionTargetText.setTranslateX(120);
        actionTargetText.setTranslateY(523);

        // display the button to submit an answer
        Button button = new Button("Submit");
        button.setFont(Font.font(20));
        button.setTranslateX(120);
        button.setTranslateY(540);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((nameField.getText() != null && !nameField.getText().isEmpty()) &&
                        (teamField.getText() != null && !teamField.getText().isEmpty())) {

                    try {
                        String nameInput = nameField.getText();
                        String teamInput = teamField.getText();
                        actionTargetText.setText("");
                        System.out.println(nameInput + " has joined Team " + teamInput);
                        Player newPlayer = new Player(nameInput, teamInput);
                        PointsKeeperSingleton.getUniqueInstance().addPlayerToTeam(teamInput, newPlayer);
                        validInput = true;

                    } catch (Exception exception) {
                        actionTargetText.setText("Please enter a name and team number.");
                    }
                }
            }
        });

        root.getChildren().addAll(imageView, promptLabel, inputLabel,
                nameField, inputLabelTwo, teamField, actionTargetText, button);
        scene = new Scene(root, 1050, 900);

        return scene;
    }
}
