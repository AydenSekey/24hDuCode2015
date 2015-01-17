package sopra.challenge.labyrinthe.generateurLabyrinthe;

public class unMur {

	int etat; // 0 ouvert, 1 fermé
	int sens; // 0 vertical, 1 horizontal

	@Override
	public String toString() {
		if (etat == 0){
			return "   ";
		}
		else if (sens == 1) {
			return "  |";
		}
		else return "---";
	}

	public unMur(int etat, int sens) {
		super();
		this.etat = etat;
		this.sens = sens;
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
}
