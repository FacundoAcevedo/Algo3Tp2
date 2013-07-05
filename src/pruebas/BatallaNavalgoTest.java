package pruebas;

import static org.junit.Assert.*;
import juego.BatallaNavalgo;

import municiones.MinaSubmarinaPorContacto;
import municiones.Municion;
import naves.Direccion;
import naves.EstadoDeSalud;
import naves.Lancha;
import naves.Nave;
import naves.Sentido;

import org.junit.Test;

public class BatallaNavalgoTest {

	@Test
	public void testCrear() {
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		assertTrue(batallaNavalgo != null);
	}
	
	@Test
	public void testJugadorComienzaConPuntajeCorrecto() {
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		assertTrue(batallaNavalgo.puntosDelJugador() == 10000);
	}
	
	@Test
	public void testTableroSeCreaSinNaves() {
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		assertTrue(batallaNavalgo.tablero().cantidadTotalDeNaves() == 0);
	} 
	
	@Test
	public void naveChocaContraMinaDeContacto() {
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		Direccion direccion = new Direccion(Sentido.NORTE);
		Nave lancha = new Lancha(direccion);
		int[] posProa = {5,5};
		Municion minaContacto = new MinaSubmarinaPorContacto();
		int[] posMina = {5,6};
		batallaNavalgo.tablero().posicionarNaveEnTablero(lancha, posProa);
		batallaNavalgo.tablero().ponerMuncion(minaContacto, posMina);
//		batallaNavalgo.tablero().imprimirTablero();
		assertTrue(lancha.estado() == EstadoDeSalud.SANO);
		batallaNavalgo.tablero().actualizarTablero();
//		batallaNavalgo.tablero().imprimirTablero();
		assertTrue(lancha.estado() != EstadoDeSalud.SANO);

	} 
	
	
	
	/*
	 * Se sac� jugadorDispara de jugador, y por ende de batallaNavalgo.
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
	*/
	
	@Test
	public void testCantidadDeNavesAlCrear(){
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		assertTrue(batallaNavalgo.cantidadTotalNaves() == 0 );
	}
	
	@Test
	public void testCantidadDeNavesAlPosicionar(){
		BatallaNavalgo batallaNavalgo = new BatallaNavalgo();
		batallaNavalgo.posicionarNavesAleatoriamente();
		assertTrue(batallaNavalgo.cantidadTotalNaves() == 7 );
	}

}
