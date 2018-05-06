/**
 * Clase pública donde se almacena el color y la forma de una ficha
 * @author María Muñoz-Cruzado
 * @version 1.0
 * @since 1.0
 */
public class Ficha implements Dibujable{
	private String color;
	
	public Ficha (String color) {
		this.color=color;
	}

    /**
     * Dibuja la forma y el color de una ficha
     * @return String
     */
	public String dibujar() {
		return String.format("%s ", color);
	}

	public boolean equals(Object obj) {
		boolean resultado = false;
		if(obj instanceof Ficha && color.equals(((Ficha) obj).color)){
			resultado = true;
		}
		return resultado;
	}

    /**
     * Compara dos objetos fichas
     * @param f Ficha con la que se desea comparar
     * @return boolean. Si devuelve true es que son iguales y sino son distintas.
     */
	public boolean compararFicha(Ficha f){
		boolean esIgual = false;
		if(f.color.equals(color)){
			esIgual = true;
		}
		return esIgual;
	}

    /**
     * Compara dos colores, en formato String.
     * @param c Color con el que se desea comparar
     * @return boolean. Si devuelve true es que son iguales y sino son distintos
     */
	public boolean compararColor(String c){
		boolean esIgual = false;
		if(color.equals(c)){
			esIgual = true;
		}
		return esIgual;
	}

    /**
     * Cambia el color de un objeto Ficha
     * @param color color del objeto Ficha
     */
	public void setColor(String color) {
		this.color = color;
	}

    /**
     * Devuelve el color de una ficha.
     * @return String
     */
    public String getColor() {
        return color;
    }
}
