package vista;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;

import controlador.Controlador;

import juego.BatallaNavalgo;

public class VistaBatalla implements Observer {

	
		private BatallaNavalgo modelo; //referencia al modelo
		private JFrame frameBatalla = new JFrame("Batalla Navalgo"); //creamos el marco
        private JPanel panelPuntaje = new JPanel(); //creamos el panel que contiene el puntaje
        private Label labelPuntos = new Label("Puntos"); //etiqueta de "Puntos"
		private TextField textoPuntos = new TextField(); //texto que mostrara el puntaje
        private JPanel panelTablero = new JPanel(); //creamos el panel que contiene el tablero
		private JTable tablero = new JTable(10,10); //creamos el tablero
        private JPanel panelBotones = new JPanel(); //creamos el panel que contiene la lista de municiones
        private JList listaMuniciones = new JList();//listado de municiones
        
		//Constructor de la vista
		public VistaBatalla(BatallaNavalgo modelo, Controlador control)
		{	
			//armado de la ventana


	        frameBatalla.setVisible(true);
	        frameBatalla.setAlwaysOnTop(true);
	        frameBatalla.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
	        frameBatalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frameBatalla.getContentPane().setLayout(new BoxLayout(frameBatalla.getContentPane(), BoxLayout.X_AXIS));
	        
			
	        frameBatalla.getContentPane().add(panelPuntaje);
	        GridBagLayout gbl_panel = new GridBagLayout();
	        gbl_panel.columnWidths = new int[]{0, 62, 80, 0};
	        gbl_panel.rowHeights = new int[]{22, 0, 0, 0};
	        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	        panelPuntaje.setLayout(gbl_panel);
	        
	        GridBagConstraints gbc_label = new GridBagConstraints();
	        gbc_label.anchor = GridBagConstraints.NORTHWEST;
	        gbc_label.insets = new Insets(0, 0, 0, 5);
	        gbc_label.gridx = 1;
	        gbc_label.gridy = 2;
	        panelPuntaje.add(labelPuntos, gbc_label);

	        textoPuntos.setEnabled(true);
	        textoPuntos.setEditable(false);
	        GridBagConstraints gbc_textField = new GridBagConstraints();
	        gbc_textField.fill = GridBagConstraints.BOTH;
	        gbc_textField.gridx = 2;
	        gbc_textField.gridy = 2;
	        panelPuntaje.add(textoPuntos, gbc_textField);
	        
	        frameBatalla.getContentPane().add(panelTablero);
	        
	        
	        tablero.setCellSelectionEnabled(true);
	        tablero.setColumnSelectionAllowed(false);
	        tablero.setRowSelectionAllowed(false);
	        tablero.setBackground(new Color(0, 255, 255));
	        tablero.setForeground(Color.WHITE);
	        
	        tablero.getColumnModel().getColumn(0).setResizable(false);
	        tablero.getColumnModel().getColumn(0).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(0).setMinWidth(50);
	        tablero.getColumnModel().getColumn(0).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(1).setResizable(false);
	        tablero.getColumnModel().getColumn(1).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(1).setMinWidth(50);
	        tablero.getColumnModel().getColumn(1).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(2).setResizable(false);
	        tablero.getColumnModel().getColumn(2).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(2).setMinWidth(50);
	        tablero.getColumnModel().getColumn(2).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(3).setResizable(false);
	        tablero.getColumnModel().getColumn(3).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(3).setMinWidth(50);
	        tablero.getColumnModel().getColumn(3).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(4).setResizable(false);
	        tablero.getColumnModel().getColumn(4).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(4).setMinWidth(50);
	        tablero.getColumnModel().getColumn(4).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(5).setResizable(false);
	        tablero.getColumnModel().getColumn(5).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(5).setMinWidth(50);
	        tablero.getColumnModel().getColumn(5).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(6).setResizable(false);
	        tablero.getColumnModel().getColumn(6).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(6).setMinWidth(50);
	        tablero.getColumnModel().getColumn(6).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(7).setResizable(false);
	        tablero.getColumnModel().getColumn(7).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(7).setMinWidth(50);
	        tablero.getColumnModel().getColumn(7).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(8).setResizable(false);
	        tablero.getColumnModel().getColumn(8).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(8).setMinWidth(50);
	        tablero.getColumnModel().getColumn(8).setMaxWidth(50);
	        tablero.getColumnModel().getColumn(9).setResizable(false);
	        tablero.getColumnModel().getColumn(9).setPreferredWidth(50);
	        tablero.getColumnModel().getColumn(9).setMinWidth(50);
	        tablero.getColumnModel().getColumn(9).setMaxWidth(50);
	        panelTablero.add(tablero);
	        
	        frameBatalla.getContentPane().add(panelBotones);
	        GridBagLayout gbl_panel_2 = new GridBagLayout();
	        gbl_panel_2.columnWidths = new int[]{0, 89, 22, 0};
	        gbl_panel_2.rowHeights = new int[]{23, 21, 0, 0, 0, 0};
	        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
	        panelBotones.setLayout(gbl_panel_2);
	        
	        listaMuniciones.setSelectedIndex(0);
	        listaMuniciones.setModel(new AbstractListModel() {
	        	String[] values = new String[] {"Disparo Convencional", "Mina Por Contacto","Mina Puntual Con Retardo", "Mina Doble Con Retardo", "Mina Triple Con Retardo"};
	        	public int getSize() {
	        		return values.length;
	        	}
	        	public Object getElementAt(int index) {
	        		return values[index];
	        	}
	        });
	        listaMuniciones.setSelectedIndex(0);
	        GridBagConstraints gbc_list = new GridBagConstraints();
	        gbc_list.insets = new Insets(0, 0, 5, 5);
	        gbc_list.fill = GridBagConstraints.BOTH;
	        gbc_list.gridx = 1;
	        gbc_list.gridy = 1;
	        panelBotones.add(listaMuniciones, gbc_list);
	        
	        frameBatalla.pack();
	        
	        
			// Conectamos esta vista con el modelo
			this.modelo = modelo;
			this.modelo.addObserver(this); 
		}
		
		//Metodo que es llamado por el modelo al actualizarse el mismo
		public void update(Observable t, Object o)
		{	
		}

				
	}

