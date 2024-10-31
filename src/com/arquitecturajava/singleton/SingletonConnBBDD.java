package com.arquitecturajava.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnBBDD {

    private static SingletonConnBBDD instancia;
    private Connection connection;

    SingletonBBDDProperties sp = SingletonBBDDProperties.getInstancia();
    String url = sp.getPropiedad("DB_URL");
    String usuario = sp.getPropiedad("DB_USER");
    String contrasena = sp.getPropiedad("DB_PASS");
    String database = sp.getPropiedad("DB_NAME");

    private SingletonConnBBDD() {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url + database, usuario, contrasena);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SingletonConnBBDD getInstance() {
        if (instancia == null) {
            instancia = new SingletonConnBBDD();
        }
        return instancia;
    }
}
