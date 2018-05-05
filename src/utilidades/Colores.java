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
     * Las distintas formas que puede tener una ficha.
     * El CIRCULO para las combinaciones introducidas.
     * El CIRCULO_PEQUEÑO para las respuestas
     * El TACHADO_CIRCULO para las respuestas que no tienen ningun tipo de acierto.
     */
	public static final char CIRCULO = '\u2b24';
	public static final char CIRCULO_PEQUENO = '\u23fa';
	public static final char TACHADO_CIRCULO = '\u2349';
    /**
     * Los 10 colores disponibles de las diferentes fichas que pueden tomar en una combinación.
     */
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
     * Los distintos tipos de aciertos que pueden tomar una respuesta.
     */
	public static final String POSICION_COLOR = "\u001B[31m" + CIRCULO_PEQUENO + RESET;
	public static final String COLOR = "\u001B[37m" + CIRCULO_PEQUENO + RESET;
	public static final String NO_ACIERTO = "\u001B[30m" + TACHADO_CIRCULO + RESET; //Aunque no haga falta el color, lo pongo para que me ocupe lo mismo a la hora de dibujar

    /**
     *
     * @param numero
     * @param dificultad
     * @return color de una ficha
     * Permite la asignacion de un color de una ficha de una combinación a través de un número.
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
     *
     * @param numero
     * @return un tipo de respuesta
     * Permite la asignacion de un tipo de respuesta a través de un número.
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
