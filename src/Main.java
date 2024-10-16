import com.arquitecturajava.singleton.SingletonProperties;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static String RUTA_JUEGO = "resources" + File.separator + "StardamValley.bin";

    public static void main(String[] args) {
        //Valores necesariso
        Granja granja = new Granja();
        int numFilHuerto = 0;
        int numColHuerto = 0;
        int diasPorEstacion = 0;


        juego(granja, numFilHuerto, numColHuerto, diasPorEstacion);

    }



    /**
     * En está función tengo toda las acciones del juego y las peticiones de datos al jugador
     * @param granja
     * @param numFilHuerto
     * @param numColHuerto
     * @param diasPorEstacion
     */
    public static void juego(Granja granja, int numFilHuerto, int numColHuerto, int diasPorEstacion) {

        Scanner sc = new Scanner(System.in);

        System.out.println("  BIENVENIDO A STARDAM VALLEY\n" +
                "-------------------------------");
        System.out.println("1. NUEVA PARTIDA");
        //Solo mostramos CARGAR PARTIDA cuando no exista un archivo de Partida
        if (comprobarPartidaExiste()) {
            System.out.println("2. CARGAR PARTIDA");
        }
        int respuestaPartida = 0;
        do {


            respuestaPartida = sc.nextInt();

            if (respuestaPartida == 1) {

            } else if (respuestaPartida == 2) {

            } else {
                System.out.println("Valor incorrecto vuelve a introducirlo");

                System.out.println("  BIENVENIDO A STARDAM VALLEY\n" +
                        "-------------------------------");
                System.out.println("1. NUEVA PARTIDA");
                if (comprobarPartidaExiste()) {

                    System.out.println("2. CARGAR PARTIDA");

                }

            }
        } while (respuestaPartida != 1 && respuestaPartida != 2);


    }

    public static boolean comprobarPartidaExiste() {

        Path path = Paths.get(RUTA_JUEGO);
        if (Files.exists(path)) {
            return true;
        } else {

            return false;
        }
    }

    public static void nuevaPartida(int numFilHuerto, int numColHuerto, int diasPorEstacion, double presupuesto, Estaciones estacion) {
        Scanner sc = new Scanner(System.in);
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
                presupuesto= Double.parseDouble(sp.getPropiedad("presupuesto"));
                diasPorEstacion= Integer.parseInt(sp.getPropiedad("duracionDiasEstacion"));
                estacion= Estaciones.valueOf(sp.getPropiedad("estacion"));

                System.out.println("Num Filas Huerto: " + numFilHuerto + "\n" +
                        "Num Columnas Huerto: " + numColHuerto + "\n" +
                        "Dias por Estación: " + diasPorEstacion + "\n" +
                        "Presupuesto: " + presupuesto + "\n" +
                        "Estación: " + estacion);
            } else if (respuestaValores == 2) {

            } else {
                System.out.println("Valor incorrecto vuelve a introducirlo");
                System.out.println("\n ELIGE UNA OPCIÓN");
                System.out.println("----------------------");
                System.out.println("1. Valores por defecto");
                System.out.println("2. Valores Personalizados");

            }
        } while (respuestaValores != 1 && respuestaValores != 2);
    }


}