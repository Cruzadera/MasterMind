/**
 * Clase main donde se ejecuta el juego.
 */
public class Juego {
	public static void main(String[] args) {
		Menu m = new Menu();
		if(m.getDificultad() != null){
            Partida p = new Partida(m.getDificultad());
        }
	}

}
