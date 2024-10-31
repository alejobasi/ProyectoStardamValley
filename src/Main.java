import com.arquitecturajava.singleton.SingletonProperties;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Granja granja = new Granja();
        int diasCont=1;


      juego(granja,diasCont);

    }

    /**
     * En está función tengo toda las acciones del juego y las peticiones de datos al jugador
     * @param granja

     */
    public static void juego(Granja granja,int diasCont) {


        Scanner sc = new Scanner(System.in);

        System.out.println("  BIENVENIDO A STARDAM VALLEY\n" +
                "-------------------------------");
        System.out.println("1. NUEVA PARTIDA");



        if (GestionFBinario.comprobarPartidaExiste()) {
            System.out.println("2. CARGAR PARTIDA");

        }
        int respuestaPartida = 0;
        do {


            respuestaPartida = sc.nextInt();

            if (respuestaPartida == 1) {

                nuevaPartida(granja);


            } else if (respuestaPartida == 2) {
                granja=GestionFBinario.cargarParida();

            } else {
                System.out.println("Valor incorrecto vuelve a introducirlo");

                System.out.println("  BIENVENIDO A STARDAM VALLEY\n" +
                        "-------------------------------");
                System.out.println("1. NUEVA PARTIDA");
                if (GestionFBinario.comprobarPartidaExiste()) {

                    System.out.println("2. CARGAR PARTIDA");

                }

            }
        } while (respuestaPartida != 1 && respuestaPartida != 2);


        menuOpciones(granja,diasCont);

    }

    /**
     * Hace las funciones para que se cree una nueva partida, borra si hay archivos creados y hace las demás funciones
     * @param granja
     */
    public static void nuevaPartida(Granja granja){
        GestionFicheros.borrarNuevaPartida();
        GestionBBDD.recogerAnimales(granja);
        Scanner sc = new Scanner(System.in);

        int numFilHuerto = 0;
        int numColHuerto = 0;
        GestionFBinario.crearFicheroBinario();

        int respuestaValores = 0;
        System.out.println("\n ELIGE UNA OPCIÓN");
        System.out.println("----------------------");
        System.out.println("1. Valores por defecto");
        System.out.println("2. Valores Personalizados");

        do {


            respuestaValores = sc.nextInt();

            if (respuestaValores == 1) {
                SingletonProperties sp=SingletonProperties.getInstancia();
                numFilHuerto= Integer.parseInt(sp.getPropiedad("numFilHuerto"));
                numColHuerto= Integer.parseInt(sp.getPropiedad("numColHuerto"));
                granja.setPresupuesto( Double.parseDouble(sp.getPropiedad("presupuesto")));
                granja.setDiasPorEstacion( Integer.parseInt(sp.getPropiedad("duracionDiasEstacion")));
                granja.setEstacion( Estaciones.valueOf(sp.getPropiedad("estacion")));
                granja.setSemillasDisponibles(GestionFSemillas.semillasDisponibles(granja.getEstacion(),granja.getSemillasPorEstacion()));

                System.out.println("Num Filas Huerto: " + numFilHuerto + "\n" +
                        "Num Columnas Huerto: " + numColHuerto + "\n" +
                        "Dias por Estación: " + granja.getDiasPorEstacion() + "\n" +
                        "Presupuesto: " + granja.getPresupuesto() + "\n" +
                        "Estación: " + granja.getEstacion());


                GestionFHuerto.crearHuerto();

            } else if (respuestaValores == 2) {
                int numCol=0;
                int numFil=0;
                int diasEstacion=0;
                double presupuesto=0;
                System.out.println(" INDICA TUS VALORES\n" +
                        "--------------------");
                sc.nextLine();

                do {
                    System.out.print("Numero de Columnas del Huerto (MAX:50) (MIN:1) : ");

                    numCol=sc.nextInt();
                }while (numCol<1||numCol>50);
                do {

                    System.out.print("Numero de Filas del Huerto (MAX:50) (MIN:1) :");
                     numFil=sc.nextInt();

                 }while (numFil<1||numFil>50);

                do {
                    System.out.print("Dias que dura cada Estacion: (MAX:80) (MIN:2)");
                     diasEstacion=sc.nextInt();
                }while (diasEstacion<2||diasEstacion>80);

                do {
                System.out.print("Presupuesto Inicial: (MAX:50000)");
                 presupuesto=sc.nextDouble();
                }while (presupuesto<0||presupuesto>500000);

                int numEstacion=0;
                boolean validacion;
                do {


                    validacion = true;
                    int ind=1;
                    for (Estaciones estaciones:Estaciones.values()){
                        System.out.println(ind+". "+estaciones);
                        ind++;
                    }
                    System.out.print("Elige el numero de la Estacion");
                    numEstacion=sc.nextInt();

                    if (numEstacion < 1 || numEstacion > 4) {
                        validacion=false;
                    }

                }while (!validacion);
                    switch (numEstacion){
                        case 1:
                            granja.setEstacion(Estaciones.PRIMAVERA);
                            break;
                        case 2:
                            granja.setEstacion(Estaciones.VERANO);
                            break;
                        case 3:
                            granja.setEstacion(Estaciones.OTOÑO);
                            break;
                        case 4:
                            granja.setEstacion(Estaciones.INVIERNO);
                            break;
                    }


                numColHuerto=numCol;
                numFilHuerto=numFil;
                granja.setDiasPorEstacion( diasEstacion);
                granja.setPresupuesto(presupuesto);
                GestionFicheros.crearFicheroPropertiesPersonalizado(numColHuerto,numFilHuerto,granja.getDiasPorEstacion(), granja.getPresupuesto(), granja.getEstacion());
                granja.setSemillasDisponibles(GestionFSemillas.semillasDisponibles(granja.getEstacion(),granja.getSemillasPorEstacion()));

                GestionFHuerto.crearHuerto();


            } else {
                System.out.println("Valor incorrecto vuelve a introducirlo");
                System.out.println("\n ELIGE UNA OPCIÓN");
                System.out.println("----------------------");
                System.out.println("1. Valores por defecto");
                System.out.println("2. Valores Personalizados");

            }
        } while (respuestaValores != 1 && respuestaValores != 2);

        Tienda.generarNuevaTienda(granja);

    }

    /**
     *
     * Es el crud donde se muestran las opciones, es donde el usuario interactua
     * @param granja donde se guarda todo
     * @param diasCont para el cambio de estacion
     */
    public static void menuOpciones(Granja granja, int diasCont){

        boolean cultivosAtendidos=false;
        Scanner sc=new Scanner(System.in);
        int respuesta=0;
        boolean salida=false;
        int diasPorEstacion=GestionFicheros.sacarDiasEstacion();

        do {


        System.out.println("\n STARDAM VALLEY\n" +
                          "---------------------");
        System.out.println("1. INICIAR NUEVO DIA");
        System.out.println("2. HUERTO");
        System.out.println("3. ESTABLO");
        System.out.println("4. SALIR");

        respuesta=sc.nextInt();

        switch (respuesta){
            case 1:
                diasCont++;
                if (diasPorEstacion==diasCont){


                    granja.cambiarEstacion();
                    System.out.print("¡¡¡ Cambio de Estacion a "+granja.getEstacion()+"!!!");

                    GestionFHuerto.crearHuerto();
                    granja.setSemillasDisponibles(GestionFSemillas.semillasDisponibles(granja.getEstacion(),granja.getSemillasPorEstacion()));
                    diasCont=1;

                }
                granja.setDiaJuego(granja.getDiaJuego()+1);
                Tienda.generarNuevaTienda(granja);
                cultivosAtendidos=false;
                menuOpciones(granja, diasCont);


            break;

            case 2:
               huertoOpciones(granja);
                break;

            case 3:
                establoOpciones(granja);
                break;

            case 4:

                GestionFBinario.guardarPartida(granja);
                salida=true;

                break;



        }

    }while (respuesta>1&&respuesta<4&&!salida);

    }

    public static void huertoOpciones(Granja granja){
        boolean cultivosAtendidos=false;
        Scanner sc=new Scanner(System.in);
        int respuesta=0;
        boolean salida2=false;

        do {


            System.out.println("\n HUERTO\n" +
                    "---------------------");

            System.out.println("1. TIENDA");
            System.out.println("2. ATENDER CULTIVOS");
            System.out.println("3. PLANTAR CULTIVOS EN COLUMNA");
            System.out.println("4. VENDER COSECHA");
            System.out.println("5. MOSTRAR INFORMACIÓN DE LA GRANJA");
            System.out.println("6. SALIR");

            respuesta=sc.nextInt();

            switch (respuesta){


                case 1:
                    Tienda.comprarTiendaDiaria(granja);;
                    break;

                case 2:
                    if (!cultivosAtendidos){
                        GestionFHuerto.atenderCultivos(granja);
                        cultivosAtendidos=true;
                    }else {
                        System.out.println("Los cultivos ya están atendidos");
                    }

                    break;

                case 3:
                    System.out.println("Dime en que columna vas a plantar: ");
                    int columna=sc.nextInt();
                    if (GestionFHuerto.columnaVacia(columna)){
                        if (granja.getAlmacen().getSemillas().isEmpty()){
                            System.out.println("No hay semillas para plantar");
                        }else {
                            Semillas sem=granja.getAlmacen().elegirSemillaPlantar();
                            GestionFHuerto.plantarEnColumna(sem,columna);

                        }
                    }else {
                        System.out.println("Columna Plantada");
                    }


                    break;

                case 4:
                    granja.venderFrutos();
                    break;

                case 5:
                    Granja.mostrarGranja(granja);
                    break;

                case 6:

                    salida2=true;
                    break;
            }

        }while (respuesta>1&&respuesta<6&&!salida2);

    }

    public static void establoOpciones(Granja granja){
        boolean animalesAlimemtados=false;
        Scanner sc=new Scanner(System.in);
        int respuesta=0;
        boolean salida3=false;

        do {


            System.out.println("\n ESTABLO\n" +
                    "---------------------");

            System.out.println("1. PRODUCIR");
            System.out.println("2. ALIMENTAR");
            System.out.println("3. VENDER PRODUCTOS");
            System.out.println("4. RELLENAR COMEDERO");
            System.out.println("5. MOSTRAR ANIMALES");
            System.out.println("6. SALIR");

            respuesta=sc.nextInt();

            switch (respuesta){


                case 1:


                case 2:
                    animalesAlimemtados=true;


                    break;

                case 3:



                    break;

                case 4:
                    granja.getEstablo().rellenarComedero(granja);
                    break;

                case 5:
                    granja.getEstablo().mostrarEstablo();
                    break;

                case 6:

                    salida3=true;
                    break;
            }

        }while (respuesta>1&&respuesta<6&&!salida3);
    }
}










