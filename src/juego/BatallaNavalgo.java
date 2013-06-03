package juego;

import municiones.Municion;
import tablero.Casillero;
import tablero.Tablero;


public class BatallaNavalgo {
	private Jugador jugador;
	private Tablero tablero;
	
	public BatallaNavalgo(){
		this.jugador = new Jugador();
		this.tablero = new Tablero();
	}
	
	public void partida(){
		while( ! this.juegoTerminado() ){
			this.ronda();
		}
	}
	
	public void ronda(){
		//juega el jugador
		
		//this.jugadorDispara(municion, casillero);
		this.avanzarTurno();
		this.finDeTurno();
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
	
	private void avanzarTurno(){
		//Este m�todo deber�a revisar el contenido de cada uno de los casilleros, y en los que
		//haya alguna munici�n con retardo = 0, deber�a accionarlas. Es decir, fijarse si en ese 
		//mismo casillero hay una parte de nave, y si la hay, atacarla. Luego borrar la munici�n 
		//del casillero. En los casilleros con municiones con retardo != 0, deber�a hacerce
		//retardo - = 1. 
	}
	
	private void finDeTurno(){
		this.jugador.descontarPuntos(10);
	}

	public void jugadorDispara(Municion municion, Casillero casillero){
		this.jugador.disparar(municion, casillero);
	}
	
	public int navesDestruidas(){
		return this.tablero.navesDestruidas();
	}
	
	/* Cantidad de naves en el tablero, sin importar estado. */
	public int cantidadTotalNaves(){
		return this.tablero.cantidadTotalNaves();
	}
	
}

