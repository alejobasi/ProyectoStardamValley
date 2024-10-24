import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionFBinario {
    public static String RUTA_JUEGO = "resources" + File.separator + "StardamValley.bin";

    /**
     * Comprueba si hay una partida guardada, para mostrar o no la opción de Cargar Partida
     * @return
     */
    public static boolean comprobarPartidaExiste() {

        Path path = Paths.get(RUTA_JUEGO);
        if (Files.exists(path)) {
            return true;
        } else {

            return false;
        }
    }

    /**
     * Crea el fichero binario donde se guardan todos los datos de la partida
     */
    public static void crearFicheroBinario(){

        Path path=Paths.get(RUTA_JUEGO);

        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Para cuando sales se guarden los progresos, manda el objeto granja al archivo binario
     * @param granja
     */
    public static void guardarPartida(Granja granja){
        Path path=Paths.get(RUTA_JUEGO);

        try {
            OutputStream archivoSalida=Files.newOutputStream(path);
            ObjectOutputStream flujoSalida=new ObjectOutputStream(archivoSalida);
            flujoSalida.writeObject(granja);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Devuelve una partida, ya guarda, para seguir con tu progreso
     * @return
     */
    public static Granja cargarParida(){

        Granja granja;
        Path path=Paths.get(RUTA_JUEGO);

        try {
            InputStream ArchivoEntrada=Files.newInputStream(path);
            ObjectInputStream flujoEntrada=new ObjectInputStream(ArchivoEntrada);
            granja=(Granja) flujoEntrada.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
return granja;
    }

}
