package Gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerR extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer t;
	private int m, s,h; 
	private JLabel horasD, horasU, minD, minU, segD, segU, sp1, sp2;
	protected ImageIcon[] imagenes;



	public TimerR() {

		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

		sp1 = new JLabel();	//Separadores
		sp2 = new JLabel();

		horasD = new JLabel();	//Digitos horas
		horasU = new JLabel();

		minD = new JLabel();	//Digitos minutos
		minU = new JLabel();

		segD = new JLabel();	//Digitos segundos
		segU = new JLabel();

		imagenes = setImagenes();
		establecerPanel();		//Agrega los digitos al panel

		m = s = h = 0;		

		t = new Timer(1000, (ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if(s < 59) {
					++s;
				}

				else if(s == 59) {
					s= 0;
					m++;
				}
				else if(m == 59){
					m = 0;
					s = 0;
					++h;
				}

				actualizarLabel();				
			}

		});	

	}
	
	private void actualizarLabel() {


		segU.setIcon((this.imagenes[s%10]));
		segD.setIcon(this.imagenes[s/10]);

		ImageIcon imageIconSp1 = new ImageIcon(this.getClass().getResource("/imgs2/separador.png"));
		sp1.setIcon(imageIconSp1);

		minU.setIcon((this.imagenes[m%10]));		
		minD.setIcon(this.imagenes[m/10]);

		ImageIcon imageIconSp2 = new ImageIcon(this.getClass().getResource("/imgs2/separador.png"));
		sp2.setIcon(imageIconSp2);

		horasU.setIcon(this.imagenes[h%10]);		
		horasD.setIcon(this.imagenes[h/10]);	

	}


	public void establecerPanel() {

		setLayout(new GridLayout(0,8,0,0));

		this.add(horasD);
		this.add(horasU);
		this.add(sp1);
		this.add(minD);
		this.add(minU);
		this.add(sp2);
		this.add(segD);
		this.add(segU);


	}

	/**
	 * Crea un arreglo de imagenes para los digitos
	 * @return Arreglo de imagenes
	 */
	public ImageIcon[] setImagenes() {		

		ImageIcon[] imagenes = new ImageIcon[10];

		for(int i=0; i < 10; i++)
			imagenes[i] = new ImageIcon(getClass().getResource("/imgs2/"+i+".png"));

		return imagenes;
	}

	public void start() {
		t.start();
	}

	public void stop() {
		t.stop();
	}

	public void restart() {
		s = 0;
		m = 0;
		h = 0;
		t.stop();

	}

	public int getS() {
		return s;
	}

	public int getM() {
		return m;
	}

	public int getH() {
		return h;
	}
}
