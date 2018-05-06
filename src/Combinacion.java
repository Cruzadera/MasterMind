import utilidades.Dificultad;
import java.util.ArrayList;

/** Clase formada por un cojunto de fichas
 *  @author María Muñoz-Cruzado
 *  @version 1.0
 *  @since 1.0
 */
public class Combinacion implements Dibujable {
	private ArrayList<Ficha> combinacion;
	private Dificultad dificultad;
	private int tamanoCombinacion;

	public Combinacion(Dificultad dificultad) {
		this.dificultad = dificultad;
		tamanoCombinacion = dificultad.getNumFichas();
		combinacion = new ArrayList<>(tamanoCombinacion);
	}

    /**
     * Dibuja las fichas por las que esté formada la combinación
     * @return Combinación dibujada
     */
	@Override
	public String dibujar() {
		String resultado = "";
		for (int i = 0; i < combinacion.size(); i++) {
			resultado += String.format("%s", combinacion.get(i).dibujar());
		}
		return resultado;
	}

	public boolean equals(Object obj) {
		boolean resultado = false;
		if(obj instanceof Combinacion && combinacion.equals(((Combinacion) obj).combinacion)){
			resultado = true;
		}
		return resultado;
	}

    /**
     * Añade una ficha en la combinación en una posición especificada.
     * @param posicion posición en la que desea ser insertada la ficha dentro de la combinación
     * @param ficha ficha que desea ser insertada
     */
	public void agregarFicha(int posicion, Ficha ficha){
		combinacion.add(posicion, ficha);
	}

    /**
     * Añade una ficha en la combinación
     * @param ficha ficha que desea ser insertada
     */
	public void agregarFicha(Ficha ficha){
		combinacion.add(ficha);
	}

    /**
     * Obtiene la combinación de fichas
     * @return combinacion
     */
	public ArrayList<Ficha> getCombinacion() {
		return combinacion;
	}

    /**
     * Cambia una combinación de fichas
     * @param combinacion combinación que se desea modificar
     */
	public void setCombinacion(ArrayList<Ficha> combinacion) {
		this.combinacion = combinacion;
	}

    /**
     * Devuelve el tamaño de una combinación de fichas
     * @return tamaño de una combinación
     */
	public int getTamanoCombinacion(){
		return tamanoCombinacion;
	}

    /**
     * Obtiene una ficha de la combinación especificando la posición en la que se encuentra dicha ficha
     * @param posicion posicion de la ficha en la combinación
     * @return Ficha
     */
	public Ficha obtenerFichaCombinacion(int posicion){
		return combinacion.get(posicion);
	}

    /**
     * Borra una combinación entera.
     */
	public void borrarCombinacion(){
		combinacion.removeAll(combinacion);
	}

}