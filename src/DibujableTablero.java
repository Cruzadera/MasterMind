/**
 * Interfaz que permite poder dibujar dos tablero a la vez. Esta interfaz sólo la implementa la clase Tablero.
 */
public interface DibujableTablero extends Dibujable {
    String dibujarTableros(Tablero tablero);
}
