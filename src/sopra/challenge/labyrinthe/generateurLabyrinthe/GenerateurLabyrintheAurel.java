package sopra.challenge.labyrinthe.generateurLabyrinthe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position;

import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.Mur;
import sopra.challenge.labyrinthe.MurIndestructible;
import sopra.challenge.labyrinthe.MurNormal;
import sopra.challenge.labyrinthe.Porte;
import sopra.challenge.labyrinthe.Zone;
import sopra.challenge.labyrinthe.ZoneArrivee;
import sopra.challenge.labyrinthe.ZoneDepart;

public class GenerateurLabyrintheAurel {
	
	public Labyrinthe labyrinthe= Labyrinthe.getInstance();
	int nbMurPossible = 0;
	int nbMurFerme = 0;
	uneCase[] matriceCases;
	unMur[] tabMurVertical;
	unMur[] tabMurHorizontal;
	List<Bloc> mursExterieurs = new ArrayList<Bloc>();
	
	int initialX,initialY;
	
//	public GenerateurLabyrintheAurel(Labyrinthe labyrinthe) {
//		super();
//		this.labyrinthe = labyrinthe;
//		if (!this.genererLabyrinthe())
//			System.out.println("Un probl�me a eu lieu dans la g�n�ration du labyrinthe (nbX > 2 et nbY > 2)");
//	}

	public void initialiserLabyrintheTest(int nbLigne, int nbColonnes){
		labyrinthe.setNbLignes(nbLigne); 
		labyrinthe.setNbColonnes(nbColonnes);
		
//		for(int i=0;i<nbLigne;i++){
//			for(int j=0;j<nbLigne;j++){
//				MurNormal blocCourant = new MurNormal(i,j);
//				this.ajouterblocAuLabyrinthe(blocCourant);
//			}			
//		}
		
		if (!this.genererLabyrinthe())
			System.out.println("Un probl�me a eu lieu dans la g�n�ration du labyrinthe (nbX > 2 et nbY > 2)");
	}
	
	private boolean genererLabyrinthe(){
		
		if ( labyrinthe.nbColonnes < 3 || labyrinthe.nbLignes < 3 || (labyrinthe.nbLignes % 2) == 0
				|| (labyrinthe.nbColonnes % 2) == 0)
			return false;
		
		nbMurPossible = (2 * labyrinthe.nbColonnes * labyrinthe.nbLignes) - labyrinthe.nbColonnes - labyrinthe.nbLignes;
		nbMurFerme = (labyrinthe.nbColonnes-1) * (labyrinthe.nbLignes-1);
		System.out.println(nbMurPossible + " " + nbMurFerme);
		System.out.println(labyrinthe.nbColonnes + " "+ labyrinthe.nbLignes);

		matriceCases = new uneCase[labyrinthe.nbColonnes*labyrinthe.nbLignes];
		//unMur[][] matriceMurPossibles = new unMur[labyrinthe.nbX-1][labyrinthe.nbY-1];
		tabMurVertical = new unMur[(labyrinthe.nbLignes-1)*labyrinthe.nbColonnes];
		tabMurHorizontal = new unMur[(labyrinthe.nbColonnes-1)*labyrinthe.nbLignes];
		
		this.init(matriceCases, tabMurHorizontal, tabMurVertical);
		this.imprimMatrice(matriceCases, tabMurHorizontal, tabMurVertical);
		
		int nbMurOuvert = 0;
		Random random = new Random();
		
		while (nbMurOuvert < (labyrinthe.nbColonnes * labyrinthe.nbLignes - 1) ) {

			boolean murTrouve = false;
			int case1 = 0;
			int case2 = 0;
			int horOuVert = -1;
			int murAlea = -1;
		
			while (murTrouve == false){
				horOuVert = random.nextInt(2);
				
				if (horOuVert == 0){
					murAlea = random.nextInt(tabMurHorizontal.length);
					
					if (tabMurHorizontal[murAlea].getEtat() == 1){
						case1 = murAlea;
						case2 = murAlea+labyrinthe.nbLignes;
					}
				}
				else{
					murAlea = random.nextInt(tabMurVertical.length);
					
					if (tabMurVertical[murAlea].getEtat() == 1){
						int dec = (murAlea/(labyrinthe.nbLignes-1));
						case1 = murAlea+dec;
						case2 = murAlea+dec+1;
					}
				}
				
				if (matriceCases[case1].getValue() != matriceCases[case2].getValue()){
					murTrouve = true;
				}
				
			}
			// Mur trouve
			System.out.println(horOuVert);
			System.out.println(murAlea + " " + case1 + " " + case2);
			System.out.println("valcase1: "+matriceCases[case1].getValue() + "valcase2: "+matriceCases[case2].getValue());
			if (horOuVert==0){
				tabMurHorizontal[murAlea].setEtat(0);
			}
			else tabMurVertical[murAlea].setEtat(0);
			
			int i,j;
			int tmp = matriceCases[case2].getValue();
			for(i=0; i<labyrinthe.nbColonnes; i++) {
				for(j=0; j<labyrinthe.nbLignes; j++) {
					if(matriceCases[i*labyrinthe.nbLignes+j].getValue() == tmp )
						matriceCases[i*labyrinthe.nbLignes+j].setValue(matriceCases[case1].getValue()); ;
				}
			}
			
			
			imprimMatrice(matriceCases, tabMurHorizontal, tabMurVertical);
			nbMurOuvert++;
			System.out.println(nbMurOuvert);
			System.out.println(labyrinthe.nbColonnes * labyrinthe.nbLignes - 1);
			
		}
		
		for (int k = 0; k < tabMurHorizontal.length; k++) {
			System.out.print(tabMurHorizontal[k].getEtat());
			
		}
		for (int k = 0; k < tabMurVertical.length; k++) {
			System.out.print(tabMurVertical[k].getEtat());
		}
		System.out.println(tabMurHorizontal.length + " " + tabMurVertical.length);
		
		return true;
	}
	
	private void init(uneCase[] matriceCases, unMur[] tabMurHorizontal, unMur[] tabMurVertical ) {
		// TODO Auto-generated method stub
		int i, j;
		int iter = 0;
		for(i=0; i<labyrinthe.nbColonnes; i++) {
			for(j=0; j<labyrinthe.nbLignes; j++) {
				matriceCases[i*labyrinthe.nbLignes+j] = new uneCase(iter++);
			}
		}
				
		for(i=0; i<tabMurHorizontal.length; i++) {
			tabMurHorizontal[i] = new unMur(1, 0);
//			System.out.print(tabMurHorizontal[i]);
		}
		
		for(i=0; i<tabMurVertical.length; i++) {
			tabMurVertical[i] = new unMur(1, 1);
//			System.out.print(tabMurVertical[i]);
		}
		
	}
	
	private void imprimMatrice(uneCase[] matriceCases, unMur[] tabMurHorizontal, unMur[] tabMurVertical) {
		// TODO Auto-generated method stub
		int i, j;
		for(i=0; i<labyrinthe.nbColonnes; i++) {
			System.out.println();
			for(j=0; j<labyrinthe.nbLignes; j++) {
				System.out.print(matriceCases[i*labyrinthe.nbLignes+j]);
			}
		}
		
		int iter = 0;
		int vise = labyrinthe.nbLignes - 1;

		int iterVert = 0;
		int iterHor = 0;
		
		System.out.println();
//		System.out.print(" |-");
//		for(i=0; i<labyrinthe.nbY; i++) 
//			System.out.print("---");
//		
//		System.out.println("-|");
//		System.out.print(" |-");
		for(i=0; i<nbMurPossible; i++) {
			iter++;
			if (iter <= vise)
				if (vise == labyrinthe.nbLignes - 1)
					System.out.print(tabMurVertical[iterVert++]);
				else System.out.print(tabMurHorizontal[iterHor++]);
			else {
				if (vise == labyrinthe.nbLignes - 1)
					vise = labyrinthe.nbLignes;
				else vise = labyrinthe.nbLignes - 1;
				iter=1;
				System.out.println("");
//				System.out.println("-| ");
//				System.out.print(" |-");
				if (vise == labyrinthe.nbLignes - 1)
					System.out.print(tabMurVertical[iterVert++]);
				else System.out.print(tabMurHorizontal[iterHor++]);
			}
		}
		
		
//		System.out.println("-| ");
//		System.out.print(" |-");
//		for(i=0; i<labyrinthe.nbY; i++) 
//			System.out.print("---");
//		
//		System.out.println("-|");
		
		
	}
	
	
	public void converter() {
		// TODO Auto-generated method stub
		
		initialY = labyrinthe.getNbLignes();
		initialX = labyrinthe.getNbColonnes();
		
		labyrinthe.setNbLignes(labyrinthe.getNbLignes() * 5);
		labyrinthe.setNbColonnes(labyrinthe.getNbColonnes() * 5);
		
		
		
		for(int i=0;i<labyrinthe.getNbLignes();i++){
			for(int j=0;j<labyrinthe.getNbColonnes();j++){
				Zone blocCourant = new Zone(j,i);
				labyrinthe.blocs.add(blocCourant);
			}			
		}
		
		
		this.ajouterBlocBase();
		
		this.ajouterMurColonne();
		this.ajouterMurLigne();
		
		this.definirMursIndestructibles();
		this.ajouterSortie();
		this.ajouterDepart();
		
	}
	
	private void ajouterDepart() {
		int xDepart = labyrinthe.getNbColonnes()/2;
		int yDepart = labyrinthe.getNbLignes()/2;
		for(int x = xDepart-3; x<=xDepart-2; x++){
			ajouterMur(x, yDepart-1);
			ajouterPorte(x, yDepart);
			ajouterMur(x, yDepart+1);
		}
		for(int x = xDepart+2; x<=xDepart+3; x++){
			ajouterMur(x, yDepart-1);
			ajouterPorte(x, yDepart);
			ajouterMur(x, yDepart+1);
		}
		for(int y = yDepart-3; y<=yDepart-2; y++){
			ajouterMur(xDepart-1, y);
			ajouterPorte(xDepart, y);
			ajouterMur(xDepart+1, y);
		}
		for(int y = yDepart+2; y<=yDepart+3; y++){
			ajouterMur(xDepart-1, y);
			ajouterPorte(xDepart, y);
			ajouterMur(xDepart+1, y);
		}
	}
	
	private void ajouterSortie() {
		// TODO Auto-generated method stub
		Random random = new Random();
		int nbMurExt = mursExterieurs.size();
		System.out.println(nbMurExt);
		Boolean sortieTrouvee = false;
		while(!sortieTrouvee){
			int indiceBloc = random.nextInt(nbMurExt);
			Bloc sortie = mursExterieurs.get(indiceBloc);
			int xSortie = sortie.getPositionBloc().coordX; 
			int ySortie = sortie.getPositionBloc().coordY; 
			if(xSortie == 0){
				if(!(labyrinthe.getBloc(xSortie + 1, ySortie) instanceof Mur)){
					sortieTrouvee = true;
					placerArriver(xSortie, ySortie);
				}
			}else if(xSortie == labyrinthe.getNbColonnes()-1){
				if(!(labyrinthe.getBloc(xSortie - 1, ySortie) instanceof Mur)){
					sortieTrouvee = true;
					placerArriver(xSortie, ySortie);
				}
			}else if(ySortie == 0){
				if(!(labyrinthe.getBloc(xSortie, ySortie + 1) instanceof Mur)){
					sortieTrouvee = true;
					placerArriver(xSortie, ySortie);
				}
			}else if(ySortie == labyrinthe.getNbLignes()-1){
				if(!(labyrinthe.getBloc(xSortie, ySortie - 1) instanceof Mur)){
					sortieTrouvee = true;
					placerArriver(xSortie, ySortie);
				}
			}
		}
		
		

	}
	
	public void placerArriver(int x, int y){
		Bloc bloc = labyrinthe.getBloc(x, y);
		labyrinthe.removeBloc(bloc);
		ZoneArrivee zd = new ZoneArrivee(x, y);
		labyrinthe.blocs.add(zd);
	}
	
	private void ajouterMurColonne(){
		int x,y;
		for (int i = 0; i < tabMurVertical.length; i++) {
			if (tabMurVertical[i].getEtat()==1){
				
				y = (i % (initialX-1)) * 5 + 4;
				x = (i / (initialX-1)) * 5 + 1;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);

				y = (i % (initialX-1)) * 5 + 5;
				x = (i / (initialX-1)) * 5 + 1;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
				
				y = (i % (initialX-1)) * 5 + 4;
				x = (i / (initialX-1)) * 5 + 2;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
			
				y = (i % (initialX-1)) * 5 + 5;
				x = (i / (initialX-1)) * 5 + 2;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
			
				y = (i % (initialX-1)) * 5 + 4;
				x = (i / (initialX-1)) * 5 + 3;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
			
				y = (i % (initialX-1)) * 5 + 5;
				x = (i / (initialX-1)) * 5 + 3;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
			
			}
			
		}
		
	}

	private void ajouterMurLigne(){
		int x,y;
		for (int i = 0; i < tabMurHorizontal.length; i++) {
			if (tabMurHorizontal[i].getEtat()==1){
				
				y = (i % initialY) * 5 + 1;
				x = (i / initialY) * 5 + 4;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);

				y = (i % initialY) * 5 + 1;
				x = (i / initialY) * 5 + 5;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
				
				y = (i % initialY) * 5 + 2;
				x = (i / initialY) * 5 + 4;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
			
				y = (i % initialY) * 5 + 2;
				x = (i / initialY) * 5 + 5;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
			
				y = (i % initialY) * 5 + 3;
				x = (i / initialY) * 5 + 4;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
			
				y = (i % initialY) * 5 + 3;
				x = (i / initialY) * 5 + 5;
				System.out.println(" "+x+" "+y);
				ajouterMur(x, y);
			
			}
			
		}
		
	}
	
	private void ajouterMur(int x, int y){
		Bloc bloc = labyrinthe.getBloc(x, y);
		labyrinthe.removeBloc(bloc);
		MurNormal blocCourant = new MurNormal(x,y);
		labyrinthe.blocs.add(blocCourant);
	}
	
	private void ajouterPorte(int x, int y){
		Bloc bloc = labyrinthe.getBloc(x, y);
		labyrinthe.removeBloc(bloc);
		Porte blocCourant = new Porte(x,y);
		labyrinthe.blocs.add(blocCourant);
	}

	
	private void ajouterBlocBase(){
		
		for(int i=0;i<labyrinthe.getNbLignes();i++){
			for(int j=0;j<labyrinthe.getNbColonnes();j++){
				if ( (i%5==0 || i%5==4) && (j%5==4 || j%5==0)){
					Bloc bloc = labyrinthe.getBloc(j, i);
					labyrinthe.removeBloc(bloc);
					MurNormal blocCourant = new MurNormal(j,i);
					labyrinthe.blocs.add(blocCourant);
				}
			}
		}
		
	}
	
	public void rendreLibre(int x, int y){
		Bloc bloc = labyrinthe.getBloc(x, y);
		this.rendreLibre(bloc);
	}
	
	public void rendreLibre(Bloc unBloc){
		//labyrinthe.removeBloc(unBloc);
		Zone zd = new Zone(unBloc.positionBloc.coordX, unBloc.positionBloc.coordY);
		labyrinthe.blocs.add(zd);
	}
	
	private void placerDepart(int x, int y){
//		Bloc bloc = labyrinthe.getBloc(x, y);
//		labyrinthe.removeBloc(bloc);
//		ZoneDepart zd = new ZoneDepart(x, y);
//		labyrinthe.blocs.add(zd);
	}
	
	
	private void definirMursIndestructibles(){
		
		for(Bloc b:labyrinthe.blocs){
			if((b.positionBloc.coordX == (labyrinthe.nbLignes -1 )) || 
					(b.positionBloc.coordX == 0) ||
						(b.positionBloc.coordY == (labyrinthe.nbColonnes -1) ) ||
							(b.positionBloc.coordY == 0) ){
				mursExterieurs.add(b);
			}
		}
		Iterator<Bloc> it = mursExterieurs.iterator();
		while (it.hasNext()) {
			Bloc b= it.next();
			labyrinthe.removeBloc(b);
			MurIndestructible md = new MurIndestructible(b.positionBloc.coordX, b.positionBloc.coordY);
			labyrinthe.blocs.add(md);
		}
	}	
	
}
