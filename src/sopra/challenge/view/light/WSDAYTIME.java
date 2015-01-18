package sopra.challenge.view.light;

public class WSDAYTIME {
	
	private static WSDAYTIME instance;
	private boolean value = false;
	
	private WSDAYTIME() {}
	
	public static WSDAYTIME getInstance(){
		if(instance == null)
			instance = new WSDAYTIME();
		return instance;
	}
	
	public boolean isDayTime() {
		return value;
	}
	
	public void setValue(boolean v) {
		this.value = v;
	}

}
