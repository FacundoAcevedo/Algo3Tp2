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
import naves.Sentido;
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
	@SuppressWarnings("null")
	public void  posicionarNaveEnTablero(Nave n){
		
		Casillero unCasillero;
		int[] id = null;
		
		do{
			id[0] = (int) Math.random() * 10;
			id[1] = (int) Math.random() * 10;
			
			unCasillero = coleccionCasilleros.get(id);
		}while( this.ubicarProaDeNave(n, unCasillero) );
		
		Iterator<SeccionDeNave> iteradorDeSecciones = n.secciones().iterator();
		iteradorDeSecciones.next();
		while (iteradorDeSecciones.hasNext()){
			id = this.proximoCasillero(unCasillero, n.direccion());
			unCasillero = coleccionCasilleros.get(id);
			unCasillero.ponerSeccionDeNave(iteradorDeSecciones.next());
		}
		
		
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
	
	public boolean ubicarProaDeNave(Nave unaNave, Casillero unCasillero){
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
		 	return false; //no se puede poner aca la nave. Aca va return false o throw excepcion	 
		 }
		
		
		Casillero casilleroActual = unCasillero;
		//itera las secciones de la nave.
		for(Object unaSeccionDeNave : unaNave){ //no se porque tira error con SeccionesDeNave en vez de object 
			casilleroActual.ponerSeccionDeNave((SeccionDeNave)unaSeccionDeNave);
			//casilleroActual = siguiente casillero
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
	
	/* Sirve para obtener el casillero proximo en la direccion 
	 */
	@SuppressWarnings("null")
	public int[] proximoCasillero(Casillero c, Sentido s){
		int[] nuevoId = null; //nuevoId no sobrevive fuera del if. CORREGIR!!
		
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
	
}
