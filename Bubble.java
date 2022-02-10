package shark_game_objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bubble extends Item {
	private int width;
	private int height;
	private int originalXCoordinate;
	private int originalYCoordinate;
	private BubbleType bubbleType;
	private Image imageShown;

	public Bubble(int xCoordinate, int yCoordinate, BubbleType bubbleType) {
		this.xCoordinateOnScreen = xCoordinate;
		this.yCoordinateOnScreen = yCoordinate;
		this.originalXCoordinate = xCoordinate;
		this.originalYCoordinate = yCoordinate;
		this.bubbleType = bubbleType;
		loadImage();
	}

	public void loadImage() {
		ImageIcon imageContainer = new ImageIcon("src/pictures/Bubble.png");
		Image imageUnscaled = imageContainer.getImage();

		width = imageUnscaled.getWidth(null);
		height = imageUnscaled.getHeight(null);

		Image smallBubble = imageUnscaled.getScaledInstance((width / 35), (height / 35), Image.SCALE_DEFAULT);
		Image mediumBubble = imageUnscaled.getScaledInstance((width / 25), (height / 25), Image.SCALE_DEFAULT);
		Image largeBubble = imageUnscaled.getScaledInstance((width / 15), (height / 15), Image.SCALE_DEFAULT);

		imageShown = selectBubbleType(bubbleType, smallBubble, mediumBubble, largeBubble);
	}

	public Image selectBubbleType(BubbleType bubbleType, Image smallBubble, Image mediumBubble, Image largeBubble) {
		
		Image newImage = null;
		
		switch (bubbleType) {
		case SMALL_BUBBLE:
			newImage = smallBubble;
			break;
		case MEDIUM_BUBBLE:
			newImage = mediumBubble;
			break;
		case LARGE_BUBBLE:
			newImage = largeBubble;
			break;
		}
		
		return newImage;
	}

	public void move() {
		incrementYCoordinateOnScreen();

		if (yCoordinateOnScreen < -100) {
			yCoordinateOnScreen = 550;
		}

	}

	public void incrementYCoordinateOnScreen() {
		yCoordinateOnScreen--;
	}

	public void setXCoordinate(int xCoordinate) {
		this.xCoordinateOnScreen = xCoordinate;
	}

	public Image getShownImage() {
		return imageShown;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getOriginalXCoordinate() {
		return originalXCoordinate;
	}
	
	public int getOriginalYCoordinate() {
		return originalYCoordinate;
	}
}
