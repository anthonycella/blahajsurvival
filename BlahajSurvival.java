package shark_game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class BlahajSurvival extends JFrame {

	private static final long serialVersionUID = 1L;

	public BlahajSurvival() {

		initializeUserInterface();
	}

	public void initializeUserInterface() {

		add(new Background());

		setTitle("Blahaj Survival");
		setSize(800, 600);

		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			BlahajSurvival blahajSurvival = new BlahajSurvival();
			blahajSurvival.setVisible(true);
		});
	}
}
