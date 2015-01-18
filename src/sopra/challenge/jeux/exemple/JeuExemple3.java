package sopra.challenge.jeux.exemple;

import java.util.Timer;
import java.util.TimerTask;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrintheAurel;
import sopra.challenge.personnages.generateurMonstre.GenerateurMonstre;

public class JeuExemple3 {

	public static Labyrinthe lab = Labyrinthe.getInstance();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GenerateurLabyrintheAurel generateur= new GenerateurLabyrintheAurel();

		long deb = System.nanoTime();
		generateur.initialiserLabyrintheTest(7,7);

		generateur.converter();
		
		Labyrinthe labyrinthe = Labyrinthe.getInstance();
		labyrinthe.afficherLabyrinthe();
		
		GenerateurMonstre generateurMonstre = new GenerateurMonstre();
		generateurMonstre.genererMonstres(1);
		
		labyrinthe.afficherLabyrinthe();
		labyrinthe.deplacerLesMonstres();
		labyrinthe.afficherLabyrinthe();
		long fin = System.nanoTime();
		
		System.out.println("Secondes : " + (fin-deb)/1000000000.0);
		
		
		
	}

}
