package juego;

import municiones.Municion;
import tablero.Casillero;

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

	public void disparar(Municion municion, Casillero casillero){
		casillero.ponerMunicion(municion);
		this.descontarPuntos(municion.costo()); //10 es la cantidad de puntos que se pierden por turno. Ver si es mejor definirlo en otro lado.
	}
}
