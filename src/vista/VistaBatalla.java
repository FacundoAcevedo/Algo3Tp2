package vista;

import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import tablero.Casillero;

import municiones.Municion;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controlador.Controlador;

import juego.BatallaNavalgo;
import java.awt.event.*;

public class VistaBatalla implements Observer {

	
		private BatallaNavalgo modelo; //referencia al modelo
		private JFrame frameBatalla = new JFrame("Batalla Navalgo"); //creamos el marco
        private JPanel panelPuntaje = new JPanel(); //creamos el panel que contiene el puntaje
        private Label labelPuntos = new Label("Puntos:"); //etiqueta de "Puntos"
		private TextField textoPuntos = new TextField(); //texto que mostrara el puntaje
        private JBackgroundPanel panelTablero = new JBackgroundPanel(); //creamos el panel que contiene el tablero
        private JPanel panelLista = new JPanel(); //creamos el panel que contiene la lista de municiones
        private Label labelMuniciones = new Label("Municiones:"); //etiqueta de "Municiones"
        private JList<String> listaMuniciones = new JList<String>();//listado de municiones
        private JButton botones[] = new JButton[ 2 ]; //Botones de iniciar y salir
        private JPanel panelBotones = new JPanel(); //Panel de "botones"
        
        
        
		//Constructor de la vista
		public VistaBatalla(BatallaNavalgo modelo, Controlador control)
		{	
			//armado de la ventana


	        frameBatalla.setVisible(true);
	        frameBatalla.setAlwaysOnTop(false); //En false para poder minimizar la pantalla
	        frameBatalla.setResizable(false);
	        frameBatalla.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
	        frameBatalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frameBatalla.getContentPane().setLayout(new BoxLayout(frameBatalla.getContentPane(), BoxLayout.X_AXIS));
	          
	       
	        panelBotones.setLayout(new GridLayout(0, 1));
		  	  
	        // crear y agregar botones
	        botones[ 0 ] = new JButton( "Iniciar Juego Nuevo" ); //Este boton deberia posicionar las naves. (seria partida() de batalla navalgo)
	        JButton botonIniciar = botones[ 0 ];
            panelBotones.add( botonIniciar );

            
	        botones[ 1 ] = new JButton ("Salir del Juego");
	        JButton botonSalir = botones [ 1 ];
            panelBotones.add( botonSalir );
            

	        botonSalir.addActionListener(new ActionListener ()
	        {
	        	   public void actionPerformed (ActionEvent e)
	        	   {
	        		   if (e.getSource()== botones [ 1 ]) {
	        	            System.exit(0);
	        	   } 
	        	}
	        });

	        frameBatalla.getContentPane().add(panelBotones);
	      
			
	        frameBatalla.getContentPane().add(panelPuntaje); //agrega una nueva columna para los puntos
	        //se setean los tamanyos de esta nueva columna
	        GridBagLayout gbl_panel = new GridBagLayout();
	        gbl_panel.columnWidths = new int[]{0, 62, 80, 0};
	        gbl_panel.rowHeights = new int[]{22, 0, 0, 0};
	        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	        // se "aplican" los cambios de la coluna (con la 1ra linea ya se dibuja,
	        // pero no se aplican los tamanyos asignados)
	        panelPuntaje.setLayout(gbl_panel);
	        
	        // Escribe "Puntos"
	        GridBagConstraints gbc_label = new GridBagConstraints();
	        gbc_label.anchor = GridBagConstraints.NORTHWEST;
	        gbc_label.insets = new Insets(0, 0, 0, 5);
	        gbc_label.gridx = 1;
	        gbc_label.gridy = 2;
	        panelPuntaje.add(labelPuntos, gbc_label);
	        
	        // Crea y dibuja caja con los puntos
	        GridBagConstraints gbc_textField = new GridBagConstraints();
	        gbc_textField.fill = GridBagConstraints.BOTH;
	        gbc_textField.gridx = 2;
	        gbc_textField.gridy = 2;
	        panelPuntaje.add(textoPuntos, gbc_textField);
	    
	        
	        
	        frameBatalla.getContentPane().add(panelTablero); //agrega la "matriz" del tablero a la ventana
	        //Se asigna la disposicion de los botones.
	        panelTablero.setLayout(new FormLayout(new ColumnSpec[] {
	        		
	        		//asigna ancho a las 10 columnas
	        		//30dlu es la medida del ancho
	        		ColumnSpec.decode("30dlu"), //1ra col
	        		ColumnSpec.decode("30dlu"), //2da col
	        		ColumnSpec.decode("30dlu"), //etc..
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		ColumnSpec.decode("30dlu"),
	        		
	        		},
	        	new RowSpec[] {
	        		
	        		//Asigna alto a las 10 filas
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
	        		}));
	        
	        
	        
	        
	        //Parece alrevez, pero nuestro tablero comienza en la esquina
	        //inferior izquierda
	        
	        
	        //este hash seria para guardar los botones que vamos creando, de ser necesario
	        // recordar que un int[] no sirve como clave!
	        //Hashtable<String, JButton> botones = new Hashtable<String, JButton>();
	        
	        //Crea todos los Botones
	        int xVista, yVista;
	        for(int x = 0; x < 10; x++){
	        	for(int y = 0; y < 10; y++){
	        		JButton botonCasillero = new JButton();
	        		xVista = x+1;
	        		yVista = 10-y;
	        		panelTablero.add(botonCasillero, ""+xVista+","+yVista);
	    	        int[] id = {x, y};
	    	        botonCasillero.putClientProperty("id", id);
	    	        //botones.guardarEnHash de ser necesario..
	        	}
	        }
	    
	        
	        //Hace que los botones de casillero sean transparentes
	        //y les agregro su listener
	        Component[] componentes =panelTablero.getComponents(); 
	        for(int i=0; i<componentes.length;i++) 
	        { 
	        	((JButton)componentes[i]).setContentAreaFilled(false);
				((JButton)componentes[i]).addActionListener(control.obtenerListenerBotonesTablero());
	        } 
	        
	        
	        
	       
	        
	     
	        // Crea panel para lista de municiones.
	        frameBatalla.getContentPane().add(panelLista);
	        // Agrega lista con municiones
	        GridBagLayout gbl_panel_2 = new GridBagLayout();
	        gbl_panel_2.columnWidths = new int[]{0, 89, 22, 0};
	        gbl_panel_2.rowHeights = new int[]{23, 21, 0, 0, 0, 0};
	        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
	        panelLista.setLayout(gbl_panel_2);
	      
	        // Escribe "Municiones"
	        GridBagConstraints gbc_label_2 = new GridBagConstraints();
	        gbc_label_2.anchor = GridBagConstraints.NORTHWEST;
	        gbc_label_2.insets = new Insets(0,0,0,5);
	        gbc_label_2.gridx = 0;
	        gbc_label_2.gridy = 1;
	        panelLista.add(labelMuniciones, gbc_label_2);
	        
	        // Crea lista con Municiones
	        listaMuniciones.setSelectedIndex(0);
	        listaMuniciones.setModel(new AbstractListModel<String>() {
	        	private static final long serialVersionUID = 1L;
				String[] values = new String[] {"Disparo Convencional",
						"Mina Por Contacto","Mina Puntual Con Retardo",
						"Mina Doble Con Retardo", "Mina Triple Con Retardo"};
					        	
	        	public int getSize() {
	        		return values.length;
	        	}
	        	
	        	public String getElementAt(int index) {
	        		return values[index];
	        	}
	        	
	        });
	        listaMuniciones.setSelectedIndex(0);
	        listaMuniciones.addMouseListener(control.obtenerListenerListadoMuniciones());
	        
	        //
	        GridBagConstraints gbc_list = new GridBagConstraints();
	        gbc_list.insets = new Insets(0, 0, 5, 5);
	        gbc_list.fill = GridBagConstraints.BOTH;
	        gbc_list.gridx = 1;
	        gbc_list.gridy = 1;
	        panelLista.add(listaMuniciones, gbc_list);
	        
	        // Asigna el tamanyo correcto a la ventana para que entre todo
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

