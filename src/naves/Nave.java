package naves;


import municiones.Municion;
import java.util.LinkedList;
import java.util.List;

public class Nave {
	protected int largo;
	protected Direccion direccion;
	protected EstadoDeSalud estado;
	protected List<SeccionDeNave> secciones = new LinkedList<SeccionDeNave>();

	public Nave(int largoNave, Direccion direccionNave) {
		this.largo = largoNave;
		this.direccion = direccionNave;
		this.estado = EstadoDeSalud.SANO;

		// Genero las secciones de la nave
		for (int i = 0; i <= this.largo; i++) {
			SeccionDeNave seccion = new SeccionDeNave(this);
			this.secciones.add(seccion);
		}
	}

	public Direccion direccion() {
		return this.direccion;
	}

	public int largo() {
		return this.largo;
	}

	public EstadoDeSalud estado() {
		return this.estado;
	}

	protected void actualizarEstado() {
		// Reviso todas las secciones
		int i = 0;
		SeccionDeNave seccion = this.secciones.get(i);
		do {	
			//Este condicional se cunple cuando ya se recorrio todas las partes
			//de la nave y TODAS estan destruidas, luego el estado de la nave sera DESTRUIDO
			if ( i >= this.largo) this.estado = EstadoDeSalud.DESTRUIDO;
			i++;
			seccion = this.secciones.get(i);
		} while( seccion.estado() == EstadoDeSalud.DESTRUIDO);
	}

	public void recibirImpacto(Municion municion) {

	}
	
	public boolean vulnerable(Municion municion){
		return true;
	}
	
	

}
