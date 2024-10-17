import com.arquitecturajava.singleton.SingletonProperties;

import java.util.Scanner;

public class Granja {

    protected Tienda tienda;
    protected double presupuesto;
    protected Estaciones estacion;

    public Granja() {
        tienda=new Tienda();
        tienda.cargarSemillas();
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }





    /**
     * En está función tengo toda las acciones del juego y las peticiones de datos al jugador
     * @param granja

     */
    public static void juego(Granja granja) {
        int numFilHuerto = 0;
        int numColHuerto = 0;
        int diasPorEstacion = 0;
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
                        granja.presupuesto= Double.parseDouble(sp.getPropiedad("presupuesto"));
                        diasPorEstacion= Integer.parseInt(sp.getPropiedad("duracionDiasEstacion"));
                        granja.estacion= Estaciones.valueOf(sp.getPropiedad("estacion"));

                        System.out.println("Num Filas Huerto: " + numFilHuerto + "\n" +
                                "Num Columnas Huerto: " + numColHuerto + "\n" +
                                "Dias por Estación: " + diasPorEstacion + "\n" +
                                "Presupuesto: " + granja.presupuesto + "\n" +
                                "Estación: " + granja.estacion);
                        GestionFicheros.crearHuerto(numColHuerto,numFilHuerto);
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
                                granja.estacion=Estaciones.PRIMAVERA;
                                break;
                            case 2:
                                granja.estacion=Estaciones.VERANO;
                                break;
                            case 3:
                                granja.estacion=Estaciones.OTOÑO;
                                break;
                            case 4:
                                granja.estacion=Estaciones.INVIERNO;
                                break;
                        }

                    }while (numEstacion<1&&numEstacion>4);
                        numColHuerto=numCol;
                        numFilHuerto=numFil;
                        diasPorEstacion=diasEstacion;
                        granja.presupuesto=presupuesto;
                        GestionFicheros.crearFicheroPropertiesPersonalizado(numColHuerto,numFilHuerto,diasPorEstacion,granja.presupuesto,granja.estacion);
                        GestionFicheros.crearHuerto(numColHuerto,numFilHuerto);

                    } else {
                        System.out.println("Valor incorrecto vuelve a introducirlo");
                        System.out.println("\n ELIGE UNA OPCIÓN");
                        System.out.println("----------------------");
                        System.out.println("1. Valores por defecto");
                        System.out.println("2. Valores Personalizados");

                    }
                } while (respuestaValores != 1 && respuestaValores != 2);

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

        do {


        }

    }
}
