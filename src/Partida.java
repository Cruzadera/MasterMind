import utilidades.Dificultad;
import static utilidades.Dificultad.*;
import static utilidades.Teclado.*;
import static utilidades.Colores.*;

public class Partida {
	private Dificultad dificultad;
	private Combinacion combinacionAResolver;
	private Combinacion combinacionAResolver2;
	private int numIntentos;


	public Partida(Dificultad dificultad) {
		this.dificultad = dificultad;
		determinarPartida();
	}

	private void determinarPartida(){
		IA maquina = new IA(dificultad);
		IA maquina2 = new IA(dificultad);
		Usuario usuario = new Usuario(dificultad);

		//El jugador es el usuario: la máquina pone la combinación oculta y le va indicando al
		//usuario las respuestas a sus intentos.
		if(dificultad==FACIL_USUARIO) {
			maquina.setDificultad(FACIL_USUARIO);
			//La máquina crea la combinación oculta.
			combinacionAResolver = maquina.crearCombinacionOculta(false);
			//Empieza la partida modo fácil usuario.
			partidaFacilUsuario();

		}else if(dificultad==FACIL_MAQUINA) {
			usuario.setDificultad(FACIL_MAQUINA);
			//El jugador es la máquina: el usuario pone la combinación oculta y le va indicando a la máquina las respuestas a sus intentos.
			//Le pedimos la combinación oculta al usuario.
			System.out.println("Introduce la combinación oculta que desea que resuelva la máquina");
			combinacionAResolver = usuario.introducirCombinacion();
			System.out.printf("Esta es la combinación oculta introducida: %s\n", combinacionAResolver.dibujar());
			//Empieza la partida modo fácil máquina.
			partidaFacilMaquina();

		}else if(dificultad==MEDIO){
			usuario.setDificultad(MEDIO);
			maquina.setDificultad(MEDIO);
			//Le preguntamos la combinación que desea que averigue la maquina al usuario.
			System.out.println("Introduce la combinación oculta que desea que resuelva la máquina");
			combinacionAResolver2 = usuario.introducirCombinacion();
			System.out.printf("Esta es la combinación oculta introducida: %s\n", combinacionAResolver2.dibujar());
			//La combinacion a resolver del usuario la genera la máquina aleatoriamente.
			combinacionAResolver = maquina.crearCombinacionOculta(false);
			//Empieza la partida en modo medio: usuario contra máquina
			partidaMedia();


		}else if(dificultad==DIFICIL){
			//Cada máquina le crea una combinación a su máquina adversaria.
			combinacionAResolver2 = maquina.crearCombinacionOculta(true);
			combinacionAResolver = maquina2.crearCombinacionOculta(true);
			//Empieza la partida entre las dos máquinas
			partidaDificil();
		}
		reiniciarPartida();
	}

	public String toString() {
		return String.format("COMBINACION OCULTA: %s\n", combinacionAResolver.dibujar());
	}

	private void partidaFacilUsuario() {
		Combinacion combinacionUser = new Combinacion(dificultad);
		Usuario usuario = new Usuario(dificultad);

		usuario.setCombinacionSecreta(combinacionAResolver); //Le pasamos la combinacionSecreta al usuario

		while (!combinacionAResolver.equals(combinacionUser) && numIntentos<FACIL_USUARIO.getNumIntentos()){
			combinacionUser = usuario.introducirCombinacion();//El usuario introduce la combinación
			usuario.getTablero().agregarCombinacion(combinacionUser);//Añadimos la combinación al tablero del usuario
			usuario.rellenarRespuestas(); //Rellenamos las respuestas del tablero del usuario
			System.out.println(usuario.getTablero().dibujar()); //Mostramos el tablero del usuario
			numIntentos++;
		}
		if(combinacionAResolver.equals(combinacionUser)){
			System.out.println("¡HAS GANADO!");
		}else{
			System.out.println("HAS PERDIDO...");
			System.out.println("La combinación oculta era: " + combinacionAResolver.dibujar());
		}

	}

	private void partidaFacilMaquina(){
		IA maquina = new IA(dificultad);
		Usuario usuario = new Usuario(dificultad);
		Combinacion combinacionMaquina = new Combinacion(dificultad);
		Combinacion respuestaUser;

		maquina.setCombinacionSecreta(combinacionAResolver);

		while(!combinacionMaquina.equals(combinacionAResolver) && numIntentos<FACIL_MAQUINA.getNumIntentos()) {
			combinacionMaquina = maquina.crearCombinacionOculta(false);
			maquina.getTablero().agregarCombinacion(combinacionMaquina);
			System.out.println(maquina.getTablero().dibujar());
			System.out.println();
			maquina.rellenarRespuestas();
			//Comprobamos que el usuario ha metido la respuesta correcta, es decir, el mismo número de aciertos.
			do {
				respuestaUser = usuario.preguntarRespuestas();
				if(!(usuario.comprobarAciertosCompletos(respuestaUser, maquina.getTablero().getRespuesta(numIntentos)) && usuario.comprobarAciertosColor(respuestaUser, maquina.getTablero().getRespuesta(numIntentos)))){
					System.err.println("Respuesta Incorrecta. Vuelve a introducirla por favor.");
				}
			} while (!(usuario.comprobarAciertosCompletos(respuestaUser, maquina.getTablero().getRespuesta(numIntentos)) && usuario.comprobarAciertosColor(respuestaUser, maquina.getTablero().getRespuesta(numIntentos))));
			numIntentos++;
		}
		if(combinacionAResolver.equals(combinacionMaquina)){
			System.out.println("¡HE GANADO!");
		}else{
			System.out.println("HE PERDIDO...");
			System.out.println("La combinación oculta era: " + combinacionAResolver.dibujar());
		}
	}

	private void partidaMedia(){
	    byte resCompletaUser = 0, resColorUser = 0, resCompletaMaquina = 0, resColorMaquina = 0;
		IA maquina = new IA(dificultad);
		Usuario usuario = new Usuario(dificultad);
		Combinacion combinacionMaquina = new Combinacion(dificultad);
		Combinacion combinacionUser = new Combinacion(dificultad);

		maquina.setCombinacionSecreta(combinacionAResolver2);
		usuario.setCombinacionSecreta(combinacionAResolver);

		while(!combinacionMaquina.equals(combinacionAResolver2) && !combinacionUser.equals(combinacionAResolver) && numIntentos<dificultad.getNumIntentos()){
			//La máquina mete una combinación aleatoria
			combinacionMaquina = maquina.crearCombinacionOculta(false);
			maquina.getTablero().agregarCombinacion(combinacionMaquina);
			maquina.rellenarRespuestas();
			//Ahora le preguntamos al usuario su combinación
			combinacionUser = usuario.introducirCombinacion();
			usuario.getTablero().agregarCombinacion(combinacionUser);
			usuario.rellenarRespuestas();
			System.out.println(usuario.getTablero().dibujarTableros(maquina.getTablero()));
			numIntentos++;
		}
		if(combinacionAResolver.equals(combinacionUser) && !combinacionAResolver2.equals(combinacionMaquina)){
			System.out.println("¡HAS GANADO!"); //Gana el usuario
		}else if(!combinacionAResolver.equals(combinacionUser) && combinacionAResolver2.equals(combinacionMaquina)){
            System.out.println("¡TE HE GANADO!"); //Gana la máquina
			System.out.println("La combinación oculta era: " + combinacionAResolver.dibujar());
		}else if(combinacionAResolver2.equals(combinacionMaquina) && combinacionAResolver.equals(combinacionUser)){
            System.out.println("¡EMPATE!"); //Si los dos aciertan la combinación a la vez
		}else if(!combinacionAResolver2.equals(combinacionMaquina) && !combinacionAResolver.equals(combinacionUser)){
		    //Si nadie acierta en el último intento
		    for(int i = 0; i<dificultad.getNumFichas(); i++){
		        if(usuario.getTablero().getRespuesta(numIntentos - 1).getFichaCombinacion(i).compararColor(POSICION_COLOR)){
		            resCompletaUser++;
                }else if(usuario.getTablero().getRespuesta(numIntentos - 1).getFichaCombinacion(i).compararColor(COLOR)){
		            resColorUser++;
                }
            }
            for(int i = 0; i<dificultad.getNumFichas(); i++){
				if(maquina.getTablero().getRespuesta(numIntentos - 1).getFichaCombinacion(i).compararColor(POSICION_COLOR)){
					resCompletaMaquina++;
				}else if(maquina.getTablero().getRespuesta(numIntentos - 1).getFichaCombinacion(i).compararColor(COLOR)){
					resColorMaquina++;
				}
			}
            if(resCompletaUser>resCompletaMaquina){
                System.out.println("¡HAS GANADO!");
            }else if(resCompletaMaquina>resCompletaUser){
                System.out.println("¡TE HE GANADO!");
				System.out.println("La combinación oculta era: " + combinacionAResolver.dibujar());
            }else if(resCompletaMaquina==resCompletaUser){
                if(resColorMaquina>resColorUser){
                    System.out.println("¡TE HE GANADO!");
					System.out.println("La combinación oculta era: " + combinacionAResolver.dibujar());
                }else if(resColorUser>resColorMaquina){
                    System.out.println("¡HAS GANADO!");
                }else if(resColorMaquina==resColorUser){
                    System.out.println("¡EMPATE!");
                }
            }
		}

	}
	private void partidaDificil() {
		IA maquina1 = new IA(DIFICIL);
		IA maquina2 = new IA(DIFICIL);
		Combinacion combinacionMaquina1 = new Combinacion(dificultad);
		Combinacion combinacionMaquina2 = new Combinacion(dificultad);
		maquina1.setCombinacionSecreta(combinacionAResolver);
		maquina2.setCombinacionSecreta(combinacionAResolver2);

		while(!combinacionMaquina1.equals(combinacionAResolver2) && !combinacionMaquina2.equals(combinacionAResolver)){
			combinacionMaquina1 = maquina1.crearCombinacionOculta(true);
			combinacionMaquina2 = maquina2.crearCombinacionOculta(true);
			maquina1.getTablero().agregarCombinacion(combinacionMaquina1);
			maquina2.getTablero().agregarCombinacion(combinacionMaquina2);
			maquina1.rellenarRespuestas();
			maquina2.rellenarRespuestas();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(maquina1.getTablero().dibujarTableros(maquina2.getTablero()));
			numIntentos++;
		}


	}
	private void reiniciarPartida(){
	    final byte MINIMO = 1, MAXIMO = 3;
	    boolean salir = false;
	    byte opcion;
        System.out.println("\n¿Qué desea hacer?");
        System.out.printf("1-Reiniciar partida.\n2-Regresar el menú principal\n3-Salir del juego.\n");
        opcion = leerEntre(MINIMO, MAXIMO, Incluido.TODOS, Tipo.BYTE);
		while (!salir) {
			switch(opcion){
				case 1:
					new Partida(dificultad);
					break;
				case 2:
					Menu m = new Menu();
					if(m.getOpcionMenu()==3){ //Cuando le da salir en el menu principal, que se salga de una vez del juego.
						salir = true;
					}else {
						new Partida(m.getDificultad());
						salir = true;
					}
					break;
				case 3:
					System.out.println("¡HASTA PRONTO!");
					salir = true;
					break;
			}
		}
	}

}

