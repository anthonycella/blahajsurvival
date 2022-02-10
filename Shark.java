package shark_game_objects;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Shark extends Item {

	private int xIncrement;
	private int incrementValue;
	private int yIncrement;
	private int width;
	private int height;
	private Image image;
	private Image reversedImage;
	private Image shownImage;

	public Shark() {
		this.xCoordinateOnScreen = 300;
		this.yCoordinateOnScreen = 200;
		this.incrementValue = 3;
		loadImage();
		loadReversedImage();
	}

	private void loadImage() {

		ImageIcon imageContainer = new ImageIcon("src/pictures/Shark Sprite GIF.gif");
		Image imageUnscaled = imageContainer.getImage();

		width = imageUnscaled.getWidth(null);
		height = imageUnscaled.getHeight(null);

		image = imageUnscaled.getScaledInstance((width / 3), (height / 3), Image.SCALE_DEFAULT);

		shownImage = image;

	}

	private void loadReversedImage() {

		ImageIcon reverseImageContainer = new ImageIcon("src/pictures/Shark Sprite GIF Reverse.gif");
		Image reversedImageUnscaled = reverseImageContainer.getImage();

		width = reversedImageUnscaled.getWidth(null);
		height = reversedImageUnscaled.getHeight(null);

		reversedImage = reversedImageUnscaled.getScaledInstance((width / 3), (height / 3), Image.SCALE_DEFAULT);

	}

	public void move() {

		int currentXCoordinate = getxCoordinateOnScreen();
		int currentYCoordinate = getyCoordinateOnScreen();

		xIncrement = calibrateXIncrement(currentXCoordinate, xIncrement);
		yIncrement = calibrateYIncrement(currentYCoordinate, yIncrement);

		incrementxCoordinateOnScreen(xIncrement);
		incrementyCoordinateOnScreen(yIncrement);

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getXIncrement() {
		return xIncrement;
	}

	public int getYIncrement() {
		return yIncrement;
	}

	public Image getImage() {
		return image;
	}

	public Image getReversedImage() {
		return reversedImage;
	}

	public Image getShownImage() {
		return shownImage;
	}

	public void endProcess() {
		this.xCoordinateOnScreen = 1000;
		this.yCoordinateOnScreen = 1000;
	}

	public void resetProcess() {
		this.xCoordinateOnScreen = 300;
		this.yCoordinateOnScreen = 200;
	}

	public void keyPressed(KeyEvent event) {

		int key = event.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			xIncrement = -incrementValue;
			shownImage = image;
		}

		if (key == KeyEvent.VK_RIGHT) {
			xIncrement = incrementValue;
			shownImage = reversedImage;
		}

		if (key == KeyEvent.VK_UP) {
			yIncrement = -incrementValue;
		}

		if (key == KeyEvent.VK_DOWN) {
			yIncrement = incrementValue;
		}
	}

	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			xIncrement = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			xIncrement = 0;
		}

		if (key == KeyEvent.VK_UP) {
			yIncrement = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			yIncrement = 0;
		}
	}

	public int calibrateXIncrement(int currentXCoordinate, int currentXIncrement) {

		if (currentXCoordinate < -50 || currentXCoordinate > 650) {
			currentXIncrement = -currentXIncrement;
			
		}

		return currentXIncrement;
	}

	public int calibrateYIncrement(int currentYCoordinate, int currentYIncrement) {

		if (currentYCoordinate < -50 || currentYCoordinate > 475) {
			currentYIncrement = -currentYIncrement;
		}

		return currentYIncrement;
	}
	
	public void setIncrementValue(int increment) {
		incrementValue = increment;
	}
	
	public void increaseIncrementValue() {
		incrementValue++;
	}
}
