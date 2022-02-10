package shark_game_objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BackgroundGraphic extends Item {
	private Image backgroundGraphic;
	private int width;
	private int height;

	public BackgroundGraphic() {
		loadBackgroundGraphic();
		this.xCoordinateOnScreen = -105;
		this.yCoordinateOnScreen = -25;
	}

	public void loadBackgroundGraphic() {
		ImageIcon imageContainer = new ImageIcon("src/pictures/Underwater_life.png");
		Image unscaledBackgroundGraphic = imageContainer.getImage();

		width = unscaledBackgroundGraphic.getWidth(null);
		height = unscaledBackgroundGraphic.getHeight(null);

		backgroundGraphic = unscaledBackgroundGraphic.getScaledInstance((int) (width / 1.4), (int) (height / 1.4),
				Image.SCALE_DEFAULT);

	}

	public Image getBackgroundGraphic() {
		return backgroundGraphic;
	}

}
