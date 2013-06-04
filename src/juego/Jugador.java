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
		this.descontarPuntos(municion.costo());
		
	}
}
