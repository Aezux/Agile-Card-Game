package game.interfaces;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerComponent {
    Rectangle rectangle;
    boolean hasQuestion;
    String player;


    public PlayerComponent(Point2D pos, String player) {
        this.player = player;
        hasQuestion = false;
        rectangle = new Rectangle();
        rectangle.setX(pos.getX());
        rectangle.setY(pos.getY());
        rectangle.setHeight(200);
        rectangle.setWidth(150);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
    }

    //Returns the rectangle
    public Rectangle getPlayerSpace() {
        return rectangle;
    }

    //updates the players turn
    public void playerTurn() {
        if (hasQuestion) hasQuestion = false;
        else hasQuestion = true;
    }

    // Changes outline if player has the current question
    public void changeColor() {
        if (hasQuestion) {
            rectangle.setStroke(Color.YELLOW);
        } else {
            rectangle.setStroke(Color.BLACK);
        }
    }

    // Returns the player selected
    public String getPlayer() {
        return player;
    }

    // Returns the location of the player space
    public Point2D location() {
        Point2D location = new Point2D(rectangle.getX(), rectangle.getY());
        return location;
    }

    /* Checks if the container contains a point */
    public boolean contains(double x, double y) {
        return rectangle.contains(x, y);
    }

}
