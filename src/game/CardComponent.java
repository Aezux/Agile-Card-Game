package game;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardComponent implements ShapeComponent{

	Boolean moveable;
	Point2D topLeft;
	int height, width;
	Card card;
	Rectangle rect;
	Color color;
	LabelLeaf label;
	
	public CardComponent(Point2D topLeft, int height, int width, Card card) {
		this.topLeft = topLeft;
		this.height = height;
		this.width = width;
		this.card = card;
		rect = new Rectangle();
		switch(card.getPoints()) {
			case(10):
				this.color = Color.GREEN;
				break;
			case(20):
				this.color = Color.YELLOW;
				break;
			case(30):
				this.color = Color.RED;
				break;
			default:
				this.color = Color.BLUE;
				break;
		}
	}
	
	//returns a rectangle
	public Rectangle getRect(){
		return rect;
	}
		
	//returns the color of the rect
	public Color getColor(){
		return color;
	}
		
	
	//Makes the Card movable, so that the user can drag and drop it into their Hand
	@Override
	public void move(double dx, double dy) {
		// TODO Auto-generated method stub
		rect.setX(rect.getX() + dx);
		rect.setY(rect.getY() + dy);
	}

	@Override
	public boolean contains(Point2D clickpoint) {
		// TODO Auto-generated method stub
		return false;
	}

}
