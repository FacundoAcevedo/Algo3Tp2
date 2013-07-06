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
import controlador.ControladorBotonesTablero;

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
		public VistaBatalla(BatallaNavalgo modelo, Controlador control, ControladorBotonesTablero controladorBotonesTablero)
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

	        frameBatalla.getContentPane().add( panelBotones);
	      
			
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
	        		
	        		},
	        	new RowSpec[] {
	        		
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
	        		//RowSpec.decode("fill:5dlu"),
	        		}));
	        
	        
	        
	        
	        //Parece alrevez, pero nuestro tablero comienza en la esquina
	        //inferior izquierda
	        JButton botonCasillero_00 = new JButton();
	        panelTablero.add(botonCasillero_00, "1, 10");
	        botonCasillero_00.putClientProperty("id", "0,0");
	        
	        JButton botonCasillero_01 = new JButton();
	        panelTablero.add(botonCasillero_01, " 1, 9");
	        botonCasillero_01.putClientProperty("id", "0,1");
	        
	        JButton botonCasillero_02 = new JButton();
	        panelTablero.add(botonCasillero_02, " 1, 8");
	        botonCasillero_02.putClientProperty("id", "0,2");
	        
	        JButton botonCasillero_03 = new JButton();
	        panelTablero.add(botonCasillero_03, " 1, 7");
	        botonCasillero_03.putClientProperty("id", "0,3");
	        
	        JButton botonCasillero_04 = new JButton();
	        panelTablero.add(botonCasillero_04, " 1, 6");
	        botonCasillero_04.putClientProperty("id", "0,4");
	        
	        JButton botonCasillero_05 = new JButton();
	        panelTablero.add(botonCasillero_05, " 1, 5");
	        botonCasillero_05.putClientProperty("id", "0,5");
	        
	        JButton botonCasillero_06 = new JButton();
	        panelTablero.add(botonCasillero_06, " 1, 4");
	        botonCasillero_06.putClientProperty("id", "0,6");
	        
	        JButton botonCasillero_07 = new JButton();
	        panelTablero.add(botonCasillero_07, " 1, 3");
	        botonCasillero_07.putClientProperty("id", "0,7");
	        
	        JButton botonCasillero_08 = new JButton();
	        panelTablero.add(botonCasillero_08, " 1, 2");
	        botonCasillero_08.putClientProperty("id", "0,8");
	        
	        JButton botonCasillero_09 = new JButton();
	        panelTablero.add(botonCasillero_09, " 1, 1");
	        botonCasillero_09.putClientProperty("id", "0,9");
	        
	        JButton botonCasillero_10 = new JButton();
	        panelTablero.add(botonCasillero_10, " 2, 10");
	        botonCasillero_10.putClientProperty("id", "1,0");
	        
	        JButton botonCasillero_11 = new JButton();
	        panelTablero.add(botonCasillero_11, " 2, 9");
	        botonCasillero_11.putClientProperty("id", "1,1");
	        
	        JButton botonCasillero_12 = new JButton();
	        panelTablero.add(botonCasillero_12, " 2, 8");
	        botonCasillero_12.putClientProperty("id", "1,2");
	        
	        JButton botonCasillero_13 = new JButton();
	        panelTablero.add(botonCasillero_13, " 2, 7");
	        botonCasillero_13.putClientProperty("id", "1,3");
	        
	        JButton botonCasillero_14 = new JButton();
	        panelTablero.add(botonCasillero_14, " 2, 6");
	        botonCasillero_14.putClientProperty("id", "1,4");
	        
	        JButton botonCasillero_15 = new JButton();
	        panelTablero.add(botonCasillero_15, " 2, 5");
	        botonCasillero_15.putClientProperty("id", "1,5");
	        
	        JButton botonCasillero_16 = new JButton();
	        panelTablero.add(botonCasillero_16, " 2, 4");
	        botonCasillero_16.putClientProperty("id", "1,6");
	        
	        JButton botonCasillero_17 = new JButton();
	        panelTablero.add(botonCasillero_17, " 2, 3");
	        botonCasillero_17.putClientProperty("id", "1,7");
	        
	        JButton botonCasillero_18 = new JButton();
	        panelTablero.add(botonCasillero_18, " 2, 2");
	        botonCasillero_18.putClientProperty("id", "1,8");
	        
	        JButton botonCasillero_19 = new JButton();
	        panelTablero.add(botonCasillero_19, " 2, 1");
	        botonCasillero_19.putClientProperty("id", "1,9");
	        
	        JButton botonCasillero_20 = new JButton();
	        panelTablero.add(botonCasillero_20, " 3, 10");
	        botonCasillero_20.putClientProperty("id", "2,0");
	        
	        JButton botonCasillero_21 = new JButton();
	        panelTablero.add(botonCasillero_21, " 3, 9");
	        botonCasillero_21.putClientProperty("id", "2,1");
	        
	        JButton botonCasillero_22 = new JButton();
	        panelTablero.add(botonCasillero_22, " 3, 8");
	        botonCasillero_22.putClientProperty("id", "2,2");
	        
	        JButton botonCasillero_23 = new JButton();
	        panelTablero.add(botonCasillero_23, " 3, 7");
	        botonCasillero_23.putClientProperty("id", "2,3");
	        
	        JButton botonCasillero_24 = new JButton();
	        panelTablero.add(botonCasillero_24, " 3, 6");
	        botonCasillero_24.putClientProperty("id", "2,4");
	       
	        JButton botonCasillero_25 = new JButton();
	        panelTablero.add(botonCasillero_25, " 3, 5");
	        botonCasillero_25.putClientProperty("id", "2,5");
	        
	        JButton botonCasillero_26 = new JButton();
	        panelTablero.add(botonCasillero_26, " 3, 4");
	        botonCasillero_26.putClientProperty("id", "2,6");
	        
	        JButton botonCasillero_27 = new JButton();
	        panelTablero.add(botonCasillero_27, " 3, 3");
	        botonCasillero_27.putClientProperty("id", "2,7");
	        
	        JButton botonCasillero_28 = new JButton();
	        panelTablero.add(botonCasillero_28, " 3, 2");
	        botonCasillero_28.putClientProperty("id", "2,8");
	        
	        JButton botonCasillero_29 = new JButton();
	        panelTablero.add(botonCasillero_29, " 3, 1");
	        botonCasillero_29.putClientProperty("id", "2,9");
	        
	        JButton botonCasillero_30 = new JButton();
	        panelTablero.add(botonCasillero_30, " 4, 10");
	        botonCasillero_30.putClientProperty("id", "3,0");
	        
	        JButton botonCasillero_31 = new JButton();
	        panelTablero.add(botonCasillero_31, " 4, 9");
	        botonCasillero_31.putClientProperty("id", "3,1");
	        
	        JButton botonCasillero_32 = new JButton();
	        panelTablero.add(botonCasillero_32, " 4, 8");
	        botonCasillero_32.putClientProperty("id", "3,2");
	        
	        JButton botonCasillero_33 = new JButton();
	        panelTablero.add(botonCasillero_33, " 4, 7");
	        botonCasillero_33.putClientProperty("id", "3,3");
	        
	        JButton botonCasillero_34 = new JButton();
	        panelTablero.add(botonCasillero_34, " 4, 6");
	        botonCasillero_34.putClientProperty("id", "3,4");
	        
	        JButton botonCasillero_35 = new JButton();
	        panelTablero.add(botonCasillero_35, " 4, 5");
	        botonCasillero_35.putClientProperty("id", "3,5");
	        
	        JButton botonCasillero_36 = new JButton();
	        panelTablero.add(botonCasillero_36, " 4, 4");
	        botonCasillero_36.putClientProperty("id", "3,6");
	        
	        JButton botonCasillero_37 = new JButton();
	        panelTablero.add(botonCasillero_37, " 4, 3");
	        botonCasillero_37.putClientProperty("id", "3,7");
	        
	        JButton botonCasillero_38 = new JButton();
	        panelTablero.add(botonCasillero_38, " 4, 2");
	        botonCasillero_38.putClientProperty("id", "3,8");
	        
	        JButton botonCasillero_39 = new JButton();
	        panelTablero.add(botonCasillero_39, " 4, 1");
	        botonCasillero_39.putClientProperty("id", "3,9");
	        //
	        JButton botonCasillero_40 = new JButton();
	        panelTablero.add(botonCasillero_40, " 5,10");
	        botonCasillero_40.putClientProperty("id", "4,0");
	        
	        JButton botonCasillero_41 = new JButton();
	        panelTablero.add(botonCasillero_41, " 5,9");
	        botonCasillero_41.putClientProperty("id", "4,1");
	        
	        JButton botonCasillero_42 = new JButton();
	        panelTablero.add(botonCasillero_42, " 5,8");
	        botonCasillero_42.putClientProperty("id", "4,2");
	        
	        JButton botonCasillero_43 = new JButton();
	        panelTablero.add(botonCasillero_43, " 5,7");
	        botonCasillero_43.putClientProperty("id", "4,3");
	        
	        JButton botonCasillero_44 = new JButton();
	        panelTablero.add(botonCasillero_44, " 5,6");
	        botonCasillero_44.putClientProperty("id", "4,4");
	        
	        JButton botonCasillero_45 = new JButton();
	        panelTablero.add(botonCasillero_45, " 5,5");
	        botonCasillero_45.putClientProperty("id", "4,5");
	        
	        JButton botonCasillero_46 = new JButton();
	        panelTablero.add(botonCasillero_46, " 5,4");
	        botonCasillero_46.putClientProperty("id", "4,6");
	        
	        JButton botonCasillero_47 = new JButton();
	        panelTablero.add(botonCasillero_47, " 5,3");
	        botonCasillero_47.putClientProperty("id", "4,7");
	        
	        JButton botonCasillero_48 = new JButton();
	        panelTablero.add(botonCasillero_48, " 5,2");
	        botonCasillero_48.putClientProperty("id", "4,8");
	        
	        JButton botonCasillero_49 = new JButton();
	        panelTablero.add(botonCasillero_49, " 5,1");
	        botonCasillero_49.putClientProperty("id", "4,9");
	        //
	        JButton botonCasillero_50 = new JButton();
	        panelTablero.add(botonCasillero_50, " 6, 10");
	        botonCasillero_50.putClientProperty("id", "5,0");
	        
	        JButton botonCasillero_51 = new JButton();
	        panelTablero.add(botonCasillero_51, " 6, 9");
	        botonCasillero_51.putClientProperty("id", "5,1");
	        
	        JButton botonCasillero_52 = new JButton();
	        panelTablero.add(botonCasillero_52, " 6, 8");
	        botonCasillero_52.putClientProperty("id", "5,2");
	        
	        JButton botonCasillero_53 = new JButton();
	        panelTablero.add(botonCasillero_53, " 6, 7");
	        botonCasillero_53.putClientProperty("id", "5,3");
	        
	        JButton botonCasillero_54 = new JButton();
	        panelTablero.add(botonCasillero_54, " 6, 6");
	        botonCasillero_54.putClientProperty("id", "5,4");
	        
	        JButton botonCasillero_55 = new JButton();
	        panelTablero.add(botonCasillero_55, " 6, 5");
	        botonCasillero_55.putClientProperty("id", "5,5");
	        
	        JButton botonCasillero_56 = new JButton();
	        panelTablero.add(botonCasillero_56, " 6, 4");
	        botonCasillero_56.putClientProperty("id", "5,6");
	        
	        JButton botonCasillero_57 = new JButton();
	        panelTablero.add(botonCasillero_57, " 6, 3");
	        botonCasillero_57.putClientProperty("id", "5,7");
	        
	        JButton botonCasillero_58 = new JButton();
	        panelTablero.add(botonCasillero_58, " 6, 2");
	        botonCasillero_58.putClientProperty("id", "5,8");
	        
	        JButton botonCasillero_59 = new JButton();
	        panelTablero.add(botonCasillero_59, " 6, 1");
	        botonCasillero_59.putClientProperty("id", "5,9");
	        //
	        JButton botonCasillero_60 = new JButton();
	        panelTablero.add(botonCasillero_60, " 7, 10");
	        botonCasillero_60.putClientProperty("id", "6,0");
	        
	        JButton botonCasillero_61 = new JButton();
	        panelTablero.add(botonCasillero_61, " 7, 9");
	        botonCasillero_61.putClientProperty("id", "6,1");
	        
	        JButton botonCasillero_62 = new JButton();
	        panelTablero.add(botonCasillero_62, " 7, 8");
	        botonCasillero_62.putClientProperty("id", "6,2");
	        
	        JButton botonCasillero_63 = new JButton();
	        panelTablero.add(botonCasillero_63, " 7, 7");
	        botonCasillero_63.putClientProperty("id", "6,3");
	        
	        JButton botonCasillero_64 = new JButton();
	        panelTablero.add(botonCasillero_64, " 7, 6");
	        botonCasillero_64.putClientProperty("id", "6,4");
	        
	        JButton botonCasillero_65 = new JButton();
	        panelTablero.add(botonCasillero_65, " 7, 5");
	        botonCasillero_65.putClientProperty("id", "6,5");
	        
	        JButton botonCasillero_66 = new JButton();
	        panelTablero.add(botonCasillero_66, " 7, 4");
	        botonCasillero_66.putClientProperty("id", "6,6");
	        
	        JButton botonCasillero_67 = new JButton();
	        panelTablero.add(botonCasillero_67, " 7, 3");
	        botonCasillero_67.putClientProperty("id", "6,7");
	        
	        JButton botonCasillero_68 = new JButton();
	        panelTablero.add(botonCasillero_68, " 7, 2");
	        botonCasillero_68.putClientProperty("id", "6,8");
	        
	        JButton botonCasillero_69 = new JButton();
	        panelTablero.add(botonCasillero_69, " 7, 1");
	        botonCasillero_69.putClientProperty("id", "6,9");
	        //
	        JButton botonCasillero_70 = new JButton();
	        panelTablero.add(botonCasillero_70, " 8, 10");
	        botonCasillero_70.putClientProperty("id", "8,0");
	        
	        JButton botonCasillero_71 = new JButton();
	        panelTablero.add(botonCasillero_71, " 8, 9");
	        botonCasillero_71.putClientProperty("id", "8,1");
	        
	        JButton botonCasillero_72 = new JButton();
	        panelTablero.add(botonCasillero_72, " 8, 8");
	        botonCasillero_72.putClientProperty("id", "8,2");
	        
	        JButton botonCasillero_73 = new JButton();
	        panelTablero.add(botonCasillero_73, " 8, 7");
	        botonCasillero_73.putClientProperty("id", "8,3");
	        
	        JButton botonCasillero_74 = new JButton();
	        panelTablero.add(botonCasillero_74, " 8, 6");
	        botonCasillero_74.putClientProperty("id", "8,4");
	        
	        JButton botonCasillero_75 = new JButton();
	        panelTablero.add(botonCasillero_75, " 8, 5");
	        botonCasillero_75.putClientProperty("id", "8,5");
	        
	        JButton botonCasillero_76 = new JButton();
	        panelTablero.add(botonCasillero_76, " 8, 4");
	        botonCasillero_76.putClientProperty("id", "8,6");
	        
	        JButton botonCasillero_77 = new JButton();
	        panelTablero.add(botonCasillero_77, " 8, 3");
	        botonCasillero_77.putClientProperty("id", "8,7");
	        
	        JButton botonCasillero_78 = new JButton();
	        panelTablero.add(botonCasillero_78, " 8, 2");
	        botonCasillero_78.putClientProperty("id", "8,8");
	        
	        JButton botonCasillero_79 = new JButton();
	        panelTablero.add(botonCasillero_79, " 8, 1");
	        botonCasillero_79.putClientProperty("id", "8,9");
	        //
	        JButton botonCasillero_80 = new JButton();
	        panelTablero.add(botonCasillero_80, " 9, 10");
	        botonCasillero_80.putClientProperty("id", "8,0");
	        
	        JButton botonCasillero_81 = new JButton();
	        panelTablero.add(botonCasillero_81, " 9, 9");
	        botonCasillero_81.putClientProperty("id", "8,1");
	        
	        JButton botonCasillero_82 = new JButton();
	        panelTablero.add(botonCasillero_82, " 9, 8");
	        botonCasillero_82.putClientProperty("id", "8,2");
	        
	        JButton botonCasillero_83 = new JButton();
	        panelTablero.add(botonCasillero_83, " 9, 7");
	        botonCasillero_83.putClientProperty("id", "8,3");
	        
	        JButton botonCasillero_84 = new JButton();
	        panelTablero.add(botonCasillero_84, " 9, 6");
	        botonCasillero_84.putClientProperty("id", "8,4");
	        
	        JButton botonCasillero_85 = new JButton();
	        panelTablero.add(botonCasillero_85, " 9, 5");
	        botonCasillero_85.putClientProperty("id", "8,5");
	        
	        JButton botonCasillero_86 = new JButton();
	        panelTablero.add(botonCasillero_86, " 9, 4");
	        botonCasillero_86.putClientProperty("id", "8,6");
	        
	        JButton botonCasillero_87 = new JButton();
	        panelTablero.add(botonCasillero_87, " 9, 3");
	        botonCasillero_87.putClientProperty("id", "8,7");
	        
	        JButton botonCasillero_88 = new JButton();
	        panelTablero.add(botonCasillero_88, " 9, 2");
	        botonCasillero_88.putClientProperty("id", "8,8");
	        
	        JButton botonCasillero_89 = new JButton();
	        panelTablero.add(botonCasillero_89, " 9, 1");
	        botonCasillero_89.putClientProperty("id", "8,9");
	        //
	        JButton botonCasillero_90 = new JButton();
	        panelTablero.add(botonCasillero_90, " 10, 10");
	        botonCasillero_90.putClientProperty("id", "9,0");
	        
	        JButton botonCasillero_91 = new JButton();
	        panelTablero.add(botonCasillero_91, " 10, 9");
	        botonCasillero_91.putClientProperty("id", "9,1");
	        
	        JButton botonCasillero_92 = new JButton();
	        panelTablero.add(botonCasillero_92, " 10, 8");
	        botonCasillero_92.putClientProperty("id", "9,2");
	        
	        JButton botonCasillero_93 = new JButton();
	        panelTablero.add(botonCasillero_93, " 10, 7");
	        botonCasillero_93.putClientProperty("id", "9,3");
	        
	        JButton botonCasillero_94 = new JButton();
	        panelTablero.add(botonCasillero_94, " 10, 6");
	        botonCasillero_94.putClientProperty("id", "9,4");
	        
	        JButton botonCasillero_95 = new JButton();
	        panelTablero.add(botonCasillero_95, " 10, 5");
	        botonCasillero_95.putClientProperty("id", "9,5");
	        
	        JButton botonCasillero_96 = new JButton();
	        panelTablero.add(botonCasillero_96, " 10, 4");
	        botonCasillero_96.putClientProperty("id", "9,6");
	        
	        JButton botonCasillero_97 = new JButton();
	        panelTablero.add(botonCasillero_97, " 10, 3");
	        botonCasillero_97.putClientProperty("id", "9,7");
	        
	        JButton botonCasillero_98 = new JButton();
	        panelTablero.add(botonCasillero_98, " 10, 2");
	        botonCasillero_98.putClientProperty("id", "9,8");
	        
	        JButton botonCasillero_99 = new JButton();
	        panelTablero.add(botonCasillero_99, " 10, 1");
	        botonCasillero_99.putClientProperty("id", "9,9");

	        
	    
	        
	        //Hace que los botones de casillero sean transparentes
	        //y les agregro su listener
	        Component[] componentes =panelTablero.getComponents(); 
	        for(int i=0; i<componentes.length;i++) 
	        { 
	        	((JButton)componentes[i]).setContentAreaFilled(false);
				((JButton)componentes[i]).addActionListener(controladorBotonesTablero);
	        } 
	        
	        
	        frameBatalla.getContentPane().add(panelLista);
	        GridBagLayout gbl_panel_2 = new GridBagLayout();
	        gbl_panel_2.columnWidths = new int[]{0, 89, 22, 0};
	        gbl_panel_2.rowHeights = new int[]{23, 21, 0, 0, 0, 0};
	        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
	        panelLista.setLayout(gbl_panel_2);
	        
	        //Municiones
	        listaMuniciones.setSelectedIndex(0);
	        listaMuniciones.setModel(new AbstractListModel() {
	        	private static final long serialVersionUID = 1L;
				String[] values = new String[] {"Disparo Convencional",
						"Mina Por Contacto","Mina Puntual Con Retardo",
						"Mina Doble Con Retardo", "Mina Triple Con Retardo"};
				
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

