package controlador;

import instanciadores.InstanciadorMuniciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Visibility;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JList;

import tablero.TableroComunicable;
import vista.VistaBatalla;

import municiones.Municion;

import juego.BatallaNavalgo;

public class Controlador {

	private BatallaNavalgo modelo;
	private VistaBatalla vista;
	private String municionSeleccionada = "Disparo Convencional" ; //seteado por defecto
	
	private Hashtable<String, String> informacionDeMuniciones = new Hashtable<String, String>();

	public void cargarModelo(BatallaNavalgo modeloRecibido){
		this.modelo = modeloRecibido;
	}
	
	public void cargarVista(VistaBatalla vistaRecibida){
		this.vista = vistaRecibida;
	}
	
	
	
	public class ListenerBotonesTablero implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e)
		{	
			JButton boton = (JButton) e.getSource();
			int [] id = (int[]) boton.getClientProperty("id");
			
			Municion municion = InstanciadorMuniciones.instanciar(municionSeleccionada);
			
			//Pasarle al tablero el id del casillero y la municionSeleccionada
			
			modelo.ronda(municion, id);
		}
	}
	
	public class ListenerBotonInicarPartida implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e)
		{		
			modelo.iniciarPartidaNueva();
		}
	}
	
	public ActionListener obtenerListenerBotonIniciarPartida(){
		return new ListenerBotonInicarPartida();
	}
	
	public ActionListener obtenerListenerBotonesTablero(){
		return new ListenerBotonesTablero();
	}
	
	
	
	public class ListenerListadoMuniciones implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			JList<String> listaMuniciones = (JList<String>) e.getSource();

			municionSeleccionada = (String) listaMuniciones.getSelectedValue();
			
			informacionDeMuniciones.put("Disparo Convencional","Impacta en una posición en el mismo momento del disparo" + "\n" + "Costo: 200 puntos");
			informacionDeMuniciones.put("Mina Por Contacto","El impacto se realiza cuando una nave pasa por esa posición" + "\n" + "Costo: 150 puntos");
			informacionDeMuniciones.put("Mina Puntual Con Retardo","Impacta tres turnos después de haber sido colocado en esa posición" + "\n" + "Costo: 50 puntos");
			informacionDeMuniciones.put("Mina Doble Con Retardo","Impacta tres turnos después de haber sido colocado en esa posición. Destruye la posición actual y las posiciones adyacentes con radio 1" + "\n" + "Costo: 100 puntos");
			informacionDeMuniciones.put("Mina Triple Con Retardo","Impacta tres turnos después de haber sido colocado en esa posición. Destruye la posición actual y las posiciones adyacentes con radio 2" + "\n" + "Costo: 150 puntos");
			
			vista.informacionMunicion.setText((String)informacionDeMuniciones.get(municionSeleccionada));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public MouseListener obtenerListenerListadoMuniciones(){
		return new ListenerListadoMuniciones();
	}
}
