package shark_game_objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Fish extends PlasticBottle {
	private int width;
	private int height;
	private int startXStored;
	private int xIncrement;
	private int storedIncrement;
	private Image shownImage;

	public Fish() {
		this.xIncrement = 2;
		loadImage();

	}

	public Fish(int xStart) {
		this.xIncrement = 2;
		loadImage();
		this.startXStored = xStart;
		this.xCoordinateOnScreen = xStart;
		this.yCoordinateOnScreen = generateYCoordinate();
	}

	public static void incrementAllFish(Fish[] fishes) {
		for (int i = 0; i < fishes.length; i++) {
			Fish currentFish = fishes[i];
			currentFish.incrementXIncrement();
		}
	}

	public void loadImage() {

		ImageIcon imageContainer = new ImageIcon("src/pictures/Fish.png");
		Image imageUnscaled = imageContainer.getImage();

		width = imageUnscaled.getWidth(null);
		height = imageUnscaled.getHeight(null);

		shownImage = imageUnscaled.getScaledInstance((width / 10), (height / 10), Image.SCALE_DEFAULT);

	}

	public void move() {
		incrementxCoordinateOnScreen(xIncrement);

		if (xCoordinateOnScreen > 900) {
			generateNewStart();
		}
	}

	public void generateNewStart() {
		yCoordinateOnScreen = generateYCoordinate();
		xCoordinateOnScreen = -1500;

	}

	public int generateYCoordinate() {
		Random randomNumberGenerator = new Random();
		int yCoordinate = randomNumberGenerator.nextInt(506) - 25;

		return yCoordinate;
	}

	public int generateYCoordinate(PlasticBottle[] plasticBottles) {
		Random randomNumberGenerator = new Random();
		int yCoordinate = 0;
		boolean isValid = false;

		while (!isValid) {
			isValid = true;
			yCoordinate = randomNumberGenerator.nextInt(506) - 25;

			for (int i = 0; i < plasticBottles.length; i++) {
				PlasticBottle currentPlasticBottle = plasticBottles[i];
				Rectangle plasticBottleTangle = new Rectangle(currentPlasticBottle.getxCoordinateOnScreen(),
						currentPlasticBottle.getyCoordinateOnScreen(), 20, 50);
				Rectangle currentRectangle = new Rectangle(xCoordinateOnScreen - 20, yCoordinate, 40, 15);

				isValid = !collisionDetected(plasticBottleTangle, currentRectangle);
			}
		}

		return yCoordinate;
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

	public int getXStored() {
		return startXStored;
	}

	public void incrementXIncrement() {
		xIncrement++;
	}
	
	public static void setIncrements(Fish[] fishies, int newIncrement) {
		for (int i = 0; i < fishies.length; i++) {
			Fish currentFish = fishies[i];
			currentFish.setXIncrement(newIncrement);
		}
	}

	public boolean collisionDetected(Rectangle firstRectangle, Rectangle secondRectangle) {
		return firstRectangle.intersects(secondRectangle);
	}
	
	public void setXIncrement(int increment) {
		xIncrement = increment;
	}

	public void endProcess() {
		storedIncrement = xIncrement;
		xIncrement = 0;
		xCoordinateOnScreen = 1000;
		yCoordinateOnScreen = 1000;
	}

	public void resetProcess() {
		xIncrement = storedIncrement;
		xCoordinateOnScreen = getXStored();
		yCoordinateOnScreen = generateYCoordinate();
	}

	public Image getShownImage() {
		return shownImage;
	}
}
