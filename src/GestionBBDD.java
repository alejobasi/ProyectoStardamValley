import com.arquitecturajava.singleton.SingletonBBDDProperties;
import com.arquitecturajava.singleton.SingletonProperties;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionBBDD {



    public static List<Alimento> recogerAlimentos(){
        List<Alimento>alimentos=new ArrayList<>();

        SingletonBBDDProperties sp= SingletonBBDDProperties.getInstancia();
        String url= sp.getPropiedad("DB_URL");
        String usuario= sp.getPropiedad("DB_USER");
        String contrasena= sp.getPropiedad("DB_PASS");
        String database= sp.getPropiedad("DB_NAME");



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url+database,usuario,contrasena);

            PreparedStatement stmt= conn.prepareStatement("Select * from alimentos");
            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                int id_alimento=rs.getInt("id");

                    String nombre_alimento=rs.getString("nombre");
                    double precio=rs.getDouble("precio");
                    Alimento alimento=new Alimento(id_alimento,nombre_alimento,precio);
                    alimentos.add(alimento);


            }
return alimentos;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static Alimento recogerAlimentoporId(int id){
        SingletonBBDDProperties sp= SingletonBBDDProperties.getInstancia();
        String url= sp.getPropiedad("DB_URL");
        String usuario= sp.getPropiedad("DB_USER");
        String contrasena= sp.getPropiedad("DB_PASS");
        String database= sp.getPropiedad("DB_NAME");



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url+database,usuario,contrasena);

            PreparedStatement stmt= conn.prepareStatement("Select * from alimentos");
            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                int id_alimento=rs.getInt("id");
                if (id_alimento==id){
                String nombre_alimento=rs.getString("nombre");
                double precio=rs.getDouble("precio");
                Alimento alimento=new Alimento(id_alimento,nombre_alimento,precio);
                return alimento;
                }

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Producto recogerProductoporId(int id){
        SingletonBBDDProperties sp= SingletonBBDDProperties.getInstancia();
        String url= sp.getPropiedad("DB_URL");
        String usuario= sp.getPropiedad("DB_USER");
        String contrasena= sp.getPropiedad("DB_PASS");
        String database= sp.getPropiedad("DB_NAME");



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url+database,usuario,contrasena);

            PreparedStatement stmt= conn.prepareStatement("Select * from productos");
            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                int id_producto=rs.getInt("id");
                if (id_producto==id){
                    String nombre_producto=rs.getString("nombre");
                    double precio=rs.getDouble("precio");
                    Producto producto=new Producto(id_producto,nombre_producto,precio);
                    return producto;
                }

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void recogerAnimales(Granja granja) {

        List<Animales> lista = new ArrayList<>();

        SingletonBBDDProperties sp = SingletonBBDDProperties.getInstancia();
        String url = sp.getPropiedad("DB_URL");
        String usuario = sp.getPropiedad("DB_USER");
        String contrasena = sp.getPropiedad("DB_PASS");
        String database = sp.getPropiedad("DB_NAME");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url + database, usuario, contrasena);

            PreparedStatement stmt = conn.prepareStatement("Select * from animales");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Animal tipo = Animal.valueOf(rs.getString("tipo"));
                String nombre = rs.getString("nombre");
                int dia_insercion = rs.getInt("dia_insercion");
                int peso = rs.getInt("peso");
                int id_alimneto = rs.getInt("id_alimento");
                int id_producto = rs.getInt("id_producto");

                Alimento alimento = recogerAlimentoporId(id_alimneto);
                Producto producto = recogerProductoporId(id_producto);
                if (tipo == Animal.VACA) {
                    Vaca vaca = new Vaca(id, tipo, nombre, dia_insercion, alimento, producto, peso);
                    lista.add(vaca);
                } else if (tipo == Animal.OVEJA) {
                    Oveja oveja = new Oveja(id, tipo, nombre, dia_insercion, alimento, producto);
                    lista.add(oveja);
                } else {
                    Animales animal = new Animales(id, tipo, nombre, dia_insercion, alimento, producto);
                    lista.add(animal);
                }

            }
            granja.getEstablo().setAnimales(lista);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static int recogerCantidadAlimeto(int id) {
        SingletonBBDDProperties sp = SingletonBBDDProperties.getInstancia();
        String url = sp.getPropiedad("DB_URL");
        String usuario = sp.getPropiedad("DB_USER");
        String contrasena = sp.getPropiedad("DB_PASS");
        String database = sp.getPropiedad("DB_NAME");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url + database, usuario, contrasena);

            PreparedStatement stmt = conn.prepareStatement("Select * from alimentos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_alimento = rs.getInt("id");
                if (id_alimento == id) {
                     int cantidad= rs.getInt("cantidad_disponible");
                    return cantidad;
                }
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public static void rellenarCantidadAlimento(int id_alimeto){
        SingletonBBDDProperties sp = SingletonBBDDProperties.getInstancia();
        String url = sp.getPropiedad("DB_URL");
        String usuario = sp.getPropiedad("DB_USER");
        String contrasena = sp.getPropiedad("DB_PASS");
        String database = sp.getPropiedad("DB_NAME");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url + database, usuario, contrasena);

            String query="Update alimentos Set cantidad_disponible = ? where id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1,25);
            stmt.setInt(2, id_alimeto);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
    }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
}
}


