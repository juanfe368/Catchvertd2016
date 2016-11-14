package com.vuforia.samples.Catchvertd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vuforia.samples.VuforiaSamples.R;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginCatchvertd extends Activity implements View.OnClickListener{


    private EditText txtUserName;
    private EditText txtUserClave;

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_catchvertd);
        txtUserName = (EditText) findViewById(R.id.txtUsuarioLogin);
        txtUserClave = (EditText) findViewById(R.id.txtClaveLogin);

        btnLogin = (Button) findViewById(R.id.butLogin);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if( v == btnLogin){
            Thread tr = new  Thread(){
                @Override
                public void run() {

                    System.out.println("********************************");
                    System.out.println(" ");
                    System.out.println("Llamando servicioWeb");
                    System.out.println(" ");
                    System.out.println("Usuario    = " + txtUserName.getText().toString());
                    System.out.println("Contraseña = " + txtUserClave.getText().toString());
                    System.out.println("********************************");
                    //
                    final String resultado = enviarDatosGET(txtUserName.getText().toString(),txtUserClave.getText().toString());

                    System.out.println("resultado del servicioWeb = " + resultado);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int r = obtenerDatosJSON(resultado);
                            if (r > 0){
                                Intent i = new Intent(getApplicationContext(),MenuCatchvertd.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(getApplicationContext(),"Usuario o Password incorrectos", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            };
            tr.start();
        }

    }


    public String enviarDatosGET(String usu, String pass) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;

        String url_local = "http://192.168.0.14:8888/Catchvertd/";
        String url_remota = "http://catchvertd.3eeweb.com/";

        try {

            url = new URL(url_remota + "valida.php?usu=" + usu + "&pas=" + pass);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();

            resul = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                while ((linea = reader.readLine()) != null) {
                    resul.append(linea);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resul.toString();
    }


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




}
