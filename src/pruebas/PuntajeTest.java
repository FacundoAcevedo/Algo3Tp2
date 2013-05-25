package pruebas;

import junit.framework.TestCase;

import modelo.Puntaje;

public class PuntajeTest extends TestCase{

	public void testCrear() {
		Puntaje puntaje = new Puntaje();
		assertTrue(puntaje != null);
	}
	
	public void testPuntajeComienzaEn10000(){
		Puntaje puntaje = new Puntaje();
		assertTrue(puntaje.puntos() == 10000);
	}
	
	public void testDescontar10Puntos(){
		Puntaje puntaje = new Puntaje();
		puntaje.descontarPuntos(10);
		assertTrue(puntaje.puntos() == 9990);
	}
	
	public void testDescontar100Puntos(){
		Puntaje puntaje = new Puntaje();
		puntaje.descontarPuntos(100);
		assertTrue(puntaje.puntos() == 9900);
	}
}

