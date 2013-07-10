package municiones;

public class MinaSubmarinaPuntualConRetardo extends MinaSubmarinaConRetardo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5300258209192766389L;
	final static int miCosto = 50;
	final static int miRango = 0;
	
	public MinaSubmarinaPuntualConRetardo(){
		super(miCosto, miRango);
	}
}
