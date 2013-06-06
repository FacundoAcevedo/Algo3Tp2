package tablero;

import java.util.Hashtable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import naves.Buque;
import naves.Destructor;
import naves.Direccion;
import naves.Lancha;
import naves.Nave;
import naves.PortaAviones;
import naves.RompeHielos;
import naves.SeccionDeNave;
import naves.Sentido;
import tablero.Casillero;
import excepciones.ErrorIdCasilleroInvalido;


public class Tablero implements Iterable{
	private Hashtable<String, Casillero> coleccionCasilleros;
	private LinkedList<int[]> casillerosConMunicion;
	private LinkedList<Nave> naves;
	
	public Tablero (){
		this.coleccionCasilleros = new Hashtable<>();
		this.crearCasilleros(coleccionCasilleros);
		this.casillerosConMunicion = new LinkedList<int[]>();
		this.naves = new LinkedList<Nave>();
		//Creo un objeto direccion por cada nave
		Direccion[] arrayDeDirecciones = null;
		for (int i =0; i<6; i++){
			arrayDeDirecciones[i] = new Direccion(null);
			arrayDeDirecciones[i].random();
		}
		
		Nave nave;
		Direccion direccion = new Direccion(null);
		
		direccion.random();
		nave = new Lancha(arrayDeDirecciones[0]);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new Lancha(arrayDeDirecciones[1]);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new Destructor(arrayDeDirecciones[2]);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new Destructor(arrayDeDirecciones[3]);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new Buque(arrayDeDirecciones[4]);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new PortaAviones(arrayDeDirecciones[5]);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new RompeHielos(arrayDeDirecciones[6]);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

	}
	
	private void crearCasilleros(Hashtable<String,Casillero> coleccionCasilleros) {

		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				int[] id = {x,y};
				coleccionCasilleros.put(Arrays.toString(id), new Casillero(id));
			}
		}
		
	}

	/*Hay que ver como situa el tablero una nave
	  en si mismo.
	 */
	@SuppressWarnings("null")
	public void  posicionarNaveEnTablero(Nave unaNave){
		
		Casillero unCasillero;
		int[] id = new int[2];
		
		do{
			id[0] = (int) (Math.random() * 10);
			id[1] = (int) (Math.random() * 10);
			
			//unCasillero = coleccionCasilleros.get(id); // REVISAR: no se crean los casilleros, es una coleccion vac�a.
			unCasillero = this.obtenerCasillero(id);
		}while( ! this.ubicarProaDeNave(unaNave, unCasillero) );
		
		Iterator<SeccionDeNave> iteradorDeSecciones = unaNave.secciones().iterator();
		//iteradorDeSecciones.next(); no entiendo que hace esto, estaba bien?
		while (iteradorDeSecciones.hasNext()){
			id = this.proximoCasillero(unCasillero, unaNave.direccion());
			unCasillero = coleccionCasilleros.get(Arrays.toString(id));
			if( unCasillero == null){
				coleccionCasilleros.put(Arrays.toString(id), new Casillero(id));
				unCasillero = coleccionCasilleros.get(Arrays.toString(id));
			}
			SeccionDeNave valor = iteradorDeSecciones.next();
			unCasillero.ponerSeccionDeNave(valor);
		}
		
		
	}
	
	public Casillero obtenerCasillero(int[] id) throws ErrorIdCasilleroInvalido{
		if ( this.coleccionCasilleros.contains(id) ){
		return this.coleccionCasilleros.get(Arrays.toString(id));
		}
		
		Casillero casillero = new Casillero(id);
		this.coleccionCasilleros.put(Arrays.toString(id), casillero);
		
		return casillero;
		
	}
	
	public boolean estaVacio (){
		return coleccionCasilleros.isEmpty();
	}
	
	public boolean ubicarProaDeNave(Nave unaNave, Casillero unCasillero){
		/* con el casillero de la proa (parte de adelante) y con la direccion
		 que tiene la nave podemos saber que casilleros va a ocupar.
		 Si no entra supongo que habr�a que levantar una excepcion,
		 o redefinir la firma de la funcion a bool..
		*/
		
		
		if( ! this.naveEntraEn(unaNave, unCasillero) ){
			return false;
		}
		
		Casillero casilleroActual = unCasillero;
		
		//itera las secciones de la nave.
		//for(SeccionDeNave unaSeccionDeNave : unaNave){ //no se porque tira error con SeccionesDeNave en vez de object 
			//casilleroActual.ponerSeccionDeNave((SeccionDeNave)unaSeccionDeNave);
			//casilleroActual = siguiente casillero
		//}
		
		Iterator<SeccionDeNave> iteradorDeSeccionesDeNave= unaNave.iterator();
		SeccionDeNave unaSeccionDeNave;
		while(iteradorDeSeccionesDeNave.hasNext()){
			unaSeccionDeNave = iteradorDeSeccionesDeNave.next();
			casilleroActual.ponerSeccionDeNave(unaSeccionDeNave);			
		}
		return true;
	}

	private boolean naveEntraEn(Nave unaNave, Casillero unCasillero) {
		int[] id = new int[2];
		
		

		for(int tmp = 0; tmp < unaNave.largo(); tmp++){
			id = this.proximoCasillero(unCasillero, unaNave.direccion());
			unCasillero = coleccionCasilleros.get(Arrays.toString(id));
			try{
				Casillero.validarId(id);
			}catch(ErrorIdCasilleroInvalido e){
				return false;
			}
		}
		
		return true;
	}

	public int navesDestruidas(){
		
		int navesDestruidas = 0;
		Nave nave;
		Iterator<Nave> iteradorDeNaves = naves.iterator();
		
		if (iteradorDeNaves.hasNext()){
			
			nave = iteradorDeNaves.next();
			
			if (nave.porcentajeVida() == 0){
				navesDestruidas = navesDestruidas++;
			}
		}
		
		return navesDestruidas;
		
	}
	
	/* Cantidad de naves en el tablero, sin importar estado. */
	public int cantidadTotalNaves(){
		return naves.size();
	}
	
	/* Sirve para obtener el casillero proximo en la direccion 
	 */
	public int[] proximoCasillero(Casillero c, Sentido s){
		int[] nuevoId = new int[2]; //nuevoId no sobrevive fuera del if. CORREGIR!!
								// "Corregido": Creo que es un problema de como estaba declarado
								// Hay que revisar que sea solo eso.
		
		if (s == Sentido.NOROESTE){
			nuevoId[0] = c.id()[0] - 1;
			nuevoId[1] = c.id()[1] - 1;
		}
		else if (s == Sentido.NORTE){
			nuevoId[0] = c.id()[0];
			nuevoId[1] = c.id()[1] - 1;
		}
		else  if (s == Sentido.NORESTE){
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1] - 1;
		}
		else  if (s == Sentido.ESTE){
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1];
		}
		else if (s == Sentido.SUDESTE){
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1] + 1;
		}
		else if (s == Sentido.SUR){
			nuevoId[0] = c.id()[0];
			nuevoId[1] = c.id()[1] + 1;
		}
		else if (s == Sentido.SUDOESTE){
			nuevoId[0] = c.id()[0] - 1;
			nuevoId[1] = c.id()[1] + 1;
		}
		else if (s == Sentido.OESTE){
			nuevoId[0] = c.id()[0] - 1;
			nuevoId[1] = c.id()[1];
		}
		
		return nuevoId;
	}
	
	/*Mismo iterador que usa la clase Nave con sus secciones
	  pero para las naves.
	  Hay que chequear la implementacion, tanto del iterator,
	  como de navesDestruidas
	*/
	@Override
	public Iterator<Nave> iterator() {
		Iterator<Nave> iterador = new Iterator<Nave>(){
			
			private Iterator<Nave> iteradorDeNaves = naves.iterator();
			
			@Override
			public boolean hasNext(){
				return iteradorDeNaves.hasNext();
			}
			
			@Override
			public Nave next(){
				return iteradorDeNaves.next(); 
			}
			
			@Override
			public void remove(){
				//no hace nada pero me obliga a definirlo.
			}
		};
		return iterador;
	}
	
	public void agregarCasilleroConMunicion(Casillero casillero){
		this.casillerosConMunicion.add(casillero.id());
	}
	
	private Hashtable<Casillero, Integer> casillerosConMunicionesSinRetardo(){
		Hashtable <Casillero, Integer> casilleros;
		casilleros = new Hashtable<>();

		int cantidadDeCasillerosConMunicion = this.casillerosConMunicion.size(); //devolverMunciones().size();

		/*Recorro la lista de casilleros con municiones*/
		for (int i = 0; i < cantidadDeCasillerosConMunicion; i++) {
			Casillero casillero = this.coleccionCasilleros.get (casilleros.get(i));
			
			/*Miro municiones que hay en cada casillero*/
			int cantidadDeMunicionesEnCasillero = casillero.devolverMuniciones().size();
			for (int x = 0; x < cantidadDeMunicionesEnCasillero; x++){
		
				/*Me fijo si tienen retardo = 0 y agrego al Hashtable*/
				if ( casillero.devolverMuniciones().get(x).retardo() == 0 ){
					casilleros.put(casillero, x); //Aunque si tiene dos municiones, una ret != 0 y otra ret= 0, la agrega igual. hay qe verificar despu�s que solo se accionen las ret=0
				}
			}
			
		}
		
		return casilleros;
	}
	
	public void actualizarTablero(){
		Hashtable <Casillero, Integer> casilleros;
		casilleros = this.casillerosConMunicionesSinRetardo();
		/*Ac� deber�a hacerce para todos los casilleros del hash, casillero.efectuarImpacto(indiceMunicion).
		 * con indiceMunicion= valor del casillero en el hash.*/
		
		/*Este m�todo deber�a hacer retardo -= 1 de las municiones que quedan en el tablero. Falta implementar*/
		this.restarRetardoDeMuniciones();
	}

	private void restarRetardoDeMuniciones() {
		// TODO Auto-generated method stub
		
	}
	private int[] sumarPatronDeSumaEId(int[] id ,int[] patronDeSuma){
		int[] total= new int[2];
		total[0] = id[0] + patronDeSuma[0];
		total[1] = id[1] + patronDeSuma[1];
		
		return total;
	}
	private int [] patronDeSumaParaUbicarNave(Direccion direccionNave){
		//Devuelve un patron para sumarle al idCelda y ubicar las secciones de nave
		int[] patronSur = {0,-1};
		int[] patronNorte = {0,1};
		int[] patronOeste = {-1,0};
		int[] patronEste = {1,0};
		int[] patronSudEste = {1,-1};
		int[] patronNorEste = {1,1};
		int[] patronSudOeste = {-1,-1};
		int[] patronNorOeste = {-1,-1};
		
		if (direccionNave.sentido() == Sentido.SUR)
			return patronSur;
		else if (direccionNave.sentido() == Sentido.NORTE)
			return patronNorte;
		else if (direccionNave.sentido() == Sentido.ESTE)
			return patronEste;
		else if (direccionNave.sentido() == Sentido.OESTE)
			return patronOeste;
		else if (direccionNave.sentido() == Sentido.NORESTE)
			return patronNorte;
		else if (direccionNave.sentido() == Sentido.NOROESTE)
			return  patronNorOeste;
		else if (direccionNave.sentido() == Sentido.SUDESTE)
			return  patronSudOeste;
		else // (direccionNave.sentido() == Sentido.SUDOESTE)
			return  patronSudOeste;		
	}
	private int[] buscarCasilleroParaProa(int largoNave){
		//Pone el barco en un area segura y devuelve el id
		boolean idX = false;
		boolean idY = true;
		int[] id = new int[2];
		final int MAX=6, MIN=3;
		
		while (idX == false){
			id[0] = (int) (Math.random() * 10);

			if (id[0]<MAX || id[0]>MIN)
				idX=true;	
		}

			id[1] = (int) (Math.random() * 10);

		while (idY == false){
			id[0] = (int) (Math.random() * 10);
			if (id[1]<MAX || id[1]>MIN)
				idX=true;			
		}
		return id;
	}
}
