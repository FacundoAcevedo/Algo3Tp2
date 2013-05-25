package pruebas;

import juego.Puntaje;
import static org.junit.Assert.*;

import org.junit.Test;

public class PuntajeTest {

	@Test
	public void testCrear() {
		Puntaje puntaje = new Puntaje();
		assertTrue(puntaje != null);
	}
	@Test
	public void testPuntajeComienzaEn10000() {
		Puntaje puntaje = new Puntaje();
		assertTrue(puntaje.puntos() == 10000);
	}
	@Test
	public void testDescontar10Puntos() {
		Puntaje puntaje = new Puntaje();
		puntaje.descontarPuntos(10);
		assertTrue(puntaje.puntos() == 9990);
	}
	@Test
	public void testDescontar100Puntos() {
		Puntaje puntaje = new Puntaje();
		puntaje.descontarPuntos(100);
		assertTrue(puntaje.puntos() == 9900);
	}
	@Test
	public void testDescontar1000Puntos() {
		Puntaje puntaje = new Puntaje();
		puntaje.descontarPuntos(10000);
		assertTrue(puntaje.puntos() == 0);
	}

}
