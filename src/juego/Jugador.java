package juego;

import java.io.Serializable;

import interfaces.Reseteable;


public class Jugador implements Reseteable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6221695574014527648L;
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
