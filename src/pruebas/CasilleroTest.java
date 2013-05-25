package pruebas;

import junit.framework.TestCase;

import modelo.Casillero;

public class CasilleroTest extends TestCase{

	public void testCrear() {
		Casillero casillero = new Casillero();
		assertTrue(casillero != null);
	}

}
