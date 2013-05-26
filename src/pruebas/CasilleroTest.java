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
    public void testCrearConIdArray() {
		int[] id = {0,0};
        Casillero casillero = new Casillero(id);
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
	 Casillero casillero = new Casillero(9,9);
	 int[] id = {9,9};
	 assertArrayEquals(casillero.id(), id);
	}
	
	@Test
    public void testCasilleroObtenerIdDiagonalInferiorCreadoConIdArray(){
		int[] id = {0,0};
    	Casillero casillero = new Casillero(id);
    	assertArrayEquals(casillero.id(), id);
	}
	
	@Test
    public void testCasilleroObtenerIdDiagonalSuperiorCreadoConIdArray(){
		int[] id = {9,9};
    	Casillero casillero = new Casillero(id);
    	assertArrayEquals(casillero.id(), id);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdInferiorACero(){
	 new Casillero(-1,-2);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdSuperiorANueve(){
	 new Casillero(100,123);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdXInvalido(){
	 new Casillero(100,5);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdYInvalido(){
	 new Casillero(5,100);
	}
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdXInvalidoConIdArray(){
	 int[] id = {111,5};
	 new Casillero(id);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
    public void testCasilleroObtenerIdYInvalidoConIdArray(){
	 int[] id = {5,111};
	 new Casillero(id);
	}
	
	
}
