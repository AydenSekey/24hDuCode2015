package sopra.challenge.personnages;

import java.util.List;
import java.util.Random;

import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.Position;


public class Monstre {
	
	public Bloc positionBloc = null;
	public String nom= null;
	public Labyrinthe labyrinthe = Labyrinthe.getInstance();
	public Random rand = new Random();
	
	public Monstre() {
		super();
	}
	
	public void deplacer(){
		Position position = positionBloc.getPositionBloc();
		List<Bloc> voisins = labyrinthe.getVoisinsLibre(position.coordX, position.coordY);
		Integer nbVoisins = voisins.size();
		Integer i = rand.nextInt(nbVoisins);
		positionBloc.setOccupee(false);
		positionBloc = voisins.get(i);
		positionBloc.setOccupee(true);
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
