package shark_game_objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class OpeningGraphic extends Item {
	private int width;
	private int height;
	private Image image;
	
	public OpeningGraphic() {
		this.xCoordinateOnScreen = 150;
		this.yCoordinateOnScreen = 0;
		loadImage();
	}
	
	private void loadImage() {
		ImageIcon imageContainer = new ImageIcon("src/pictures/Opening Graphic.png");
		Image unscaledImage = imageContainer.getImage();

		width = unscaledImage.getWidth(null);
		height = unscaledImage.getHeight(null);

		image = unscaledImage.getScaledInstance((width / 2), (height / 2), Image.SCALE_DEFAULT);
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
