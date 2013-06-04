package municiones;

public class Municion {
	private int miCosto;
	private int miRetardo;
	
	public Municion(int unCosto, int unRetardo){
		miCosto = unCosto;
		miRetardo = unRetardo;
	}
	
	public int retardo(){
		return miRetardo;
	}
	
	public int costo(){
		return miCosto;
	}
	
}
