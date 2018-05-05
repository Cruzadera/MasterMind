import utilidades.Colores;
import utilidades.Dificultad;

import static utilidades.Teclado.*;

public class Usuario extends Jugador {

    public Usuario(Dificultad dificultad) {
        super(dificultad);
    }

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
                if (dificultad == Dificultad.FACIL_MAQUINA &&i!=0 && Integer.parseInt(String.valueOf(combinacionCadena.charAt(i-1)))== Integer.parseInt(String.valueOf(combinacionCadena.charAt(i)))) {
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
