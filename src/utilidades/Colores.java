package utilidades;
/**
 * Esta clase almacena las fichas coloreadas del juego.
 *
 * @author María Muñoz-Cruzado
 * @version 1.0
 * @since 1.0
 *
 */

public final class Colores {
    /**
     * Constante que determina la forma de una ficha en una combinación
     */
	public static final char CIRCULO = '\u2b24';
    /**
     * Contante que determina la forma de una ficha en una respuesta si es algún tipo de acierto
     */
	public static final char CIRCULO_PEQUENO = '\u23fa';
    /**
     * Constante que determina la forma de una ficha de una respuesta cuando ésta no es correcta.
     */
	public static final char TACHADO_CIRCULO = '\u2349';

	private static final String RESET = "\u001B[0m";
	public static final String NEGRO = "\u001B[30m" + CIRCULO + RESET;
	public static final String ROJO = "\u001B[31m"+ CIRCULO + RESET;
	public static final String VERDE = "\u001B[32m"+ CIRCULO + RESET;
	public static final String AMARILLO = "\u001B[33m"+ CIRCULO + RESET;
	public static final String AZUL = "\u001B[34m"+ CIRCULO + RESET;
	public static final String MORADO = "\u001B[35m"+ CIRCULO + RESET;
	public static final String CELESTE = "\u001B[36m"+ CIRCULO + RESET;
	public static final String BLANCO = "\u001B[37m" + CIRCULO + RESET;
	public static final String ROSA = "\u001B[91m" + CIRCULO + RESET;
	public static final String VERDE_CLARO = "\u001B[92m" + CIRCULO + RESET;
    /**
     * Constante que determina la forma y el color de una ficha en un respuesta, siendo ésta correcta de color y posición con respecto a una ficha de la combinación.
     */
	public static final String POSICION_COLOR = "\u001B[31m" + CIRCULO_PEQUENO + RESET;
    /**
     * Constante que determina la forma y el color de una ficha en una respuesta, siendo ésta solo acertada en color con respecto a una ficha de una combinación
     */
	public static final String COLOR = "\u001B[37m" + CIRCULO_PEQUENO + RESET;
    /**
     * Constante que determina la forma y el color de una ficha en una respuesta, siendo ésta incorrecta con respecto a una ficha de la combinación
     */
	public static final String NO_ACIERTO = "\u001B[30m" + TACHADO_CIRCULO + RESET; //Aunque no haga falta el color, lo pongo para que me ocupe lo mismo a la hora de dibujar

    /**
     * Permite la asignacion de un color de una ficha de una combinación a través de un número.
     * @param numero numero del color
     * @param dificultad para saber que colores asignarles según dicha dificultad.
     * @return color de una ficha
     */
	public static String asignacionColores(int numero, Dificultad dificultad){
		String resultado = "";
		if (dificultad == Dificultad.FACIL_USUARIO || dificultad == Dificultad.FACIL_MAQUINA|| dificultad == Dificultad.MEDIO) {
			switch (numero){
				case 1:
					resultado = ROJO;
					break;
				case 2:
					resultado = VERDE;
					break;
				case 3:
					resultado = AMARILLO;
					break;
				case 4:
					resultado = AZUL;
					break;
				case 5:
					resultado = MORADO;
					break;
				case 6:
					resultado = CELESTE;
					break;
				case 7:
					resultado = NEGRO;
					break;
				case 8:
					resultado = BLANCO;
			}
		}else if(dificultad == Dificultad.DIFICIL){
			switch (numero){
				case 0:
					resultado = ROJO;
					break;
				case 1:
					resultado = VERDE;
					break;
				case 2:
					resultado = AMARILLO;
					break;
				case 3:
					resultado = AZUL;
					break;
				case 4:
					resultado = MORADO;
					break;
				case 5:
					resultado = CELESTE;
					break;
				case 6:
					resultado = NEGRO;
					break;
				case 7:
					resultado = BLANCO;
					break;
				case 8:
					resultado = VERDE_CLARO;
           			 break;
				case 9:
                	resultado = ROSA;
               	 	break;
        }
    }
		return resultado;
}

    /**
     * Permite la asignacion de un tipo de respuesta a través de un número.
     * @param numero valor que puede tomar una respuesta. 1- No acierto, 2- Acierto solo de color, 3- Acierto completo
     * @return un tipo de respuesta
     */

    public static String asignacionColoresRespuestas(int numero){
        String resultado = "";
        switch (numero){
            case 1:
                resultado = NO_ACIERTO;
                break;
            case 2:
                resultado = COLOR;
                break;
            case 3:
                resultado = POSICION_COLOR;
                break;
		}
		return resultado;
	}

    /**
     *
     * @return Menú de opciones para elegir tipo de respuestas
     */
	public static String mostrarMenuRespuestas(){
		return String.format("Escribe una opcion:\n1-No acierto: %s\n2-Acierto solo de color: %s\n3-Acierto de color y posición: %s", NO_ACIERTO, COLOR, POSICION_COLOR);
	}

    /**
     *
     * @return Menú para la elección de colores de una combinación
     */
	public static String mostrarMenuColores() {
		String resultado = String.format("Elige una combinación de colores:\n1-ROJO: %s\n2-VERDE: %s\n3-AMARILLO: %s\n4-AZUL: %s\n5-MORADO: %s\n6-CELESTE: %s\n7-NEGRO: %s\n8-BLANCO: %s\n",
				ROJO, VERDE, AMARILLO, AZUL, MORADO, CELESTE, NEGRO, BLANCO);
		return resultado;
	}


}
