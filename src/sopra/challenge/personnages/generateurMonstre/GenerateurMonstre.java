package sopra.challenge.personnages.generateurMonstre;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.personnages.Monstre;

public class GenerateurMonstre {
	Labyrinthe labyrinthe = Labyrinthe.getInstance();

	public GenerateurMonstre() {
		super();
	}
	
	public void genererMonstres(Integer nbMonstres){
		for(int i=0; i<nbMonstres ; i++){
			Monstre m = labyrinthe.creerMonstre();
			labyrinthe.positionnerMonstre(m);
		}
	}
	
}
