package sopra.challenge.jeux.exemple;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrinthe2D;

public class JeuExemple2 {
	
	public static Labyrinthe lab = Labyrinthe.getInstance();
	
	public static void main(String[] args) {
		GenerateurLabyrinthe2D generateur= new GenerateurLabyrinthe2D();
		generateur.initialiserLabyrintheTest(500,500);
		generateur.definirMursIndestructibles();
		generateur.placerDepart(0, 0);
		generateur.placerArriver(499,100);
		
		generateur.afficherLabyrinthe();
		
	}
	

}
