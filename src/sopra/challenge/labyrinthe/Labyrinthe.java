package sopra.challenge.labyrinthe;

import java.util.LinkedList;
import java.util.List;

import sopra.challenge.personnages.Monstre;

public class Labyrinthe {
	
	private static Labyrinthe INSTANCE= new Labyrinthe() ;
	
	public int nbX =0;
	public int nbY= 0;
	public String nom= null;
	public List<Bloc> blocs= new LinkedList<Bloc>();
	public List<Monstre> monstres= new LinkedList<Monstre>();
	
	private Labyrinthe(){}
	
	public static Labyrinthe getInstance()
	{	
		return INSTANCE;
	}
	
	public int getCoordX() {
		return nbX;
	}

	public void setCoordX(int coordX) {
		this.nbX = coordX;
	}

	public int getCoordY() {
		return nbY;
	}

	public void setCoordY(int coordY) {
		this.nbY = coordY;
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
