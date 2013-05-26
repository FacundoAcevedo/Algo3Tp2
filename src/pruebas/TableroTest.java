package pruebas;


import static org.junit.Assert.*;

import org.junit.Test;

import tablero.*;

public class TableroTest {

	@Test
	public void testCrear() {
		Tablero tablero = new Tablero();
        assertTrue(tablero != null);
	}
	
	


}
