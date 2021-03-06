package sopra.challenge.control;

import java.util.Random;

import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.view.generator.TypeLayer;

public class MatriceConverteur {
	
	private static final int tailleMur = 3;
	private static final int hauteurMur = 10;
	private static final int tailleAllee = 3;
	private static final int tailleCamp = 10;
	
	public static TypeLayer getBloc(int x, int z, boolean day) {
		
		Bloc bloc = Labyrinthe.getInstance().getBloc(x, z);
		
		if(bloc != null) {
			if(Labyrinthe.getInstance().getBloc(x, z).isMurIndestructible())
				return TypeLayer.BEDROCK;
			if(Labyrinthe.getInstance().getBloc(x, z).isMurNormal())
				return TypeLayer.STONEBRICK;
			if(Labyrinthe.getInstance().getBloc(x, z).isZoneDepart())
				return TypeLayer.DIRT;
			if(Labyrinthe.getInstance().getBloc(x, z).isZoneArrivee())
				return TypeLayer.VOID;
			if(Labyrinthe.getInstance().getBloc(x,z).isPorte()) {
				return TypeLayer.VOID;
			}
		}

		return TypeLayer.VOID;
	}
	
	public static int getHauteur(int x, int z) {
		
		Bloc bloc = Labyrinthe.getInstance().getBloc(x, z);
		int r = (int)((new Random().nextInt(hauteurMur +2 - (hauteurMur - 2)))) + (hauteurMur - 2);
		
		if(bloc != null) {
			if(Labyrinthe.getInstance().getBloc(x, z).isMurIndestructible())
				return hauteurMur;
			if(Labyrinthe.getInstance().getBloc(x, z).isMurNormal())
				return r;
			if(Labyrinthe.getInstance().getBloc(x, z).isZoneDepart())
				return 2;
			if(Labyrinthe.getInstance().getBloc(x, z).isZoneArrivee())
				return 2;
			if(Labyrinthe.getInstance().getBloc(x,z).isPorte())
				return hauteurMur;
		}

		return 2;
	}

}
