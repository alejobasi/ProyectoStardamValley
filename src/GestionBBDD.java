import com.arquitecturajava.singleton.SingletonBBDDProperties;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionBBDD {
    private static GestionBBDD instancia;
    private Connection conn;

    SingletonBBDDProperties sp = SingletonBBDDProperties.getInstancia();
    private String url = sp.getPropiedad("DB_URL");
    private String usuario = sp.getPropiedad("DB_USER");
    private String contrasena = sp.getPropiedad("DB_PASS");
    private String database = sp.getPropiedad("DB_NAME");

    private GestionBBDD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url + database, usuario, contrasena);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static GestionBBDD getInstance() {
        if (instancia == null) {
            instancia = new GestionBBDD();
        }
        return instancia;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public static List<Alimento> recogerAlimentos() {
        List<Alimento> alimentos = new ArrayList<>();



        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();

            PreparedStatement stmt = conn.prepareStatement("Select * from alimentos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_alimento = rs.getInt("id");

                String nombre_alimento = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad= rs.getInt("cantidad_disponible");
                Alimento alimento = new Alimento(id_alimento, nombre_alimento, precio, cantidad);

                alimentos.add(alimento);


            }
            return alimentos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Producto> recogerProductos() {
        List<Producto> productos = new ArrayList<>();



        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();

            PreparedStatement stmt = conn.prepareStatement("Select * from productos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_producto= rs.getInt("id");

                String nombre_producto = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad= rs.getInt("cantidad_disponible");
                Producto producto = new Producto(id_producto, nombre_producto, precio, cantidad);

                productos.add(producto);


            }
            return productos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static Alimento recogerAlimentoporId(int id) {



        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();

            PreparedStatement stmt = conn.prepareStatement("Select * from alimentos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_alimento = rs.getInt("id");
                if (id_alimento == id) {
                    String nombre_alimento = rs.getString("nombre");
                    double precio = rs.getDouble("precio");
                    Alimento alimento = new Alimento(id_alimento, nombre_alimento, precio);
                    return alimento;
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /*public static Producto recogerProductoporId(int id) {

        try {
            PreparedStatement stmt = conn.prepareStatement("Select * from productos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_producto = rs.getInt("id");
                if (id_producto == id) {
                    String nombre_producto = rs.getString("nombre");
                    double precio = rs.getDouble("precio");
                    Producto producto = new Producto(id_producto, nombre_producto, precio);
                    return producto;
                }

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }*/

    public static void recogerAnimales(Granja granja) {

        List<Animales> lista = new ArrayList<>();

        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();

            PreparedStatement stmt = conn.prepareStatement("Select * from animales a JOIN alimentos al ON a.id_alimento=al.id JOIN productos p ON a.id_producto=p.id");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("a.id");
                Animal tipo = Animal.valueOf(rs.getString("a.tipo"));
                String nombre = rs.getString("a.nombre");
                int dia_insercion = rs.getInt("a.dia_insercion");
                int peso = rs.getInt("a.peso");

                int id_alimento = rs.getInt("al.id");
                String nombre_alimento = rs.getString("al.nombre");
                double precio_alimento = rs.getDouble("al.precio");

                int id_producto = rs.getInt("p.id");
                String nombre_producto = rs.getString("p.nombre");
                double precio_producto = rs.getDouble("p.precio");


                Alimento alimento = new Alimento(id_alimento,nombre_alimento,precio_alimento);
                Producto producto = new Producto(id_producto,nombre_producto,precio_producto);
                if (tipo == Animal.VACA) {
                    Vaca vaca = new Vaca(id, tipo, nombre, dia_insercion, alimento, producto, peso);
                    lista.add(vaca);
                } else if (tipo == Animal.OVEJA) {
                    Oveja oveja = new Oveja(id, tipo, nombre, dia_insercion, alimento, producto);
                    lista.add(oveja);
                } else if (tipo==Animal.GALLINA){

                    Gallina gallina = new Gallina(id, tipo, nombre, dia_insercion, alimento, producto);
                    lista.add(gallina);
                }else if (tipo==Animal.CERDO){
                    Cerdo cerdo = new Cerdo(id, tipo, nombre, dia_insercion, alimento, producto);
                    lista.add(cerdo);
                }

            }
            granja.getEstablo().setAnimales(lista);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int recogerCantidadAlimeto(int id) {
        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();

            PreparedStatement stmt = conn.prepareStatement("Select * from alimentos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_alimento = rs.getInt("id");
                if (id_alimento == id) {
                    int cantidad = rs.getInt("cantidad_disponible");
                    return cantidad;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public static void rellenarCantidadAlimento(int id_alimeto) {
        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();

            String query = "Update alimentos Set cantidad_disponible = ? where id=?";
            PreparedStatement stmt = conn.prepareStatement(query);


            stmt.setInt(1, 25);
            stmt.setInt(2, id_alimeto);
            stmt.executeUpdate();


            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


    }
    public static void transsacciones( double precio ,Transaccion transaccion, Elemento elemento){

        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();
            String query2 = "INSERT INTO transacciones (tipo_transaccion,tipo_elemento,precio,fecha_transaccion) VALUES(?,?,?,?)";
            PreparedStatement stmt2 = conn.prepareStatement(query2);

            stmt2.setString(1, transaccion.toString());
            stmt2.setString(2, elemento.toString());
            stmt2.setDouble(3, precio);
            stmt2.setString(4, LocalDateTime.now().toString());

            stmt2.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void actualizarAlimento(Alimento alimento, int cantidad){
        try {
        GestionBBDD gestionBBDD = GestionBBDD.getInstance();
        Connection conn = gestionBBDD.getConn();
        String query = "Update alimentos Set cantidad_disponible = cantidad_disponible-? where id=?";
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setInt(1, cantidad);
        stmt.setInt(2, alimento.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void actualizarCantidadProducto(int id_producto, int catidad) {
        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();

            String query = "Update productos Set cantidad_disponible = cantidad_disponible+? where id=?";
            PreparedStatement stmt = conn.prepareStatement(query);


            stmt.setInt(1, catidad);
            stmt.setInt(2, id_producto);
            stmt.executeUpdate();


            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


    }

    public static void historialConsumo(int id, int cantidad, String fecha){

        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();
            String query2 = "INSERT INTO historialconsumo (id_animal,cantidad_consumida,fecha_consumo) VALUES(?,?,?)";
            PreparedStatement stmt2 = conn.prepareStatement(query2);

            stmt2.setInt(1, id);
            stmt2.setInt(2, cantidad);
            stmt2.setTimestamp(3, Timestamp.valueOf(fecha));

            stmt2.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void historialProduccion(int id, int cantidad, String fecha){

        try {

            GestionBBDD gestionBBDD = GestionBBDD.getInstance();
            Connection conn = gestionBBDD.getConn();
            String query2 = "INSERT INTO historialproduccion (id_animal,cantidad,fecha_produccion) VALUES(?,?,?)";
            PreparedStatement stmt2 = conn.prepareStatement(query2);

            stmt2.setInt(1, id);
            stmt2.setInt(2, cantidad);
            stmt2.setTimestamp(3, Timestamp.valueOf(fecha));

            stmt2.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


