package shark_game_objects;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class PlasticBottle extends Item {

	protected int width;
	protected int height;
	protected int xIncrement;
	protected int storedIncrement;
	protected int startXStored;
	protected Image image;
	protected Image reversedImage;
	private Image sodaCanImage;
	private Image sodaCanImageReversed;
	private Image plasticBagImage;
	private Image plasticBagImageReversed;
	protected Image shownImage;

	public PlasticBottle() {
		this.xIncrement = 2;
		loadImage();
		loadReversedImage();
		loadPlasticBagImage();
		loadPlasticBagReversedImage();
		loadSodaCanImage();
		loadSodaCanReversedImage();

	}

	public PlasticBottle(int xStart) {
		this.xIncrement = 2;
		loadImage();
		loadReversedImage();
		loadPlasticBagImage();
		loadPlasticBagReversedImage();
		loadSodaCanImage();
		loadSodaCanReversedImage();

		this.startXStored = xStart;
		this.xCoordinateOnScreen = xStart;
		this.yCoordinateOnScreen = generateYCoordinate();
	}

	public static void incrementAllPlasticBottles(PlasticBottle[] plasticBottles) {
		for (int i = 0; i < plasticBottles.length; i++) {
			PlasticBottle currentPlasticBottle = plasticBottles[i];
			currentPlasticBottle.incrementXIncrement();
		}
	}

	public void loadImage() {

		ImageIcon imageContainer = new ImageIcon("src/pictures/Plastic Bottle.png");
		Image imageUnscaled = imageContainer.getImage();

		width = imageUnscaled.getWidth(null);
		height = imageUnscaled.getHeight(null);

		image = imageUnscaled.getScaledInstance((width / 10), (height / 10), Image.SCALE_DEFAULT);

		shownImage = image;
	}

	public void loadReversedImage() {

		ImageIcon reverseImageContainer = new ImageIcon("src/pictures/Plastic Bottle Reverse.png");
		Image reversedImageUnscaled = reverseImageContainer.getImage();

		width = reversedImageUnscaled.getWidth(null);
		height = reversedImageUnscaled.getHeight(null);

		reversedImage = reversedImageUnscaled.getScaledInstance((width / 10), (height / 10), Image.SCALE_DEFAULT);

	}

	public void loadPlasticBagImage() {

		ImageIcon plasticBagImageContainer = new ImageIcon("src/pictures/Plastic Bag.png");
		Image plasticBagImageUnscaled = plasticBagImageContainer.getImage();

		width = plasticBagImageUnscaled.getWidth(null);
		height = plasticBagImageUnscaled.getHeight(null);

		plasticBagImage = plasticBagImageUnscaled.getScaledInstance((width / 10), (height / 10), Image.SCALE_DEFAULT);
	}

	public void loadPlasticBagReversedImage() {

		ImageIcon reversePlasticBagImageContainer = new ImageIcon("src/pictures/Plastic Bag Reverse.png");
		Image reversePlasticBagImageUnscaled = reversePlasticBagImageContainer.getImage();

		width = reversePlasticBagImageUnscaled.getWidth(null);
		height = reversePlasticBagImageUnscaled.getHeight(null);

		plasticBagImageReversed = reversePlasticBagImageUnscaled.getScaledInstance((width / 10), (height / 10),
				Image.SCALE_DEFAULT);
	}

	public void loadSodaCanImage() {

		ImageIcon sodaCanImageContainer = new ImageIcon("src/pictures/Soda Can.png");
		Image sodaCanImageUnscaled = sodaCanImageContainer.getImage();

		width = sodaCanImageUnscaled.getWidth(null);
		height = sodaCanImageUnscaled.getHeight(null);

		sodaCanImage = sodaCanImageUnscaled.getScaledInstance((width / 13), (height / 13), Image.SCALE_DEFAULT);
	}

	public void loadSodaCanReversedImage() {

		ImageIcon reverseSodaCanImageContainer = new ImageIcon("src/pictures/Soda Can.png");
		Image reverseSodaCanImageUnscaled = reverseSodaCanImageContainer.getImage();

		width = reverseSodaCanImageUnscaled.getWidth(null);
		height = reverseSodaCanImageUnscaled.getHeight(null);

		sodaCanImageReversed = reverseSodaCanImageUnscaled.getScaledInstance((width / 15), (height / 15),
				Image.SCALE_DEFAULT);
	}

	public void move() {
		incrementxCoordinateOnScreen(xIncrement);

		if (xCoordinateOnScreen > 900) {
			generateNewStart();
		}
	}

	public void generateNewStart() {
		shownImage = selectImage();
		yCoordinateOnScreen = generateYCoordinate();
		xCoordinateOnScreen = -100;
		
	}
	
	public void generateNewStart(PlasticBottle[] plasticBottles) {
		boolean isTaken = false;
		shownImage = selectImage();
		yCoordinateOnScreen = generateYCoordinate();
		xCoordinateOnScreen = -100;
		PlasticBottle plasticBottleInQuestion = null;
		
		for(int i = 0; i < plasticBottles.length; i++) {
			PlasticBottle currentPlasticBottle = plasticBottles[i];
			int currentPlasticBottleXLocation = currentPlasticBottle.getxCoordinateOnScreen();
			
			if (xCoordinateOnScreen == currentPlasticBottleXLocation) {
				isTaken = true;
				plasticBottleInQuestion = currentPlasticBottle;
			}
		}
		
		while(isTaken) {
			yCoordinateOnScreen = generateYCoordinate();
			isTaken = findIfTaken(plasticBottleInQuestion.getyCoordinateOnScreen(), 
					yCoordinateOnScreen);
		}
		
		
		
	}

	public Image selectImage() {
		Image currentImage = image;

		Random randomNumberGenerator = new Random();
		int selectionNumber = randomNumberGenerator.nextInt(60) + 1;

		if (selectionNumber < 10) {
			currentImage = sodaCanImage;
		} else if (selectionNumber < 20) {
			currentImage = sodaCanImageReversed;
		} else if (selectionNumber < 30) {
			currentImage = plasticBagImage;
		} else if (selectionNumber < 40) {
			currentImage = plasticBagImageReversed;
		} else if (selectionNumber < 50) {
			currentImage = image;
		} else {
			currentImage = reversedImage;
		}

		return currentImage;
	}

	public int getWidthByType() {

		int width = -1;

		if (shownImage.equals(sodaCanImage) || shownImage.equals(sodaCanImageReversed)) {
			width = 20;
		} else if (shownImage.equals(image) || shownImage.equals(reversedImage)) {
			width = 25;
		} else if (shownImage.equals(plasticBagImage) || shownImage.equals(plasticBagImageReversed)) {
			width = 25;
		}

		return width;
	}

	public int getHeightByType() {

		int height = -1;

		if (shownImage.equals(sodaCanImage) || shownImage.equals(sodaCanImageReversed)) {
			height = 35;
		} else if (shownImage.equals(image) || shownImage.equals(reversedImage)) {
			height = 50;
		} else if (shownImage.equals(plasticBagImage) || shownImage.equals(plasticBagImageReversed)) {
			height = 40;
		}

		return height;
	}

	public int getYAdjustmentByType() {
		int yAdjustment = 0;

		if (shownImage.equals(sodaCanImage) || shownImage.equals(sodaCanImageReversed)) {
			yAdjustment = -35;
		} else if (shownImage.equals(image) || shownImage.equals(reversedImage)) {
			yAdjustment = -20;
		} else if (shownImage.equals(plasticBagImage) || shownImage.equals(plasticBagImageReversed)) {
			yAdjustment = -20;
		}

		return yAdjustment;
	}
	
	public int getXAdjustmentByType() {
		int xAdjustment = 0;

		if (shownImage.equals(sodaCanImage) || shownImage.equals(sodaCanImageReversed)) {
			xAdjustment = -15;
		} else if (shownImage.equals(image) || shownImage.equals(reversedImage)) {
			xAdjustment = -20;
		} else if (shownImage.equals(plasticBagImage) || shownImage.equals(plasticBagImageReversed)) {
			xAdjustment = 0;
		}

		return xAdjustment;
	}

	public int generateYCoordinate() {
		Random randomNumberGenerator = new Random();
		int yCoordinate = randomNumberGenerator.nextInt(496);

		return yCoordinate;
	}
	
	public int generateXCoordinate() {
		Random randomNumberGenerator = new Random();
		int xCoordinate = randomNumberGenerator.nextInt(496);

		return xCoordinate;
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

	public void incrementXIncrement() {
		xIncrement++;
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

	public int getXStored() {
		return startXStored;
	}

	public static void setIncrements(PlasticBottle[] plasticBottles, int incrementToSet) {
		for (int i = 0; i < plasticBottles.length; i++) {
			PlasticBottle currentPlasticBottle = plasticBottles[i];
			currentPlasticBottle.setXIncrement(incrementToSet);
		}
	}
	
	public boolean findIfTaken(int possiblyTaken, int mainNumber) {
		int difference = mainNumber - possiblyTaken;
		
		if (difference < 0) {
			difference = -difference;
		}
		
		if (difference < 20) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public void setXIncrement(int increment) {
		xIncrement = increment;
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
}
