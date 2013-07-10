package naves;


public class Lancha extends Nave{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6903491068046648681L;
	final static int miLargo = 2;

	public Lancha(Direccion direccionLancha){
		//Llamo al constructor de Nave
		super(miLargo, direccionLancha);
	}

}
