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
		int seccionesSanas = 0, seccionesDestruidas = 0, seccionesDanadas = 0;
		SeccionDeNave seccion;
		for (i = 0; i <= this.largo; i++){
			seccion = this.secciones.get(i);
			if (seccion.estado() == EstadoDeSalud.SANO) seccionesSanas++;
			if (seccion.estado() == EstadoDeSalud.DANADO) seccionesDanadas++;
			if (seccion.estado() == EstadoDeSalud.DESTRUIDO) seccionesDestruidas++;
		}
		
		if (seccionesDestruidas == this.largo) this.estado = EstadoDeSalud.DESTRUIDO;
		else if (seccionesSanas == this.largo) this.estado = EstadoDeSalud.SANO;
		else this.estado = EstadoDeSalud.DANADO;
		
		
	}

	public void recibirImpacto(Municion municion) {

	}
	
	public boolean vulnerable(Municion municion){
		return true;
	}
	
	

}
