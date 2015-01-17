package sopra.challenge.personnages;

import sopra.challenge.labyrinthe.Bloc;


public class Monstre {
	
	public Bloc positionBloc = null;
	public String nom= null;
	
	public Monstre(String nom) {
		super();
		this.nom = nom;
	}
	
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
