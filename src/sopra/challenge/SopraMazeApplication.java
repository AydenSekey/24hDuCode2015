package sopra.challenge;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrinthe2D;
import sopra.challenge.view.SopraMazeGame;
import sopra.challenge.view.impor.ArdorBaseApplication;


public class SopraMazeApplication extends ArdorBaseApplication{

	public SopraMazeApplication(Labyrinthe l) {
		super(new SopraMazeGame(l));
	}
	
	public static void main(final String[] args) {
		Labyrinthe labyrinthe = recupererLab();
		final ArdorBaseApplication sopraEx = new SopraMazeApplication(labyrinthe);
		new Thread(sopraEx, "MainArdorThrad").start();
	}
	
	public static Labyrinthe recupererLab() {
		GenerateurLabyrinthe2D generateur= new GenerateurLabyrinthe2D();
		generateur.initialiserLabyrintheTest(20,20);
		generateur.definirMursIndestructibles();
		generateur.placerDepart(3, 7);
		generateur.placerArriver(19,3);
		
		return generateur.laby;
	}
	
}
