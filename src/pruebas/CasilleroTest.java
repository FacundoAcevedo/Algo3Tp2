package pruebas;
import juego.Casillero;
import static org.junit.Assert.*;

import org.junit.Test;

public class CasilleroTest {

	@Test
    public void testCrear() {
        Casillero casillero = new Casillero();
        assertTrue(casillero != null);
	}
	
}
