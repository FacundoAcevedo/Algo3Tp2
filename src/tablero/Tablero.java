package tablero;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import municiones.Municion;
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
	private Hashtable<int[], Casillero> coleccionCasilleros;
	private LinkedList<int[]> casillerosConMunicion;
	private LinkedList<Nave> naves;
	
	public Tablero (){
		this.coleccionCasilleros = new Hashtable<>();
		this.casillerosConMunicion = new LinkedList<int[]>();
		this.naves = new LinkedList<Nave>();
		
		Nave nave;
		Direccion direccion = new Direccion(null);
		
		direccion.random();
		nave = new Lancha(direccion);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new Lancha(direccion);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new Destructor(direccion);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new Destructor(direccion);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new Buque(direccion);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new PortaAviones(direccion);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

		direccion.random();
		nave = new RompeHielos(direccion);
		this.posicionarNaveEnTablero(nave);
		this.naves.add(nave);

	}
	
	/*Hay que ver como situa el tablero una nave
	  en si mismo.
	 */
	@SuppressWarnings("null")
	public void  posicionarNaveEnTablero(Nave n){
		
		Casillero unCasillero;
		int[] id = new int[2];
		
		do{
			id[0] = (int) Math.random() * 10;
			id[1] = (int) Math.random() * 10;
			
			unCasillero = coleccionCasilleros.get(id); // REVISAR: no se crean los casilleros, es una coleccion vacía.
		}while( this.ubicarProaDeNave(n, unCasillero) );
		
		Iterator<SeccionDeNave> iteradorDeSecciones = n.secciones().iterator();
		iteradorDeSecciones.next();
		while (iteradorDeSecciones.hasNext()){
			id = this.proximoCasillero(unCasillero, n.direccion());
			unCasillero = coleccionCasilleros.get(id);
			unCasillero.ponerSeccionDeNave(iteradorDeSecciones.next());
		}
		
		
	}
	
	public Casillero obtenerCasillero(int[] id) throws ErrorIdCasilleroInvalido{
		if ( this.coleccionCasilleros.contains(id) ){
		return this.coleccionCasilleros.get(id);
		}
		
		Casillero casillero = new Casillero(id);
		this.coleccionCasilleros.put(id, casillero);
		
		return casillero;
		
	}
	
	public boolean estaVacio (){
		return coleccionCasilleros.isEmpty();
	}
	
	public boolean ubicarProaDeNave(Nave unaNave, Casillero unCasillero){
		/* con el casillero de la proa (parte de adelante) y con la direccion
		 que tiene la nave podemos saber que casilleros va a ocupar.
		 Si no entra supongo que habría que levantar una excepcion,
		 o redefinir la firma de la funcion a bool..
		*/
		
		
		 int[] idProa = unCasillero.id();
		 try{
		 	Casillero.validarId(idProa);
		 }
		 catch (ErrorIdCasilleroInvalido e){
		 	return false; //no se puede poner aca la nave. Aca va return false o throw excepcion	 
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
		if (s == Sentido.NORTE){
			nuevoId[0] = c.id()[0];
			nuevoId[1] = c.id()[1] - 1;
		}
		if (s == Sentido.NORESTE){
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1] - 1;
		}
		if (s == Sentido.ESTE){
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1];
		}
		if (s == Sentido.SUDESTE){
			nuevoId[0] = c.id()[0] + 1;
			nuevoId[1] = c.id()[1] + 1;
		}
		if (s == Sentido.SUR){
			nuevoId[0] = c.id()[0];
			nuevoId[1] = c.id()[1] + 1;
		}
		if (s == Sentido.SUDOESTE){
			nuevoId[0] = c.id()[0] - 1;
			nuevoId[1] = c.id()[1] + 1;
		}
		if (s == Sentido.OESTE){
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
					casilleros.put(casillero, x); //Aunque si tiene dos municiones, una ret != 0 y otra ret= 0, la agrega igual. hay qe verificar después que solo se accionen las ret=0
				}
			}
			
		}
		
		return casilleros;
	}
	
	public void actualizarTablero(){
		Hashtable <Casillero, Integer> casilleros;
		casilleros = this.casillerosConMunicionesSinRetardo();
		/*Acá debería hacerce para todos los casilleros del hash, casillero.efectuarImpacto(indiceMunicion).
		 * con indiceMunicion= valor del casillero en el hash.*/
		
		/*Este método debería hacer retardo -= 1 de las municiones que quedan en el tablero. Falta implementar*/
		this.restarRetardoDeMuniciones();
	}

	private void restarRetardoDeMuniciones() {
		// TODO Auto-generated method stub
		
	}
}
