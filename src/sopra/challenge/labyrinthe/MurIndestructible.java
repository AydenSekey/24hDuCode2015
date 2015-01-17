package sopra.challenge.labyrinthe;

public class MurIndestructible extends Mur {

	public MurIndestructible(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isMurIndestructible() {
		return true;
	}

	@Override
	public boolean isMurNormal() {
		return false;
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
	public String toString(){
		return " £ ";
	}
}
