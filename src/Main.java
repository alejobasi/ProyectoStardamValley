import com.arquitecturajava.singleton.SingletonProperties;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Granja granja = new Granja();



      juego(granja);

    }

    /**
     * En está función tengo toda las acciones del juego y las peticiones de datos al jugador
     * @param granja

     */
    public static void juego(Granja granja) {


        Scanner sc = new Scanner(System.in);

        System.out.println("  BIENVENIDO A STARDAM VALLEY\n" +
                "-------------------------------");
        System.out.println("1. NUEVA PARTIDA");



        if (GestionFicheros.comprobarPartidaExiste()) {
            System.out.println("2. CARGAR PARTIDA");
        }
        int respuestaPartida = 0;
        do {


            respuestaPartida = sc.nextInt();

            if (respuestaPartida == 1) {

                nuevaPartida(granja);


            } else if (respuestaPartida == 2) {

            } else {
                System.out.println("Valor incorrecto vuelve a introducirlo");

                System.out.println("  BIENVENIDO A STARDAM VALLEY\n" +
                        "-------------------------------");
                System.out.println("1. NUEVA PARTIDA");
                if (GestionFicheros.comprobarPartidaExiste()) {

                    System.out.println("2. CARGAR PARTIDA");

                }

            }
        } while (respuestaPartida != 1 && respuestaPartida != 2);



    }

    public static void nuevaPartida(Granja granja){
        Scanner sc = new Scanner(System.in);

        int numFilHuerto = 0;
        int numColHuerto = 0;


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

                System.out.println("Num Filas Huerto: " + numFilHuerto + "\n" +
                        "Num Columnas Huerto: " + numColHuerto + "\n" +
                        "Dias por Estación: " + granja.getDiasPorEstacion() + "\n" +
                        "Presupuesto: " + granja.getPresupuesto() + "\n" +
                        "Estación: " + granja.getEstacion());
                GestionFHuerto.crearHuerto(numColHuerto,numFilHuerto);
                GestionFHuerto.mostrarHuerto();

            } else if (respuestaValores == 2) {

                System.out.println(" INDICA TUS VALORES\n" +
                        "--------------------");
                sc.nextLine();
                System.out.print("Numero de Columnas del Huerto: ");
                int numCol=sc.nextInt();
                System.out.print("Numero de Filas del Huerto: ");
                int numFil=sc.nextInt();
                System.out.print("Dias que dura cada Estacion: ");
                int diasEstacion=sc.nextInt();
                System.out.print("Presupuesto Inicial: ");
                double presupuesto=sc.nextDouble();
                int numEstacion=0;
                do {



                    int ind=1;
                    for (Estaciones estaciones:Estaciones.values()){
                        System.out.println(ind+". "+estaciones);
                        ind++;
                    }
                    System.out.print("Elige el numero de la Estacion");
                    numEstacion=sc.nextInt();

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

                }while (numEstacion<1&&numEstacion>4);
                numColHuerto=numCol;
                numFilHuerto=numFil;
                granja.setDiasPorEstacion( diasEstacion);
                granja.setPresupuesto(presupuesto);
                GestionFicheros.crearFicheroPropertiesPersonalizado(numColHuerto,numFilHuerto,granja.getDiasPorEstacion(), granja.getPresupuesto(), granja.getEstacion());
                GestionFHuerto.crearHuerto(numColHuerto,numFilHuerto);
                GestionFHuerto.mostrarHuerto();

            } else {
                System.out.println("Valor incorrecto vuelve a introducirlo");
                System.out.println("\n ELIGE UNA OPCIÓN");
                System.out.println("----------------------");
                System.out.println("1. Valores por defecto");
                System.out.println("2. Valores Personalizados");

            }
        } while (respuestaValores != 1 && respuestaValores != 2);



    }

    public static void menuOpciones(Granja granja){
        System.out.println("\n STARDAM VALLEY\n" +
                          "---------------------");
        System.out.println("1. INICIAR NUEVO DIA");
        System.out.println("2. TIENDA");
        System.out.println("3. ATENDER CULTIVOS");
        System.out.println("4. PLANTAR CULTIVOS EN COLUMNA");
        System.out.println("5. VENDER COSECHA");
        System.out.println("6. MOSTRAR INFORMACIÓN DE LA GRANJA");
        System.out.println("7. SALIR");

    }
}










