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
	
	public void partida(){
		while( ! this.juegoTerminado() ){
			//juega el jugador
			this.finDeTurno();
		}
	}
	
	public boolean juegoTerminado() {
		return ( this.navesDestruidas() == this.cantidadTotalNaves() );
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
	
	public void finDeTurno(){
		this.jugador.descontarPuntos(10);
	}
	
	public void jugadorDispara(Municion municion, Casillero casillero){
		//FALTA IMPLEMENTAR EL DISPARO EN SI
		
		this.jugador.descontarPuntos(municion.costo());
	}
	
	public int navesDestruidas(){
		return this.tablero.navesDestruidas();
	}
	
	/* Cantidad de naves en el tablero, sin importar estado. */
	public int cantidadTotalNaves(){
		return this.tablero.cantidadTotalNaves();
	}
	
}

