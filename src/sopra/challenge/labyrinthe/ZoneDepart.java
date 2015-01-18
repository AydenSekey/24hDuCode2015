package sopra.challenge.labyrinthe;

public class ZoneDepart extends Zone {
	Boolean milieu = false;

	public ZoneDepart(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	
	public Boolean getMilieu() {
		return milieu;
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
		return false;
	}

	@Override
	public boolean isZoneDepart() {
		return true;
	}
	
	public void setMilieu(Boolean milieu) {
		this.milieu = milieu;
	}


	public String toString(){
		return " D ";
	}
}
