import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GestionFHuerto {

    public static String RUTA_FICHERO="resources"+ File.separator+"huerto.dat";
    public static int TAMANO_ID_SEMILLA=Integer.BYTES;
    public static int TAMANO_REGADA=1;
    public static int TAMANO_NUM_DIAS_CRECIMIENTO=Integer.BYTES;
    public static int TAMANO_REGISTRO=TAMANO_ID_SEMILLA+TAMANO_REGADA+TAMANO_NUM_DIAS_CRECIMIENTO;
    public static int VALOR_DEFECTO_ENTERO=-1;
    public static boolean VALOR_DEFECTO_BOOLEAN=false;



    /**
     * Crea el fichero del huerto con las columnas y filas indicadas

     */
    public static void crearHuerto(){
        int numCol=GestionFicheros.sacarColumnas();
        int numFil=GestionFicheros.sacarFilas();
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

    /**
     * Muestra por pantalla los valores del huerto de forma legible para el jugador
     */
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

    /**
     * Comprueba que la columna pasada por parámetro está vacia, para poder cosechar en ella
     * @param columna
     * @return
     */
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

    /**
     * Planta la semilla indicada en la columna elegida y reescribe el fichero con los nuevos datos
     * @param sem
     * @param columna
     */
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

    /**
     * Devuelve un True o un False dependiendo si la semilla ha llegado al tiempo indicado para recogerse
     * @param idSemilla
     * @param tiempo
     * @return
     */
    public static boolean semillaTiempo(int idSemilla,int tiempo){
        boolean resultado=false;
        List<Semillas>listaSemillas=GestionFSemillas.cargarSemillas();
        for (Semillas sem:listaSemillas){
            if (sem.getId()==idSemilla){
            if (sem.getDiasCrecimiento()==tiempo){
                resultado=true;
            }
        }
        }
        return resultado;
    }

    /**
     * Sirve para regar los cultivos, pone todos los false a true y avanza en un día el parámetro para contar los días que lleva plantado
     * @param granja
     */
    public static void atenderCultivos(Granja granja){
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
                            boolean semillaTerminada=semillaTiempo(idSemilla,tiempo);



                            if (semillaTerminada==true){
                                System.out.println("!!FRUTOS RECOGIDOS!!");
                            }

                            if (semillaTerminada){
                                granja.getAlmacen().anadirFrutoAlmacen(idSemilla);
                                raf.seek(posPrimera);
                                raf.writeInt(VALOR_DEFECTO_ENTERO);

                                raf.writeBoolean(VALOR_DEFECTO_BOOLEAN);

                                raf.writeInt(VALOR_DEFECTO_ENTERO);
                            }else {
                                raf.seek(posTiempo);
                                raf.writeInt(tiempo+1);
                            }


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
