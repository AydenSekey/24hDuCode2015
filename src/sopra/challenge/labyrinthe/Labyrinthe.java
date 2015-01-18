package sopra.challenge.labyrinthe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ardorcraft.data.Pos;

import sopra.challenge.personnages.Monstre;

public class Labyrinthe {
	
	private static Labyrinthe INSTANCE;
	
	public int nbLignes =0;
	public int nbColonnes= 0;
	public String nom= null;
	public List<Bloc> blocs= new ArrayList<Bloc>();
	public List<Monstre> monstres= new ArrayList<Monstre>();
	
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
		List<Bloc> zonesLibre = new ArrayList<Bloc>();
		for (Bloc bloc : blocs) {
			if(bloc.isZone() && !bloc.isOccupee() && !bloc.isZoneArrivee() && !bloc.isZoneDepart()){
				zonesLibre.add(bloc);
			}
		}
		return zonesLibre;
	}
	
	public List<Bloc> getVoisinsLibre(Integer x, Integer y){
		List<Bloc> listVoisinsLibre = new ArrayList<>();
		if(y+1<nbLignes-2){
			Bloc blocNord = getBloc(x, y+1);
			if(blocNord != null && blocNord.isZone() && !blocNord.isOccupee()){
				listVoisinsLibre.add(blocNord);
			}
		}
		if(y-1>0){
			Bloc blocSud = getBloc(x, y-1);
			if(blocSud != null && blocSud.isZone() && !blocSud.isOccupee()){
				listVoisinsLibre.add(blocSud);
			}
		}
		if(x+1<nbColonnes-2){
			Bloc blocEst = getBloc(x+1, y);
			if(blocEst != null && blocEst.isZone() && !blocEst.isOccupee()){
				listVoisinsLibre.add(blocEst);
			}
		}
		if(x-1>0){
			Bloc blocOuest = getBloc(x-1, y);
			if(blocOuest != null && blocOuest.isZone() && !blocOuest.isOccupee()){
				listVoisinsLibre.add(blocOuest);
			}
		}
		return listVoisinsLibre;
	}
	
	public void deplacerLesMonstres(){
		for (Monstre m : monstres) {
			
			m.deplacer();

		}
	}
	
	public void afficherLabyrinthe(){
		for(int y=nbLignes-1;y>-1; y--){
			for(int x=0;x<nbColonnes; x++){
				Bloc bloc = getBloc(x, y);
				if(bloc.isZone() && bloc.isOccupee()){
					System.out.print(" M ");
				}else{
					System.out.print(getBloc(x, y));
				}
			}
			System.out.print("\n");
		}
	}
	public boolean estUnBlocAvecUnMonstreCommeVoisin(Pos position){
		return estUnBlocAvecUnMonstreCommeVoisin (position.x, position.y);
	}
	public boolean estUnBlocAvecUnMonstreCommeVoisin(int coordX, int coordY){
		//Bloc bloc=this.getBloc(coordX, coordY);
		List<Bloc> voisins = new ArrayList<Bloc>();
		if(coordX > 0){
			voisins.add(this.getBloc(coordX-1, coordY));
		}
		if(coordX < this.nbColonnes-1){
			voisins.add(this.getBloc(coordX+1, coordY));
		}
		if(coordY > 0){
			voisins.add(this.getBloc(coordX, coordY-1));
		}
		if(coordY < this.nbLignes-1){
			voisins.add(this.getBloc(coordX, coordY+1));
		}
		for(Bloc bloc: voisins){
			if(bloc.isZone() && !bloc.isPorte() && bloc.isOccupee())
				return true;
		}
				
		return false;
	}
	public List<Monstre> getMonstres() {
		return monstres;
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

	public void setBlocs(List<Bloc> blocs) {
		this.blocs = blocs;
	}
	
	public Bloc getDepart() {
		for(Bloc b: blocs){
			if(b.isZoneDepart() && ((ZoneDepart)b).getMilieu())
				return b;
		}
		return null;
	}

}
