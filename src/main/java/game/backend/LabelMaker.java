package game.backend;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LabelMaker {
	
	/* Creates a Label */
	public Label createLabel(String name, Color color, int scaleX, int scaleY, int posX, int posY) {
    	Label label = new Label(name);
    	label.setTextFill(color);
    	label.setScaleX(scaleX);
    	label.setScaleY(scaleY);
    	label.setTranslateX(posX);
    	label.setTranslateY(posY);
    	return label;
    }
	
}
