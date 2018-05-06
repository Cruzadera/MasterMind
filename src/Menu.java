import static utilidades.Colores.*;
import utilidades.Dificultad;

import static utilidades.Teclado.Incluido.*;
import static utilidades.Teclado.Tipo.*;
import static utilidades.Teclado.*;

/**
 * Clase pública que contiene los menús de interacción con el usuario
 * @author María Muñoz-Cruzado
 * @since 1.0
 * @version 1.0
 */
public class Menu {
    private Dificultad dificultad;
    private byte contadorEntrada;
    private byte opcionMenu;

    /**
     * Constructor que llama al método mostrarMenu(), para pasarle la dificultad a su atributo.
     */
    public Menu() {
        dificultad = mostrarMenu();
    }

    /**
     * Muestra el menú principal en el cual tiene tres opciones: empezar a jugar, instrucciones y salir.
     * Si se elige la opción 1, emepezar a jugar, muestra el siguiente menú llamando al método pregutarDificultad().
     * La opción 2, muestra las instrucciones del juego, llamando al método mostrarInstrucciones(). Después vuelve a aparecer de nuevo el menú.
     * Y si se escoge la opción 3, salir, la dificultad que se devuelve es null.
     * @return Dificultad
     */
    private Dificultad mostrarMenu() {
        final byte MINIMO = 1, MAXIMO = 3;
        boolean salir = false;
        String instrucciones;

        if(contadorEntrada==0){
            System.out.println("-------BIENVENIDO A MASTERMIND---------");
        }
        contadorEntrada++;
        while (!salir) {
            System.out.println("-----MENÚ PRINCIPAL-----");
            System.out.printf("1-Empezar a jugar\n2-Instrucciones\n3-Salir\n");
            opcionMenu = leerEntre(MINIMO, MAXIMO, TODOS, BYTE);
            switch (opcionMenu) {
                case 1:
                    dificultad = preguntarDificultad();
                    salir = true;
                    break;
                case 2:
                    instrucciones = mostrarInstrucciones();
                    System.out.println(instrucciones);
                    break;
                case 3:
                    System.out.println("¡HASTA PRONTO!");
                    salir = true;
                    break;
            }
        }
        return dificultad;
    }

    /**
     * Pregunta al usuario que dificultad prefiere para empezar la partida. La primeras 4 opciones son las dificultades que puede escoger el usuario.
     * La última opción, la 5 es volver atrás, para volver al menú principal.
     * @return Dificultad
     */
    private Dificultad preguntarDificultad() {
        Dificultad escogida = null;
        int opcion = 0;
        boolean atras = false;
        final int MINIMO = 1, MAXIMO = 5;

        System.out.println("Elige el modo de juego que deseas jugar:");
        System.out.printf("1-Modo fácil (adivina el usuario)\n2-Modo fácil (adivina la máquina)\n3-Modo medio\n4-Modo difícil\n5-Atrás\n");
        opcion = leerEntre(MINIMO, MAXIMO, TODOS, INT);
        switch (opcion) {
            case 1:
                escogida = Dificultad.FACIL_USUARIO;
                break;
            case 2:
                escogida = Dificultad.FACIL_MAQUINA;
                break;
            case 3:
                escogida = Dificultad.MEDIO;
                break;
            case 4:
                escogida = Dificultad.DIFICIL;
                break;
            case 5:
                mostrarMenu();
                break;
        }

        return escogida;
    }

    /**
     * Muestra en formato cadena las instrucciones del juego, tanto de los distintos modos, como de la inserción de las fichas en una combinación
     * @return Instrucciones
     */
    private String mostrarInstrucciones() {
        String resultado = String.format("-------Instrucciones de los distintos modos de juegos-------\nModo fácil (usuario): El jugador es el usuario: la máquina pone la combinación oculta y le va indicando al usuario las respuestas a sus intentos." +
                "\nModo fácil (máquina): El jugador es la máquina: el usuario pone la combinación oculta y le va indicando a la máquina las respuestas a sus intentos." +
                "\n\t En estos dos modos fáciles tienen las siguientes caracteristicas: \n" +
                "\t\tNúmero de casillas: 4\n" +
                "\t\tNúmero de colores: 8\n" +
                "\t\tRepetición de colores: no\n" +
                "\t\tNúmero de intentos: 10\n" +
                "\nModo medio: Se compone de 2 jugadores: el usuario y la máquina. " +
                "\n\tSe caracteriza por: \n" +
                "\t\tNúmero de casillas: 5\n" +
                "\t\tNúmero de colores: 8\n" +
                "\t\tRepetición de colores: no\n" +
                "\t\tNúmero de intentos: 15. Si en este número de intentos no hay ningún ganador, ganará el que más" +
                "fichas colocadas tenga en el último intento.\n" +
                "\t\tEn caso de empate, el que más fichas colocadas en otro lugar tenga. En caso de empate, se considerará un empate en dicha partida.\n" +
                "\nModo difícil: Se compone de 2 jugadores: la máquina contra la máquina.\n" +
                "\tSe caracteriza por: \n" +
                "\t\tNúmero de casillas: 8\n" +
                "\t\tNúmero de colores: 10\n" +
                "\t\tRepetición de colores: sí\n" +
                "\t\tNúmero de intentos: hasta que uno de los dos gane.\n");
        resultado += String.format("\n------Instrucciones para introducir una combinación-------\n%s", instruccionesColores());
        return resultado;
    }

    /**
     * Muestra las intrucciones para el usuario de cómo debe introducir las distintas fichas de colores para formar una combinación
     * @return Instrucciones para introducir las fichas
     */
    private String instruccionesColores() {
        Combinacion combinacionEjemplo = new Combinacion(Dificultad.FACIL_USUARIO);
        combinacionEjemplo.agregarFicha(new Ficha(ROJO));
        combinacionEjemplo.agregarFicha(new Ficha(CELESTE));
        combinacionEjemplo.agregarFicha(new Ficha(VERDE));
        combinacionEjemplo.agregarFicha(new Ficha(MORADO));
        String resultado = String.format("Para introducir los colores deseados solo hace falta escribir un número con dichos colores\nEjemplo: 1625 y el resultado sería %s\n", combinacionEjemplo.dibujar());
        return resultado;
    }

    /**
     * Obtiene la dificultad del menú
     * @return Dificultad
     */
    public Dificultad getDificultad() {
        return dificultad;
    }

    /**
     * Obtiene la opción escogida por el usuario del menú principal
     * @return opcionMenu
     */
    public byte getOpcionMenu() {
        return opcionMenu;
    }
}
