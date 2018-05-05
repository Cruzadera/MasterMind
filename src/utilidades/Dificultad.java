package utilidades;

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

	public int getNumJugadores() {
		return numJugadores;
	}

	public boolean isRepeticionColores() {
		return repeticionColores;
	}

	public int getNumIntentos() {
		return numIntentos;
	}

	public int getNumFichas() {
		return numFichas;
	}

	public int getNumColores() {
		return numColores;
	}

	@Override
	public String toString() {
		return "utilidades.Dificultad{" +
				"numJugadores=" + numJugadores +
				", repeticionColores=" + repeticionColores +
				", numIntentos=" + numIntentos +
				", numCasillas=" + numFichas +
				", numColores=" + numColores +
				'}';
	}
}
