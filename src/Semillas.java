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
import java.util.List;

public class Semillas {

    protected int id;
    protected String nombre;
    protected String estacion;
    protected int diasCrecimiento;
    protected int precioCompra;
    protected int precioVenta;
    protected int cantidad;


    public Semillas(int id, String nombre,String estacion,  int diasCrecimiento, int precioCompra, int precioVenta, int cantidad) {
        this.estacion = estacion;
        this.id = id;
        this.nombre = nombre;
        this.diasCrecimiento = diasCrecimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
    }
}
