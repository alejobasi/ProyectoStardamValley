import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
        public static String RUTA_JUEGO="resources"+ File.separator+"StardamValley.bin";
    public static void main(String[] args) {
        Granja granja = new Granja();
juego();


    }

    public static void juego(){

        System.out.println("  BIENVENIDO A STARDAM VALLEY\n" +
                         "-------------------------------");
        System.out.println("1. NUEVA PARTIDA");
        if (comprobarPartidaExiste()){

            System.out.println("2. CARGAR PARTIDA");

        }

    }

    public static boolean comprobarPartidaExiste(){

        Path path= Paths.get(RUTA_JUEGO);
        if (Files.exists(path)){
            return true;
        }else {

            return false;
        }
    }
}