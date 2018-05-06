import utilidades.Dificultad;

import java.util.*;

import static utilidades.Colores.*;

/**
 * Clase abstracta que tiene todas las funciones comunes de un jugador
 * @author María Muñoz-Cruzado
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class Jugador {
    protected Tablero tablero;
    private Combinacion combinacionSecreta;
    protected LinkedHashMap<Integer, Ficha> mapaAciertos = new LinkedHashMap<>();
    protected int numRespuestas = 0;
    protected Dificultad dificultad;

    public Jugador(Dificultad dificultad) {
        this.dificultad = dificultad;
        tablero = new Tablero(dificultad);
    }

    /**
     * Agrega una respuesta al tablero del usuario con respecto a la última combinación introducida.
     * @return LinckedHashMap
     */
    //Tercer método que rellena las respuestas y las introduce dentro del tablero del Jugador
    public LinkedHashMap<Integer, Combinacion> rellenarRespuestas() {
        mapaAciertos = new LinkedHashMap<>(comprobarAciertosColor());
        Combinacion combinacion = new Combinacion(dificultad);
        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(mapaAciertos.get(i) == null){
                mapaAciertos.put(i, new Ficha(NO_ACIERTO));
            }
        }

        for(Ficha f: mapaAciertos.values()){
            combinacion.agregarFicha(f);
        }
        tablero.agregarRespuesta(numRespuestas, combinacion);
        numRespuestas++;
        return tablero.getRespuestas();
    }

    /**
     * Comprueba los aciertos completos, es decir, los de color y posición. Si hay acierto coloca una ficha roja
     * @return LinckedHashMap
     *
     */
    //Primero tenemos que comprobar los aciertos completos: color y posición.
    private LinkedHashMap<Integer, Ficha> comprobarAciertosCompletos() {
        LinkedHashMap<Integer, Ficha> mapaAciertosCompletos = new LinkedHashMap<>();
        for (int i = 0; i < tablero.getCombinacion(numRespuestas).getTamanoCombinacion(); i++) {
            if (combinacionSecreta.obtenerFichaCombinacion(i).compararFicha(tablero.getCombinacion(numRespuestas).obtenerFichaCombinacion(i))){
                mapaAciertosCompletos.put(i, new Ficha(POSICION_COLOR));
            }
        }
        return mapaAciertosCompletos;
    }

    /**
     * Comprueba los aciertos de color, de la última combinación introducida. Teniendo en cuenta los aciertos completos del método comprobarAciertosCompletos()
     * @return LinckedHashMap
     *
     */
    //	Segundo método que comprueba si los aciertos son solo de color
    private LinkedHashMap<Integer, Ficha> comprobarAciertosColor() {
        mapaAciertos = new LinkedHashMap<>(comprobarAciertosCompletos());
        ArrayList<Integer> posicionesSaltar = new ArrayList<>();
        if (mapaAciertos.size() == 0) {
            mapaAciertos = new LinkedHashMap<>();
        }
        for (int i = 0; i < tablero.getCombinacion(numRespuestas).getTamanoCombinacion(); i++) {
            for (int j = 0; j < combinacionSecreta.getTamanoCombinacion(); j++) {
                if (!comprobarAciertosCompletos().containsKey(j) && !comprobarAciertosCompletos().containsKey(i) && !mapaAciertos.containsKey(j)
                        && !posicionesSaltar.contains(i) && combinacionSecreta.obtenerFichaCombinacion(i).compararFicha(tablero.getCombinacion(numRespuestas).obtenerFichaCombinacion(j))) {
                    mapaAciertos.put(j, new Ficha(COLOR));
                    posicionesSaltar.add(i);
                }
            }

        }
        return mapaAciertos;
    }

    /**
     * Devuelve el número de aciertos completos de la última combinación introducida
     * @return int numeroAciertosCompletos
     */
    public int numeroAciertosCompletos(){
        int numero = 0;
        if(!mapaAciertos.isEmpty()){
            for (Ficha f : mapaAciertos.values()){
                if(f.compararColor(POSICION_COLOR)){
                    numero++;
                }
            }
        }
        return numero;
    }

    /**
     * Devuelve el número de aciertos de color de la última combinación introducida
     * @return int numAciertosColor
     */
    public int numeroAciertosColor(){
        int numero = 0;
        if(!mapaAciertos.isEmpty()){
            for (Ficha f : mapaAciertos.values()){
                if(f.compararColor(COLOR)){
                    numero++;
                }
            }
        }
        return numero;
    }

    /**
     *  Comprueba si una respuesta introducida es igual a la respuesta correcta, teniendo en cuenta sólo el número de aciertos completos.
     *  Si devuelve true son iguales, y sino son distintas.
     * @param respuestaAComprobar respuesta que se desea comprobar
     * @param respuestaCorrecta respuesta correcta con la que se compara
     * @return boolean si es true: son iguales, si es false: no son iguales
     *
     */
    public boolean comprobarAciertosCompletos (Combinacion respuestaAComprobar, Combinacion respuestaCorrecta){
        int aciertosCompletosC = 0, aciertosCompletosA = 0;
        boolean mismoNumeroAciertosCompletos = false;

        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(respuestaCorrecta.obtenerFichaCombinacion(i).compararColor(POSICION_COLOR)){
                aciertosCompletosC++;
            }
        }
        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(respuestaAComprobar.obtenerFichaCombinacion(i).compararColor(POSICION_COLOR)){
                aciertosCompletosA++;
            }
        }

        if(aciertosCompletosC==aciertosCompletosA){
            mismoNumeroAciertosCompletos = true;
        }

        return mismoNumeroAciertosCompletos;
    }

    /**
     *  Comprueba si una respuesta introducida es igual a la respuesta correcta, teniendo en cuenta sólo el número de aciertos de color.
     *  Si devuelve true son iguales, y sino son distintas.
     * @param respuestaAComprobar respuesta que se desea comprobar
     * @param respuestaCorrecta respuesta correcta con la que se compara
     * @return boolean
     *
     */
    public boolean comprobarAciertosColor (Combinacion respuestaAComprobar, Combinacion respuestaCorrecta){
        int aciertosColorC = 0, aciertosColorA = 0;
        boolean mismoNumeroAciertosColor = false;

        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(respuestaCorrecta.obtenerFichaCombinacion(i).compararColor(COLOR)){
                aciertosColorC++;
            }
        }

        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(respuestaAComprobar.obtenerFichaCombinacion(i).compararColor(COLOR)){
                aciertosColorA++;
            }
        }

        if(aciertosColorA==aciertosColorC){
            mismoNumeroAciertosColor = true;
        }
        return mismoNumeroAciertosColor;
    }

    /**
     * Devuelve el atributo tablero del jugador.
     * @return tablero
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Obtiene la combinacion secreta que tiene que resolver el Jugador
     * @param combinacionSecreta combinacion que tiene que resolver el Jugador
     */
    public void setCombinacionSecreta(Combinacion combinacionSecreta) {
        this.combinacionSecreta = combinacionSecreta;
    }

    /**
     * Obtiene la dificultad del Jugador
     * @param dificultad dificultad en la que juega el Jugador
     */
    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }
}
