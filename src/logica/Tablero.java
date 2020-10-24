package logica;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class Tablero {

	private BufferedReader bufferLectura;	
	private Celda[][] tablero;
	private int cantFilas;

	/**
	 * Inicializa el tablero de 9x9 estableciendo los valores iniciales pertenecientes al archivo leido.
	 * Cada celda pertenece a un sector, que es una submatriz de 3x3.
	 * @param ruta Ruta que contiene al archivo con la solucion.
	 * @param separador Separador de los numeros en el archivo.
	 */
	public Tablero() {
		this.cantFilas = 9;
		tablero = new Celda[9][9];
		this.bufferLectura = null;

		for (int i =0; i<cantFilas; i++) {
			for (int j =0; j<cantFilas; j++) {

				tablero[i][j] = new Celda();
				tablero[i][j].setValor(0);	
				tablero[i][j].setPos(i, j);
				tablero[i][j].setSector();
			}
		}	
	}

	public void Inicializar_Tablero(String ruta, String separador) throws IOException, NumberFormatException {

		int i = 0;
		this.bufferLectura 	= new BufferedReader(new FileReader(ruta));
		String linea = bufferLectura.readLine();

		while (linea != null) {
			// Salto de linea en la matriz

			// Separa la línea leída con el separador definido previamente
			String[] campos = linea.split(separador);

			// Establece en la matriz los resultados
			int j = 0;

			for (String num : campos) {

				tablero[i][j].setValor(Integer.parseInt(num));
				

				j++;
			}	
			i++;

			linea = this.bufferLectura.readLine();
		}

		// Se cierra el buffer
		if (this.bufferLectura != null) {
			bufferLectura.close();
		}
	}

	/**
	 * Actualiza el valor de la celda llamando al metodo actualizar().
	 * @param c Celda a modificar
	 */
	public void accionar(Celda c) {
		c.actualizar();
	}

	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}

	public int getCantFilas() {
		return this.cantFilas;
	}

	public Celda[][] getTablero() {
		return this.tablero;
	}

	/**
	 * Elimina de forma aleatoria algunos elementos del tablero para dar comienzo al juego.
	 */
	public void eliminar_Algunas() {

		int result;
		int randomLocation;

		for(int i = 0; i < tablero.length; i++) {

			result = new Random().nextInt(5);

			for(int j = 0; j < result; j++) {

				randomLocation = new Random().nextInt(tablero.length);				

				if(tablero[i][randomLocation].getValor() != 0) { //Compruebo que la celda no haya sido eliminada antes

					tablero[i][randomLocation].setValor(0);
					tablero[i][randomLocation].setEditable();
				}
			}	
		}
	}

}
