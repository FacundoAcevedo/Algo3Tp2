package juego;


import java.util.Observable;

import municiones.Municion;

import tablero.Casillero;
import tablero.Tablero;
import tablero.TableroComunicable;


public class BatallaNavalgo extends Observable {
	private Jugador jugador;
	private TableroComunicable tablero;
	
	public BatallaNavalgo(){
		this.jugador = new Jugador();
		this.tablero = new Tablero();
	}
	
	// Esto supongo que o va en el controlador, o habra
	// que modificarlo para que se puedan pasar los 
	// parametros the this.ronda(Municion, Casillero)
	/*
	public void partida(){
		this.posicionarNavesAleatoriamente();
		while( ! this.juegoTerminado() ){
			this.ronda();
		}
	}*/
	
	public void ronda(Municion municionElegida, Casillero casilleroElegido){
		this.tablero.ponerMuncion(municionElegida, casilleroElegido.id());
		this.finDeTurno();
	}
	
	public void posicionarNavesAleatoriamente() {
		this.tablero.construirYPosicionarLasNavesAleatoriamente();
		this.ActualizarObservadores();
	}

	public boolean juegoTerminado() {
		return ( this.cantidadDeNavesDestruidas() == this.cantidadTotalNaves() );
	}

	public Jugador obtenerJugador(){
		return this.jugador;
	}

	//Tablero
	public TableroComunicable obtenerTablero(){
		return this.tablero;
	}
	
	public void ponerMunicion(Municion municion, int[] id){
		this.tablero.ponerMuncion(municion, id);
		this.ActualizarObservadores();
	}
	
	public int puntosDelJugador(){
		return this.jugador.puntaje().puntos();
	}
	
	private void finDeTurno(){
		this.tablero.actualizarTablero();
		this.jugador.descontarPuntos(10);
	}
	
	public int cantidadDeNavesDestruidas(){
		return this.tablero.cantidadDeNavesDestruidas();
	}
	
	/* Cantidad de naves en el tablero, sin importar estado. */
	public int cantidadTotalNaves(){
		return this.tablero.cantidadTotalDeNaves();
	}
	
	public void ActualizarObservadores()
	{
		setChanged();
		notifyObservers();		
	}
	
}

