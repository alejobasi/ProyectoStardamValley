import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class GestionFicheros {
    public static String RUTA_VALORES_POR_DEFECTO="resources"+ File.separator+"default_config.properties";
    public static String RUTA_JUEGO = "resources" + File.separator + "StardamValley.bin";
    public static String RUTA_VALORES_PERSONALIZADOS="resources"+ File.separator+"personalized_config.properties";
    public static String RUTA_FICHERO_HUERTO="resources"+ File.separator+"huerto.dat";




    public static boolean comprobarPartidaExiste() {

        Path path = Paths.get(RUTA_JUEGO);
        if (Files.exists(path)) {
            return true;
        } else {

            return false;
        }
    }

    public static void crearFicheroPropertiesPersonalizado(int numColHuerto, int numFilHuerto, int diasPorEstacion ,double presupuesto, Estaciones estacion){

        Path valoresPorDefecto=Paths.get(RUTA_VALORES_POR_DEFECTO);
        Path valoresPersonalizados=Paths.get(RUTA_VALORES_PERSONALIZADOS);

        try {
            Files.copy(valoresPorDefecto,valoresPersonalizados);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Properties properties= new Properties();

        try {
            properties.load(new FileInputStream(RUTA_VALORES_PERSONALIZADOS));

            properties.setProperty("numFilHuerto", String.valueOf(numFilHuerto));
            properties.setProperty("numColHuerto", String.valueOf(numColHuerto));
            properties.setProperty("presupuesto", String.valueOf(presupuesto));
            properties.setProperty("estacion", String.valueOf(estacion));
            properties.setProperty("duracionDiasEstacion", String.valueOf(diasPorEstacion));

            properties.store(new FileOutputStream(RUTA_VALORES_PERSONALIZADOS),"");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void borrarNuevaPartida(){
        Path pathHuerto=Paths.get(RUTA_FICHERO_HUERTO);
        Path pathJuego=Paths.get(RUTA_JUEGO);
        Path pathPersonalizados=Paths.get(RUTA_VALORES_PERSONALIZADOS);

        if (Files.exists(pathJuego)){
            try {
                Files.delete(pathJuego);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (Files.exists(pathHuerto)){
            try {
                Files.delete(pathHuerto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (Files.exists(pathPersonalizados)){
            try {
                Files.delete(pathPersonalizados);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static int sacarFilas(){
        Properties properties= new Properties();
        int numFila;
        if (Files.exists(Path.of(RUTA_VALORES_PERSONALIZADOS))){
            try {
                properties.load(new FileInputStream(RUTA_VALORES_PERSONALIZADOS));
                 numFila=Integer.parseInt(properties.getProperty("numFilHuerto"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            try {
                properties.load(new FileInputStream(RUTA_VALORES_POR_DEFECTO));
                numFila=Integer.parseInt(properties.getProperty("numFilHuerto"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return numFila;
    }

    public static int sacarColumnas(){
        Properties properties= new Properties();
        int numFila;
        if (Files.exists(Path.of(RUTA_VALORES_PERSONALIZADOS))){
            try {
                properties.load(new FileInputStream(RUTA_VALORES_PERSONALIZADOS));
                numFila=Integer.parseInt(properties.getProperty("numColHuerto"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            try {
                properties.load(new FileInputStream(RUTA_VALORES_POR_DEFECTO));
                numFila=Integer.parseInt(properties.getProperty("numColHuerto"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return numFila;
    }

    public static void mostrarGranja(Granja granja){
        System.out.println("INFORMACIÓN DE LA GRNAJA: ");
        System.out.println(" - Día de juego: "+granja.getDiaJuego());
        System.out.println(" - Dinero disponible: "+granja.getPresupuesto());
        System.out.println(" - Estación: "+granja.getEstacion());
        System.out.print(" - Semillas en Venta: ");Tienda.mostrarTiendaDiaria(granja);
        System.out.println("");
        System.out.println(" - Estado del huerto: ");GestionFHuerto.mostrarHuerto();

    }

}
