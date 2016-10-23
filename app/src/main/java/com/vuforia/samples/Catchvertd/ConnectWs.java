package com.vuforia.samples.Catchvertd;

import java.util.ArrayList;

/**
 * Created by juanfe on 23/10/16.
 */

public class ConnectWs {

    public final static String USUARIO = "demos";
    public final static String CLAVE = "1234";

    public ConnectWs(){

    }

    public ArrayList autenticarUsuario(String pUsuario, String pClave){
        ArrayList dataUsuario = new ArrayList();
        if(pUsuario.equals(USUARIO)){
            if(pClave.equals(CLAVE)){
                dataUsuario.add(0, USUARIO);
                dataUsuario.add(1, CLAVE);
                dataUsuario.add(2, "Prueba Usuario");
                dataUsuario.add(3, "prueba@gmail.com");
                dataUsuario.add(4, "3001234567");
            }
        }
        return dataUsuario;
    }

}
