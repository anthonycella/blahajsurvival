package shark_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import shark_game_objects.BackgroundGraphic;
import shark_game_objects.Bubble;
import shark_game_objects.BubbleType;
import shark_game_objects.Fish;
import shark_game_objects.GameOver;
import shark_game_objects.Heart;
import shark_game_objects.OpeningGraphic;
import shark_game_objects.PlasticBottle;
import shark_game_objects.Shark;

public class Background extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private boolean gameIsOver;
	private boolean gameHasStarted;
	private int highScore;
	private Timer timer;
	private final int DELAY = 10;
	private BackgroundGraphic backgroundGraphic;
	private int score;
	private int level;
	private String userScore;
	private String userLevel;
	private int numberOfHeartsRemaining = 3;
	private Rectangle player;
	private Rectangle fishTangle;
	private Rectangle plasticBottleTangle;
	private Shark playerShark;
	private OpeningGraphic openingGraphic;
	private GameOver gameOver;
	private Heart[] hearts = { new Heart(650), new Heart(600), new Heart(550) };
	private Bubble[] bubbles = { new Bubble(100, 350, BubbleType.SMALL_BUBBLE),
			new Bubble(50, 550, BubbleType.MEDIUM_BUBBLE), new Bubble(150, 200, BubbleType.MEDIUM_BUBBLE),
			new Bubble(200, 300, BubbleType.LARGE_BUBBLE), new Bubble(250, 500, BubbleType.MEDIUM_BUBBLE),
			new Bubble(300, 200, BubbleType.SMALL_BUBBLE), new Bubble(350, 550, BubbleType.LARGE_BUBBLE),
			new Bubble(0, 350, BubbleType.LARGE_BUBBLE), new Bubble(400, 350, BubbleType.MEDIUM_BUBBLE),
			new Bubble(450, 650, BubbleType.SMALL_BUBBLE), new Bubble(500, 750, BubbleType.MEDIUM_BUBBLE),
			new Bubble(550, 200, BubbleType.LARGE_BUBBLE), new Bubble(600, 450, BubbleType.LARGE_BUBBLE),
			new Bubble(650, 750, BubbleType.SMALL_BUBBLE), new Bubble(700, 200, BubbleType.MEDIUM_BUBBLE) };
	private PlasticBottle[] plasticBottles = { new PlasticBottle(-100), new PlasticBottle(-400),
			new PlasticBottle(-600), new PlasticBottle(-850), new PlasticBottle(-925), new PlasticBottle(-1265),
			new PlasticBottle(-1615), new PlasticBottle(-2685) };
	private Fish[] fishies = { new Fish(-500), new Fish(-900), new Fish(-1400), new Fish(-2100), new Fish(-2400) };

	public Background() {

		initializeBackground();

	}

	private void initializeBackground() {

		addKeyListener(new TAdapter());
		float[] hsbValues = new float[3];

		float[] hsbValue = Color.RGBtoHSB(40, 0, 77, hsbValues);
		setBackground(Color.getHSBColor(hsbValue[0], hsbValue[1], hsbValue[2]));
		setFocusable(true);

		playerShark = new Shark();
		openingGraphic = new OpeningGraphic();
		gameOver = new GameOver();
		backgroundGraphic = new BackgroundGraphic();
		gameIsOver = false;
		gameHasStarted = false;
		highScore = 0;
		level = 1;

		timer = new Timer(DELAY, this);
		timer.start();

	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		doDrawing(graphics);

		Toolkit.getDefaultToolkit().sync();

	}

	private void doDrawing(Graphics graphics) {

		Graphics2D graphics2D = (Graphics2D) graphics;

		Font scoreFont = new Font("Verdana", Font.PLAIN, 32);
		graphics2D.setFont(scoreFont);
		graphics2D.setColor(Color.WHITE);

		for (int i = 0; i < bubbles.length; i++) {
			Bubble currentBubble = bubbles[i];
			graphics2D.drawImage(currentBubble.getShownImage(), currentBubble.getxCoordinateOnScreen(),
					currentBubble.getyCoordinateOnScreen(), this);
		}

		graphics2D.drawImage(backgroundGraphic.getBackgroundGraphic(), backgroundGraphic.getxCoordinateOnScreen(),
				backgroundGraphic.getyCoordinateOnScreen(), this);

		if (gameHasStarted) {
			graphics2D.drawImage(playerShark.getShownImage(), playerShark.getxCoordinateOnScreen(),
					playerShark.getyCoordinateOnScreen(), this);

			for (int i = 0; i < plasticBottles.length; i++) {
				PlasticBottle plasticBottle = plasticBottles[i];
				graphics2D.drawImage(plasticBottle.getShownImage(), plasticBottle.getxCoordinateOnScreen(),
						plasticBottle.getyCoordinateOnScreen(), this);
			}

			for (int i = 0; i < fishies.length; i++) {
				Fish fish = fishies[i];
				graphics2D.drawImage(fish.getShownImage(), fish.getxCoordinateOnScreen(), fish.getyCoordinateOnScreen(),
						this);
			}

			for (int i = 0; i < numberOfHeartsRemaining; i++) {
				Heart currentHeart = hearts[i];
				graphics2D.drawImage(currentHeart.getImage(), currentHeart.getxCoordinateOnScreen(),
						currentHeart.getyCoordinateOnScreen(), this);
			}

			if (gameIsOver) {
				playerShark.setxCoordinateOnScreen(-9000);
				graphics2D.drawImage(playerShark.getImage(), playerShark.getxCoordinateOnScreen(),
						playerShark.getyCoordinateOnScreen(), this);

				String finalScore = "Final Score: " + score;
				graphics2D.drawString(finalScore, 275, 325);

				if (score > highScore) {
					highScore = score;
				}

				String highScoreStatement = "Current Highscore: " + highScore;

				graphics2D.drawString(highScoreStatement, 225, 375);

				String pressEnter = "To start a new game: press Enter";
				graphics2D.drawString(pressEnter, 125, 425);
			}

			else {

				userLevel = "Level: " + level;
				userScore = "Score: " + score;
				graphics2D.drawString(userLevel, 300, 50);
				graphics2D.drawString(userScore, 300, 100);
			}

			graphics2D.drawImage(gameOver.getImage(), gameOver.getxCoordinateOnScreen(),
					gameOver.getyCoordinateOnScreen(), this);
		}

		else {

			PlasticBottle.setIncrements(plasticBottles, 0);
			Fish.setIncrements(fishies, 0);

			graphics2D.setColor(Color.ORANGE);

			graphics2D.drawImage(openingGraphic.getImage(), openingGraphic.getxCoordinateOnScreen(),
					openingGraphic.getyCoordinateOnScreen(), this);

			String objective = "Eat the fish and avoid the trash!";
			graphics2D.drawString(objective, 125, 350);

			String movement = "Use the arrow keys to move the shark";
			graphics2D.drawString(movement, 75, 400);

			String pressEnterToBegin = "Press Enter to begin....if you dare...";
			graphics2D.drawString(pressEnterToBegin, 90, 450);

		}

	}

	private void endGame() {

		takeAwayOneHeart();

		playerShark.endProcess();

		for (int i = 0; i < plasticBottles.length; i++) {
			PlasticBottle plasticBottle = plasticBottles[i];
			plasticBottle.endProcess();
		}

		for (int i = 0; i < fishies.length; i++) {
			Fish fish = fishies[i];
			fish.endProcess();
		}

		if (numberOfHeartsRemaining == 0) {

			gameIsOver = true;
			gameOver.setxCoordinateOnScreen(150);
			gameOver.setyCoordinateOnScreen(-25);
			paintComponent(getGraphics());
		}

		else {
			paintComponent(getGraphics());
			resetVariables(playerShark, plasticBottles, fishies);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		step();

	}

	private void step() {

		if (gameIsOver) {

		}

		playerShark.move();

		int beginSharkX = playerShark.getxCoordinateOnScreen() - 1;
		int beginSharkY = playerShark.getyCoordinateOnScreen() - 1;
		int endSharkX = playerShark.getWidth() + 2;
		int endSharkY = playerShark.getHeight() + 2;

		player = new Rectangle(beginSharkX - 30, beginSharkY, 150, 20);

		repaint(beginSharkX, beginSharkY, endSharkX, endSharkY);

		for (int i = 0; i < plasticBottles.length; i++) {

			PlasticBottle plasticBottle = plasticBottles[i];

			int beginPlasticBottleX = plasticBottle.getxCoordinateOnScreen() - 1;
			int beginPlasticBottleY = plasticBottle.getyCoordinateOnScreen() - 1;
			int endPlasticBottleX = plasticBottle.getWidth() + 2;
			int endPlasticBottleY = plasticBottle.getHeight() + 2;

			int spriteWidth = plasticBottle.getWidthByType();
			int spriteHeight = plasticBottle.getHeightByType();
			int xAdjustment = plasticBottle.getXAdjustmentByType();
			int yAdjustment = plasticBottle.getYAdjustmentByType();

			plasticBottleTangle = new Rectangle(beginPlasticBottleX + xAdjustment, beginPlasticBottleY + yAdjustment,
					spriteWidth, spriteHeight);

			for (int j = 0; j < i; j++) {
				PlasticBottle otherPlasticBottle = plasticBottles[j];

				Rectangle otherRectangle = new Rectangle(otherPlasticBottle.getxCoordinateOnScreen() + xAdjustment - 1,
						otherPlasticBottle.getyCoordinateOnScreen() + yAdjustment - 1,
						otherPlasticBottle.getWidthByType(), otherPlasticBottle.getHeightByType());

				boolean isColliding = collisionDetected(plasticBottleTangle, otherRectangle);

				while (isColliding) {
					plasticBottle.setyCoordinateOnScreen(plasticBottle.generateYCoordinate());
					beginPlasticBottleY = plasticBottle.getyCoordinateOnScreen() - 1;

					plasticBottleTangle = new Rectangle(beginPlasticBottleX + xAdjustment,
							beginPlasticBottleY + yAdjustment, spriteWidth, spriteHeight);

					isColliding = collisionDetected(plasticBottleTangle, otherRectangle);

				}

			}

			plasticBottle.move();

			if (collisionDetected(player, plasticBottleTangle)) {
				endGame();
			}

			repaint(beginPlasticBottleX, beginPlasticBottleY, endPlasticBottleX, endPlasticBottleY);

		}

		for (int i = 0; i < fishies.length; i++) {

			Fish fish = fishies[i];

			fish.move();

			int beginFishX = fish.getxCoordinateOnScreen() - 1;
			int beginFishY = fish.getyCoordinateOnScreen() - 20;
			int endFishX = fish.getWidth() + 2;
			int endFishY = fish.getHeight() + 2;

			fishTangle = new Rectangle(beginFishX, beginFishY, 30, 40);

			for (int j = 0; j < plasticBottles.length; j++) {
				PlasticBottle otherPlasticBottle = plasticBottles[j];

				int xAdjustment = otherPlasticBottle.getXAdjustmentByType();
				int yAdjustment = otherPlasticBottle.getYAdjustmentByType();

				Rectangle otherRectangle = new Rectangle(otherPlasticBottle.getxCoordinateOnScreen() + xAdjustment - 1,
						otherPlasticBottle.getyCoordinateOnScreen() + yAdjustment - 1,
						otherPlasticBottle.getWidthByType(), otherPlasticBottle.getHeightByType());

				boolean isColliding = collisionDetected(fishTangle, otherRectangle);

				while (isColliding) {
					fish.setyCoordinateOnScreen(fish.generateYCoordinate());
					beginFishY = fish.getyCoordinateOnScreen() - 1;

					fishTangle = new Rectangle(beginFishX, beginFishY, 30, 40);

					isColliding = collisionDetected(fishTangle, otherRectangle);

				}
			}

			for (int m = 0; m < i; m++) {
				Fish otherFish = fishies[m];

				Rectangle otherFishRectangle = new Rectangle(otherFish.getxCoordinateOnScreen() - 1,
						otherFish.getyCoordinateOnScreen() - 1, 30, 40);

				boolean isFishColliding = collisionDetected(fishTangle, otherFishRectangle);

				while (isFishColliding) {
					fish.setyCoordinateOnScreen(fish.generateYCoordinate());
					beginFishY = fish.getyCoordinateOnScreen() - 1;

					fishTangle = new Rectangle(beginFishX, beginFishY, 30, 40);

					isFishColliding = collisionDetected(fishTangle, otherFishRectangle);

				}

			}

			if (collisionDetected(player, fishTangle)) {
				fish.generateNewStart();
				score += 100;

				if (score % 2500 == 0) {
					level++;
					Fish.incrementAllFish(fishies);
					PlasticBottle.incrementAllPlasticBottles(plasticBottles);
					playerShark.increaseIncrementValue();
				}

			}

			repaint(beginFishX, beginFishY, endFishX, endFishY);
		}

		for (int i = 0; i < bubbles.length; i++) {
			Bubble currentBubble = bubbles[i];

			currentBubble.move();

			int beginBubbleX = currentBubble.getxCoordinateOnScreen() - 1;
			int beginBubbleY = currentBubble.getyCoordinateOnScreen() - 1;
			int endBubbleX = currentBubble.getWidth() + 2;
			int endBubbleY = currentBubble.getHeight() + 2;

			repaint(beginBubbleX, beginBubbleY, endBubbleX, endBubbleY);
		}
	}

	public boolean collisionDetected(Rectangle player, Rectangle object) {
		return player.intersects(object);
	}

	public void takeAwayOneHeart() {
		numberOfHeartsRemaining -= 1;
	}

	public void resetVariables(Shark playerShark, PlasticBottle[] plasticBottles, Fish[] fishies) {
		playerShark.resetProcess();

		for (int i = 0; i < plasticBottles.length; i++) {
			PlasticBottle currentPlasticBottle = plasticBottles[i];
			currentPlasticBottle.resetProcess();
		}

		for (int i = 0; i < fishies.length; i++) {
			Fish currentFish = fishies[i];
			currentFish.resetProcess();
		}
	}

	public int getScore() {
		return score;
	}

	public void incrementScore(int increment) {
		score += increment;
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent event) {
			playerShark.keyReleased(event);
		}

		@Override
		public void keyPressed(KeyEvent event) {
			int key = event.getKeyCode();

			if (!gameHasStarted && key == KeyEvent.VK_ENTER) {
				gameHasStarted = true;
				paintComponent(getGraphics());

				PlasticBottle.setIncrements(plasticBottles, 2);
				Fish.setIncrements(fishies, 2);

			}

			else if (gameIsOver && key == KeyEvent.VK_ENTER) {
				gameIsOver = false;
				score = 0;
				level = 1;
				numberOfHeartsRemaining = 3;
				gameOver.setxCoordinateOnScreen(-9000);
				gameOver.setyCoordinateOnScreen(-9000);
				paintComponent(getGraphics());
				resetVariables(playerShark, plasticBottles, fishies);
				playerShark.setIncrementValue(3);
				PlasticBottle.setIncrements(plasticBottles, 2);
				Fish.setIncrements(fishies, 2);
			}

			else {
				playerShark.keyPressed(event);
			}

		}

	}

}
