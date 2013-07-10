package naves;

import municiones.*;
public class Destructor extends Nave{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5722465092889817460L;
	final static int miLargo = 3;

	public Destructor(Direccion direccionDestructor){
		//Llamo al constructor de Nave
		super(miLargo, direccionDestructor);
	}

	public  EstadoDeSalud vulnerable(Municion municion){
		if (municion instanceof DisparoConvencional){
			return EstadoDeSalud.DESTRUIDO;
		}
		return EstadoDeSalud.SANO;
	}
	

}
