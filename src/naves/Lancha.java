package naves;

public class Lancha extends Nave{
	final static int miLargo = 2;

	public Lancha(Direccion direccionLancha){
		//Llamo al constructor de Nave
		super(miLargo, direccionLancha);
	}

}
