package sopra.challenge.labyrinthe;

public class MurNormal extends Mur  {

	public MurNormal(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isMurIndestructible() {
		return false;
	}

	@Override
	public boolean isMurNormal() {
		return true;
	}

	@Override
	public boolean isZone() {
		return false;
	}

	@Override
	public boolean isZoneArrivee() {
		return false;
	}

	@Override
	public boolean isZoneDepart() {
		return false;
	}

	@Override
	public boolean isPorte() {
		return false;
	}
	
}
