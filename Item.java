package shark_game_objects;

public class Item {
	
	protected int xCoordinateOnScreen, yCoordinateOnScreen;
	

	
	public int getxCoordinateOnScreen() {
		return xCoordinateOnScreen;
	}
	
	public void setxCoordinateOnScreen(int xCoordinateOnScreen) {
		this.xCoordinateOnScreen = xCoordinateOnScreen;
	}
	
	public void incrementxCoordinateOnScreen(int newIncrement) {
		this.xCoordinateOnScreen += newIncrement;
	}
	
	public int getyCoordinateOnScreen() {
		return yCoordinateOnScreen;
	}
	
	public void setyCoordinateOnScreen(int yCoordinateOnScreen) {
		this.yCoordinateOnScreen = yCoordinateOnScreen;
	}
	
	public void incrementyCoordinateOnScreen(int newIncrement) {
		this.yCoordinateOnScreen += newIncrement;
	}
	
	
}
