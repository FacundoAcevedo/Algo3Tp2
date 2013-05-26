package pruebas;
import static org.junit.Assert.*;

import org.junit.Test;

import tablero.Casillero;

public class CasilleroTest {

	@Test
    public void testCrear() {
        Casillero casillero = new Casillero(0,0);
        assertTrue(casillero != null);
	}
	
}
