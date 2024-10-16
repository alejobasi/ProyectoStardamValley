import java.io.*;
import java.util.Properties;

public class GestionFicheros {
    public static String RUTA_VALORES_PRE="resources"+ File.separator+"default_config.properties";



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

}
