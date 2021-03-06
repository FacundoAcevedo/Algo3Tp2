package vista;

import instanciadores.InstanciadorImagenes;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import municiones.Municion;
import naves.SeccionDeNave;

import tablero.Casillero;


import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controlador.Controlador;

import juego.BatallaNavalgo;

public class VistaBatalla implements Observer, Serializable{

	
		/**
	 * 
	 */
	private static final long serialVersionUID = 3332326063543538707L;
	
		private BatallaNavalgo modelo; //referencia al modelo
		public JFrame frameBatalla = new JFrame("Batalla Navalgo"); //creamos el marco
        private JPanel panelPuntaje = new JPanel(); //creamos el panel que contiene el puntaje
        private Label labelPuntos = new Label("Puntos:"); //etiqueta de "Puntos"
		private JTextField textoPuntos = new JTextField(); //texto que mostrara el puntaje
        private JBackgroundPanel panelTablero = new JBackgroundPanel(); //creamos el panel que contiene el tablero
        private JPanel panelLista = new JPanel(); //creamos el panel que contiene la lista de municiones
        private Label labelMuniciones = new Label("Municiones:"); //etiqueta de "Municiones"
        private JList<String> listaMuniciones = new JList<String>();//listado de municiones
        private JButton botonesOpciones[] = new JButton[ 5 ]; //Botones de iniciar, guardar, cargar y salir
        private JPanel panelBotones = new JPanel(); //Panel de botones de  opciones
        private Boolean JuegoIniciado = false;
        
        private JPanel panelMuniciones = new JPanel();
        final ImageIcon imageIcon = new ImageIcon("estaticos/Fondos/fondoMuniciones.jpg");
        public JTextArea informacionMunicion = new JTextArea(){
        	private static final long serialVersionUID = 1L;
			Image image = imageIcon.getImage();
            {
              setOpaque(false);
            }

            public void paint(Graphics g) {
              g.drawImage(image, 0, 0, this);
              super.paint(g);
            }
          };
         
         
        
        private Hashtable<String, JButton> botonesTablero = new Hashtable<String, JButton>();
        
        //private Component[] componentes;
        
		//Constructor de la vista
		public VistaBatalla(BatallaNavalgo modelo, Controlador control)
		{	
			//armado de la ventana
			Image icono = Toolkit.getDefaultToolkit().getImage("estaticos/icono.png");
			frameBatalla.setIconImage(icono);
	        //frameBatalla.setVisible(true);
	        frameBatalla.setAlwaysOnTop(false); //En false para poder minimizar la pantalla
	        frameBatalla.setResizable(false);
	        frameBatalla.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
	        frameBatalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frameBatalla.getContentPane().setLayout(new BoxLayout(frameBatalla.getContentPane(), BoxLayout.X_AXIS));
	          
	       
	        panelBotones.setLayout(new GridLayout(0, 1));
	        
		  	  
	        // crear y agregar botones
	        botonesOpciones[ 0 ] = new JButton( "Iniciar Juego Nuevo" ); 
	        JButton botonIniciar = botonesOpciones[ 0 ];
            botonIniciar.addActionListener(control.obtenerListenerBotonIniciarPartida());

            botonesOpciones[ 1 ] = new JButton ("Guardar Juego");
	        JButton botonGuardar = botonesOpciones [ 1 ];
	        botonGuardar.setEnabled(true); 
	        botonGuardar.addActionListener(control.obtenerListenerBotonGuardar());
            
            botonesOpciones[ 2 ] = new JButton ("Cargar Juego");
	        JButton botonCargar = botonesOpciones [ 2 ];
	        botonCargar.setEnabled(true); 
	        botonCargar.addActionListener(control.obtenerListenerBotonCargar(this));
	        
	        botonesOpciones[ 3 ] = new JButton ("Ver Instrucciones");
	        JButton botonInstrucciones = botonesOpciones [ 3 ];
	        botonInstrucciones.setEnabled(true); 
	        botonInstrucciones.addActionListener(new ActionListener ()
	        {
	        	public void actionPerformed (ActionEvent e)
	        	{
	        		if (e.getSource()== botonesOpciones [ 3 ]){
	        			Instruccion instrucciones = new Instruccion();
	        			instrucciones.setVisible(true);
	        		}
	        	}
	        });
	        
            botonesOpciones[ 4 ] = new JButton ("Salir del Juego");
	        JButton botonSalir = botonesOpciones [ 4 ];
            
	        //Personaliza los botones y los agrega al panel
            for(int i = 0; i < 5; i++){
            	botonesOpciones[ i ].setBackground(Color.BLACK);
            	botonesOpciones[ i ].setForeground(Color.WHITE);
            	panelBotones.add( botonesOpciones[ i ] );
            }

	        botonSalir.addActionListener(new ActionListener ()
	        {
	        	   public void actionPerformed (ActionEvent e)
	        	   {
	        		   if (e.getSource()== botonesOpciones [ 4 ]) {
	        	            System.exit(0);
	        	   } 
	        	}
	        });

	        frameBatalla.getContentPane().add(panelBotones);
	      
	        panelPuntaje.setBackground(Color.BLACK);
	        labelPuntos.setForeground(Color.WHITE);
			
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
	        textoPuntos.setEditable(false);
	        textoPuntos.setBackground(Color.BLACK);
	        textoPuntos.setForeground(Color.WHITE);
	        textoPuntos.setBorder(null);
	    
	     
	       
	        
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
	        
	        
	        //Crea todos los Botones del tablero y los configura
	        int xVista, yVista;
	        for(int x = 0; x < 10; x++){
	        	for(int y = 0; y < 10; y++){
	        		JButton botonCasillero = new JButton();
	        		xVista = x+1;
	        		yVista = 10-y;
	        		panelTablero.add(botonCasillero, ""+xVista+","+yVista);
	    	        int[] id = {x, y};
	    	        botonCasillero.putClientProperty("id", id);
	    	        //Seteo la trasparencia en los botones
	    	        botonCasillero.setContentAreaFilled(false);
	    	        //Cargo su listener
	    	        botonCasillero.addMouseListener(control.obtenerMouseListenerBotonesTablero());   

	    	        // Guarda en el hash
	    	        String idComoString = Integer.toString(id[0]) + Integer.toString(id[1]);
	    	        botonesTablero.put(idComoString, botonCasillero);
	        	}
	        }

	        Border border = BorderFactory.createLineBorder(Color.BLACK);
	        
	        labelMuniciones.setForeground(Color.WHITE);
	        panelLista.setBackground(Color.BLACK);
	        panelMuniciones.setBackground(Color.BLACK);
	        panelMuniciones.setLayout(new GridLayout(0, 1));
	        panelMuniciones.add(panelLista);
	        panelMuniciones.add(informacionMunicion);
	        panelMuniciones.setBorder(BorderFactory.createCompoundBorder(border, 
	                BorderFactory.createEmptyBorder(20, 30, 20, 30)));
	        
	        informacionMunicion.setEditable(false);
	        informacionMunicion.setLineWrap(true);
	        informacionMunicion.setWrapStyleWord(true);
	        informacionMunicion.setBorder(BorderFactory.createCompoundBorder(border, 
	                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
	        
	        frameBatalla.getContentPane().add(panelMuniciones);
	        
	     
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
	        listaMuniciones.clearSelection();
	        listaMuniciones.setEnabled(false);
	        
	        //
	        GridBagConstraints gbc_list = new GridBagConstraints();
	        gbc_list.insets = new Insets(0, 0, 5, 5);
	        gbc_list.fill = GridBagConstraints.BOTH;
	        gbc_list.gridx = 1;
	        gbc_list.gridy = 1;
	        panelLista.add(listaMuniciones, gbc_list);
	        
	        // Asigna el tamanyo correcto a la ventana para que entre todo
	        frameBatalla.pack(); 
	        	        
			// Conectamos esta vista con el modelo
			this.modelo = modelo;
			this.modelo.addObserver(this); 
		}
		
		//Metodo que es llamado por el modelo al actualizarse el mismo
		public void update(Observable t, Object o){
			if (JuegoIniciado){
			if (modelo.juegoTerminado()){
				String mensaje;
				String titulo;
				String[] opcion;
				ImageIcon icono;
				String rutaImagenesInformativas = "estaticos/informativos/";
				String[] rutaCaras= {rutaImagenesInformativas+"cara_feliz.png", rutaImagenesInformativas+"cara_indiferente.png", rutaImagenesInformativas+"cara_triste.png"};
				int puntaje = modelo.puntosDelJugador();
				
				if (puntaje >= 5000){
					mensaje = "Asi es, parece que sos bueno en esto!\n Tu puntaje es:"+Integer.toString(puntaje);
					titulo = "¡¡Victoria!!";
					opcion = new String[]{ "Acepto con orgullo"};
					icono = new ImageIcon(rutaCaras[0]);
				}
				else if (puntaje >= 1000 && puntaje <5000){
					mensaje = "No es un mal puntaje...\n Tu puntaje es:"+Integer.toString(puntaje);
					titulo = "Victoria?";
					opcion = new String[]{ "Acepto..."};
					icono = new ImageIcon(rutaCaras[1]);
				}
				else{
					mensaje = "Segui participando...\n Tu puntaje es:"+Integer.toString(puntaje);
					titulo =  "Cof Cof";
					opcion = new String[]{ "Acepto, pero no le digan a nadie!"};
					icono = new ImageIcon(rutaCaras[2]);
					
				}
				JOptionPane.showOptionDialog(
						this.frameBatalla,
						mensaje,
						titulo,
						   JOptionPane.OK_OPTION,
						   JOptionPane.INFORMATION_MESSAGE,
						   icono,    
						   opcion,   
						   null);
			}
			else{
				this.actualizarPuntaje();
				this.actualizarBotonesDelTablero();
			}
			}
		}
		private void actualizarPuntaje() {
			int puntaje = this.modelo.puntosDelJugador();
			setTextoPuntos(Integer.toString(puntaje));

		}

		public void habilitarJuego(){
			listaMuniciones.setEnabled(true);
			JuegoIniciado = true;
			this.actualizarPuntaje();
		}
		
		public void actualizarBotonesDelTablero(){
			ImageIcon imagenBarcos = null;
			ImageIcon imagenMuniciones = null;
			this.limpiarBotonesDelTablero();
			 for(int x = 0; x < 10; x++){
		        	for(int y = 0; y < 10; y++){
		        		int[] id = {x,y};	
		        		Casillero  casillero = modelo.obtenerCasillero(id);
		        		List<SeccionDeNave> coleccionSeccionesDeNave = casillero.devolverSeccionesDeNave();
		        		List<Municion> coleccionMuniciones = casillero.devolverMuniciones();
	        			String idComoString = Integer.toString(id[0]) + Integer.toString(id[1]);


		        		//Solo una de las secciones sera mostrada
		        		if (!coleccionSeccionesDeNave.isEmpty() & coleccionMuniciones.isEmpty()){		        			
		        			imagenBarcos = InstanciadorImagenes.nave(coleccionSeccionesDeNave);
		        			JButton botonTablero = this.botonesTablero.get(idComoString);
		        			botonTablero.setIcon(imagenBarcos);
		        		}
		        		
		        		else if(coleccionSeccionesDeNave.isEmpty()& !coleccionMuniciones.isEmpty()){
		        			imagenMuniciones = InstanciadorImagenes.municion(coleccionMuniciones);
		        			JButton botonTablero = this.botonesTablero.get(idComoString);
		        			botonTablero.setIcon(imagenMuniciones);
		        		}

		        		else if (!coleccionSeccionesDeNave.isEmpty()& !coleccionMuniciones.isEmpty()){
		        			imagenBarcos = InstanciadorImagenes.nave(coleccionSeccionesDeNave);
		        			imagenMuniciones = InstanciadorImagenes.municion(coleccionMuniciones);
		        			LinkedList<ImageIcon> listaImagenesBarcoYMuniciones = new LinkedList<ImageIcon>();
		        			listaImagenesBarcoYMuniciones.add(imagenBarcos);
		        			listaImagenesBarcoYMuniciones.add(imagenMuniciones);
		        			
		        			ImageIcon imagenBarcosYMuniciones = EditorDeImagenes.mixDesdeIconImage(listaImagenesBarcoYMuniciones);
		        			JButton botonTablero = this.botonesTablero.get(idComoString);
		        			botonTablero.setIcon(imagenBarcosYMuniciones);
		        		}
		        		
		        	}
		        }
			
		}
		public void limpiarBotonesDelTablero(){
			 for(int x = 0; x < 10; x++){
		        	for(int y = 0; y < 10; y++){
		        		int[] id = {x,y};
	        			String idComoString = Integer.toString(id[0]) + Integer.toString(id[1]);
	        			JButton botonTablero = this.botonesTablero.get(idComoString);
	        			botonTablero.setIcon(null);

		        	}
			 }
		}

		public void setTextoPuntos(String s){
			textoPuntos.setText(s);
		}
		public void cambiarModelo(BatallaNavalgo modelo){
			
			this.modelo.deleteObservers();
			
			this.modelo = modelo;
			this.modelo.deleteObservers();
			this.modelo.addObserver(this);
			this.actualizarBotonesDelTablero();
		}
	}

