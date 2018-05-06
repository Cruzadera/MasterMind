import utilidades.Dificultad;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Clase pública que guarda toda la información del tablero del juego.
 * @author María Muñoz-Cruzado
 * @version 1.0
 * @since 1.0
 */
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

    /**
     * Dibuja el tablero de un Jugador
     * @return String
     */
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

    /**
     * Dibuja dos tableros a la vez, de dos jugadores distintos. El primer tablero lo coge del tablero del jugador
     * @param tablero2 el segundo tablero que se desea dibujar
     * @return String
     */
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

    /**
     * Obtiene las respuestas del tablero
     * @return LinkedHashMap
     */
    public LinkedHashMap<Integer, Combinacion> getRespuestas() {
        return respuestas;
    }

    /**
     * Devuelve una respuesta concreta del tablero indicada por una posición
     * @param posicion posición en la que se desea obtener dicha respuesta.
     * @return Combinacion respuesta
     */
    public Combinacion obtenerRespuestaTablero(int posicion) {
        return respuestas.get(posicion);
    }

    /**
     * Añade una respuesta a las respuestas del tablero, indicandole la posición y la respuesta que se le desea añadir.
     * @param i posición en la que se desea agregar la respuesta
     * @param respuesta respuesta escogida para añadir
     */
    public void agregarRespuesta(Integer i, Combinacion respuesta) {
        respuestas.put(i, respuesta);
    }
    /**
     * Añade una combinación a las combinaciones del tablero, indicandole sólo la combinación que se le desea añadir.
     * @param combinacion combinación que se desea añadir
     */
    public void agregarCombinacion(Combinacion combinacion) {
        combinaciones.add(combinacion);
    }

    /**
     * Devuelve una combinación del tablero indicada por una posición
     * @param posicion posicion deseada para obtener la combinacion
     * @return Combinacion
     */
    public Combinacion getCombinacion(int posicion) {
        return combinaciones.get(posicion);
    }

}
