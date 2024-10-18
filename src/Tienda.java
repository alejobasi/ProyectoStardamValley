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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Tienda {

   private static int NUM_SEMILLAS_TIENDA=3;
    private HashSet<Semillas> tiendaDiaria;

    public Tienda() {

    }

    public HashSet<Semillas> getTiendaDiaria() {
        return tiendaDiaria;
    }

    public void setTiendaDiaria(HashSet<Semillas> tiendaDiaria) {
        this.tiendaDiaria = tiendaDiaria;
    }

    public static void generarNuevaTienda(Granja granja){

        System.out.println("Tienda Diaria:");
        System.out.println("(Por cada compra se adquieren 3 semillas)");
        int ind=1;

        for(Semillas semilla: granja.getTienda().tiendaDiaria){
            System.out.println(ind+". Semilla: "+semilla.getNombre() +" Precio: "+semilla.getPrecioVenta()*NUM_SEMILLAS_TIENDA);
        }



    }

    public static void mostrarTiendaDiaria(){



    }


    public static void tienda(Granja granja){

    }
}
