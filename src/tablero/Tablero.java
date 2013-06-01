package tablero;
import java.util.Hashtable;
import java.util.LinkedList;

import naves.Buque;
import naves.Destructor;
import naves.Lancha;
import naves.Nave;
import naves.PortaAviones;
import naves.RompeHielos;
import naves.SeccionDeNave;
import tablero.Casillero;
import excepciones.ErrorIdCasilleroInvalido;


public class Tablero {
	private Hashtable<int[], Casillero> coleccionCasilleros;
	private LinkedList<Nave> naves;
	
	public Tablero (){
		this.coleccionCasilleros = new Hashtable<>();
		this.naves = new LinkedList<Nave>();
		
		/* habria que ver como genera las direcciones
		   aleatorias para agregar las naves en el 
		   tablero
		*/
		
		this.naves.add(new Lancha(null));
		this.naves.add(new Lancha(null));
		this.naves.add(new Destructor(null));
		this.naves.add(new Destructor(null));
		this.naves.add(new Buque(null));
		this.naves.add(new PortaAviones(null));
		this.naves.add(new RompeHielos(null));
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
		
		for(int i=0; i<7; i++){
			//habria que hacer la lista de naves iterable
			
			if (this.naves.get(i).porcentajeVida() == 0){
				navesDestruidas = navesDestruidas++;
			}
		}
		
		return navesDestruidas;
		
	}
	
}
