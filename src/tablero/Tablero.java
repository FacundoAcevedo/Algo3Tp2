package tablero;

import java.util.Hashtable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import municiones.DisparoConvencional;
import municiones.MinaSubmarinaPorContacto;
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
	
	private Hashtable<String, Casillero> coleccionCasilleros;
	private LinkedList<Casillero> casillerosConMunicion;
	private LinkedList<Nave> naves;

	public Tablero() {
		
		this.coleccionCasilleros = new Hashtable<String,Casillero>();
		this.casillerosConMunicion = new LinkedList<Casillero>();
		this.naves = new LinkedList<Nave>();
		
		//this.crearCasilleros(coleccionCasilleros);
		this.crearCasilleros();
	}
	
	private void crearCasilleros(){
		//Hashtable<String, Casillero> coleccionCasilleros) {

	for (int x = 0; x < 10; x++) {
		for (int y = 0; y < 10; y++) {
			int[] id = { x, y };
			String id_string = this.estandarizarId(id);
			this.coleccionCasilleros.put(id_string, new Casillero(id));
		}
	}

}
	public  String estandarizarId(int[] id){
		//Transforma el id de array al que se usa como llave en la tabla de casilleros
		String id_string = Integer.toString(id[0]) + Integer.toString(id[1]);
		return id_string;
	}

	public void posicionarNavesAleatoriamente() {
		
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
			int [] posProa = this.buscarCasilleroParaProa();
			this.posicionarNaveEnTablero(arrayDeNaves[i], posProa);
			
			
		}
	}
	
	public LinkedList<Nave> devolverNaves(){
		return this.naves;
	}



	public void posicionarNaveEnTablero(Nave nave, int[] posicionDeProa) {
		
		int [] posProa = posicionDeProa;
		int [] patronDeTrayectoria = this.patronDeSumaParaTrayectoriaDeNave(nave.direccion());
		int [] posSeccion = posProa;
		Casillero casillero;
		
		this.naves.add(nave);
		for (SeccionDeNave seccion : nave.secciones()){
			casillero = coleccionCasilleros.get(this.estandarizarId(posSeccion));
			casillero.ponerSeccionDeNave(seccion);
			
			//Calcula la poiscion de la siguiente seccion.
			posSeccion = this.sumarPatronDeSumaEId(posSeccion, patronDeTrayectoria);
		}

	}

	public Casillero obtenerCasillero(int[] id) throws ErrorIdCasilleroInvalido {
		Casillero.validarId(id);
		String idString = this.estandarizarId(id);
//		if (this.coleccionCasilleros.contains(idString )) {
		return this.coleccionCasilleros.get(idString );
//		}

		//Casillero casillero = new Casillero(id);
		//this.coleccionCasilleros.put(idString , casillero);

		//return casillero;

	}

	public boolean tieneNaves(){
		if (this.cantidadTotalDeNaves() != 0)
			return true;
		return false;
	}
	
	public int cantidadTotalDeNaves() {
		//Cantidad total de naves, sin distincion de estado
		return this.naves.size();
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
		int totalNaves = this.cantidadTotalDeNaves();
		int totalNavesActivas = this.cantidadDeNavesActivas();
		return (totalNaves - totalNavesActivas);
	}


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
//		0-Estallan las minas que tengan retardo 0/  - hecho
//		1-Se resta 1 al retardo de la municion  - hecho
//		2-Se mueven las naves

		
		//0
		for (Casillero casillero : coleccionCasilleros.values()){
			if( casillero.tieneMuniciones() ){
				List<Municion> municiones = casillero.devolverMuniciones();
				
				for( Municion municion : municiones){
					
					if (municion.retardo() == 0){
						
						if(casillero.tieneSeccionesDeNave()){
							//Quita las minas por contacto tambien
							municiones.remove(municion);							
						}
						else if (!(municion instanceof MinaSubmarinaPorContacto)){
							//Quita las minas que esten con retardo 0 pero que no sean por contacto
							municiones.remove(municion);
						}//if
						
						for (SeccionDeNave seccion : casillero.devolverSeccionesDeNave()){
							seccion.recibirImpacto(municion);
						}//for
						
					}//if
					else
						municion.disminuirRetardo();
					
				}//for
				
			}//if
		}//for
		
		this.moverTodasLasNaves();
		


	}
	
	private void moverTodasLasNaves(){
		
		this.invertirSentidoDeNavesEnElBorde();
		this.avanzarNaves();
		
		
	}
	public void avanzarNaves(){
		int[] idCasillero, patronDeSuma, idCasilleroProximo;
		List<SeccionDeNave> seccionesYaMovidas = new LinkedList<SeccionDeNave>();
		for ( Casillero casillero : coleccionCasilleros.values()){
			
			idCasillero = casillero.id();
			
			for (SeccionDeNave seccion : casillero.devolverSeccionesDeNave()){
				if(!seccionesYaMovidas.contains(seccion)){
				casillero.quitarSeccion(seccion);
				patronDeSuma = this.patronDeSumaParaTrayectoriaDeNave(seccion.sentido());
				idCasilleroProximo = this.sumarPatronDeSumaEId(idCasillero, patronDeSuma);
				Casillero casilleroProximo = this.obtenerCasillero(idCasilleroProximo);
				
				casilleroProximo.ponerSeccionDeNave(seccion);
				seccionesYaMovidas.add(seccion);
				}
			}
		}
		
	}
	
	private void invertirSentidoDeNavesEnElBorde() {
		// Las naves afectadas son aquellas que estan en el borde

		// Debido a nuestra manera de poner las naves aleatoreamente en una
		// posicion segura
		// Las naves nunca transitan tocando el borde de manera paralela
		
		//Pequeño grafico para entender el if
//		
//		AAAAAAAAAB
//		C		 B
//		C		 B
//		C		 B
//		C		 B
//		C		 B
//		C		 B
//		C		 B
//		C		 B
//		C		 B
//		CDDDDDDDDD

		

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				int[] idDeBorde = { x, y };
				
				if( ((x==0) && (y>=1 || y<=9)) //C
				 ||((x==9) && (y>=0 || y<=9)) //B
				 ||((x>=0 || y<=8) || y == 0) //A
				  ||((x>=1 || y<=9) || y == 9) ){//D
					
				Casillero casilleroDelBorde = this.obtenerCasillero(idDeBorde);
				List<SeccionDeNave> seccionesDeNaveEnBorde = casilleroDelBorde
						.devolverSeccionesDeNave();

				for (SeccionDeNave seccion : seccionesDeNaveEnBorde) {
					seccion.invertirSentido();
					}
				}

			}
		}

	}


	private int[] sumarPatronDeSumaEId(int[] id, int[] patronDeSuma) {
		int[] total = new int[2];
		total[0] = id[0] + patronDeSuma[0];
		total[1] = id[1] + patronDeSuma[1];

		return total;
	}

	private int[] patronDeSumaParaTrayectoriaDeNave(Sentido sentido) {
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
		//Devuelve un id en un area segura y devuelve el id
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
