import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionFBinario {
    public static String RUTA_JUEGO = "resources" + File.separator + "StardamValley.bin";

    public static boolean comprobarPartidaExiste() {

        Path path = Paths.get(RUTA_JUEGO);
        if (Files.exists(path)) {
            return true;
        } else {

            return false;
        }
    }

    public static void crearFicheroBinario(){

        Path path=Paths.get(RUTA_JUEGO);

        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
