package com.arquitecturajava.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SingletonPropertiesPerso {
    public static String RUTA_VALORES_PRE="resources"+ File.separator+"default_config.properties";
    private static SingletonProperties instancia=null;
    private Properties p;
    private SingletonPropertiesPerso() {

        p= new Properties();
        try {
            p.load(new FileInputStream(new File(RUTA_VALORES_PRE)));
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static SingletonProperties getInstancia() {

        if (instancia==null) {

            instancia= new SingletonProperties();
        }
        return instancia;
    }

    public String getPropiedad(String clave) {

        return p.getProperty(clave);
    }
}
