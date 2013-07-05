package municiones;

import tablero.Casillero;
import tablero.Tablero;

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
	
	public void disminuirRetardo(){
		if (miRetardo > 0)
			miRetardo -=1;
	}
	
	public int costo(){
		return miCosto;
	}
	
	public void impactar(Tablero tablero, Casillero miCasillero){
		
	}
	
}
