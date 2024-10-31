package com.arquitecturajava.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SingletonBBDDProperties {
    public static String RUTA_VALORES_BBDD="resources"+ File.separator+"bbdd.properties";
    private static SingletonBBDDProperties instancia=null;
    private Properties p;

    private SingletonBBDDProperties(){


        p= new Properties();
        try {
            p.load(new FileInputStream(new File(RUTA_VALORES_BBDD)));
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static SingletonBBDDProperties getInstancia() {

        if (instancia==null) {

            instancia= new SingletonBBDDProperties();
        }
        return instancia;
    }

    public String getPropiedad(String clave) {

        return p.getProperty(clave);
    }
    public void cambiarPropiedad(String clave, String valor){
        p.replace(clave,valor);
    }
}
