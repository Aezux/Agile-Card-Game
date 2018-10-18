package game.choice;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Topic {

	Rectangle rectangle;
	Color color;
	
	/* Constructor */
	public Topic(Point2D pos, Color color) {	
		this.color = color;
		
		rectangle = new Rectangle();
		rectangle.setX(pos.getX());
		rectangle.setY(pos.getY());
		rectangle.setHeight(180);
		rectangle.setWidth(160);
		rectangle.setFill(Color.TRANSPARENT);
		rectangle.setStroke(color);
		rectangle.setStrokeWidth(3);
	}
	
	/* Returns the rectangle */
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	/* Returns the rectangles color */
	public Color getColor() {
		return color;
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
