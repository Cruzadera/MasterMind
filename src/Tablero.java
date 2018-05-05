import utilidades.Dificultad;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Tablero implements DibujableTablero {
    private ArrayList<Combinacion> combinaciones;
    private LinkedHashMap<Integer, Combinacion> respuestas;
    private Dificultad dificultad;
    private int numIntentos = 1;

    Tablero(Dificultad dificultad) {
        this.dificultad = dificultad;
        combinaciones = new ArrayList<>();
        respuestas = new LinkedHashMap<>();
    }

    public String dibujar() {
        String resultado = "";
        String espacio = " ";
        int aux = 1;
        resultado += String.format("\n\t\tTABLERO\n");
        for (int i = 0; i < combinaciones.size(); i++) {
            resultado += String.format("_________________________\n");
            resultado += String.format("\n%-2s", String.valueOf(aux));
            resultado += String.format("|%12s", combinaciones.get(i).dibujar());
            if(respuestas.get(i)==null){
                resultado += String.format("|%-12s", espacio);
            }else{
                resultado += String.format("|%-12s\n", respuestas.get(i).dibujar());
            }

            aux++;
        }
        return resultado;
    }

    public String dibujarTableros(Tablero tablero2) {
        String resultado = "";
        int aux = 1;
        if (dificultad == Dificultad.MEDIO) {
            resultado += String.format("\n    Tablero usuario");
            resultado += "                      ";
            resultado += String.format("    Tablero máquina\n");
            for (int i = 0; i < combinaciones.size(); i++) {
                resultado += String.format("_____________________________");
                resultado += "          ";
                resultado += String.format("_____________________________\n");
                resultado += String.format("\n%-3s", String.valueOf(aux));
                resultado += String.format("|%12s", combinaciones.get(i).dibujar());
                resultado+=String.format("|%-16s", respuestas.get(i).dibujar());
                resultado += "          ";
                resultado += String.format("%-2s", String.valueOf(aux));
                resultado += String.format("|%12s", tablero2.combinaciones.get(i).dibujar());
                resultado += String.format("|%-16s\n", tablero2.respuestas.get(i).dibujar());
                aux++;
            }
        }else{
            if(numIntentos==1) {
                resultado += String.format("\n             Tablero máquina 1");
                resultado += "                                        ";
                resultado += String.format("  Tablero máquina 2\n");
            }
                resultado += String.format("_____________________________________________");
                resultado += "           ";
                resultado += String.format("______________________________________________\n");
                resultado += String.format("\n%-4s", String.valueOf(numIntentos));
                resultado += String.format("|%24s", combinaciones.get(combinaciones.size()-1).dibujar());
                resultado+=String.format("|%-24s", respuestas.get(respuestas.size()-1).dibujar());
                resultado += "          ";
                resultado += String.format("%-4s", String.valueOf(numIntentos));
                resultado += String.format("|%24s", tablero2.combinaciones.get(tablero2.combinaciones.size()-1).dibujar());
                resultado += String.format("|%-24s", tablero2.respuestas.get(tablero2.respuestas.size()-1).dibujar());
                numIntentos++;

        }
        return resultado;
    }

    public LinkedHashMap<Integer, Combinacion> getRespuestas() {
        return respuestas;
    }

    public Combinacion getRespuesta(int posicion) {
        return respuestas.get(posicion);
    }
    public void borrarRespuesta(int posicion){
        respuestas.get(posicion).borrarCombinacion();
    }
    public void agregarRespuesta(Integer i, Combinacion respuesta) {
        respuestas.put(i, respuesta);
    }

    public void agregarCombinacion(Combinacion combinacion) {
        combinaciones.add(combinacion);
    }

    public Combinacion getCombinacion(int posicion) {
        return combinaciones.get(posicion);
    }

}
