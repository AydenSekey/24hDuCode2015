package sopra.challenge.labyrinthe;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import sopra.challenge.personnages.Monstre;

public class Labyrinthe {
	
	private static Labyrinthe INSTANCE;
	
	public int nbLignes =0;
	public int nbColonnes= 0;
	public String nom= null;
	public List<Bloc> blocs= new LinkedList<Bloc>();
	public List<Monstre> monstres= new LinkedList<Monstre>();
	
	private Labyrinthe(){}
	
	public static Labyrinthe getInstance()
	{	
		if(INSTANCE == null )
			INSTANCE = new Labyrinthe();
		
		return INSTANCE;
	}

	public Bloc getBloc(int x,int y){
		for(Bloc b: blocs){
			if(b.estALaPosition(x, y)){
				return b;
			}
		}
		return null;
	}
	
//	public boolean blocEstRenseigne(int x, int y){
//		return (this.getBloc(x, y) != null);
//	}
	
	public void removeBloc(Bloc b){
		this.blocs.remove(b);
	}
	
	public Monstre creerMonstre(){
		Monstre m = new Monstre();
		monstres.add(m);
		return m;
	}
	
	public void positionnerMonstre(Monstre m){
		Random rand = new Random();
		List<Bloc> zonesLibre = getZonesLibre();
		Integer nbZonesLibre = zonesLibre.size();
		if(nbZonesLibre > 0){
			Integer posListeZone = rand.nextInt(nbZonesLibre);
			Bloc bloc = zonesLibre.get(posListeZone);
			m.setPositionBloc(bloc);
			bloc.setOccupee(true);
		}
	}
	
	public List<Bloc> getZonesLibre(){
		List<Bloc> zonesLibre = new LinkedList<>();
		for (Bloc bloc : blocs) {
			if(bloc.isZone() && !bloc.isOccupee() && !bloc.isZoneArrivee() && !bloc.isZoneDepart()){
				zonesLibre.add(bloc);
			}
		}
		return zonesLibre;
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
