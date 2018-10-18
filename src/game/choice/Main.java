package game.choice;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	/* Launches the GUI */
	public static void main(String[] args) {
		launch(args);
	}

	/* Starts the program with the Choice scene */
	public void start(Stage cardStage) throws Exception {
		Choice choice = new Choice();
		cardStage.setTitle("Choice Scene");
		cardStage.setScene(choice.getScene());
		cardStage.show();
		choice.startScene();
		choice.move();
	}

}
