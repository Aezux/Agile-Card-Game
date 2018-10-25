package main.java.game.interfaces;

import javafx.geometry.Point2D;;

public interface ShapeComponent {
	public void move(double dx, double dy);
	public boolean contains(Point2D clickpoint);
}
