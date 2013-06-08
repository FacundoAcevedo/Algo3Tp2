package pruebas;


import static org.junit.Assert.*;

import java.util.LinkedList;

import naves.Nave;

import org.junit.Test;

import excepciones.ErrorIdCasilleroInvalido;

import tablero.*;

public class TableroTest {

	@Test
	public void testCrear() {
		Tablero tablero = new Tablero();
        assertTrue(tablero != null);
	}
	
	@Test
	public void testSeCreaVacio() {
		Tablero tablero = new Tablero();
        assertTrue(tablero.estaVacio());
	}
	@Test
	public void testPonerNavesAleatoriamente() {
		//Se testea la existencia de las naves, y su tipo
		//no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
        tablero.posicionarNavesAleatoriamente();
        assertTrue(tablero.cantidadTotalNaves() == 7);
        LinkedList<Nave> naves = tablero.devolverNaves();
        
        for(Nave nave : naves){
        	assertTrue(nave instanceof Nave);
        }
	}
	
	@Test
	public void testPedirCasilleroValido() {
		Tablero tablero = new Tablero();
		int [] id = {0,0};
        Casillero casillero = tablero.obtenerCasillero(id);
        assertTrue(casillero != null);
	}
	
	@Test(expected=ErrorIdCasilleroInvalido.class)
	public void testPedirCasilleroInvalido() {
		Tablero tablero = new Tablero();
		int [] id = {-10,10};
		tablero.obtenerCasillero(id);

	}
	


}
