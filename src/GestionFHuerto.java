import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GestionFHuerto {

    public static String RUTA_FICHERO="resources"+ File.separator+"huerto.dat";
    public static int TAMANO_ID_SEMILLA=Integer.BYTES;
    public static int TAMANO_REGADA=1;
    public static int TAMANO_NUM_DIAS_CRECIMIENTO=Integer.BYTES;
    public static int TAMANO_REGISTRO=TAMANO_ID_SEMILLA+TAMANO_REGADA+TAMANO_NUM_DIAS_CRECIMIENTO;
    public static int VALOR_DEFECTO_ENTERO=-1;
    public static boolean VALOR_DEFECTO_BOOLEAN=false;

    public static void crearHuerto(int numCol, int numFil){
        try {
            RandomAccessFile raf=new RandomAccessFile(RUTA_FICHERO, "rw");
            for (int fil=0; fil<numFil; fil++){
                for (int col=0; col<numCol; col++){
                    raf.writeInt(VALOR_DEFECTO_ENTERO);


                    raf.writeBoolean(VALOR_DEFECTO_BOOLEAN);


                    raf.writeInt(VALOR_DEFECTO_ENTERO);


                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void mostrarHuerto(){
        try {
            RandomAccessFile raf=new RandomAccessFile(RUTA_FICHERO, "r");

            while (raf.getFilePointer()< raf.length()){

                int idSemilla= raf.readInt();
                System.out.println("ID: "+idSemilla);

                boolean regado= raf.readBoolean();
                System.out.println("Regado: "+regado);

                int diasPlantado= raf.readInt();
                System.out.println("Dias Plantado: "+diasPlantado);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
