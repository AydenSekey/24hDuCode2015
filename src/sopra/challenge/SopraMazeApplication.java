package sopra.challenge;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.fabrique.FabriqueLabyrinthe;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrinthe2D;
import sopra.challenge.view.SopraMazeGame;
import sopra.challenge.view.SopraMazeMazeGame;
import sopra.challenge.view.impor.ArdorBaseApplication;


public class SopraMazeApplication extends ArdorBaseApplication{

	public SopraMazeApplication(Labyrinthe l) {
		super(new SopraMazeMazeGame(l));
	}
	
	public static void main(final String[] args) {
		Labyrinthe labyrinthe = recupererLab();
		final ArdorBaseApplication sopraEx = new SopraMazeApplication(labyrinthe);
		new Thread(sopraEx, "MainArdorThrad").start();
	}
	
	public static Labyrinthe recupererLab() {
		return FabriqueLabyrinthe.labyrintheFixe();
	}
	
}
