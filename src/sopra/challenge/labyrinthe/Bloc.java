package sopra.challenge.labyrinthe;

public abstract class Bloc {
	public Position positionBloc = new Position();
	public boolean occupee = false;

	
	public Bloc(int x, int y) {
		super();
		this.positionBloc.coordX =x;
		this.positionBloc.coordY =y;
	}

	public abstract boolean isMur();

	public abstract boolean isMurIndestructible();

	public abstract boolean isMurNormal();

	public abstract boolean isZone();

	public abstract boolean isZoneArrivee();

	public abstract boolean isZoneDepart();
	
	public abstract boolean isPorte();
	
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
	
	public boolean estALaPosition(int x , int y){
		return (this.getPositionBloc().coordX ==x) && (this.getPositionBloc().coordY ==y);
	}
}
