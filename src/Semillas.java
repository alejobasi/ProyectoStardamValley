import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Semillas {

    protected int id;
    protected String nombre;
    protected String estacion;
    protected int diasCrecimiento;
    protected int precio;
    protected int cantidad;

    public static String RUTA_FICHERO_XML=""

    public Semillas(int id, String nombre, String estacion, int diasCrecimiento, int precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.estacion = estacion;
        this.diasCrecimiento = diasCrecimiento;
        this.precio = precio;
        this.cantidad = cantidad;
    }


    public void cargarSemillas(){
        DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
        DocumentBuilder dBulider  = dbFactory.newDocumentBuilder();
        Document doc=dBulider.parse(new File(RUTA_FICHERO_XML));
        doc.getDocumentElement().normalize();
    }
}
