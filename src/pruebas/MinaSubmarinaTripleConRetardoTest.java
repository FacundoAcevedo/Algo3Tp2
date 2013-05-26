package pruebas;

import static org.junit.Assert.*;

import municiones.MinaSubmarinaTripleConRetardo;

import org.junit.Test;

public class MinaSubmarinaTripleConRetardoTest {

	@Test
	public void testCrear() {
		MinaSubmarinaTripleConRetardo minaSubmarinaTripleConRetardo = new MinaSubmarinaTripleConRetardo();
		assertTrue(minaSubmarinaTripleConRetardo != null);
	}
	
	@Test
	public void testCosto(){
		MinaSubmarinaTripleConRetardo minaSubmarinaTripleConRetardo = new MinaSubmarinaTripleConRetardo();
		assertTrue(minaSubmarinaTripleConRetardo.costo() == 125);
	}

}
