package tablero;
import java.util.Hashtable;
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
import tablero.Casillero;
import excepciones.ErrorIdCasilleroInvalido;


public class Tablero implements Iterable{
	private Hashtable<int[], Casillero> coleccionCasilleros;
	private LinkedList<Nave> naves;
	
	public Tablero (){
		this.coleccionCasilleros = new Hashtable<>();
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
	public void  posicionarNaveEnTablero(Nave n){
		
	}
	
	public Casillero obtenerCasillero(int[] id){
		IdCasillero.validarId(id);
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
	
	public void ubicarProaDeNave(Nave unaNave, Casillero unCasillero){
		/* con el casillero de la proa (parte de adelante) y con la direccion
		 que tiene la nave podemos saber que casilleros va a ocupar.
		 Si no entra supongo que habría que levantar una excepcion,
		 o redefinir la firma de la funcion a bool..
		*/
		
		
		 int[] idProa = unCasillero.id();
		 try{
		 	IdCasillero.validarId(idProa);
		 }
		 catch (ErrorIdCasilleroInvalido e){
		 	return; //no se puede poner aca la nave. Aca va return false o throw excepcion	 
		 }
		
		
		Casillero casilleroActual = unCasillero;
		//itera las secciones de la nave.
		for(Object unaSeccionDeNave : unaNave){ //no se porque tira error con SeccionesDeNave en vez de object 
			casilleroActual.ponerSeccionDeNave((SeccionDeNave)unaSeccionDeNave);
			//casilleroActual = siguiente casillero
		}
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
	
}
