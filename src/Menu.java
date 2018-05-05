import static utilidades.Colores.*;
import utilidades.Dificultad;

import static utilidades.Teclado.Incluido.*;
import static utilidades.Teclado.Tipo.*;
import static utilidades.Teclado.*;

public class Menu {
    private Dificultad dificultad;
    private byte contadorEntrada;
    private byte opcionMenu;

    public Menu() {
        dificultad = mostrarMenu();
    }

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
                    instrucciones = menuInstrucciones();
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

    private String menuInstrucciones() {
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



    private String instruccionesColores() {
        Combinacion combinacionEjemplo = new Combinacion(Dificultad.FACIL_USUARIO);
        combinacionEjemplo.agregarFicha(new Ficha(ROJO));
        combinacionEjemplo.agregarFicha(new Ficha(CELESTE));
        combinacionEjemplo.agregarFicha(new Ficha(VERDE));
        combinacionEjemplo.agregarFicha(new Ficha(MORADO));
        String resultado = String.format("Para introducir los colores deseados solo hace falta escribir un número con dichos colores\nEjemplo: 1625 y el resultado sería %s\n", combinacionEjemplo.dibujar());
        return resultado;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public String toString() {
        return String.format("Menú: %s", dificultad.toString());
    }

    public byte getOpcionMenu() {
        return opcionMenu;
    }
}
