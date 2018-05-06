package utilidades;

/**
 * ENUM que contiene toda la información de los distintos modos de juego que tiene el MasterMind
 * @author María Muñoz-Cruzado
 * @version 1.0
 * @since 1.0
 */
public enum Dificultad {
	FACIL_USUARIO(1, false, 10, 8, 4), FACIL_MAQUINA(1, false, 10, 8, 4), MEDIO(2, false, 15, 8, 5), DIFICIL(2, true, 0, 10, 8);

	private int numJugadores;
	private boolean repeticionColores;
	private int numIntentos;
	private int numFichas;
	private int numColores;

	Dificultad(int numJugadores, boolean repeticionColores, int numIntentos, int numColores, int numFichas){
		this.numJugadores = numJugadores;
		this.repeticionColores = repeticionColores;
		this.numIntentos = numIntentos;
		this.numColores = numColores;
		this.numFichas = numFichas;
	}

    /**
     * Obtiene el número de jugadores de una dificultad
     * @return int numJugadores
     */
	public int getNumJugadores() {
		return numJugadores;
	}

    /**
     * Devuelve true si hay repetición de colores y sino que no la hay.
     * @return boolean repeticionColores
     */
	public boolean isRepeticionColores() {
		return repeticionColores;
	}

    /**
     * Devuelve el número de intentos que tiene cada dificultad. En la dificultad DIFICIL, el número de intentos es 0 porque no tiene límite.
     * @return int numIntentos
     */
	public int getNumIntentos() {
		return numIntentos;
	}

    /**
     * Devuelve el número de fichas que hay en cada combinación dependiendo de la dificultad
     * @return int numFichas
     */
	public int getNumFichas() {
		return numFichas;
	}

    /**
     * Obtiene el número de colores que hay en cada modo de juego
     * @return int numColores
     */
	public int getNumColores() {
		return numColores;
	}

}
