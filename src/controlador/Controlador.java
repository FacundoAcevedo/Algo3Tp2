package controlador;

import instanciadores.InstanciadorMuniciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;

import naves.SeccionDeNave;

import tablero.Casillero;
import vista.VistaBatalla;

import municiones.Municion;

import juego.BatallaNavalgo;

public class Controlador {

	private BatallaNavalgo modelo;
	private VistaBatalla vista;
	private String municionSeleccionada = "Disparo Convencional"; // seteado por
																	// defecto

	private Hashtable<String, String> informacionDeMuniciones = new Hashtable<String, String>();

	public void cargarModelo(BatallaNavalgo modeloRecibido) {
		this.modelo = modeloRecibido;
	}

	public void cargarVista(VistaBatalla vistaRecibida) {
		this.vista = vistaRecibida;
	}

	public class MouseListenerBotonesTablero implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			JButton boton = (JButton) e.getSource();
			int[] id = (int[]) boton.getClientProperty("id");

			Municion municion = InstanciadorMuniciones
					.instanciar(municionSeleccionada);

			// Pasarle al tablero el id del casillero y la municionSeleccionada

			modelo.ronda(municion, id);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton boton = (JButton) e.getSource();
			int[] id = (int[]) boton.getClientProperty("id");
			Casillero casillero = modelo.obtenerCasillero(id);
			List<SeccionDeNave> coleccionSeccionesDeNave = casillero
					.devolverSeccionesDeNave();
			if (!coleccionSeccionesDeNave.isEmpty()) {
				int porcenjateDeSalud = coleccionSeccionesDeNave.get(0)
						.obtenerPorcentajeDeSalud();
				String nombreNave = coleccionSeccionesDeNave.get(0).obtenerTipoDeNave();
				boton.setToolTipText(nombreNave+"  "+Integer.toString(porcenjateDeSalud) + "%");

			}

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

	public MouseListener obtenerMouseListenerBotonesTablero() {
		return new MouseListenerBotonesTablero();
	}

	public class ListenerBotonInicarPartida implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			modelo.iniciarPartidaNueva();
			vista.actualizarBotonesDelTablero();
			vista.habilitarJuego();
		}
	}

	public ActionListener obtenerListenerBotonIniciarPartida() {
		return new ListenerBotonInicarPartida();
	}

	public class ListenerListadoMuniciones implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			JList<String> listaMuniciones = (JList<String>) e.getSource();

			municionSeleccionada = (String) listaMuniciones.getSelectedValue();

			informacionDeMuniciones.put("Disparo Convencional",
					"Impacta en una posicion en el mismo momento del disparo"
							+ "\n" + "Costo: 200 puntos");
			informacionDeMuniciones.put("Mina Por Contacto",
					"El impacto se realiza cuando una nave pasa por esa posicion"
							+ "\n" + "Costo: 150 puntos");
			informacionDeMuniciones.put("Mina Puntual Con Retardo",
					"Impacta tres turnos despues de haber sido colocado en esa posicion"
							+ "\n" + "Costo: 50 puntos");
			informacionDeMuniciones
					.put("Mina Doble Con Retardo",
							"Impacta tres turnos despues de haber sido colocado en esa posicion. Destruye la posicion actual y las posiciones adyacentes con radio 1"
									+ "\n" + "Costo: 100 puntos");
			informacionDeMuniciones
					.put("Mina Triple Con Retardo",
							"Impacta tres turnos despues de haber sido colocado en esa posicion. Destruye la posicion actual y las posiciones adyacentes con radio 2"
									+ "\n" + "Costo: 150 puntos");

			vista.informacionMunicion.setText((String) informacionDeMuniciones
					.get(municionSeleccionada));
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

	public class ListenerBotonGuardar implements ActionListener {
		private BatallaNavalgo modelo;

		public ListenerBotonGuardar(BatallaNavalgo modeloRecibido) {
			modelo = modeloRecibido;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (this.modelo.juegoTerminado() | !this.modelo.juegoEnProceso()) {
				JOptionPane.showOptionDialog(
						null,
						"El juego no se puede guardar en este estado",
						"Error",
						   JOptionPane.OK_OPTION,
						   JOptionPane.ERROR_MESSAGE,
						  null,    
						   new String[]{"Acepto"},   
						   null);

			} else {
				try {
					// Creo el fichero que que escribira en disco
					FileOutputStream fichero = new FileOutputStream(
							"PartidaGuardad.dato");
					// Creo el escritor de objetos
					ObjectOutputStream out = new ObjectOutputStream(fichero);
					// Escribo los objetos
					out.writeObject(modelo);
					// Cierro el archivo
					out.close();

				} catch (IOException error) {
					JOptionPane.showOptionDialog(
							null,
							"No se pudo escribir en el disco.",
							"Error",
							   JOptionPane.OK_OPTION,
							   JOptionPane.ERROR_MESSAGE,
							  null,    
							   new String[]{"Acepto"},   
							   null);
				}
			}
		}

	}

	public class ListenerBotonCargar implements ActionListener {
		private Controlador control;
		private VistaBatalla vista;

		public ListenerBotonCargar(Controlador controlador, VistaBatalla vista) {
			this.control = controlador;
			this.vista = vista;

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				FileInputStream ficheroEntrada = new FileInputStream("PartidaGuardad.dato");
				ObjectInputStream streamDeEntrada = new ObjectInputStream(ficheroEntrada);
				BatallaNavalgo modelo =  (BatallaNavalgo) streamDeEntrada.readObject();
				ficheroEntrada.close();
				streamDeEntrada.close();
				this.control.cargarModelo(modelo);
				this.vista.cambiarModelo(modelo);
				this.vista.habilitarJuego();
				modelo.ActualizarObservadores();
				this.vista.actualizarBotonesDelTablero();

			} catch (IOException | ClassNotFoundException error) {
				JOptionPane.showOptionDialog(
						null,
						"No se pudo leer el archivo desde el disco.",
						"Error",
						   JOptionPane.OK_OPTION,
						   JOptionPane.ERROR_MESSAGE,
						  null,    
						   new String[]{"Acepto"},   
						   null);
			}
		}

	}

	public MouseListener obtenerListenerListadoMuniciones() {
		return new ListenerListadoMuniciones();
	}

	public ActionListener obtenerListenerBotonCargar(VistaBatalla vista) {
		return new ListenerBotonCargar(this, vista);
	}

	public ActionListener obtenerListenerBotonGuardar() {
		return new ListenerBotonGuardar(this.modelo);
	}
}
