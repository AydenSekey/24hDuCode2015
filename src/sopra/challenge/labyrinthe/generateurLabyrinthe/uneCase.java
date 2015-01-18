package sopra.challenge.labyrinthe.generateurLabyrinthe;

public class uneCase {
	 
	@Override
	public String toString() {
		if (value<10)
			return "  "+value+" ";
		if (value<100)
			return " "+value+" ";
		return value+" ";
	}

	private int value;

	public uneCase(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
