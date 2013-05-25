package juego;

public class PortaAviones extends Nave {
	final static int miLargo = 4;
	
	public PortaAviones(Direccion direccionPortaAviones) {
		//Llamo al constructor de Nave
		super(miLargo, direccionPortaAviones);
	}

}
