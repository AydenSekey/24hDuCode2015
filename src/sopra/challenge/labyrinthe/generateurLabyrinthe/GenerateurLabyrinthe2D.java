package sopra.challenge.labyrinthe.generateurLabyrinthe;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.MurIndestructible;
import sopra.challenge.labyrinthe.MurNormal;
import sopra.challenge.labyrinthe.Zone;
import sopra.challenge.labyrinthe.ZoneArrivee;
import sopra.challenge.labyrinthe.ZoneDepart;

public class GenerateurLabyrinthe2D {
	public Labyrinthe laby= Labyrinthe.getInstance();
	
	public void initialiserLabyrintheTest(int nbLigne, int nbColonnes){
		laby.setNbLignes(nbLigne);; 
		laby.setNbColonnes(nbColonnes);
		
		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbLigne;j++){
				MurNormal blocCourant = new MurNormal(i,j);
				this.ajouterblocAuLabyrinthe(blocCourant);
			}			
		}
	}
	
	public void definirMursIndestructibles(){
		Set<Bloc> mursExterieurs = new HashSet<Bloc>();
		
		for(Bloc b:laby.blocs){
			if((b.positionBloc.coordX == (laby.nbLignes -1 )) || 
					(b.positionBloc.coordX == 0) ||
						(b.positionBloc.coordY == (laby.nbColonnes -1) ) ||
							(b.positionBloc.coordY == 0) ){
				mursExterieurs.add(b);
			}
		}
		Iterator<Bloc> it = mursExterieurs.iterator();
		while (it.hasNext()) {
		 Bloc b= it.next();
		 laby.removeBloc(b);
			MurIndestructible md = new MurIndestructible(b.positionBloc.coordX, b.positionBloc.coordY);
			laby.blocs.add(md);
		}
	}
	
	public void placerDepart(int x, int y){
		Bloc bloc = laby.getBloc(x, y);
		laby.removeBloc(bloc);
		ZoneDepart zd = new ZoneDepart(x, y);
		laby.blocs.add(zd);
	}
	
	public void placerArriver(int x, int y){
		Bloc bloc = laby.getBloc(x, y);
		laby.removeBloc(bloc);
		ZoneArrivee zd = new ZoneArrivee(x, y);
		laby.blocs.add(zd);
	}
	
	public void ajouterblocAuLabyrinthe(Bloc bloc){
		laby.blocs.add(bloc);
	}
	
	public void rendreLibre(int x, int y){
		Bloc bloc = laby.getBloc(x, y);
		this.rendreLibre(bloc);
	}
	
	public void rendreLibre(Bloc unBloc){
		laby.removeBloc(unBloc);
		Zone zd = new Zone(unBloc.positionBloc.coordX, unBloc.positionBloc.coordY);
		laby.blocs.add(zd);
	}
}
