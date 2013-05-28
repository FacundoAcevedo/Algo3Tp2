package juego;

import municiones.Municion;
import tablero.Casillero;
import tablero.Tablero;


public class BatallaNavalgo {
	protected Jugador jugador;
	protected Tablero tablero;
	
	public BatallaNavalgo(){
		this.jugador = new Jugador();
		this.tablero = new Tablero();
	}
	
	public Jugador jugador(){
		return this.jugador;
	}
	
	public Tablero tablero(){
		return this.tablero;
	}
	
	public int puntosDelJugador(){
		return this.jugador.puntaje().puntos();
	}
	
	public void jugadorDispara(Municion municion, Casillero casillero){
		
	}
}

