package vista;

import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import municiones.Municion;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controlador.Controlador;

import juego.BatallaNavalgo;

public class VistaBatalla implements Observer {

	
		private BatallaNavalgo modelo; //referencia al modelo
		private JFrame frameBatalla = new JFrame("Batalla Navalgo"); //creamos el marco
        private JPanel panelPuntaje = new JPanel(); //creamos el panel que contiene el puntaje
        private Label labelPuntos = new Label("Puntos"); //etiqueta de "Puntos"
		private TextField textoPuntos = new TextField(); //texto que mostrara el puntaje
        private JBackgroundPanel panelTablero = new JBackgroundPanel(); //creamos el panel que contiene el tablero
        private JPanel panelLista = new JPanel(); //creamos el panel que contiene la lista de municiones
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
	        panelTablero.setLayout(new FormLayout(new ColumnSpec[] {
	        		ColumnSpec.decode("5dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("5dlu"),},
	        	new RowSpec[] {
	        		RowSpec.decode("fill:5dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:30dlu"),
	        		RowSpec.decode("fill:5dlu"),}));
	        
	        
	        
	        
	        JButton botonCasillero_A1 = new JButton();
	        panelTablero.add(botonCasillero_A1, "2, 2");
	        
	        JButton botonCasillero_B1 = new JButton();
	        panelTablero.add(botonCasillero_B1, "3, 2");
	        
	        JButton botonCasillero_C1 = new JButton();
	        panelTablero.add(botonCasillero_C1, "4, 2");
	        
	        JButton botonCasillero_D1 = new JButton();
	        panelTablero.add(botonCasillero_D1, "5, 2");
	        
	        JButton botonCasillero_E1 = new JButton();
	        panelTablero.add(botonCasillero_E1, "6, 2");
	        
	        JButton botonCasillero_F1 = new JButton();
	        panelTablero.add(botonCasillero_F1, "7, 2");
	        
	        JButton botonCasillero_G1 = new JButton();
	        panelTablero.add(botonCasillero_G1, "8, 2");
	        
	        JButton botonCasillero_H1 = new JButton();
	        panelTablero.add(botonCasillero_H1, "9, 2");
	        
	        JButton botonCasillero_I1 = new JButton();
	        panelTablero.add(botonCasillero_I1, "10, 2");
	        
	        JButton botonCasillero_J1 = new JButton();
	        panelTablero.add(botonCasillero_J1, "11, 2");
	        
	        JButton botonCasillero_A2 = new JButton();
	        panelTablero.add(botonCasillero_A2, "2, 3");
	        
	        JButton botonCasillero_B2 = new JButton();
	        panelTablero.add(botonCasillero_B2, "3, 3");
	        
	        JButton botonCasillero_C2 = new JButton();
	        panelTablero.add(botonCasillero_C2, "4, 3");
	        
	        JButton botonCasillero_D2 = new JButton();
	        panelTablero.add(botonCasillero_D2, "5, 3");
	        
	        JButton botonCasillero_E2 = new JButton();
	        panelTablero.add(botonCasillero_E2, "6, 3");
	        
	        JButton botonCasillero_F2 = new JButton();
	        panelTablero.add(botonCasillero_F2, "7, 3");
	        
	        JButton botonCasillero_G2 = new JButton();
	        panelTablero.add(botonCasillero_G2, "8, 3");
	        
	        JButton botonCasillero_H2 = new JButton();
	        panelTablero.add(botonCasillero_H2, "9, 3");
	        
	        JButton botonCasillero_I2 = new JButton();
	        panelTablero.add(botonCasillero_I2, "10, 3");
	        
	        JButton botonCasillero_J2 = new JButton();
	        panelTablero.add(botonCasillero_J2, "11, 3");
	        
	        JButton botonCasillero_A3 = new JButton();
	        panelTablero.add(botonCasillero_A3, "2, 4");
	        
	        JButton botonCasillero_B3 = new JButton();
	        panelTablero.add(botonCasillero_B3, "3, 4");
	        
	        JButton botonCasillero_C3 = new JButton();
	        panelTablero.add(botonCasillero_C3, "4, 4");
	        
	        JButton botonCasillero_D3 = new JButton();
	        panelTablero.add(botonCasillero_D3, "5, 4");
	        
	        JButton botonCasillero_E3 = new JButton();
	        panelTablero.add(botonCasillero_E3, "6, 4");
	        
	        JButton botonCasillero_F3 = new JButton();
	        panelTablero.add(botonCasillero_F3, "7, 4");
	        
	        JButton botonCasillero_G3 = new JButton();
	        panelTablero.add(botonCasillero_G3, "8, 4");
	        
	        JButton botonCasillero_H3 = new JButton();
	        panelTablero.add(botonCasillero_H3, "9, 4");
	        
	        JButton botonCasillero_I3 = new JButton();
	        panelTablero.add(botonCasillero_I3, "10, 4");
	        
	        JButton botonCasillero_J3 = new JButton();
	        panelTablero.add(botonCasillero_J3, "11, 4");
	        
	        JButton botonCasillero_A4 = new JButton();
	        panelTablero.add(botonCasillero_A4, "2, 5");
	        
	        JButton botonCasillero_B4 = new JButton();
	        panelTablero.add(botonCasillero_B4, "3, 5");
	        
	        JButton botonCasillero_C4 = new JButton();
	        panelTablero.add(botonCasillero_C4, "4, 5");
	        
	        JButton botonCasillero_D4 = new JButton();
	        panelTablero.add(botonCasillero_D4, "5, 5");
	        
	        JButton botonCasillero_E4 = new JButton();
	        panelTablero.add(botonCasillero_E4, "6, 5");
	        
	        JButton botonCasillero_F4 = new JButton();
	        panelTablero.add(botonCasillero_F4, "7, 5");
	        
	        JButton botonCasillero_G4 = new JButton();
	        panelTablero.add(botonCasillero_G4, "8, 5");
	        
	        JButton botonCasillero_H4 = new JButton();
	        panelTablero.add(botonCasillero_H4, "9, 5");
	        
	        JButton botonCasillero_I4 = new JButton();
	        panelTablero.add(botonCasillero_I4, "10, 5");
	        
	        JButton botonCasillero_J4 = new JButton();
	        panelTablero.add(botonCasillero_J4, "11, 5");
	        
	        JButton botonCasillero_A5 = new JButton();
	        panelTablero.add(botonCasillero_A5, "2, 6");
	        
	        JButton botonCasillero_B5 = new JButton();
	        panelTablero.add(botonCasillero_B5, "3, 6");
	        
	        JButton botonCasillero_C5 = new JButton();
	        panelTablero.add(botonCasillero_C5, "4, 6");
	        
	        JButton botonCasillero_D5 = new JButton();
	        panelTablero.add(botonCasillero_D5, "5, 6");
	        
	        JButton botonCasillero_E5 = new JButton();
	        panelTablero.add(botonCasillero_E5, "6, 6");
	        
	        JButton botonCasillero_F5 = new JButton();
	        panelTablero.add(botonCasillero_F5, "7, 6");
	        
	        JButton botonCasillero_G5 = new JButton();
	        panelTablero.add(botonCasillero_G5, "8, 6");
	        
	        JButton botonCasillero_H5 = new JButton();
	        panelTablero.add(botonCasillero_H5, "9, 6");
	        
	        JButton botonCasillero_I5 = new JButton();
	        panelTablero.add(botonCasillero_I5, "10, 6");
	        
	        JButton botonCasillero_J5 = new JButton();
	        panelTablero.add(botonCasillero_J5, "11, 6");
	        
	        JButton botonCasillero_A6 = new JButton();
	        panelTablero.add(botonCasillero_A6, "2, 7");
	        
	        JButton botonCasillero_B6 = new JButton();
	        panelTablero.add(botonCasillero_B6, "3, 7");
	        
	        JButton botonCasillero_C6 = new JButton();
	        panelTablero.add(botonCasillero_C6, "4, 7");
	        
	        JButton botonCasillero_D6 = new JButton();
	        panelTablero.add(botonCasillero_D6, "5, 7");
	        
	        JButton botonCasillero_E6 = new JButton();
	        panelTablero.add(botonCasillero_E6, "6, 7");
	        
	        JButton botonCasillero_F6 = new JButton();
	        panelTablero.add(botonCasillero_F6, "7, 7");
	        
	        JButton botonCasillero_G6 = new JButton();
	        panelTablero.add(botonCasillero_G6, "8, 7");
	        
	        JButton botonCasillero_H6 = new JButton();
	        panelTablero.add(botonCasillero_H6, "9, 7");
	        
	        JButton botonCasillero_I6 = new JButton();
	        panelTablero.add(botonCasillero_I6, "10, 7");
	        
	        JButton botonCasillero_J6 = new JButton();
	        panelTablero.add(botonCasillero_J6, "11, 7");
	        
	        JButton botonCasillero_A7 = new JButton();
	        panelTablero.add(botonCasillero_A7, "2, 8");
	        
	        JButton botonCasillero_B7 = new JButton();
	        panelTablero.add(botonCasillero_B7, "3, 8");
	        
	        JButton botonCasillero_C7 = new JButton();
	        panelTablero.add(botonCasillero_C7, "4, 8");
	        
	        JButton botonCasillero_D7 = new JButton();
	        panelTablero.add(botonCasillero_D7, "5, 8");
	        
	        JButton botonCasillero_E7 = new JButton();
	        panelTablero.add(botonCasillero_E7, "6, 8");
	        
	        JButton botonCasillero_F7 = new JButton();
	        panelTablero.add(botonCasillero_F7, "7, 8");
	        
	        JButton botonCasillero_G7 = new JButton();
	        panelTablero.add(botonCasillero_G7, "8, 8");
	        
	        JButton botonCasillero_H7 = new JButton();
	        panelTablero.add(botonCasillero_H7, "9, 8");
	        
	        JButton botonCasillero_I7 = new JButton();
	        panelTablero.add(botonCasillero_I7, "10, 8");
	        
	        JButton botonCasillero_J7 = new JButton();
	        panelTablero.add(botonCasillero_J7, "11, 8");
	        
	        JButton botonCasillero_A8 = new JButton();
	        panelTablero.add(botonCasillero_A8, "2, 9");
	        
	        JButton botonCasillero_B8 = new JButton();
	        panelTablero.add(botonCasillero_B8, "3, 9");
	        
	        JButton botonCasillero_C8 = new JButton();
	        panelTablero.add(botonCasillero_C8, "4, 9");
	        
	        JButton botonCasillero_D8 = new JButton();
	        panelTablero.add(botonCasillero_D8, "5, 9");
	        
	        JButton botonCasillero_E8 = new JButton();
	        panelTablero.add(botonCasillero_E8, "6, 9");
	        
	        JButton botonCasillero_F8 = new JButton();
	        panelTablero.add(botonCasillero_F8, "7, 9");
	        
	        JButton botonCasillero_G8 = new JButton();
	        panelTablero.add(botonCasillero_G8, "8, 9");
	        
	        JButton botonCasillero_H8 = new JButton();
	        panelTablero.add(botonCasillero_H8, "9, 9");
	        
	        JButton botonCasillero_I8 = new JButton();
	        panelTablero.add(botonCasillero_I8, "10, 9");
	        
	        JButton botonCasillero_J8 = new JButton();
	        panelTablero.add(botonCasillero_J8, "11, 9");
	        
	        JButton botonCasillero_A9 = new JButton();
	        panelTablero.add(botonCasillero_A9, "2, 10");
	        
	        JButton botonCasillero_B9 = new JButton();
	        panelTablero.add(botonCasillero_B9, "3, 10");
	        
	        JButton botonCasillero_C9 = new JButton();
	        panelTablero.add(botonCasillero_C9, "4, 10");
	        
	        JButton botonCasillero_D9 = new JButton();
	        panelTablero.add(botonCasillero_D9, "5, 10");
	        
	        JButton botonCasillero_E9 = new JButton();
	        panelTablero.add(botonCasillero_E9, "6, 10");
	        
	        JButton botonCasillero_F9 = new JButton();
	        panelTablero.add(botonCasillero_F9, "7, 10");
	        
	        JButton botonCasillero_G9 = new JButton();
	        panelTablero.add(botonCasillero_G9, "8, 10");
	        
	        JButton botonCasillero_H9 = new JButton();
	        panelTablero.add(botonCasillero_H9, "9, 10");
	        
	        JButton botonCasillero_I9 = new JButton();
	        panelTablero.add(botonCasillero_I9, "10, 10");
	        
	        JButton botonCasillero_J9 = new JButton();
	        panelTablero.add(botonCasillero_J9, "11, 10");
	        
	        JButton botonCasillero_A10 = new JButton();
	        panelTablero.add(botonCasillero_A10, "2, 11");
	        
	        JButton botonCasillero_B10 = new JButton();
	        panelTablero.add(botonCasillero_B10, "3, 11");
	        
	        JButton botonCasillero_C10 = new JButton();
	        panelTablero.add(botonCasillero_C10, "4, 11");
	        
	        JButton botonCasillero_D10 = new JButton();
	        panelTablero.add(botonCasillero_D10, "5, 11");
	        
	        JButton botonCasillero_E10 = new JButton();
	        panelTablero.add(botonCasillero_E10, "6, 11");
	        
	        JButton botonCasillero_F10 = new JButton();
	        panelTablero.add(botonCasillero_F10, "7, 11");
	        
	        JButton botonCasillero_G10 = new JButton();
	        panelTablero.add(botonCasillero_G10, "8, 11");
	        
	        JButton botonCasillero_H10 = new JButton();
	        panelTablero.add(botonCasillero_H10, "9, 11");
	        
	        JButton botonCasillero_I10 = new JButton();
	        panelTablero.add(botonCasillero_I10, "10, 11");
	        
	        JButton botonCasillero_J10 = new JButton();
	        panelTablero.add(botonCasillero_J10, "11, 11");
	    
	        
	        //Hace que los botones de casillero sean transparentes
	        Component[] componentes =panelTablero.getComponents(); 
	        for(int i=0; i<componentes.length;i++) 
	        { 
	        	((JButton)componentes[i]).setContentAreaFilled(false);
	        } 
	        
	        
	        
	        frameBatalla.getContentPane().add(panelLista);
	        GridBagLayout gbl_panel_2 = new GridBagLayout();
	        gbl_panel_2.columnWidths = new int[]{0, 89, 22, 0};
	        gbl_panel_2.rowHeights = new int[]{23, 21, 0, 0, 0, 0};
	        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
	        panelLista.setLayout(gbl_panel_2);
	        
	        listaMuniciones.setSelectedIndex(0);
	        listaMuniciones.setModel(new AbstractListModel() {
	        	private static final long serialVersionUID = 1L;
				String[] values = new String[] {"Disparo Convencional", "Mina Por Contacto","Mina Puntual Con Retardo", "Mina Doble Con Retardo", "Mina Triple Con Retardo"};
	        	Municion municionElegida = new Municion(0, 0);
	        	
	        	public int getSize() {
	        		return values.length;
	        	}
	        	
	        	public String getElementAt(int index) {
	        		return values[index];
	        	}
	        });
	        listaMuniciones.setSelectedIndex(0);
	        GridBagConstraints gbc_list = new GridBagConstraints();
	        gbc_list.insets = new Insets(0, 0, 5, 5);
	        gbc_list.fill = GridBagConstraints.BOTH;
	        gbc_list.gridx = 1;
	        gbc_list.gridy = 1;
	        panelLista.add(listaMuniciones, gbc_list);
	        
	        frameBatalla.pack();
	        
	        //DEJO LA SIGUIENTE LINEA COMENTADA PARA QUE NO ME ROMPA LOS HUEVOS EL COMPILADOR
	        //listaMuniciones.addListSelectionListener(control.getListenerLista());
	        
			// Conectamos esta vista con el modelo
			this.modelo = modelo;
			this.modelo.addObserver(this); 
		}
		
		//Metodo que es llamado por el modelo al actualizarse el mismo
		public void update(Observable t, Object o){
			setTextoPuntos(Integer.toString(modelo.puntosDelJugador()));
		}

		public void setTextoPuntos(String s){
			textoPuntos.setText(s);
		}
	}

