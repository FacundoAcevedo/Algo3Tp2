package batallaNavalgo.pruebas;

import junit.framework.TestCase;
import batallaNavalgo.modelo.Casillero;

public class CasilleroTest extends TestCase{

	public void testCrear() {
		Casillero casillero = new Casillero();
		assertTrue(casillero != null);
	}

}
