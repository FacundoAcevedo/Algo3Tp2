package juego;


import java.io.Serializable;
import java.util.Observable;

import municiones.Municion;

import tablero.Casillero;
import tablero.Tablero;
import tablero.TableroComunicable;


public class BatallaNavalgo extends Observable implements Serializable {

	private static final long serialVersionUID = -4624714350585496059L;
	
	private Jugador jugador;
	private TableroComunicable tablero;
	
	public BatallaNavalgo(){
		this.jugador = new Jugador();
		this.tablero = new Tablero();
	}
	

	public void iniciarPartidaNueva(){
		this.reset();
		this.posicionarNavesAleatoriamente();
	}
	
	
	public void ronda(Municion municionElegida, int[] idCasillero){
		this.ponerMunicion(municionElegida, idCasillero);
		this.finDeTurno();
		this.ActualizarObservadores();
	}
	
	public void posicionarNavesAleatoriamente() {
		this.tablero.construirYPosicionarLasNavesAleatoriamente();
		this.ActualizarObservadores();
	}

	public boolean juegoTerminado() {
		if (this.cantidadTotalNaves() == 0)
			return false;
		else
			return ( this.cantidadDeNavesDestruidas() == this.cantidadTotalNaves() );
	}
	
	public boolean juegoEnProceso(){
		if (this.cantidadTotalNaves() != 0)
			return true;
		return false;
	}
	

	public Jugador obtenerJugador(){
		return this.jugador;
	}
	
	//Tablero
	public TableroComunicable obtenerTablero(){
		return this.tablero;
	}
	
	private void ponerMunicion(Municion municion, int[] id){
		this.tablero.ponerMunicion(municion, id);
		this.jugador.descontarPuntos(municion.costo());
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
	public Casillero obtenerCasillero(int[] id){
		return this.tablero.obtenerCasillero(id);
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


	public void reset() {
		this.tablero.reset();
		this.jugador.reset();
		
	}
	
}

