package pruebas;


import static org.junit.Assert.*;

import org.junit.Test;

import excepciones.ErrorIdCasilleroInvalido;

import tablero.*;

public class TableroTest {

	@Test
	public void testCrear() {
		Tablero tablero = new Tablero();
        assertTrue(tablero != null);
	}
	
	@Test
	public void testNoSeCreaVacio() {
		Tablero tablero = new Tablero();
        assertFalse(tablero.estaVacio());
	}
	
	@Test
	public void testPedirCasilleroValido() {
		Tablero tablero = new Tablero();
		int [] id = {0,0};
        Casillero casillero = tablero.obtenerCasillero(id);
        assertTrue(casillero != null);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
	public void testPedirCasilleroInvalido() {
		Tablero tablero = new Tablero();
		int [] id = {-10,10};
		tablero.obtenerCasillero(id);

	}
	


}
