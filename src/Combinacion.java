import utilidades.Dificultad;
import java.util.ArrayList;

public class Combinacion implements Dibujable {
	private ArrayList<Ficha> combinacion;
	private Dificultad dificultad;
	private int tamanoCombinacion;

	public Combinacion(Dificultad dificultad) {
		this.dificultad = dificultad;
		tamanoCombinacion = dificultad.getNumFichas();
		combinacion = new ArrayList<>(tamanoCombinacion);
	}

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


	public void agregarFicha(int posicion, Ficha ficha){
		combinacion.add(posicion, ficha);
	}
	public void agregarFicha(Ficha ficha){
		combinacion.add(ficha);
	}

	public ArrayList<Ficha> getCombinacion() {
		return combinacion;
	}

	public void setCombinacion(ArrayList<Ficha> combinacion) {
		this.combinacion = combinacion;
	}

	public int getTamanoCombinacion(){
		return tamanoCombinacion;
	}

	public Ficha getFichaCombinacion(int posicion){
		return combinacion.get(posicion);
	}
	public void borrarCombinacion(){
		combinacion.removeAll(combinacion);
	}

}