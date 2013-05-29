package tablero;

import municiones.Municion;
import naves.SeccionDeNave;

import java.util.LinkedList;
import java.util.List;

public class ContenidoCasillero {
	protected List<SeccionDeNave> coleccionDeSeccionesDeNave = new LinkedList<SeccionDeNave>();
	protected List<Municion> coleccionMuniciones= new LinkedList<Municion>();
	
	public void ponerSeccionDeNave(SeccionDeNave seccionDeNave) {
		if (!this.coleccionDeSeccionesDeNave.contains(seccionDeNave)){
			this.coleccionDeSeccionesDeNave.add(seccionDeNave);
		}
		
	}
	public List<SeccionDeNave> devolverSeccionesDeNave(){
		return this.coleccionDeSeccionesDeNave;
	}

	public void ponerMunicion(Municion municion) {
		if (!this.coleccionMuniciones.contains(municion)){
			this.coleccionMuniciones.add(municion);
		}
		
		
	}
	public List<Municion> devolverMuniciones(){
		return this.coleccionMuniciones;
	}
	public boolean estaVacio() {
		//Devuelve que esta vacio, si NO HAY secciones de naves NI municiones en el casillero.
		if (this.coleccionDeSeccionesDeNave.isEmpty() && this.coleccionMuniciones.isEmpty()) return true;
		return false;
	}

}
