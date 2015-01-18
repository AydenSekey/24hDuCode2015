package sopra.challenge.labyrinthe;

public abstract class Mur extends Bloc{

	public Mur(int x, int y) {
		super(x, y);
		setOccupee(true);
	}

	@Override
	public boolean isMur() {
		return true;
	}
	public boolean isZone() {
		return false;
	}
	public String toString(){
		return " # ";
	}
}
