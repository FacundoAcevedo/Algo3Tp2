package naves;

public class SeccionDeNave {
	protected EstadoSeccion estado;
	
	public SeccionDeNave() {
		this.estado = EstadoSeccion.SANO;
	}
	
	public void recibirImpacto(){
		this.estado = EstadoSeccion.DESTRUIDO;
	}
	
	public EstadoSeccion estado(){
		return this.estado;
	}
	
	
	

}
