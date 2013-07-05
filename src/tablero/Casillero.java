package tablero;

import java.util.LinkedList;
import java.util.List;

import excepciones.ErrorCasilleroOcupadoConOtraMunicion;
import excepciones.ErrorIdCasilleroInvalido;

import naves.SeccionDeNave;
import municiones.DisparoConvencional;
import municiones.MinaSubmarinaConRetardo;
import municiones.MinaSubmarinaPorContacto;
import municiones.Municion;

public class Casillero {
	protected int x, y;

	protected List<SeccionDeNave> coleccionDeSeccionesDeNave = new LinkedList<SeccionDeNave>();
	protected List<Municion> coleccionMuniciones = new LinkedList<Municion>();

	public Casillero(int x, int y) throws ErrorIdCasilleroInvalido {
		Casillero.validarId(x, y);
		this.x = x;
		this.y = y;
	}

	public Casillero(int[] id) {
		this(id[0], id[1]);
	}

	public void ponerSeccionDeNave(SeccionDeNave seccionDeNave) {
		if (!this.coleccionDeSeccionesDeNave.contains(seccionDeNave)) {
			this.coleccionDeSeccionesDeNave.add(seccionDeNave);
		}

	}
//	public void actualizarContenidos(){
//		//Verifica que las minas que deban estallar, estallen
//		if (tieneMuniciones()){
//			for( Municion municion : coleccionMuniciones){
//				if ( municion instanceof MinaSubmarinaConRetardo && municion.retardo() == 0){
//					for (SeccionDeNave seccion : coleccionDeSeccionesDeNave)
//						seccion.recibirImpacto(municion);
//					coleccionMuniciones.remove(municion);
//						
//					
//				}
//			}
//		}
//	}
	public List<SeccionDeNave> devolverSeccionesDeNave() {
		return this.coleccionDeSeccionesDeNave;
	}
	
	public boolean tieneMuniciones(){
		return (this.coleccionMuniciones.size() > 0);
	}
	public boolean tieneSeccionesDeNave(){
		return (this.coleccionDeSeccionesDeNave.size() > 0);
	}

	public void ponerMunicion(Municion municion){// throws ErrorCasilleroOcupadoConOtraMunicion{
		
		if (municion instanceof DisparoConvencional
				|| (municion instanceof MinaSubmarinaPorContacto && coleccionDeSeccionesDeNave.size() > 0)){
			for (SeccionDeNave seccion : coleccionDeSeccionesDeNave){
				seccion.recibirImpacto(municion);
			}
		}
		
		else {
			this.coleccionMuniciones.add(municion);
		}
		

	}

	public List<Municion> devolverMuniciones() {
		return this.coleccionMuniciones;
	}

	public int[] id() {
		int[] duplaPosicion = new int[2];
		duplaPosicion[0] = this.x;
		duplaPosicion[1] = this.y;
		return duplaPosicion;
	}

	public boolean estaVacio() {
		// Devuelve que esta vacio, si NO HAY secciones de naves NI municiones
		// en el casillero.
		if (this.coleccionDeSeccionesDeNave.isEmpty()
				&& this.coleccionMuniciones.isEmpty())
			return true;
		return false;
	}

	// metodo estatico para validar id
	static public void validarId(int x, int y) throws ErrorIdCasilleroInvalido {
		if (x < 0 || x > 9 || y < 0 || y > 9) {
			throw new ErrorIdCasilleroInvalido();
		}
	}

	static public void validarId(int[] id) throws ErrorIdCasilleroInvalido {
		Casillero.validarId(id[0], id[1]);
	}
	
	public void quitarSeccion(SeccionDeNave seccion){
		this.coleccionDeSeccionesDeNave.remove(seccion);
	}
	
	public void quitarMunicion(Municion municion){
		this.coleccionMuniciones.remove(municion);
	}
	
//	public void efectuarImpacto(int indiceMunicion){
//		/*Si hay naves*/
//		if (this.coleccionDeSeccionesDeNave.isEmpty() == false ){
//			
//			int cantidadDeSeccionesDeNave = this.coleccionDeSeccionesDeNave.size();
//			for (int i = 0; i < cantidadDeSeccionesDeNave; i++){
//				(this.coleccionDeSeccionesDeNave.get(i)).recibirImpacto(coleccionMuniciones.get(indiceMunicion));
//				/*Ac� falta borrar la munici�n que ya impact� del casillero*/
//			}
//		}
//	}
}
