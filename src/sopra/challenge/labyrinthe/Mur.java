package sopra.challenge.labyrinthe;

public abstract class Mur extends Bloc{

	public Mur(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isMur() {
		return true;
	}

}
