package sopra.challenge.labyrinthe;

public class MurNormal extends Mur  {

	public MurNormal(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return false;
	}
	
}
