package naves;

import municiones.*;
public class Destructor extends Nave{
	final static int miLargo = 3;

	public Destructor(Direccion direccionDestructor){
		//Llamo al constructor de Nave
		super(miLargo, direccionDestructor);
	}

	public  boolean vulnerable(Municion municion){
		if (municion instanceof DisparoConvecional){
			return true;
		}
		return false;
	}
	

}
