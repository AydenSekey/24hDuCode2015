package sopra.challenge.jeux.exemple;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrinthe2D;

public class JeuExemple2 {
	
	public static Labyrinthe lab = Labyrinthe.getInstance();
	
	public static void main(String[] args) {
		GenerateurLabyrinthe2D generateur= new GenerateurLabyrinthe2D();
		generateur.initialiserLabyrintheTest(20,20);
		generateur.definirMursIndestructibles();
		generateur.placerDepart(10, 10);
		generateur.placerArriver(19,19);
		
		generateur.afficherLabyrinthe();
		
	}
	

}
