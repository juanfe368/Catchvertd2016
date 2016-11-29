package com.vuforia.samples.Catchvertd;

import android.support.annotation.NonNull;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by juanfe on 23/10/16.
 */

public class ConnectWs {

    private String url_local = "http://192.168.0.14:8888/Catchvertd/"; // Url del MAC de Oscar
    //private String url_remota = "http://catchvertd.com.co/";

    private String url_remota2 ="http://gamethemed.comli.com/catchvertdweb/Clases/";


    public ConnectWs(){

    }

    /**
     * Llama a el servicio web
     * @param usu
     * @param pass
     * @return
     */
    public String enviarDatosLoginGET(String usu, String pass) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;

        try {
            // Prepara la URL con el metodo GET y se asigna el usuario y la contraseña
            url = new URL(url_remota2 + "ClassValidarUsuario.php?usu=" + usu
                                                             + "&pas=" + pass);

            resul = getStringBuilder(url);


        } catch (Exception e) {
            e.printStackTrace();
        }

        //Retorna el resultado del servicio web que es un JSON
        return resul.toString();
    }



    /**
     * Llama a el servicio web
     * @param usu
     * @param pass
     * @return
     */
    public String enviarDatosRegistroGET(String usu, String ape, String email, String pass) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;

        try {
            // Prepara la URL con el metodo GET y se asigna el usuario, email y contraseña
            //url = new URL(url_remota + "SetData.php?nom=" + usu + "&ema=" + email+ "&pas=" + pass);
            url = new URL(url_remota2 + "ClassRegistrarUsuario.php?nom=" + usu
                                                               + "&ape=" + ape
                                                               + "&ema=" + email
                                                               + "&pas=" + pass);

            resul = getStringBuilder(url);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Retorna el resultado del servicio web que es un JSON
        return resul.toString();
    }

    /**
     * Verifica la cantidad de datos que regreso el servicio web
     * @param response
     * @return retorna 1 si el JSON contiene datos, de lo contrario, retorna 0
     */
    public int obtenerDatosJSON(String response){

        int res = 0;

        try{
            JSONArray json = new JSONArray(response);

            if (json.length() > 0 ){
                res = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @NonNull
    private StringBuilder getStringBuilder(URL url) throws IOException {

        int respuesta;

        String linea;// Abre la conexion a internet
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Prepara la respuesta
        respuesta = connection.getResponseCode();
        // Almacena el resultado que llega del servicio web
        StringBuilder resul = new StringBuilder();
        // Valida que la respuesta del servicio sea correcto con OK
        if (respuesta == HttpURLConnection.HTTP_OK) {
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            // Recorre las lineas de la respuesta del servicio web
            while ((linea = reader.readLine()) != null) {
                //Adiciona a resul el contenido de linea
                resul.append(linea);
            }
        }
        return resul;
    }

}
