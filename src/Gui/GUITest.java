package Gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.Celda;
import logica.Logica;
import logica.Tablero;


import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GUITest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel2;
	private Tablero juego;

	private JButton btnComenzar, btnValidar;
	private ActionListener actionComenzar, actionSolucionar;

	private Logica solucionador;
	private JLabel lblNewLabel;

	private TimerR reloj;

	private ArrayList<Celda> deshabilitadas = new ArrayList<Celda>();
	private boolean habilitado = true;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITest frame = new GUITest();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});;
	}

	/**
	 * Create the application.
	 */
	public GUITest() {

		inicializarTablero();
		crearActionListeners();	
		inicializarGui();

	}
	
	/**
	 * Crea el juego que contiene el tablero con la informacion del archivo recibido.
	 */
	protected void inicializarTablero() {
		
		juego = new Tablero();
		try {
			juego.Inicializar_Tablero("src\\imgs\\Tablero.txt", ",");
		} catch (IOException e1) {}

		solucionador = new Logica();

		//Compruebo que el archivo con la solucion sea valida, informando con un dialogo en caso de que no lo sea
		if(solucionador.Main(juego))	
			juego.eliminar_Algunas();
		else {
			JOptionPane.showConfirmDialog(new JFrame(),"El archivo inicial de lectura no corresponde","Dialog",JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			System.exit(0);	
		}
		
		
	}

	/**
	 * Inicializa la GUI e invoca al metodo crearPanel().
	 */
	private void inicializarGui() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 650, 500);	
		getContentPane().setLayout(new BorderLayout());
		crearPanel();

	}

	/**
	 * Inicializa el Panel izquierdo que contiene el tablero, y el panel derecho que contiene los botones, el Timer y el logo.
	 * Invoca otros metodos privados para hacerlo.
	 */
	private void crearPanel() {

		//JPanel Izquierdo
		contentPane = new JPanel();	
		contentPane.setBorder(new EmptyBorder(2, 2, 1, 1));
		contentPane.setLayout(new GridLayout(9, 9, 1, 1));



		//JPanel Derecho
		panel2 = new JPanel();	
		GridBagLayout gbl_panel2 = new GridBagLayout();
		gbl_panel2.columnWidths = new int[]{208, 0};
		gbl_panel2.rowHeights = new int[]{208, 25, 25, 0, 0, 0, 0, 0};
		gbl_panel2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel2.setLayout(gbl_panel2);


		crearLogo();
		crearBotones();
		actualizarTablero();
		crearTimer();

		//Agregar al contenedor//
		getContentPane().add(contentPane,BorderLayout.CENTER);
		getContentPane().add(panel2,BorderLayout.EAST);

	}

	/**
	 * Incializa el logo en el Panel2.
	 */
	private void crearLogo() {

		ImageIcon icon = new ImageIcon("src\\imgs2\\Logo.png");
		icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, BufferedImage.SCALE_SMOOTH));


		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(icon); 
		lblNewLabel.setBorder(new EmptyBorder(4, 4, 4, 4));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel2.add(lblNewLabel, gbc_lblNewLabel);


	}

	/**
	 * Crea el boton para Comenzar el juego, y para Validar el resultado del mismo.
	 */
	private void crearBotones() {
		Font fuente = new Font("Tahoma", Font.BOLD, 14);

		//Boton Comenzar//
		btnComenzar = new JButton("Comenzar");
		btnComenzar.setFont(fuente);	
		btnComenzar.setBounds(340, 296, 162, 23);
		btnComenzar.addActionListener(this.actionComenzar);


		GridBagConstraints gbc_btnComenzar = new GridBagConstraints();
		gbc_btnComenzar.anchor = GridBagConstraints.WEST;
		gbc_btnComenzar.insets = new Insets(-30, 50, 5, 0);
		gbc_btnComenzar.gridx = 0;
		gbc_btnComenzar.gridy = 4;
		panel2.add(btnComenzar, gbc_btnComenzar);


		//Boton Validar//
		btnValidar = new JButton("Validar");
		btnValidar.setFont(fuente);	
		btnValidar.setBounds(340, 296, 400, 23);
		btnValidar.addActionListener(this.actionSolucionar);
		btnValidar.setEnabled(false); //Inhabilitado hasta que se comience a jugar

		GridBagConstraints gbc_btnValidar = new GridBagConstraints();
		gbc_btnValidar.anchor = GridBagConstraints.WEST;
		gbc_btnValidar.insets = new Insets(20, 62, 5, 0);
		gbc_btnValidar.gridx = 0;
		gbc_btnValidar.gridy = 5;
		panel2.add(btnValidar, gbc_btnValidar);

	}


	/**
	 * Crea los ActionListeners de los botones.
	 */
	private void crearActionListeners() {


		//Boton Comenzar
		this.actionComenzar = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				reloj.start();
				reloj.setVisible(true);

				btnComenzar.setEnabled(false);
				btnValidar.setEnabled(true);	
			}						
		};

		//Boton Validar
		this.actionSolucionar = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				btnComenzar.setEnabled(true);
				boolean solucion = solucionador.Main(juego);  //Compruebo que la solucion sea valida
				reloj.stop();
				
				if(!solucion) { //Solucion no valida
					
					JOptionPane.showMessageDialog(new JPanel(),"La solucion es incorrecta");	
					
					btnComenzar.setText("Reanudar");	

				}
				else {		//Solucion valida

					int seleccion = JOptionPane.showOptionDialog(
							new JPanel(),
							"Felicitaciones, usted ha ganado."
							+ " Tiempo total: "+(reloj.getH()<=9?"0":"")+reloj.getH()+":"+(reloj.getM()<=9?"0":"")+reloj.getM()+":"+(reloj.getS()<=9?"0":"")+reloj.getS(),	
							null,
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							new Object[] { "Volver a jugar", "Salir"},
							null);
									
					if(seleccion == 0){ //Reinicia el juego una vez ganado
						
						contentPane.removeAll();
						contentPane.updateUI();
						juego.eliminar_Algunas();
						actualizarTablero();
					}
					
					if(seleccion == 1)	
						System.exit(0);	

					reloj.restart();
					btnComenzar.setText("Comenzar");	
				}

			}			

		};		

	}

	/**
	 * Inicializa el timer.
	 */
	private void crearTimer() {

		reloj = new TimerR();
		reloj.setVisible(false);
		GridBagConstraints gbc_reloj = new GridBagConstraints();
		gbc_reloj.anchor = GridBagConstraints.WEST;
		gbc_reloj.insets = new Insets(20, 20, 20, 0);
		gbc_reloj.gridx = 0;
		gbc_reloj.gridy = 6;
		panel2.add(reloj, gbc_reloj);	


	}

	/**
	 * Inicializa cada una de las celdas con su imagen correspondiente.
	 */
	private void actualizarTablero() {


		for (int i = 0; i <juego.getCantFilas(); i++) {

			for(int j =0; j<juego.getCantFilas(); j++) {

				Celda c = juego.getCelda(i,j);
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				pintarSector(label,c);

				contentPane.add(label);

				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});

				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						if(c.editable() && (!btnComenzar.isEnabled())) {
							juego.accionar(c);

							if(!solucionador.Valido(solucionador.CeldasComparables(juego, c))) {	//Si el valor no es valido, se deshabilita el
																					//resto de las celdas y se remarca el error
								if(habilitado) {								
									label.setBorder(new LineBorder(Color.RED, 3, true));
									habilitado = false;
									deshabilitadas = solucionador.deshabilitar(juego, c);									
								}													
							}					
							else {
								if(!habilitado) {			//Si se corrige el error, se habilitan nuevamente las celdas
																								
									solucionador.habilitar(deshabilitadas, juego);
									habilitado = true;
									pintarSector(label,c);	
								}

							}

							reDimensionar(label,grafico);

						}
					}
				});
			}
		}
	}

	private void reDimensionar(JLabel label, ImageIcon grafico) {

		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}

	/**
	 * Pinta el borde de cada celda con el color correspondiente segun su sector. 
	 * @param label Componente grafico que representa a la celda.
	 * @param c Celda a pintar.
	 */
	private void pintarSector(JLabel label, Celda c) {

		if(c.getSector() == 1 || c.getSector() == 3 || c.getSector() == 5 || c.getSector() == 7 || c.getSector() == 9) {
			label.setBorder(new LineBorder(Color.RED, 1, true));
		}
		else if(c.getSector() == 2 || c.getSector() == 4 || c.getSector() == 6 || c.getSector() == 8 ) {
			label.setBorder(new LineBorder(Color.BLUE, 1, true));
		}

	}	
}