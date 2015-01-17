package sopra.challenge.jeux.exemple;

import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrinthe2D;
import sopra.challenge.personnages.generateurMonstre.GenerateurMonstre;

public class JeuExemple {

	public static void main(String[] args) {
		GenerateurLabyrinthe2D generateur= new GenerateurLabyrinthe2D();
		generateur.initialiserLabyrintheTest(20,20);
		generateur.definirMursIndestructibles();
		generateur.placerDepart(10, 10);
		generateur.placerArriver(13,19);
		
		generateur.placerArriver(19,18);
		/*Création du chemin*/
		generateur.rendreLibre(9,10);
		generateur.rendreLibre(8,10);
		generateur.rendreLibre(7,10);
		generateur.rendreLibre(6,10);
		generateur.rendreLibre(5,10);
		generateur.rendreLibre(4,10);
		generateur.rendreLibre(3,10);
		generateur.rendreLibre(2,10);
		generateur.rendreLibre(1,10);
		generateur.rendreLibre(1,9);
		generateur.rendreLibre(1,8);
		generateur.rendreLibre(1,7);
		generateur.rendreLibre(1,6);
		generateur.rendreLibre(1,5);
		generateur.rendreLibre(1,4);
		generateur.rendreLibre(1,3);
		generateur.rendreLibre(1,2);
		generateur.rendreLibre(1,2);
		generateur.rendreLibre(1,2);
		generateur.rendreLibre(1,1);
		generateur.rendreLibre(2,1);
		generateur.rendreLibre(3,1);
		generateur.rendreLibre(4,1);
		generateur.rendreLibre(5,1);
		generateur.rendreLibre(6,1);
		generateur.rendreLibre(7,1);
		generateur.rendreLibre(8,1);
		generateur.rendreLibre(9,1);
		generateur.rendreLibre(10,1);
		generateur.rendreLibre(11,1);
		generateur.rendreLibre(12,1);
		generateur.rendreLibre(13,1);
		generateur.rendreLibre(14,1);
		generateur.rendreLibre(15,1);
		generateur.rendreLibre(16,1);
		generateur.rendreLibre(17,1);
		generateur.rendreLibre(18,1);
		generateur.rendreLibre(3,3);
		generateur.rendreLibre(3,4);
		generateur.rendreLibre(3,5);
		generateur.rendreLibre(3,6);
		generateur.rendreLibre(3,7);
		generateur.rendreLibre(3,8);
		generateur.rendreLibre(4,8);
		generateur.rendreLibre(5,8);
		generateur.rendreLibre(6,8);
		generateur.rendreLibre(7,8);
		generateur.rendreLibre(8,8);
		generateur.rendreLibre(9,8);
		generateur.rendreLibre(10,8);
		generateur.rendreLibre(18,2);
		generateur.rendreLibre(18,3);
		generateur.rendreLibre(18,4);
		generateur.rendreLibre(18,5);
		generateur.rendreLibre(18,6);
		generateur.rendreLibre(18,7);
		generateur.rendreLibre(18,8);
		generateur.rendreLibre(18,9);
		generateur.rendreLibre(18,10);
		generateur.rendreLibre(18,11);
		generateur.rendreLibre(18,12);
		generateur.rendreLibre(18,13);
		generateur.rendreLibre(18,14);
		generateur.rendreLibre(18,15);
		generateur.rendreLibre(18,16);
		generateur.rendreLibre(18,17);
		generateur.rendreLibre(18,18);
		
		generateur.rendreLibre(10,11);
		generateur.rendreLibre(10,12);
		generateur.rendreLibre(10,13);
		generateur.rendreLibre(10,14);
		generateur.rendreLibre(10,15);
		generateur.rendreLibre(10,16);
		generateur.rendreLibre(10,17);
		generateur.rendreLibre(10,18);

		generateur.rendreLibre(8,11);
		generateur.rendreLibre(8,12);
		generateur.rendreLibre(8,13);
		generateur.rendreLibre(8,14);
		generateur.rendreLibre(8,15);
		generateur.rendreLibre(8,16);
		generateur.rendreLibre(8,17);
		generateur.rendreLibre(8,18);
		
		generateur.rendreLibre(7,18);
		generateur.rendreLibre(6,18);
		generateur.rendreLibre(5,18);
		generateur.rendreLibre(4,18);
		generateur.rendreLibre(3,18);
		generateur.rendreLibre(2,18);
		generateur.rendreLibre(1,18);
		generateur.rendreLibre(1,17);
		generateur.rendreLibre(1,16);
		generateur.rendreLibre(1,15);
		generateur.rendreLibre(1,14);
		generateur.rendreLibre(1,13);
		generateur.rendreLibre(1,12);
		
		GenerateurMonstre generateurMonstre = new GenerateurMonstre();
		generateurMonstre.genererMonstres(10);
		generateur.afficherLabyrinthe();

	}

}
