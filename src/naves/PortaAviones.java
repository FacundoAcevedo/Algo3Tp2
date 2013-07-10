package naves;

public class PortaAviones extends Nave {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1159056783778241341L;
	final static int miLargo = 4;
	
	public PortaAviones(Direccion direccionPortaAviones) {
		//Llamo al constructor de Nave
		super(miLargo, direccionPortaAviones);
	}

}
