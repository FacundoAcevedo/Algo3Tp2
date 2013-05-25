package naves;

public class Nave {
	private int largo;
	private Direccion direccion;
	
	public Nave(int largoNave, Direccion direccionNave){
		this.largo = largoNave;
		this.direccion = direccionNave;
	}
	
	public Direccion direccion(){
		return direccion;	
	}
	
	public int largo(){
		return largo;
	}
	

}
