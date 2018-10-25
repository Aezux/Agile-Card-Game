package main.java.game.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class InputBox extends Application  {
    Scene scene;
    Stage inputStage;
    Button button;
    String prompt;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){
        //Setting our stage
        inputStage = stage;
        inputStage.setTitle("Enter your answer: ");

        //Form
        TextField userInput = new TextField();
        button = new Button("Submit");
        button.setOnAction(e -> isAnswer(userInput.getText()));

        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(userInput, button);

        scene = new Scene(layout, 300, 100 );
        inputStage.setScene(scene);
        inputStage.show();
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    private boolean isAnswer(String textInput){

            if  (
                    (textInput.toLowerCase().equals("a")) ||
                    (textInput.toLowerCase().equals("b")) ||
                    (textInput.toLowerCase().equals("c")) ||
                    (textInput.toLowerCase().equals("d"))
                 ) {
                System.out.println("User has entered a valid answer.");
                return false;
                   }
            else {
                System.out.println("User has not entered a valid answer.");
                return true;
            }
        }
}
