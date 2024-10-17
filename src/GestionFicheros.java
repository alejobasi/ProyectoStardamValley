import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class GestionFicheros {
    public static String RUTA_VALORES_PRE="resources"+ File.separator+"default_config.properties";
    public static String RUTA_JUEGO = "resources" + File.separator + "StardamValley.bin";



    public static void crearHuerto(int numCol, int numFil){
        try {
            RandomAccessFile raf=new RandomAccessFile("resources"+File.separator+"huerto.dat", "rw");
            for (int col=0; col<numCol; col++){
                for (int fil=0; fil<numFil; fil++){
                    raf.writeInt(-1);


                    raf.writeBoolean(false);


                    raf.writeInt(-1);


                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean comprobarPartidaExiste() {

        Path path = Paths.get(RUTA_JUEGO);
        if (Files.exists(path)) {
            return true;
        } else {

            return false;
        }
    }

}
