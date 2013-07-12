package municiones;

import java.io.Serializable;

import tablero.Casillero;
import tablero.Tablero;

public class Municion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6585086692317395546L;
	private int miCosto;
	private int miRetardo;
	public boolean efectoInmediato;
	
	public Municion(int unCosto, int unRetardo, boolean esEfectoInmediato){
		miCosto = unCosto;
		miRetardo = unRetardo;
		efectoInmediato = esEfectoInmediato;
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
	
	public String obtenerNombre(){
		return this.getClass().getSimpleName();
	}
	
	public boolean efectoInmediato(){
		return this.efectoInmediato;
	}
	
}
