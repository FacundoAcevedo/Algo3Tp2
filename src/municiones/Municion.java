package municiones;

public class Municion {
	private int miCosto;
	private int miRetardo;
	
	public Municion(int unCosto, int unTiempo){
		miCosto = unCosto;
		miRetardo = unTiempo;
	}
	
	public int costo(){
		return miCosto;
	}
	
	public int retardo(){
		return miRetardo;
	}
}
