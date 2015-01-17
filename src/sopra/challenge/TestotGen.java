package sopra.challenge;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrintheAurel;

public class TestotGen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Labyrinthe lab = new Labyrinthe("Balaise", 16, 32);
		GenerateurLabyrintheAurel gen = new GenerateurLabyrintheAurel(lab);
		
	}

}
