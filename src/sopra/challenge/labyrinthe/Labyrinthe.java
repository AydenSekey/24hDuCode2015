package sopra.challenge.labyrinthe;

import java.util.LinkedList;
import java.util.List;

import sopra.challenge.personnages.Monstre;

public class Labyrinthe {
	
	private static Labyrinthe INSTANCE= new Labyrinthe() ;
	
	public int nbLignes =0;
	public int nbColonnes= 0;
	public String nom= null;
	public List<Bloc> blocs= new LinkedList<Bloc>();
	public List<Monstre> monstres= new LinkedList<Monstre>();
	
	private Labyrinthe(){}
	
	public static Labyrinthe getInstance()
	{	
		return INSTANCE;
	}
	
	public int getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(int nb) {
		this.nbLignes = nb;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public void setNbColonnes(int nb) {
		this.nbColonnes = nb;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Bloc> getBlocs() {
		return blocs;
	}

	public void setBlocs(List<Bloc> blocs) {
		this.blocs = blocs;
	}

}
