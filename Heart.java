package shark_game_objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Heart extends Item {
	
	private int width;
	private int height;
	private Image image;
	
	public Heart(int xCoordinate) {
		loadImage();
		this.xCoordinateOnScreen = xCoordinate;
		this.yCoordinateOnScreen = 0;
	}
	
	private void loadImage() {
		ImageIcon imageContainer = new ImageIcon("src/pictures/Heart.png");
		Image unscaledImage = imageContainer.getImage();

		width = unscaledImage.getWidth(null);
		height = unscaledImage.getHeight(null);
		
		image = unscaledImage.getScaledInstance((width / 10), (height / 10), Image.SCALE_DEFAULT);
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Image getImage() {
		return image;
	}
	
}
