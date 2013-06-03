package municiones;

public abstract class MinaSubmarina extends Municion {
	
	private int miRetardo;
	
	public MinaSubmarina(int unCosto, int unTiempo){
		super(unCosto);
		miRetardo = unTiempo;
	}
	
	public int retardo(){
		return miRetardo;
	}
	
}
