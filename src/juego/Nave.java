package juego;

public class Nave {
	private int largo;
	private Direccion direccion;
	
	public Nave(int largoNave, Direccion direccionNave){
		largo = largoNave;
		direccion = direccionNave;
	}
	
	public Direccion direccion(){
		return direccion;	
	}
	
	public int largo(){
		return largo;
	}
	

}
