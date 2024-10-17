import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class GestionFicheros {
    public static String RUTA_VALORES_POR_DEFECTO="resources"+ File.separator+"default_config.properties";
    public static String RUTA_JUEGO = "resources" + File.separator + "StardamValley.bin";
    public static String RUTA_VALORES_PERSONALIZADOS="resources"+ File.separator+"personalized_config.properties";




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

}
