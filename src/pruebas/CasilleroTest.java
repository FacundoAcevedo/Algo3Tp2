package pruebas;
import static org.junit.Assert.*;

import org.junit.Test;

import excepciones.ErrorIdCasilleroInvalido;

import tablero.Casillero;

public class CasilleroTest {

	@Test
    public void testCrear() {
        Casillero casillero = new Casillero(0,0);
        assertTrue(casillero != null);
	}
	@Test
    public void testCasilleroObtenerIdDiagonalInferior(){
	 Casillero casillero = new Casillero(0,0);
	 int[] id = {0,0};
	 assertArrayEquals(casillero.id(), id);
	}
	
	@Test
    public void testCasilleroObtenerIdDiagonalSuperior(){
	 Casillero casillero = new Casillero(99,99);
	 int[] id = {99,99};
	 assertArrayEquals(casillero.id(), id);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdInferiorACero(){
	 Casillero casillero = new Casillero(-1,-2);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdSuperiorAnoventaYNueve(){
	 Casillero casillero = new Casillero(100,123);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdXInvalido(){
	 Casillero casillero = new Casillero(100,5);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdYInvalido(){
	 Casillero casillero = new Casillero(5,100);
	}
	
	
}
