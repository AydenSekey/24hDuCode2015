package sopra.challenge.personnages;

import java.util.List;

import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;


public class Monstre {
	
	public Bloc positionBloc = null;
	public String nom= null;
	public Labyrinthe labyrinthe = Labyrinthe.getInstance();
	
	public Monstre() {
		super();
	}
	
//	PUBLIC VOID DEPLACER(){
//		LIST<BLOC> LISTVOISINLIBRE = LABYRINTHE.GETVOISIN(POSITIONBLOC);
//	}
	
	public Bloc getPositionBloc() {
		return positionBloc;
	}
	public void setPositionBloc(Bloc position) {
		this.positionBloc = position;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
