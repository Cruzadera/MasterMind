import utilidades.Dificultad;

import java.util.*;

import static utilidades.Colores.*;

/**
 * @author María
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
     *
     * @return LinckedHashMap
     * Agrega una respuesta al tablero del usuario con respecto a la última combinación introducida
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
     *
     * @return LinckedHashMap
     * Comprueba los aciertos completos, es decir, los de color y posición.
     * Si hay acierto coloca una ficha roja
     */
    //Primero tenemos que comprobar los aciertos completos: color y posición.
    private LinkedHashMap<Integer, Ficha> comprobarAciertosCompletos() {
        LinkedHashMap<Integer, Ficha> mapaAciertosCompletos = new LinkedHashMap<>();
        for (int i = 0; i < tablero.getCombinacion(numRespuestas).getTamanoCombinacion(); i++) {
            if (combinacionSecreta.getFichaCombinacion(i).compararFicha(tablero.getCombinacion(numRespuestas).getFichaCombinacion(i))){
                mapaAciertosCompletos.put(i, new Ficha(POSICION_COLOR));
            }
        }
        return mapaAciertosCompletos;
    }

    /**
     *
     * @return LinckedHashMap
     *  Comprueba los aciertos de color, de la última combinación introducida. Teniendo en cuenta los aciertos completos del método comprobarAciertosCompletos()
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
                        && !posicionesSaltar.contains(i) && combinacionSecreta.getFichaCombinacion(i).compararFicha(tablero.getCombinacion(numRespuestas).getFichaCombinacion(j))) {
                    mapaAciertos.put(j, new Ficha(COLOR));
                    posicionesSaltar.add(i);
                }
            }

        }
        return mapaAciertos;
    }

    /**
     *
     * @return numero de aciertos completos de la última combinación introducida
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
     *
     * @return numero de aciertos de color de la última combinación introducida
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
     *
     * @param respuestaAComprobar
     * @param respuestaCorrecta
     * @return boolean
     * Comprueba si una respuesta introducida es igual a la respuesta correcta, teniendo en cuenta sólo el número de aciertos completos.
     * Si devuelve true son iguales, y sino son distintas.
     */
    public boolean comprobarAciertosCompletos (Combinacion respuestaAComprobar, Combinacion respuestaCorrecta){
        int aciertosCompletosC = 0, aciertosCompletosA = 0;
        boolean mismoNumeroAciertosCompletos = false;

        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(respuestaCorrecta.getFichaCombinacion(i).compararColor(POSICION_COLOR)){
                aciertosCompletosC++;
            }
        }
        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(respuestaAComprobar.getFichaCombinacion(i).compararColor(POSICION_COLOR)){
                aciertosCompletosA++;
            }
        }

        if(aciertosCompletosC==aciertosCompletosA){
            mismoNumeroAciertosCompletos = true;
        }

        return mismoNumeroAciertosCompletos;
    }

    /**
     *
     * @param respuestaAComprobar
     * @param respuestaCorrecta
     * @return boolean
     * Comprueba si una respuesta introducida es igual a la respuesta correcta, teniendo en cuenta sólo el número de aciertos de color.
     * Si devuelve true son iguales, y sino son distintas.
     */
    public boolean comprobarAciertosColor (Combinacion respuestaAComprobar, Combinacion respuestaCorrecta){
        int aciertosColorC = 0, aciertosColorA = 0;
        boolean mismoNumeroAciertosColor = false;

        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(respuestaCorrecta.getFichaCombinacion(i).compararColor(COLOR)){
                aciertosColorC++;
            }
        }

        for(int i = 0; i<dificultad.getNumFichas(); i++){
            if(respuestaAComprobar.getFichaCombinacion(i).compararColor(COLOR)){
                aciertosColorA++;
            }
        }

        if(aciertosColorA==aciertosColorC){
            mismoNumeroAciertosColor = true;
        }
        return mismoNumeroAciertosColor;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setCombinacionSecreta(Combinacion combinacionSecreta) {
        this.combinacionSecreta = combinacionSecreta;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }
}
