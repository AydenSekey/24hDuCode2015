package sopra.challenge.view;

public enum End {
	NO_END, EXIT, DEAD;
	
	/**
	 * Donne le texte associé au statut de fin.
	 * @return le texte de statut de fin.
	 */
	public String text() {
		switch(this) {
			case DEAD :
				return "Vous etes mort !";
			case EXIT :
				return "Congratulation !";
			default:
				return "";
		}
	}
}
