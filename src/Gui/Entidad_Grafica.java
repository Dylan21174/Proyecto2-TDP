package Gui;
import javax.swing.ImageIcon;

public class Entidad_Grafica {
	
	
	private ImageIcon grafico;
	private String[] imagenes;
	
	/**
	 * Inicializa la entidad Grafica, y un arreglo que contiene las imagenes a utilizar en la misma.
	 */
	public Entidad_Grafica() {
		
		this.grafico = new ImageIcon();
		this.imagenes = new String[] {"/imgs/0.png","/imgs/1.png","/imgs/2.png","/imgs/3.png","/imgs/4.png","/imgs/5.png","/imgs/6.png","/imgs/7.png",
									  "/imgs/8.png","/imgs/9.png"};
		}
	
	/**
	 * Actualiza la imagen de la entidad.
	 * @param indice Indice del arreglo de imagenes para establecer.
	 */
	public void actualizar(Integer indice) {
		
			if(indice < this.imagenes.length) {			
				
				ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
				this.grafico.setImage(imageIcon.getImage());	
		}
	}

	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}

}
