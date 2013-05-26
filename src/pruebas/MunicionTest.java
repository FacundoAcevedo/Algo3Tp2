package pruebas;

import municiones.Municion;
import static org.junit.Assert.*;

import org.junit.Test;

public class MunicionTest {

	@Test
	public void testCrear() {
		Municion municion = new Municion(0);
		assertTrue(municion != null);
	}
	
	@Test
	public void testCosto0(){
		Municion municion = new Municion(0);
		assertTrue(municion.costo() == 0);
	}
	
	@Test
	public void testCosto50(){
		Municion municion = new Municion(50);
		assertTrue(municion.costo() == 50);
	}
	
}
