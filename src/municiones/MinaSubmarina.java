package municiones;

public abstract class MinaSubmarina extends Municion {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3026762751593588180L;
	private static boolean efectoInmediato = false;
	
	public MinaSubmarina(int unCosto, int unTiempo){
		super(unCosto, unTiempo, efectoInmediato);
	}
	
	
}
