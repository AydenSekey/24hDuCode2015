package sopra.challenge.jeux.exemple;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrinthe2D;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrintheAurel;

public class JeuExemple3 {

	public static Labyrinthe lab = Labyrinthe.getInstance();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GenerateurLabyrintheAurel generateur= new GenerateurLabyrintheAurel();
		generateur.initialiserLabyrintheTest(5,5);
		generateur.converter();
		
		Labyrinthe labyrinthe = Labyrinthe.getInstance();
		labyrinthe.afficherLabyrinthe();
		
	}

}
