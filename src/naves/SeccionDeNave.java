package naves;

public class SeccionDeNave {
	private EstadoDeSalud estado;
	private Nave nave; //Referencia a la nave donde pertenece esta seccion
	
	public SeccionDeNave(Nave nave) {
		this.nave = nave;
		this.estado = EstadoDeSalud.ACTIVO;
	}
	
	public void destruir(){
		this.estado = EstadoDeSalud.DESTRUIDO;
		this.nave.actualizar();
	}
	
	
	public EstadoDeSalud estado(){
		return this.estado;
	}
	
	
	

}
