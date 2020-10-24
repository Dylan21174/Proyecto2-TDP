package logica;

import Gui.Entidad_Grafica;

public class Celda {

	private Integer valor;
	private int posF,posC;
	private int sector;
	private Entidad_Grafica entidadGrafica;
	boolean esEditable;

	/**
	 * Inicializa una celda vacia y no editable.
	 */
	public Celda() {
		this.valor = null;
		this.entidadGrafica = new Entidad_Grafica();
		esEditable = false;
	}

	/**
	 * Actualiza el valor de la celda, en un rango entre 0 y 9.
	 */
	public void actualizar() {
		if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
			this.valor++;
		}else {
			this.valor = 0;
		}
		entidadGrafica.actualizar(this.valor);
	}


	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}


	/**
	 * Retorna el valor de la Celda.
	 * @return Valor numerico que contiene la celda.
	 */
	public Integer getValor() {
		return this.valor;
	}

	/**
	 * Establece la posicion a la que pertenece la celda en al Matriz.
	 * @param f Posicion en fila.
	 * @param c Posicion en columna.
	 */
	public void setPos(int f, int c) {

		posF = f;
		posC = c;
	}
	
	/**
	 * Devuelve la posicion vertical de la matriz.
	 * @return Posicion de la fila de la celda.
	 */
	public int getPosF() {
		return posF;
	}
	/**
	 * Devuelve la posicion horizontal de la matriz.
	 * @return Posicion de la columna de la celda.
	 */
	public int getPosC() {
		return posC;
	}

	/**
	 * Establece el valor de la celda, actualizando su entidad Grafica.
	 * @param valor Valor entero de la celda.
	 */
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
			
		}else {
			this.valor = null;
			
		}
	}

	/**
	 * Retorna la entidad Grafica asociada a la celda.
	 * @return la Entidad Grafica.
	 */
	public Entidad_Grafica getEntidadGrafica() {
		return this.entidadGrafica; 
	}

	/**
	 * Establece la entidad Grafica asociada a la celda.
	 * @param g Entidad Grafica de la celda.
	 */
	public void setGrafica(Entidad_Grafica g) {
		this.entidadGrafica = g;
	}

	/**
	 *  Establece el sector al que pertenece la celda, dependiendo de su posicion en el tablero.
	 *  Cada sector se divide en una submatriz de 3x3.
	 */
	public void setSector() {
		
		switch(posF) {

		case 0:
		case 1:
		case 2:
			switch(posC) {
			case 0:
			case 1:
			case 2:
				sector = 1;
				break;
			case 3:
			case 4:
			case 5:
				sector = 2;
				break;
			case 6:
			case 7: 
			case 8:
				sector = 3;
				break;
			default:
				break;
			}
			break;
		case 3:
		case 4:
		case 5:
			switch(posC) {
			case 0:
			case 1:
			case 2:
				sector = 4;
				break;
			case 3:
			case 4:
			case 5:
				sector = 5;
				break;
			case 6:
			case 7: 
			case 8:
				sector = 6;
				break;
			default:
				break;
			}
			break;
		case 6:
		case 7:
		case 8:
			switch(posC) {
			case 0:
			case 1:
			case 2:
				sector = 7;
				break;
			case 3:
			case 4:
			case 5:
				sector = 8;
				break;
			case 6:
			case 7: 
			case 8:
				sector = 9;
				break;
			default:
				break;
			}
			break;
		}

	}
	/**
	 * Retorna el sector al que pertenece la celda.
	 * @return Sector de la celda.
	 */
	public int getSector() {
		return sector;
	}
	/**
	 * Metodo que determina si la celda es editable en el tablero o no.
	 * @return True = Es editable, False caso contrario.
	 */
	public boolean editable() {		
		return esEditable;
	}
	/**
	 * Establece la celda como editable.
	 */
	public void setEditable() {
		this.esEditable = true;
	}
	/**
	 * Inhabilita la celda, siendo no editable.
	 */
	public void notEditable() {
		this.esEditable = false;
	}
}
