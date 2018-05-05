
public class Ficha implements Dibujable{
	private String color;
	
	public Ficha (String color) {
		this.color=color;
	}

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

	public boolean compararFicha(Ficha f){
		boolean esIgual = false;
		if(f.color.equals(color)){
			esIgual = true;
		}
		return esIgual;
	}

	public boolean compararColor(String c){
		boolean esIgual = false;
		if(color.equals(c)){
			esIgual = true;
		}
		return esIgual;
	}

	public void setColor(String color) {
		this.color = color;
	}

    public String getColor() {
        return color;
    }
}
