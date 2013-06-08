package tablero;

import java.util.Hashtable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import naves.Buque;
import naves.Destructor;
import naves.Direccion;
import naves.EstadoDeSalud;
import naves.Lancha;
import naves.Nave;
import naves.PortaAviones;
import naves.RompeHielos;
import naves.SeccionDeNave;
import naves.Sentido;
import tablero.Casillero;
import excepciones.ErrorIdCasilleroInvalido;

public class Tablero implements Iterable {
	private Hashtable<String, Casillero> coleccionCasilleros;
	private LinkedList<Casillero> casillerosConMunicion;
	private LinkedList<Nave> naves;

	public Tablero() {
		this.coleccionCasilleros = new Hashtable<>();
		this.crearCasilleros(coleccionCasilleros);
		this.casillerosConMunicion = new LinkedList<Casillero>();
		this.naves = new LinkedList<Nave>();

	}

	public void posicionarNavesAleatoriamente() {
		
		// Creo un objeto direccion por cada nave
		Direccion[] arrayDeDirecciones = new Direccion[7];
		Nave[] arrayDeNaves = new Nave[7];
		
		for (int i = 0; i < 7; i++) {
			arrayDeDirecciones[i] = new Direccion(null);
			arrayDeDirecciones[i].random();
		}
		
		arrayDeNaves[0] = new Lancha(arrayDeDirecciones[0]);
		arrayDeNaves[1] = new Lancha(arrayDeDirecciones[1]);
		arrayDeNaves[2] = new Destructor(arrayDeDirecciones[2]);
		arrayDeNaves[3] = new Destructor(arrayDeDirecciones[3]);
		arrayDeNaves[4] = new Buque(arrayDeDirecciones[4]);
		arrayDeNaves[5] = new PortaAviones(arrayDeDirecciones[5]);
		arrayDeNaves[6] = new RompeHielos(arrayDeDirecciones[6]);
		
		for (int i =0; i<7; i++){
			this.posicionarNaveEnTablero(arrayDeNaves[i]);
			this.naves.add(arrayDeNaves[i]);
			
		}
	}
	
	public LinkedList<Nave> devolverNaves(){
		return this.naves;
	}

	private void crearCasilleros(
			Hashtable<String, Casillero> coleccionCasilleros) {

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int[] id = { x, y };
				coleccionCasilleros.put(Arrays.toString(id), new Casillero(id));
			}
		}

	}

	public void posicionarNaveEnTablero(Nave nave) {
		
		int [] posProa = this.buscarCasilleroParaProa();
		int [] patronDePocicion = this.patronDeSumaParaUbicarNave(nave.direccion());
		int [] posSeccion = posProa;
		Casillero casillero;
		
		for (SeccionDeNave seccion : nave.secciones()){
			casillero = coleccionCasilleros.get(Arrays.toString(posProa));
			casillero.ponerSeccionDeNave(seccion);
			
			//Calcula la poiscion de la siguiente seccion.
			posSeccion = this.sumarPatronDeSumaEId(posSeccion, patronDePocicion);
		}


	}

	public Casillero obtenerCasillero(int[] id) throws ErrorIdCasilleroInvalido {
		if (this.coleccionCasilleros.contains(id)) {
			return this.coleccionCasilleros.get(Arrays.toString(id));
		}

		Casillero casillero = new Casillero(id);
		this.coleccionCasilleros.put(Arrays.toString(id), casillero);

		return casillero;

	}

	public boolean estaVacio() {
		return coleccionCasilleros.isEmpty();
	}

	private boolean naveEntraEn(Nave unaNave, Casillero unCasillero) {
		int[] id = new int[2];

		for (int tmp = 0; tmp < unaNave.largo(); tmp++) {
			id = this.proximoCasillero(unCasillero, unaNave.direccion());
			unCasillero = coleccionCasilleros.get(Arrays.toString(id));
			try {
				Casillero.validarId(id);
			} catch (ErrorIdCasilleroInvalido e) {
				return false;
			}
		}

		return true;
	}

	public int navesDestruidas() {

		int navesDestruidas = 0;
		Nave nave;
		Iterator<Nave> iteradorDeNaves = naves.iterator();

		if (iteradorDeNaves.hasNext()) {

			nave = iteradorDeNaves.next();

			if (nave.porcentajeVida() == 0) {
				navesDestruidas = navesDestruidas++;
			}
		}

		return navesDestruidas;

	}

	/* Cantidad de naves en el tablero, sin importar estado. */
	public int cantidadTotalNaves() {
		return this.naves.size();
	}
	
	public boolean tieneNaves(){
		if (this.cantidadTotalNaves() != 0)
			return true;
		return false;
	}
	
	public int cantidadDeNavesActivas(){
		//Devuelve la cantidad de naves sanas y dañadas
		int totalNavesActivas =0;
		for (Nave nave : this.naves){
			if( nave.estado()== EstadoDeSalud.SANO || nave.estado()== EstadoDeSalud.DANADO)
				totalNavesActivas +=1;
		}
		return totalNavesActivas;
	}
	public int cantidadDeNavesDestruidas(){
		int totalNaves = this.cantidadTotalNaves();
		int totalNavesActivas = this.cantidadDeNavesActivas();
		return (totalNaves - totalNavesActivas);
	}

	/*
	 * Sirve para obtener el casillero proximo en la direccion
	 */
	public int[] proximoCasillero(Casillero c, Sentido s) {
		int[] nuevoId = new int[2]; // nuevoId no sobrevive fuera del if.
									// CORREGIR!!
		// "Corregido": Creo que es un problema de como estaba declarado
		// Hay que revisar que sea solo eso.

		if (s == Sentido.NOROESTE) {
			nuevoId[0] = c.id()[0] - 1;
			nuevoId[1] = c.id()[1] - 1;
		} else if (s == Sentido.NORTE) {
			nuevoId[0] = c.id()[0];
			nuevoId[1] = c.id()[1] - 1;
		} else if (s == Sentido.NORESTE) {
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1] - 1;
		} else if (s == Sentido.ESTE) {
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1];
		} else if (s == Sentido.SUDESTE) {
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1] + 1;
		} else if (s == Sentido.SUR) {
			nuevoId[0] = c.id()[0];
			nuevoId[1] = c.id()[1] + 1;
		} else if (s == Sentido.SUDOESTE) {
			nuevoId[0] = c.id()[0] - 1;
			nuevoId[1] = c.id()[1] + 1;
		} else if (s == Sentido.OESTE) {
			nuevoId[0] = c.id()[0] - 1;
			nuevoId[1] = c.id()[1];
		}

		return nuevoId;
	}

	/*
	 * Mismo iterador que usa la clase Nave con sus secciones pero para las
	 * naves. Hay que chequear la implementacion, tanto del iterator, como de
	 * navesDestruidas
	 */
	@Override
	public Iterator<Nave> iterator() {
		Iterator<Nave> iterador = new Iterator<Nave>() {

			private Iterator<Nave> iteradorDeNaves = naves.iterator();

			@Override
			public boolean hasNext() {
				return iteradorDeNaves.hasNext();
			}

			@Override
			public Nave next() {
				return iteradorDeNaves.next();
			}

			@Override
			public void remove() {
				// no hace nada pero me obliga a definirlo.
			}
		};
		return iterador;
	}

	public void agregarCasilleroConMunicion(Casillero casillero) {
		this.casillerosConMunicion.add(casillero);
	}

	private Hashtable<Casillero, Integer> casillerosConMunicionesSinRetardo() {
		Hashtable<Casillero, Integer> casilleros;
		casilleros = new Hashtable<>();

		int cantidadDeCasillerosConMunicion = this.casillerosConMunicion.size(); // devolverMunciones().size();

		/* Recorro la lista de casilleros con municiones */
		for (int i = 0; i < cantidadDeCasillerosConMunicion; i++) {
			Casillero casillero = this.coleccionCasilleros.get(casilleros
					.get(i));

			/* Miro municiones que hay en cada casillero */
			int cantidadDeMunicionesEnCasillero = casillero
					.devolverMuniciones().size();
			for (int x = 0; x < cantidadDeMunicionesEnCasillero; x++) {

				/* Me fijo si tienen retardo = 0 y agrego al Hashtable */
				if (casillero.devolverMuniciones().get(x).retardo() == 0) {
					casilleros.put(casillero, x); // Aunque si tiene dos
													// municiones, una ret != 0
													// y otra ret= 0, la agrega
													// igual. hay qe verificar
													// despu�s que solo se
													// accionen las ret=0
				}
			}

		}

		return casilleros;
	}

	public void actualizarTablero() {
		Hashtable<Casillero, Integer> casilleros;
		casilleros = this.casillerosConMunicionesSinRetardo();
		/*
		 * Ac� deber�a hacerce para todos los casilleros del hash,
		 * casillero.efectuarImpacto(indiceMunicion). con indiceMunicion= valor
		 * del casillero en el hash.
		 */

		/*
		 * Este m�todo deber�a hacer retardo -= 1 de las municiones que quedan
		 * en el tablero. Falta implementar
		 */
		this.restarRetardoDeMuniciones();
	}

	private void restarRetardoDeMuniciones() {
		// TODO Auto-generated method stub

	}

	private int[] sumarPatronDeSumaEId(int[] id, int[] patronDeSuma) {
		int[] total = new int[2];
		total[0] = id[0] + patronDeSuma[0];
		total[1] = id[1] + patronDeSuma[1];

		return total;
	}

	private int[] patronDeSumaParaUbicarNave(Sentido sentido) {
		// Devuelve un patron para sumarle al idCelda y ubicar las secciones de
		// nave
		int[] patronSur = { 0, -1 };
		int[] patronNorte = { 0, 1 };
		int[] patronOeste = { -1, 0 };
		int[] patronEste = { 1, 0 };
		int[] patronSudEste = { 1, -1 };
		int[] patronNorEste = { 1, 1 };
		int[] patronSudOeste = { -1, -1 };
		int[] patronNorOeste = { -1, -1 };

		if (sentido == Sentido.SUR)
			return patronSur;
		else if (sentido == Sentido.NORTE)
			return patronNorte;
		else if (sentido == Sentido.ESTE)
			return patronEste;
		else if (sentido == Sentido.OESTE)
			return patronOeste;
		else if (sentido == Sentido.NORESTE)
			return patronNorEste;
		else if (sentido == Sentido.NOROESTE)
			return patronNorOeste;
		else if (sentido == Sentido.SUDESTE)
			return patronSudEste;
		else
			// (sentido== Sentido.SUDOESTE)
			return patronSudOeste;
	}

	private int[] buscarCasilleroParaProa() {
		// Pone el barco en un area segura y devuelve el id
		boolean idXCorrecto = false;
		boolean idYCorrecto = false;
		int[] id = new int[2];
		int MAX = 6, MIN = 3;

		while (idXCorrecto == false) {
			id[0] = (int) (Math.random() * 10);

			if (id[0] < MAX && id[0] > MIN)
				idXCorrecto = true;
		}

		while (idYCorrecto == false) {
			id[1] = (int) (Math.random() * 10);
			if (id[1] < MAX && id[1] > MIN)
				idYCorrecto = true;
		}
		return id;
	}
}
