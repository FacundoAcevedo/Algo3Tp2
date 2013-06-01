package juego;

public class Jugador {
	protected Puntaje puntaje;
	
	public Jugador(){
		this.puntaje = new Puntaje();
	}
	
	public Puntaje puntaje(){
		return this.puntaje;
	}

	public void descontarPuntos(int puntosPerdidos) {
		puntaje.descontarPuntos(puntosPerdidos);
	}
	
}
