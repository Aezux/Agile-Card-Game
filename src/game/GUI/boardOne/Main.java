package game.GUI.boardOne;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage boardStage) throws Exception {
        PlayerBoard board = new PlayerBoard();
        boardStage.setTitle("Player Board");
        boardStage.setScene(board.getScene());
        boardStage.show();
        board.startScene();
    }

}