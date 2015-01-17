package sopra.challenge.labyrinthe;

public class ZoneArrivee extends Zone{

	public ZoneArrivee(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isMur() {
		return false;
	}

	@Override
	public boolean isMurIndestructible() {
		return false;
	}

	@Override
	public boolean isMurNormal() {
		return false;
	}

	@Override
	public boolean isZoneArrivee() {
		return true;
	}

	@Override
	public boolean isZoneDepart() {
		return false;
	}

}
