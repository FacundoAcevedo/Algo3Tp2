package juego;


import excepciones.ErrorCasilleroOcupadoConOtraMunicion;
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
		this.posicionarNavesAleatoriamente();
		while( ! this.juegoTerminado() ){
			this.ronda();
		}
	}
	
	public void ronda(){
		Municion municionElegida = this.jugador.eligeMunicion();
		Casillero casilleroElegido = this.jugador.eligeCasillero(this.tablero);
		
		try {
			casilleroElegido.ponerMunicion(municionElegida);
		} catch (ErrorCasilleroOcupadoConOtraMunicion e) {
			// Y aca lo mismo, ver que hace
		}
		
		this.finDeTurno();
	}
	
	public void posicionarNavesAleatoriamente() {
		this.tablero.posicionarNavesAleatoriamente();
	}

	public boolean juegoTerminado() {
		return ( this.cantidadDeNavesDestruidas() == this.cantidadTotalNaves() );
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
	
	private void finDeTurno(){
		this.tablero.actualizarTablero();
		this.jugador.descontarPuntos(10);
	}

	/* Se saco jugador.disparar de Jugador.
	public void jugadorDispara(Municion municion, Casillero casillero){
		this.jugador.disparar(municion, casillero);
		this.tablero.agregarCasilleroConMunicion(casillero);
	}
	*/
	
	public int cantidadDeNavesDestruidas(){
		return this.tablero.cantidadDeNavesDestruidas();
	}
	
	/* Cantidad de naves en el tablero, sin importar estado. */
	public int cantidadTotalNaves(){
		return this.tablero.cantidadTotalDeNaves();
	}
	
}

