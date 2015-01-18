package sopra.challenge.view.light;

import java.util.ArrayList;
import java.util.List;

import sopra.challenge.control.MatriceConverteur;
import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.Porte;
import sopra.challenge.view.SopraMazeMazeGame;
import sopra.challenge.view.generator.TypeLayer;

public class LightListenerGame implements LightListener{
	
	private static LightListenerGame instance;
	private SopraMazeMazeGame game;
	private List<Porte> portes;
	
	private LightListenerGame() {
		// TODO Auto-generated constructor stub
	}
	
	public static LightListenerGame getInstance() {
		if(instance == null)
			instance = new LightListenerGame();
		return instance;
	}
	
	public void setGame(SopraMazeMazeGame game) {
		this.game = game;
		recupererPortes();
	}

	@Override
	public void dayStart() {
		// TODO Auto-generated method stub
		System.out.println("JOUR");
		for(Porte porte : portes) {
			for(int y = 2; y < MatriceConverteur.getHauteur(porte.getPositionBloc().coordX, porte.getPositionBloc().coordY); y++) {
				this.game.getBlockWorld().setBlock(porte.getPositionBloc().coordX, y, porte.getPositionBloc().coordY, TypeLayer.VOID.type());
			}
			
		}
	}

	@Override
	public void nightStart() {
		// TODO Auto-generated method stub
		System.out.println("NUIT");
		for(Porte porte : portes) {
			for(int y = 2; y < MatriceConverteur.getHauteur(porte.getPositionBloc().coordX, porte.getPositionBloc().coordY); y++) {
				this.game.getBlockWorld().setBlock(porte.getPositionBloc().coordX, y, porte.getPositionBloc().coordY, TypeLayer.STONEBRICK.type());
			}
		}
	}

	private void recupererPortes() {
		portes = new ArrayList<Porte>();
		List<Bloc> blocs = Labyrinthe.getInstance().blocs;
		for(Bloc bloc : blocs) {
			if(bloc.isPorte()) {
				portes.add((Porte) bloc);
			}
		}
	}
}
