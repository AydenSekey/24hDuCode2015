package sopra.challenge.labyrinthe.generateurLabyrinthe;

import java.util.Random;

import sopra.challenge.labyrinthe.Labyrinthe;

public class GenerateurLabyrintheAurel {
	
	Labyrinthe labyrinthe = null;
	int nbMurPossible = 0;
	int nbMurFerme = 0;
	
	public GenerateurLabyrintheAurel(Labyrinthe labyrinthe) {
		super();
		this.labyrinthe = labyrinthe;
		if (!this.genererLabyrinthe())
			System.out.println("Un problème a eu lieu dans la génération du labyrinthe (nbX > 2 et nbY > 2)");
	}

	private boolean genererLabyrinthe(){
		
		if ( labyrinthe.nbX < 3 || labyrinthe.nbY < 3 )
			return false;
		
		nbMurPossible = (2 * labyrinthe.nbX * labyrinthe.nbY) - labyrinthe.nbX - labyrinthe.nbY;
		nbMurFerme = (labyrinthe.nbX-1) * (labyrinthe.nbY-1);
		System.out.println(nbMurPossible + " " + nbMurFerme);
		System.out.println(labyrinthe.nbX + " "+ labyrinthe.nbY);

		uneCase[] matriceCases = new uneCase[labyrinthe.nbX*labyrinthe.nbY];
		//unMur[][] matriceMurPossibles = new unMur[labyrinthe.nbX-1][labyrinthe.nbY-1];
		unMur[] tabMurVertical = new unMur[(labyrinthe.nbY-1)*labyrinthe.nbX];
		unMur[] tabMurHorizontal = new unMur[(labyrinthe.nbX-1)*labyrinthe.nbY];
		
		this.init(matriceCases, tabMurHorizontal, tabMurVertical);
		this.imprimMatrice(matriceCases, tabMurHorizontal, tabMurVertical);
		
		int nbMurOuvert = 0;
		Random random = new Random();
		
		while (nbMurOuvert < (labyrinthe.nbX * labyrinthe.nbY - 1) ) {

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
						case2 = murAlea+labyrinthe.nbY;
					}
				}
				else{
					murAlea = random.nextInt(tabMurVertical.length);
					
					if (tabMurVertical[murAlea].getEtat() == 1){
						int dec = (murAlea/(labyrinthe.nbY-1));
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
			for(i=0; i<labyrinthe.nbX; i++) {
				for(j=0; j<labyrinthe.nbY; j++) {
					if(matriceCases[i*labyrinthe.nbY+j].getValue() == tmp )
						matriceCases[i*labyrinthe.nbY+j].setValue(matriceCases[case1].getValue()); ;
				}
			}
			
			
			imprimMatrice(matriceCases, tabMurHorizontal, tabMurVertical);
			nbMurOuvert++;
			System.out.println(nbMurOuvert);
			System.out.println(labyrinthe.nbX * labyrinthe.nbY - 1);
			
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
		for(i=0; i<labyrinthe.nbX; i++) {
			for(j=0; j<labyrinthe.nbY; j++) {
				matriceCases[i*labyrinthe.nbY+j] = new uneCase(iter++);
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
		for(i=0; i<labyrinthe.nbX; i++) {
			System.out.println();
			for(j=0; j<labyrinthe.nbY; j++) {
				System.out.print(matriceCases[i*labyrinthe.nbY+j]);
			}
		}
		
		int iter = 0;
		int vise = labyrinthe.nbY - 1;

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
				if (vise == labyrinthe.nbY - 1)
					System.out.print(tabMurVertical[iterVert++]);
				else System.out.print(tabMurHorizontal[iterHor++]);
			else {
				if (vise == labyrinthe.nbY - 1)
					vise = labyrinthe.nbY;
				else vise = labyrinthe.nbY - 1;
				iter=1;
				System.out.println("");
//				System.out.println("-| ");
//				System.out.print(" |-");
				if (vise == labyrinthe.nbY - 1)
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
	
}
