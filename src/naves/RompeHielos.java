package naves;

import municiones.Municion;

public class RompeHielos extends Nave{
	final static int miLargo = 3;
	
	public RompeHielos(Direccion direccionRompehielos){
		//Llamo al constructor de Nave
		super(miLargo, direccionRompehielos);
		
	}
	public EstadoDeSalud vulnerable(Municion municion){
		//Devuelve el grado de vulnerabilidad a ese tipo de municion
		return EstadoDeSalud.DANADO;
	}
}
