package pruebas;

import static org.junit.Assert.*;
import juego.BatallaNavalgo;

import municiones.DisparoConvencional;
import municiones.MinaSubmarinaDobleConRetardo;
import municiones.MinaSubmarinaPorContacto;
import municiones.MinaSubmarinaPuntualConRetardo;
import municiones.MinaSubmarinaTripleConRetardo;

import org.junit.Test;

import tablero.Casillero;

public class BatallaNavalgoTest {

	@Test
	public void testCrear() {
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		assertTrue(batallaNavalgo.puntosDelJugador() == 10000);
		assertTrue(batallaNavalgo.tablero().estaVacio());
	}
	
	@Test
	public void testDispararConvencional(){
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		DisparoConvencional disparo = new DisparoConvencional();
		Casillero casillero = new Casillero(0,0);
		batallaNavalgo.jugadorDispara(disparo,casillero);
		assertTrue (batallaNavalgo.puntosDelJugador() == 9800);
	}
	
	@Test
	public void testDispararMinaSubmarinaPorContacto(){
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		MinaSubmarinaPorContacto mina = new MinaSubmarinaPorContacto();
		Casillero casillero = new Casillero(0,0);
		batallaNavalgo.jugadorDispara(mina,casillero);
		assertTrue (batallaNavalgo.puntosDelJugador() == 9850);
	}

	@Test
	public void testDispararMinaSubmarinaPuntualConRetardo(){
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		MinaSubmarinaPuntualConRetardo mina = new MinaSubmarinaPuntualConRetardo();
		Casillero casillero = new Casillero(0,0);
		batallaNavalgo.jugadorDispara(mina,casillero);
		assertTrue (batallaNavalgo.puntosDelJugador() == 9950);
	}

	@Test
	public void testDispararMinaSubmarinaDobleConRetardo(){
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		MinaSubmarinaDobleConRetardo mina = new MinaSubmarinaDobleConRetardo();
		Casillero casillero = new Casillero(0,0);
		batallaNavalgo.jugadorDispara(mina,casillero);
		assertTrue (batallaNavalgo.puntosDelJugador() == 9900);
	}

	@Test
	public void testDispararMinaSubmarinaTripleConRetardo(){
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		MinaSubmarinaTripleConRetardo mina = new MinaSubmarinaTripleConRetardo();
		Casillero casillero = new Casillero(0,0);
		batallaNavalgo.jugadorDispara(mina,casillero);
		assertTrue (batallaNavalgo.puntosDelJugador() == 9875);
	}
	
	@Test
	public void testDisparar(){
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		DisparoConvencional disparo = new DisparoConvencional();
		MinaSubmarinaTripleConRetardo minatriple = new MinaSubmarinaTripleConRetardo();
		MinaSubmarinaDobleConRetardo minadoble = new MinaSubmarinaDobleConRetardo();
		Casillero unCasillero = new Casillero(1,1);
		Casillero otroCasillero = new Casillero(0,0);
		batallaNavalgo.jugadorDispara(disparo,unCasillero);
		batallaNavalgo.jugadorDispara(minadoble,unCasillero);
		batallaNavalgo.jugadorDispara(minatriple,otroCasillero);
		assertTrue (batallaNavalgo.puntosDelJugador() == 9575);
	}
	

}
