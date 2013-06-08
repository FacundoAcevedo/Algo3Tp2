package juego;

import municiones.Municion;
import tablero.Casillero;
import tablero.Tablero;

public class Jugador {
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


	public Municion eligeMunicion() {
		// TODO iteractua con el usuario para que elija una Municion y la devuelva
		
		//this.descontarPuntos(municion.costo());
		return null;
	}

	public Casillero eligeCasillero(Tablero tablero) {
		// TODO iteractua con el usuario para que elija un Casillero 
		// del tablero y lo devuelva.
		return null;
	}
}
