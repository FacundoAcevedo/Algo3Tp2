package pruebas;

import static org.junit.Assert.*;
import municiones.MinaSubmarinaPorContacto;

import org.junit.Test;

public class MinaSubmarinaPorContactoTest {
	
	@Test
	public void testCrear() {
		MinaSubmarinaPorContacto minaSubmarinaPorContacto = new MinaSubmarinaPorContacto();
		assertTrue(minaSubmarinaPorContacto != null);
	}
	
	@Test
	public void testCosto(){
		MinaSubmarinaPorContacto minaSubmarinaPorContacto = new MinaSubmarinaPorContacto();
		assertTrue(minaSubmarinaPorContacto.costo() == 150);
	}

}
