package logica;

import java.util.ArrayList;

public class Logica {

	/**
	 * Metodo principal. Verifica que se cumplan las reglas del juego.
	 * @param tablero Tablero que contiene las celdas del juego
	 * @return True = Sudoku Solucionado. False = Caso contrario.
	 */
	public boolean Main(Tablero tablero) {

		boolean esValido = true;
		int i = 0;
		ArrayList<Celda> celdas = ArregloCeldas(tablero.getTablero()); //Arreglo con todos los elementos del tablero


		while(i < celdas.size() && esValido) {

			if(celdas.get(i).getValor() == 0)
				esValido = false;
			else {

				ArrayList<ArrayList<Celda>> listasParaValidar = this.CeldasComparables(tablero, celdas.get(i));	//Arreglo que contiene la fila, columna y sector de la celda	
				esValido = Valido(listasParaValidar); //Se valida que no se repitan elementos
			}

			i++;
		}

		return esValido;	

	}



	/**
	 * Metodo privado que almacena los elementos de la matriz en un arreglo dinamico.
	 * @param celdas Tablero matriz.
	 * @return Arreglo con las celdas editables.*/

	public ArrayList<Celda> ArregloCeldas(Celda[][] celdas){

		ArrayList<Celda> arreglo = new ArrayList<Celda>();

		for(int i = 0; i < celdas.length; i++) {

			for(int j = 0; j < celdas[i].length; j++ ) {

				arreglo.add(celdas[i][j]);
			}		
		}	
		return arreglo;
	}

	/**
	 * Crea un Arreglo de arreglos, que contiene las celdas con la misma fila, columna, y sector a la celda actual.
	 * @param tablero
	 * @param cActual Celda Actual	
	 * @return
	 */
	public ArrayList<ArrayList<Celda>> CeldasComparables(Tablero tablero, Celda cActual){

		ArrayList<ArrayList<Celda>> to_return = new ArrayList<ArrayList<Celda>>();

		ArrayList<Celda> fila = new ArrayList<Celda>();
		ArrayList<Celda> columna = new ArrayList<Celda>();
		ArrayList<Celda> sector = new ArrayList<Celda>();

		for(int i = 0; i < tablero.getTablero().length; i++) {

			for(int j = 0; j < tablero.getTablero()[i].length; j++) {

				if (tablero.getTablero()[i][j].getPosF() == cActual.getPosF()) {
					fila.add(tablero.getTablero()[i][j]);
				}


				if (tablero.getTablero()[i][j].getPosC() == cActual.getPosC()) {
					columna.add(tablero.getTablero()[i][j]);
				}

				if (tablero.getTablero()[i][j].getSector() == cActual.getSector()) {
					sector.add(tablero.getTablero()[i][j]);
				}

			}
		}

		to_return.add(fila);
		to_return.add(columna);
		to_return.add(sector);

		return to_return;

	}

	/**
	 * Valida si no se repite ningun elemento en las filas, columnas y el sector.
	 * @param arreglos Arreglo de arreglos (fila, columna y sector)
	 * @return Verdadero si no se repiten, falso en caso contrario.
	 */
	public boolean Valido(ArrayList<ArrayList<Celda>> arreglos) {

		for(ArrayList<Celda> arreglo : arreglos) {

			ArrayList<Integer> valores = new ArrayList<Integer>();

			for(Celda celda : arreglo) {

				if (celda.getValor() != 0) {

					if(valores.contains(celda.getValor())) {
						return false;
					}
					else valores.add(celda.getValor());
				}	
			}	

		}	

		return true;
	}

	/**
	 * Deshabilita todas las celdas del tablero a excepcion de la celda recibida como parametro.
	 * @param t Tablero del juego.
	 * @param c Celda incorrecta que debe modificarse.
	 * @return Devuelve un arreglo dinamico que contiene las celdas no editables del juego.
	 */
	public ArrayList<Celda> deshabilitar(Tablero t, Celda c) {

		Celda[][] tablero = t.getTablero();
		ArrayList<Celda> deshabilitados = new ArrayList<Celda>();

		for(int i = 0; i < tablero.length; i++) {

			for(int j = 0; j < tablero[i].length; j++ ) {

				if(tablero[i][j].editable() && tablero[i][j] != c) {
					tablero[i][j].notEditable();

				}
				else if(!tablero[i][j].editable()) {
					deshabilitados.add(tablero[i][j]);		

				}
			}		
		}	

		return deshabilitados;
	}

	/**
	 * Habilita nuevamente las celdas del juego, una vez solucionado el error.
	 * @param desh Arreglo dinamico que contiene las celdas no editables del juego.
	 * @param juego Tablero que contiene la matriz de celdas.
	 */
	public void habilitar(ArrayList<Celda> desh, Tablero juego) {


		ArrayList<Celda> total = ArregloCeldas(juego.getTablero());
		int i = 0; int j;
		boolean noEsta;

		while(i < total.size()) {
			j = 0;
			noEsta = true;

			while(j < desh.size() && noEsta) {

				if(total.get(i) == desh.get(j))
					noEsta = false;

				else if((j == desh.size() - 1) && noEsta) {
					total.get(i).setEditable();

				}
				j++; 
			}	
			i++;			
		}		
	}
}