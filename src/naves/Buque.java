package naves;

public class Buque extends Nave{
	final static int miLargo = 4;
	
	public Buque (Direccion direccionBuqe){
		//Llamo al constructor de Nave
		super(miLargo, direccionBuqe);
	}
	
}
