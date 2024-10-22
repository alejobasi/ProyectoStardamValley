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
        int numFilas=GestionFicheros.sacarFilas();
        int numCol=GestionFicheros.sacarColumnas();
        try {
            RandomAccessFile raf=new RandomAccessFile(RUTA_FICHERO, "r");

            raf.seek(0);
            for (int fila = 0; fila < numFilas; fila++) {
                for (int col = 0; col < numCol; col++) {
                    System.out.print("[");

                    long pos = raf.getFilePointer();

                    if (raf.readInt()!=VALOR_DEFECTO_ENTERO) {
                        raf.seek(pos);
                        int idSemilla = raf.readInt();
                        System.out.print(idSemilla + "-");


                            boolean regado = raf.readBoolean();
                            if (regado==false){
                                System.out.print("F-");

                            }else {
                                System.out.print("T-");
                            }



                            int diasPlantado = raf.readInt();
                            System.out.print(diasPlantado);

                    } else {
                        raf.seek(pos);
                        int idSemilla = raf.readInt();
                        boolean regado = raf.readBoolean();
                        int diasPlantado = raf.readInt();
                        System.out.print("SS");
                    }

                    System.out.print("]");

                }
                System.out.println("");
            }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

    public static boolean columnaVacia(int columna){

        int numFilas=GestionFicheros.sacarFilas();
        int numCol=GestionFicheros.sacarColumnas();

        try {
            RandomAccessFile raf=new RandomAccessFile(RUTA_FICHERO, "r");
            boolean nula=false;



            for (int fil=0; fil<numFilas; fil++){
                for (int col=0;col<numCol; col++){
                    if (col==columna-1){
                        if (raf.readInt()==-1){
                            nula=true;
                        }



                        raf.readBoolean();


                        raf.readInt();
                    }else {
                        raf.skipBytes(TAMANO_REGISTRO);
                    }

                }
            }

            return nula;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void plantarEnColumna(Semillas sem, int columna){
        int numFilas=GestionFicheros.sacarFilas();
        int numCol=GestionFicheros.sacarColumnas();
        try {
            RandomAccessFile raf=new RandomAccessFile(RUTA_FICHERO, "rw");
            raf.seek(0);
            for (int fil=0; fil<numFilas; fil++){
                for (int col=0;col<numCol; col++){
                    if (col==columna-1){
                        raf.writeInt(sem.getId());


                        raf.writeBoolean(VALOR_DEFECTO_BOOLEAN);


                        raf.writeInt(1);
                    }else {
                        raf.skipBytes(TAMANO_REGISTRO);
                    }

                }
            }
            mostrarHuerto();
            raf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean semillaTiempo(int idSemilla,int tiempo){
        boolean resultado=false;

        return resultado;
    }

    public static void atenderCultivos(){
        int numFilas=GestionFicheros.sacarFilas();
        int numCol=GestionFicheros.sacarColumnas();


        try {
            RandomAccessFile raf=new RandomAccessFile(RUTA_FICHERO, "rw");

            for (int fil=0; fil<numFilas; fil++){
                for (int col=0;col<numCol; col++){
                    long posPrimera= raf.getFilePointer();
                   int idSemilla=raf.readInt();
                        if (idSemilla==-1){

                            raf.readBoolean();
                            raf.readInt();
                        }else {

                            raf.writeBoolean(true);
                            long posTiempo=raf.getFilePointer();
                            int tiempo=raf.readInt();

                           // raf.seek();
                           // raf.writeInt();

                        }









                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
