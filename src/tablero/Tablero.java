package tablero;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import municiones.Municion;
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
	private Casillero[][] casilleros;
	private LinkedList<Casillero> casillerosConMunicion;
	private LinkedList<Nave> naves;

	public Tablero() {
		this.crearCasilleros();
		this.casillerosConMunicion = new LinkedList<Casillero>();
		this.naves = new LinkedList<Nave>();

	}
	
	public void crearCasilleros(){
		this.casilleros = new Casillero[10][10];
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				this.casilleros[x][y] = new Casillero(x,y);
			}
		}
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

	public void posicionarNaveEnTablero(Nave nave) {
		
		Casillero unCasillero;
		int[] id = new int[2];

		do {
			id[0] = (int) (Math.random() * 10);
			id[1] = (int) (Math.random() * 10);

			unCasillero = this.obtenerCasillero(id);
		} while (!this.ubicarProaDeNave(nave, unCasillero));

		Iterator<SeccionDeNave> iteradorDeSecciones = nave.secciones().iterator();
		while (iteradorDeSecciones.hasNext()) {
			id = this.proximoCasillero(unCasillero, nave.direccion());
			unCasillero = this.obtenerCasillero(id);
			SeccionDeNave valor = iteradorDeSecciones.next();
			unCasillero.ponerSeccionDeNave(valor);
		}

	}

	public Casillero obtenerCasillero(int[] id) throws ErrorIdCasilleroInvalido{
		Casillero.validarId(id);
		return this.casilleros[id[0]][id[1]];
	}

	public boolean estaVacio() {
		return naves.isEmpty();
	}

	public boolean ubicarProaDeNave(Nave unaNave, Casillero unCasillero) {
		
		if (!this.naveEntraEn(unaNave, unCasillero)) {
			return false;
		}

		Casillero casilleroActual = unCasillero;

		Iterator<SeccionDeNave> iteradorDeSeccionesDeNave = unaNave.iterator();
		SeccionDeNave unaSeccionDeNave;
		while (iteradorDeSeccionesDeNave.hasNext()) {
			unaSeccionDeNave = iteradorDeSeccionesDeNave.next();
			casilleroActual.ponerSeccionDeNave(unaSeccionDeNave);
		}
		return true;
	}

	private boolean naveEntraEn(Nave unaNave, Casillero unCasillero) {
		int[] id = new int[2];

		for (int tmp = 0; tmp < unaNave.largo(); tmp++) {
			id = this.proximoCasillero(unCasillero, unaNave.direccion());
			try {
				unCasillero = this.obtenerCasillero(id);
			} catch (ErrorIdCasilleroInvalido e) {
				return false;
			}
		}

		return true;
	}

	public int cantidadDeNavesDestruidas() {

		int navesDestruidas = 0;
		Nave nave;
		Iterator<Nave> iteradorDeNaves = naves.iterator();

		if (iteradorDeNaves.hasNext()) {

			nave = iteradorDeNaves.next();

			if (nave.porcentajeVida() == 0) {
				navesDestruidas++;
			}
		}

		return navesDestruidas;

	}

	/* Cantidad de naves en el tablero, sin importar estado. */
	public int cantidadTotalNaves() {
		return this.naves.size();
	}
	
	/* Devuelve la cantidad de naves sanas y dañadas. */
	public int cantidadDeNavesActivas(){
		int totalNavesActivas = 0;
		for (Nave nave : this.naves){
			if( nave.estado() != EstadoDeSalud.DESTRUIDO)
				totalNavesActivas++;
		}
		return totalNavesActivas;
	}
	

	/* Sirve para obtener el casillero proximo en la direccion */
	public int[] proximoCasillero(Casillero c, Sentido s) {
		int[] nuevoId = new int[2]; 
		
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



	// REVER ESTO
	/*
	private Hashtable<Casillero, Integer> casillerosConMunicionesSinRetardo() {
		Hashtable<Casillero, Integer> unosCasilleros;
		unosCasilleros = new Hashtable<>();

		int cantidadDeCasillerosConMunicion = this.casillerosConMunicion.size(); // devolverMunciones().size();

		// Recorro la lista de casilleros con municiones
		for (int i = 0; i < cantidadDeCasillerosConMunicion; i++) {
			Casillero casillero = this.coleccionCasilleros.get(unosCasilleros.get(i));

			// Miro municiones que hay en cada casillero
			int cantidadDeMunicionesEnCasillero = casillero
					.devolverMuniciones().size();
			for (int x = 0; x < cantidadDeMunicionesEnCasillero; x++) {

				// Me fijo si tienen retardo = 0 y agrego al Hashtable 
				if (casillero.devolverMuniciones().get(x).retardo() == 0) {
					unosCasilleros.put(casillero, x); // Aunque si tiene dos
													// municiones, una ret != 0
													// y otra ret= 0, la agrega
													// igual. hay qe verificar
													// despuï¿½s que solo se
													// accionen las ret=0
				}
			}

		}

		return unosCasilleros;
	}
	*/

	public void actualizarTablero() {
		//Hashtable<Casillero, Integer> casilleros;
		//casilleros = this.casillerosConMunicionesSinRetardo();
		/*
		 * Acï¿½ deberï¿½a hacerce para todos los casilleros del hash,
		 * casillero.efectuarImpacto(indiceMunicion). con indiceMunicion= valor
		 * del casillero en el hash.
		 */

		/*
		 * Este mï¿½todo deberï¿½a hacer retardo -= 1 de las municiones que quedan
		 * en el tablero. Falta implementar
		 */
		this.restarRetardoDeMuniciones();
	}

	private void restarRetardoDeMuniciones() {
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				int[] id = {x,y};
				for( Municion municion : this.obtenerCasillero(id).devolverMuniciones() ){
					municion.disminuirRetardo();
				}
			}
		}
	}

}
