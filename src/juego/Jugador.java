package juego;

import interfaces.Reseteable;
import municiones.Municion;
import tablero.Casillero;
import tablero.Tablero;

public class Jugador implements Reseteable {
	private Puntaje puntaje;
	
	public Jugador(){
		this.puntaje = new Puntaje();
	}
	
	public Puntaje puntaje(){
		return this.puntaje;
	}

	public void descontarPuntos(int puntosPerdidos) {
		puntaje.descontarPuntos(puntosPerdidos);
	}

	@Override
	public void reset() {
		this.puntaje.reset();
		
	}

}
