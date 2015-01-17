package sopra.challenge;

import sopra.challenge.view.SopraMazeGame;
import sopra.challenge.view.impor.ArdorBaseApplication;


public class SopraMazeApplication extends ArdorBaseApplication{

	public SopraMazeApplication() {
		super(new SopraMazeGame());
	}
	
	public static void main(final String[] args) {
		final ArdorBaseApplication sopraEx = new SopraMazeApplication();
		new Thread(sopraEx, "MainArdorThrad").start();
	}
	
}
