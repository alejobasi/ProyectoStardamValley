import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Tienda {

   private static int NUM_SEMILLAS_TIENDA=3;
    private HashSet<Semillas> tiendaDiaria;

    public Tienda() {
        this.tiendaDiaria = new HashSet<>();

    }

    public HashSet<Semillas> getTiendaDiaria() {
        return tiendaDiaria;
    }

    public void setTiendaDiaria(HashSet<Semillas> tiendaDiaria) {
        this.tiendaDiaria = tiendaDiaria;
    }

    public static void generarNuevaTienda(Granja granja){


List<Semillas>disponibles=granja.getSemillasDisponibles();
        Random random = new Random();
        disponibles.sort((a, b) -> random.nextInt(3 - 1));
        HashSet<Semillas>listaFinal=new HashSet<>();

        while (listaFinal.size()<3){

            int semillaAleatoria=random.nextInt(disponibles.size());
            listaFinal.add(disponibles.get(semillaAleatoria));

        }

        granja.getTienda().setTiendaDiaria(listaFinal);


    }

    public static void mostrarTiendaDiaria(Granja granja){

        System.out.println("Tienda Diaria:");
        System.out.println("(Por cada compra se adquieren 3 semillas)");
        int ind=1;

        for(Semillas semilla: granja.getTienda().tiendaDiaria){
            System.out.println(ind+". Semilla: "+semilla.getNombre() +" Precio: "+semilla.getPrecioVenta()*NUM_SEMILLAS_TIENDA);
        }

    }


    public static void tienda(Granja granja){
        generarNuevaTienda(granja);
        mostrarTiendaDiaria(granja);

    }
}
