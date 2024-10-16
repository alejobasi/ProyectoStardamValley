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
import java.util.List;

public class Tienda {

    List<Semillas> listaTienda;


    public Tienda() {
        this.listaTienda = new ArrayList<>();
    }
    public static String RUTA_FICHERO_XML="resources"+File.separator+"semillas.xml";

    public void cargarSemillas(){

        try {
            DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder dBulider  = dbFactory.newDocumentBuilder();
            Document doc= dBulider.parse(new File(RUTA_FICHERO_XML));
            doc.getDocumentElement().normalize();

            NodeList listaSemillas=doc.getElementsByTagName("semilla");

            for (int i=0; i<listaSemillas.getLength();i++){
                Node nodoSemilla=listaSemillas.item(i);
                nodoSemilla.getAttributes();

                if (nodoSemilla.getNodeType()==Node.ELEMENT_NODE){
                    Element semilla=(Element) nodoSemilla;

                    int id= Integer.parseInt(semilla.getAttribute("id"));
                    String nombre=semilla.getElementsByTagName("nombre").item(0).getTextContent();
                    String estacion=semilla.getElementsByTagName("estacion").item(0).getTextContent();
                    int diasCrecimiento= Integer.parseInt(semilla.getElementsByTagName("diasCrecimiento").item(0).getTextContent());
                    int precioCompra= Integer.parseInt(semilla.getElementsByTagName("precioCompraSemilla").item(0).getTextContent());
                    int precioVenta= Integer.parseInt(semilla.getElementsByTagName("precioVentaFruto").item(0).getTextContent());
                    int cantidad= Integer.parseInt(semilla.getElementsByTagName("maxFrutos").item(0).getTextContent());

                    Semillas sem= new Semillas(id,nombre,estacion,diasCrecimiento,precioCompra,precioVenta,cantidad);

                    listaTienda.add(sem);

                }

            }


        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }
}
