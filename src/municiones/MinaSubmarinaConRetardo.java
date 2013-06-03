package municiones;

public abstract class MinaSubmarinaConRetardo extends MinaSubmarina {
	final static int miRetardo = 3;
	
	public MinaSubmarinaConRetardo(int unCosto){
		super(unCosto, miRetardo);
	}
}
