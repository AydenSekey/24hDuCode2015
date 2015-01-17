package sopra.challenge.view.generator;

public enum TypeLayer {

	STONE, SAND, DIRT, STONEBRICK, WOODPLANK, BEDROCK, VOID;
	
	public static int getType(TypeLayer type) {
		
		switch(type) {
		case STONE: return 1;
		case DIRT: return 3;
		case SAND: return 12;
		case STONEBRICK: return 4;
		case WOODPLANK: return 5;
		case BEDROCK: return 7;
		case VOID: return 0;
		default: return 1;
		}
	}
	
}
