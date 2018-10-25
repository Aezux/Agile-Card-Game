package main.java.game.GUI.choice;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Topic {

	Rectangle rectangle;
	boolean clicked;
	String topic;

	/* Constructor */
	public Topic(Point2D pos, String topic) {
		this.topic = topic;
		clicked = false;
		rectangle = new Rectangle();
		rectangle.setX(pos.getX());
		rectangle.setY(pos.getY());
		rectangle.setHeight(300);
		rectangle.setWidth(250);
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.BLACK);
		rectangle.setStrokeWidth(3);
	}

	/* Returns the rectangle */
	public Rectangle getCard() {
		return rectangle;
	}

	/* Checks if a card has been clicked */
	public boolean checkClicked() {
		return clicked;
	}

	/* User clicks on the card */
	public void clicked() {
		if (clicked) {
			clicked = false;
		} else {
			clicked = true;
		}
	}

	/* Changes the outline of the card */
	public void changeColor() {
		if (clicked) {
			rectangle.setStroke(Color.YELLOW);
		} else {
			rectangle.setStroke(Color.BLACK);
		}
	}

	/* Returns the topic */
	public String getTopic() {
		return topic;
	}

	/* Returns the rectangles location */
	public Point2D location() {
		Point2D location = new Point2D(rectangle.getX(), rectangle.getY());
		return location;
	}

	/* Checks if the container contains a point */
	public boolean contains(double x, double y) {
		return rectangle.contains(x, y);
	}

}