package pruebas;

import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;

import municiones.DisparoConvencional;
import municiones.MinaSubmarinaDobleConRetardo;
import municiones.MinaSubmarinaPorContacto;
import municiones.MinaSubmarinaPuntualConRetardo;
import municiones.MinaSubmarinaTripleConRetardo;
import municiones.Municion;
import naves.Destructor;
import naves.Direccion;
import naves.Lancha;
import naves.Nave;
import naves.SeccionDeNave;
import naves.Sentido;

import org.junit.Before;
import org.junit.Test;

import excepciones.ErrorCasilleroOcupadoConOtraMunicion;
import excepciones.ErrorIdCasilleroInvalido;

import tablero.Casillero;

public class CasilleroTest {
	private MinaSubmarinaTripleConRetardo minaSubmarinaTripleConRetardo;
	private MinaSubmarinaPuntualConRetardo minaSubmarinaPuntualConRetardo;
	private MinaSubmarinaDobleConRetardo minaSubmarinaDobleConRetardo;
	private MinaSubmarinaPorContacto minaSubmarinaPorContacto;
	private Destructor destructor;
	private Lancha lancha;

	@Before
	public void preparaObjetos() {
		minaSubmarinaDobleConRetardo = new MinaSubmarinaDobleConRetardo();
		minaSubmarinaPuntualConRetardo = new MinaSubmarinaPuntualConRetardo();
		minaSubmarinaTripleConRetardo = new MinaSubmarinaTripleConRetardo();
		minaSubmarinaPorContacto = new MinaSubmarinaPorContacto();
		Direccion direccion = new Direccion(Sentido.SUR);
		destructor = new Destructor(direccion);
		lancha = new Lancha(direccion);

	}

	@Test
	public void testCrear() {
		Casillero casillero = new Casillero(0, 0);
		assertTrue(casillero != null);
	}

	@Test
	public void testCrearConIdArray() {
		int[] id = { 0, 0 };
		Casillero casillero = new Casillero(id);
		assertTrue(casillero != null);
	}

	@Test
	public void testCasilleroObtenerIdDiagonalInferior() {
		Casillero casillero = new Casillero(0, 0);
		int[] id = { 0, 0 };
		assertArrayEquals(casillero.id(), id);
	}

	@Test
	public void testCasilleroObtenerIdDiagonalSuperior() {
		Casillero casillero = new Casillero(9, 9);
		int[] id = { 9, 9 };
		assertArrayEquals(casillero.id(), id);
	}

	@Test
	public void testCasilleroObtenerIdDiagonalInferiorCreadoConIdArray() {
		int[] id = { 0, 0 };
		Casillero casillero = new Casillero(id);
		assertArrayEquals(casillero.id(), id);
	}

	@Test
	public void testCasilleroObtenerIdDiagonalSuperiorCreadoConIdArray() {
		int[] id = { 9, 9 };
		Casillero casillero = new Casillero(id);
		assertArrayEquals(casillero.id(), id);
	}

	@Test(expected = ErrorIdCasilleroInvalido.class)
	public void testCasilleroObtenerIdInferiorACero() {
		new Casillero(-1, -2);
	}

	@Test(expected = ErrorIdCasilleroInvalido.class)
	public void testCasilleroObtenerIdSuperiorANueve() {
		new Casillero(100, 123);
	}

	@Test(expected = ErrorIdCasilleroInvalido.class)
	public void testCasilleroObtenerIdXInvalido() {
		new Casillero(100, 5);
	}

	@Test(expected = ErrorIdCasilleroInvalido.class)
	public void testCasilleroObtenerIdYInvalido() {
		new Casillero(5, 100);
	}

	@Test(expected = ErrorIdCasilleroInvalido.class)
	public void testCasilleroObtenerIdXInvalidoConIdArray() {
		int[] id = { 111, 5 };
		new Casillero(id);
	}

	@Test(expected = ErrorIdCasilleroInvalido.class)
	public void testCasilleroObtenerIdYInvalidoConIdArray() {
		int[] id = { 5, 111 };
		new Casillero(id);
	}

	@Test
	public void testAgregarYObtenerMuniciones() {
		Casillero casillero = new Casillero(0, 0);
		List<Municion> listadoMuniciones = new LinkedList<Municion>();
		listadoMuniciones.add(minaSubmarinaDobleConRetardo);
		listadoMuniciones.add(minaSubmarinaPuntualConRetardo);

		try {
			casillero.ponerMunicion(minaSubmarinaDobleConRetardo);
		} catch (ErrorCasilleroOcupadoConOtraMunicion e) {
			// Ver que hacemos aca
		}
		try {
			casillero.ponerMunicion(minaSubmarinaPuntualConRetardo);
		} catch (ErrorCasilleroOcupadoConOtraMunicion e) {
			// Ver que hacemos aca
		}
		// assertEquals(casillero.devolverMuniciones(), listadoMuniciones);
		for (int i = 0; i < listadoMuniciones.size(); i++) {
			assertTrue(casillero.devolverMuniciones().get(i) == listadoMuniciones
					.get(i));
		}

	}

	public void testAgregarYObtenerSeccionesDeNave() {
		Casillero casillero = new Casillero(0, 0);
		List<SeccionDeNave> listadoSeccionesDeNave = new LinkedList<SeccionDeNave>();
		listadoSeccionesDeNave.add(lancha.secciones().get(1));
		listadoSeccionesDeNave.add(destructor.secciones().get(2));

		casillero.ponerSeccionDeNave(lancha.secciones().get(1));
		casillero.ponerSeccionDeNave(destructor.secciones().get(1));

		assertTrue(casillero.devolverSeccionesDeNave().get(0) == listadoSeccionesDeNave
				.get(0));
		assertTrue(casillero.devolverSeccionesDeNave().get(1) == listadoSeccionesDeNave
				.get(1));

	}

}
