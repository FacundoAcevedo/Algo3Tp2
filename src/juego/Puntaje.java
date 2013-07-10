package juego;

import java.io.Serializable;

import interfaces.Reseteable;

public class Puntaje implements Reseteable, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6937809165775033662L;
	private int puntos;

    public Puntaje() {
            puntos = 10000;
    }

    public int puntos() {
            return puntos;
    }

    public void descontarPuntos(int puntosPerdidos) {
            puntos -= puntosPerdidos;        
    }

	@Override
	public void reset() {
        this.puntos = 10000;

		
	}

}
