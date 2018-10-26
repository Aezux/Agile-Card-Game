package main.java.game.backend;

import javafx.scene.image.Image;

public class ImageFinder {
	
	/* Returns the Image that you request */
	public Image getImage(String imageName, int scale, int dimensions) {
		
		Image image = null;
		
		/* Finds your image and switches the file path based on the operating system you are using */
		if (System.getProperty("os.name").startsWith("Windows")) {
			image = new Image("file:src\\images\\" + imageName, scale*dimensions, scale*dimensions, true, true);
		}
		else {
			image = new Image("file:src//images//" + imageName, scale*dimensions, scale*dimensions, true, true);
		}
		
		return image;
	}
	
}
