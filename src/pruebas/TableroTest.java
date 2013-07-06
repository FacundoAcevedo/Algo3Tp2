package pruebas;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

import municiones.DisparoConvencional;
import municiones.MinaSubmarinaDobleConRetardo;
import municiones.MinaSubmarinaPorContacto;
import municiones.MinaSubmarinaPuntualConRetardo;
import municiones.MinaSubmarinaTripleConRetardo;
import naves.Buque;
import naves.Direccion;
import naves.EstadoDeSalud;
import naves.Lancha;
import naves.Nave;
import naves.SeccionDeNave;
import naves.Sentido;

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
	public void testSeCreaSinNaves() {
		Tablero tablero = new Tablero();
		assertFalse(tablero.tieneNaves());
	}

	@Test
	public void testPonerNavesAleatoriamente() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		tablero.construirYPosicionarLasNavesAleatoriamente();
		assertTrue(tablero.cantidadTotalDeNaves() == 7);
		

	}
	@Test
	public void insertarUnaNaveYVerCantidad() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {5,5};
		Nave nave = new Lancha(new Direccion(Sentido.SUR));
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		
		assertTrue(tablero.cantidadTotalDeNaves() == 1);
		LinkedList<Nave> naves = tablero.devolverNaves();

		for (Nave naveRecibida : naves) {
			assertTrue(nave == naveRecibida);
		}
	}
	
	@Test
	public void insertarUnaNaveYComprbarUnicidadDeObjetosNave() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {4,4};
		Nave nave = new Lancha(new Direccion(Sentido.SUR));
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		

		LinkedList<Nave> naves = tablero.devolverNaves();

		for (Nave naveRecibida : naves) {
			assertTrue(nave == naveRecibida);
		}
	}
	
	@Test
	public void insertarUnaNaveYMoverlaUnaVez() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {4,4};
		int[] posicionDeProaLuegoDeAvanzar = {4,3};
		Nave nave = new Lancha(new Direccion(Sentido.SUR));
		Casillero casilleroLuegoDeAvanzar;
		SeccionDeNave seccionDeProaLuegoDeAvanzar; 
		
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.avanzarNaves();
		
		casilleroLuegoDeAvanzar= tablero.obtenerCasillero(posicionDeProaLuegoDeAvanzar);
		seccionDeProaLuegoDeAvanzar = casilleroLuegoDeAvanzar.devolverSeccionesDeNave().get(0);	
		
		assertTrue(nave.secciones().get(0) == seccionDeProaLuegoDeAvanzar);
		
		//esto seria la parte de atras de la lancha
		casilleroLuegoDeAvanzar= tablero.obtenerCasillero(posicionDeProa);
		seccionDeProaLuegoDeAvanzar = casilleroLuegoDeAvanzar.devolverSeccionesDeNave().get(0);	
		assertTrue(nave.secciones().get(1) == seccionDeProaLuegoDeAvanzar);
		
	}

	@Test
	public void moverNaveHastaQueRegreseALaPosicionInicial() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {5,5};
		Nave nave = new Lancha(new Direccion(Sentido.SUR));
		Casillero casilleroLuegoDeAvanzar;
		SeccionDeNave seccionDeProaLuegoDeAvanzar; 
		
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		
		for (int i= 0; i<10; i++){
			tablero.moverTodasLasNaves();
		}
		
		casilleroLuegoDeAvanzar= tablero.obtenerCasillero(posicionDeProa);
		seccionDeProaLuegoDeAvanzar = casilleroLuegoDeAvanzar.devolverSeccionesDeNave().get(0);	
		
		assertTrue(nave.secciones().get(0) == seccionDeProaLuegoDeAvanzar);
		
		assertTrue(nave.direccion() == Sentido.NORTE);
		
	}
	
	@Test
	public void chocarUnaNaveContraUnBordeVerticalDelTablero() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {1,0};
		Nave nave = new Lancha(new Direccion(Sentido.SUR));
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.invertirSentidoDeNavesEnElBorde();

		assertTrue(nave.direccion() == Sentido.NORTE);

		
	}
	
	@Test
	public void chocarUnaNaveContraUnEsquinaDelTablero() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {9,9};
		Nave nave = new Lancha(new Direccion(Sentido.NORESTE));
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.invertirSentidoDeNavesEnElBorde();
		
		assertTrue(nave.direccion() == Sentido.SUDOESTE);

		
	}
	
	@Test
	public void movimientoNavePegadaAlBorde() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,5};
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.moverTodasLasNaves();
		tablero.moverTodasLasNaves();
		tablero.moverTodasLasNaves();
		
		int[] nuevaPosicionProa = {0,8};
		int[] nuevaPosicionPopa = {0,7};
		boolean exito = (!tablero.obtenerCasillero(nuevaPosicionProa).estaVacio()) &&
						(!tablero.obtenerCasillero(nuevaPosicionPopa).estaVacio());
		
		assertTrue(exito);

		
	}
	
//	@Test
//	public void naveEncerrada() {
//		// Se pone una nave con sentido diagonal en una esquina del tablero
//		/* Gr�fico:
//		 *  .......... 
//			.......... 
//			.......... 
//			.......... 
//			.......... 
//			.......... 
//			.......... 
//			.......... 
//			0......... 
//			.0........ 
// 
//		 */
//		Tablero tablero = new Tablero();
//		int[] posicionDeProa = {1,0};
//		Nave nave = new Lancha(new Direccion(Sentido.NOROESTE));
//		
//		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
//		tablero.invertirSentidoDeNavesEnElBorde();
//		
//		boolean exito;
//		tablero.imprimirTablero();
//		try{
//			tablero.moverTodasLasNaves();
//			exito = false;
//		}catch(Exception e){
//			exito = true;
//		}
//		tablero.imprimirTablero();
//		//tablero.moverTodasLasNaves();
//		//tablero.imprimirTablero();
//		
//		assertFalse(exito);
//
//	}
	@Test
	public void movimientoNaveSentidoNoreste() {
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {7,9};
		Nave nave = new Lancha(new Direccion(Sentido.NORESTE));
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.invertirSentidoDeNavesEnElBorde();
		
		assertTrue(nave.direccion() == Sentido.SUDOESTE);

		
	}
	
	@Test
	public void movimientoNaveSentidoNoroeste() {
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {7,9};
		Nave nave = new Lancha(new Direccion(Sentido.NOROESTE));
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.invertirSentidoDeNavesEnElBorde();
		
		assertTrue(nave.direccion() == Sentido.SUDESTE);

		
	}
	
	@Test
	public void chocarUnaNaveContraUnBordeHorizontalDelTablero() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,5};
		Nave nave = new Lancha(new Direccion(Sentido.OESTE));

		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.invertirSentidoDeNavesEnElBorde();
		assertTrue(nave.direccion() == Sentido.ESTE);

		
	}
	
	@Test
	public void moverUnaNaveYChocarContraUnBorde() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {5,8};
		int[] posicionDeProaLuegoDeAvanzar = {5,9};
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		Casillero casilleroLuegoDeAvanzar;
		SeccionDeNave seccionDeProaLuegoDeAvanzar; 
		
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.avanzarNaves();
		tablero.invertirSentidoDeNavesEnElBorde();

		
		casilleroLuegoDeAvanzar= tablero.obtenerCasillero(posicionDeProaLuegoDeAvanzar);
		seccionDeProaLuegoDeAvanzar = casilleroLuegoDeAvanzar.devolverSeccionesDeNave().get(0);	
		
		assertTrue(nave.secciones().get(0) == seccionDeProaLuegoDeAvanzar);
		
		//esto seria la parte de atras de la lancha
		casilleroLuegoDeAvanzar= tablero.obtenerCasillero(posicionDeProa);
		seccionDeProaLuegoDeAvanzar = casilleroLuegoDeAvanzar.devolverSeccionesDeNave().get(0);	
		assertTrue(nave.secciones().get(1) == seccionDeProaLuegoDeAvanzar);
		assertTrue(nave.direccion() == Sentido.SUR);
		
	}
	
	@Test
	public void agregarDiezNavesYComprobarUnicidadDeObjetosNave() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {5,5};

		Direccion[] arrayDeDirecciones = new Direccion[9];
		Nave[] arrayDeNaves = new Nave[9];
		
		for (int i = 0; i < 9; i++) {
			arrayDeDirecciones[i] = new Direccion(null);
			arrayDeDirecciones[i].random();
			
			arrayDeNaves[i] = new Lancha(arrayDeDirecciones[i]);
			tablero.posicionarNaveEnTablero(arrayDeNaves[i], posicionDeProa);
		}
		
		
	
		LinkedList<Nave> naves = tablero.devolverNaves();
		int i = 0;
		for (Nave naveRecibida : naves) {
			assertTrue(arrayDeNaves[i] == naveRecibida);
			i++;
		}
	}
	
	@Test
	public void testPedirCasilleroValido() {
		Tablero tablero = new Tablero();
		int[] id = { 0, 0 };
		Casillero casillero = tablero.obtenerCasillero(id);
		assertTrue(casillero instanceof Casillero);
		
	}
	
	@Test
	public void testPedirDosVecesElMismoCasillero() {
		//verifica que el casillero sea el mismo
		Tablero tablero = new Tablero();
		int[] id = { 5, 5 };
		Casillero casillero = tablero.obtenerCasillero(id);

		Casillero casilleroRepedido = tablero.obtenerCasillero(id);
		
		assertTrue(casilleroRepedido == casillero);
		
		
	}
	

	@Test(expected = ErrorIdCasilleroInvalido.class)
	public void testPedirCasilleroInvalido() {
		Tablero tablero = new Tablero();
		int[] id = { -10, 10 };
		tablero.obtenerCasillero(id);

	}

	@Test
	public void patronDeSumaParaUbicarNaveSur() throws NoSuchMethodException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		// pruebas por reflexion
		Tablero tablero = new Tablero();
		Sentido unSentido = Sentido.SUR;
		int[] patronSur = { 0, -1 };

		Method patronDeSuma = Tablero.class.getDeclaredMethod(
				"patronDeSumaParaTrayectoriaDeNave", Sentido.class);
		patronDeSuma.setAccessible(true);

		int[] patron = (int[]) patronDeSuma.invoke(tablero, unSentido);

		assertTrue(patron[0] == patronSur[0] && patron[1] == patronSur[1]);
	}
	@Test public void estandarizarId(){
		Tablero tablero = new Tablero();
		int[] id = {0,0};
		String id_string = tablero.estandarizarId(id);
		assertTrue(id_string.equals("00"));
	}
	@Test
	public void patronDeSumaParaUbicarNaveNoreste()
			throws NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		// pruebas por reflexion
		Tablero tablero = new Tablero();
		Sentido unSentido = Sentido.NORESTE;
		int[] patronNorEste = { 1, 1 };

		Method patronDeSuma = Tablero.class.getDeclaredMethod(
				"patronDeSumaParaTrayectoriaDeNave", Sentido.class);
		patronDeSuma.setAccessible(true);

		int[] patron = (int[]) patronDeSuma.invoke(tablero, unSentido);

		assertTrue(patron[0] == patronNorEste[0]
				&& patron[1] == patronNorEste[1]);
	}

	
	@Test
	public void sumarPatronDeSumaEId() throws NoSuchMethodException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		// pruebas por reflexion
	
		Tablero tablero = new Tablero();
		int[] id = {1,0};
		int[] patronDeSuma = {2,1};

		Method sumarPatronDeSumaEId = Tablero.class
				.getDeclaredMethod("sumarPatronDeSumaEId", int[].class, int[].class);
		sumarPatronDeSumaEId.setAccessible(true);

			int[] idResultante = (int[]) sumarPatronDeSumaEId.invoke(tablero, id, patronDeSuma);

			assertTrue(idResultante[0] == 3 && idResultante[1] == 1 );
		}
	
	@Test
	public void dispararoConvencionalACasilleroVacio() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,1};
		int[] posicionVacia = {9,9};
		
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		DisparoConvencional disparoConvencional = new DisparoConvencional();
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.ponerMuncion(disparoConvencional, posicionVacia);
		assertTrue(nave.estado() == EstadoDeSalud.SANO);
	}
	
	@Test
	public void dispararoConvencionalANave() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,1};
					
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		DisparoConvencional disparoConvencional = new DisparoConvencional();
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.ponerMuncion(disparoConvencional, posicionDeProa);
		
		assertTrue(nave.estado() == EstadoDeSalud.DANADO);
	}
	
	@Test
	public void naveNoPisaMinaDeContacto() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,1};
		int[] posicionDeMina = {0,2};
		
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		MinaSubmarinaPorContacto mina = new MinaSubmarinaPorContacto();
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.ponerMuncion(mina, posicionDeMina);
		
		//todavia no se movieron las naves.
		assertTrue(nave.estado() == EstadoDeSalud.SANO);
	}
	
	@Test
	public void navePisaMinaDeContacto() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,1};
		int[] posicionDeMina = {0,2};
		
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		MinaSubmarinaPorContacto mina = new MinaSubmarinaPorContacto();
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.ponerMuncion(mina, posicionDeMina);
		
		tablero.actualizarTablero();
		
		assertTrue(nave.estado() == EstadoDeSalud.DANADO);
	}
	
	@Test
	public void minaConRetardoNoExplotaAntesDeTiempo() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,1};
		int[] posicionDeMina = {0,3};
		
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		MinaSubmarinaPuntualConRetardo mina = new MinaSubmarinaPuntualConRetardo();
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.ponerMuncion(mina, posicionDeMina);
		
		tablero.actualizarTablero();
		tablero.actualizarTablero(); //todavia no explota
		
		assertTrue(nave.estado() == EstadoDeSalud.SANO);
	}
	
	@Test
	public void naveDanadaPorMinaConRetardo() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,1};
		int[] posicionDeMina = {0,3};
		
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		MinaSubmarinaPuntualConRetardo mina = new MinaSubmarinaPuntualConRetardo();
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.ponerMuncion(mina, posicionDeMina);
		
		tablero.actualizarTablero();
		tablero.actualizarTablero();
		tablero.actualizarTablero(); //explota
		
		assertTrue(nave.estado() == EstadoDeSalud.DANADO);
	}
	
	@Test
	public void naveDestruidaPorMinaSubmarinaDobleConRetardo() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProa = {0,1};
		int[] posicionDeMina = {0,4};
		
		Nave nave = new Lancha(new Direccion(Sentido.NORTE));
		MinaSubmarinaDobleConRetardo mina = new MinaSubmarinaDobleConRetardo();
		
		tablero.posicionarNaveEnTablero(nave, posicionDeProa);
		tablero.ponerMuncion(mina, posicionDeMina);
		
		tablero.actualizarTablero();
		tablero.actualizarTablero();
		tablero.actualizarTablero(); //explota
		
		assertTrue(nave.estado() == EstadoDeSalud.DESTRUIDO);
	}
	
	@Test
	public void minaSubmarinaTripeConRetardoDanaVariasNaves() {
		// Se testea la existencia de las naves, y su tipo
		// no la aleatoreidad de sus posiciones.
		Tablero tablero = new Tablero();
		int[] posicionDeProaLancha = {0,1};
		int[] posicionDeProaBuque = {3,4};
		int[] posicionDeMina = {1,4};
		
		Nave lancha = new Lancha(new Direccion(Sentido.NORTE));
		Nave buque = new Buque(new Direccion(Sentido.NORTE));
		MinaSubmarinaTripleConRetardo mina = new MinaSubmarinaTripleConRetardo();
		
		tablero.posicionarNaveEnTablero(lancha, posicionDeProaLancha);
		tablero.posicionarNaveEnTablero(buque, posicionDeProaBuque);
		tablero.ponerMuncion(mina, posicionDeMina);
		
		tablero.actualizarTablero();
		tablero.actualizarTablero();
		tablero.actualizarTablero(); //explota
		
		boolean todasLasNavesDestruidas = (lancha.estado() == EstadoDeSalud.DESTRUIDO) && (buque.estado() == EstadoDeSalud.DESTRUIDO);
		
		assertTrue(todasLasNavesDestruidas);
	}
}

