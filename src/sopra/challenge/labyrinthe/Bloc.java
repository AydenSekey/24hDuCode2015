package sopra.challenge.labyrinthe;



public class Bloc {
	public Position positionBloc = new Position();
	public boolean occupee = false;
	
	public Bloc(int x, int y) {
		super();
		this.positionBloc.coordX =x;
		this.positionBloc.coordY =y;
	}
	
	
	public boolean isOccupee() {
		return occupee;
	}

	public void setOccupee(boolean occupee) {
		this.occupee = occupee;
	}


	public Position getPositionBloc() {
		return positionBloc;
	}

	public void setPositionBloc(Position positionBloc) {
		this.positionBloc = positionBloc;
	}
	
	
}
