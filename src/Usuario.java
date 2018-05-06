import utilidades.Colores;
import utilidades.Dificultad;

import static utilidades.Teclado.*;

/**
 * Clase pública hija de Jugador, especifica para los usuarios humanos
 * @author María Muñoz-Cruzado
 * @version 1.0
 * @since 1.0
 */
public class Usuario extends Jugador {

    public Usuario(Dificultad dificultad) {
        super(dificultad);
    }

    /**
     * Pregunta la respuesta al usuario respecto a una combinación introducida por la máquina
     * @return Combinacion respuesta
     */
    public Combinacion preguntarRespuestas() {
        final byte MINIMO = 1, MAXIMO = 3;
        boolean valorNoValido;
        int numeroRespuesta;
        String respuestaCadena;
        Combinacion respuestaUser = new Combinacion(dificultad);
        Ficha ficha = new Ficha(null);

        System.out.println(Colores.mostrarMenuRespuestas());

        do {
            valorNoValido = false;
            numeroRespuesta = leerNumero(Tipo.INT);
            respuestaCadena = String.valueOf(numeroRespuesta);
            if (respuestaCadena.length() != dificultad.getNumFichas()) {
                System.err.printf("Introduce un número de %d cifras. Por favor.\n", dificultad.getNumFichas());
            }
            //Comprobamos si la cifra está entre los valores
            for (int i = 0; i < respuestaCadena.length() && !valorNoValido; i++) {
                if (!(Integer.parseInt(String.valueOf(respuestaCadena.charAt(i))) >= MINIMO && Integer.parseInt(String.valueOf(respuestaCadena.charAt(i))) <= MAXIMO)) {
                    System.err.println("Uno de los números introducidos en la cifra no está entre los valores 1 y 3. Introduce de nuevo la cifra por favor.");
                    valorNoValido = true;
                }
            }
        } while (respuestaCadena.length() != dificultad.getNumFichas() || valorNoValido);
        //Sacamos la cifra que ha escrito el usuario mediante charAt.
        for (int i = 0; i < respuestaCadena.length(); i++) {
            if (Integer.parseInt(String.valueOf(respuestaCadena.charAt(i))) != 0) {
                ficha = new Ficha(Colores.asignacionColoresRespuestas(Integer.parseInt(String.valueOf(respuestaCadena.charAt(i)))));
            }
            respuestaUser.agregarFicha(ficha);
        }
        numRespuestas++;
        return respuestaUser;
    }

    /**
     * Pregunta al usuario las fichas que desea introducir en una combinación. Se tiene en cuenta de que la cifra introducida por el usuario no tenga ningún
     * número no válido, es decir, entre 1 y 8. También se comprueba de que la cifra del número introducido no supere al número de fichas establecido en cada
     * dificultad. Por último, el usuario no podrá introducir números repetidos en la cifra si él está jugando en la dificultad FACIL_MAQUINA.
     * @return Combinacion combinacionIntroducida
     */
    public Combinacion introducirCombinacion() {
        final int MINIMO = 1;
        Combinacion combinacionUser = new Combinacion(dificultad);
        String combinacionCadena;
        int numeroColor;
        Ficha ficha;
        boolean valorNoValido;

        System.out.println(Colores.mostrarMenuColores());
        do {
            valorNoValido = false;
            numeroColor = leerNumero(Tipo.INT);
            combinacionCadena = String.valueOf(numeroColor);
            //Comprobamos que no hay ningún número que no sea del 1 al 8 en la cifra
            if (combinacionCadena.length() != dificultad.getNumFichas()) {
                System.err.printf("Introduce un número de %d cifras. Por favor.\n", dificultad.getNumFichas());
            }

            //Comprobamos que no haya metido un número salido de rango y que no haya un número de la cifra repetida
            for (int i = 0; i < combinacionCadena.length() && !valorNoValido; i++) {
                if (!(Integer.parseInt(String.valueOf(combinacionCadena.charAt(i))) >= MINIMO && Integer.parseInt(String.valueOf(combinacionCadena.charAt(i))) <= dificultad.getNumColores())) {
                    System.err.println("Uno de los números introducidos en la cifra no está entre los valores 1 y 8. Introduce de nuevo la cifra por favor.");
                    valorNoValido = true;
                }
                if (dificultad == Dificultad.FACIL_MAQUINA && i!=0 && Integer.parseInt(String.valueOf(combinacionCadena.charAt(i-1)))== Integer.parseInt(String.valueOf(combinacionCadena.charAt(i)))) {
                    System.err.println("No se puede introducir colores repetidos. Vuelve a introducirla por favor.");
                    valorNoValido = true;
                }
            }
        } while (combinacionCadena.length() != dificultad.getNumFichas() || valorNoValido);
        //Sacamos la cifra que ha escrito el usuario mediante charAt.
        for (int i = 0; i < combinacionCadena.length(); i++) {
            ficha = new Ficha(Colores.asignacionColores(Integer.parseInt(String.valueOf(combinacionCadena.charAt(i))), dificultad));
            combinacionUser.agregarFicha(ficha);
            //Agregamos las fichas a la combinación.
        }
        return combinacionUser;
    }


}
