package sopra.challenge.labyrinthe;

public class Zone extends Bloc {

	public Zone(int x, int y) {
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
	public boolean isZone() {
		return true;
	}

	@Override
	public boolean isZoneArrivee() {
		return false;
	}

	@Override
	public boolean isZoneDepart() {
		return false;
	}
	public String toString(){
		return "   ";
	}
}
