package com.vuforia.samples.Catchvertd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vuforia.samples.VuforiaSamples.R;

import org.json.JSONArray;

public class LoginCatchvertd extends Activity implements View.OnClickListener{

    private EditText txtUserName;
    private EditText txtUserClave;
    Button btnLogin;
    TextView lblRegistro, lblRecuperarPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_catchvertd);

        txtUserName = (EditText) findViewById(R.id.txtUsuarioLogin);
        txtUserClave = (EditText) findViewById(R.id.txtClaveLogin);

        btnLogin = (Button) findViewById(R.id.butLogin);
        btnLogin.setOnClickListener(this);

        lblRegistro = (TextView) findViewById(R.id.lblRegistroLogin);
        lblRegistro.setOnClickListener(this);

        lblRecuperarPassword = (TextView) findViewById(R.id.lblRecoveryLogin);
        lblRecuperarPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        //Valida la existencia de conexión a internet
        if(existeInternet()) {

            if (v == btnLogin) {

                // Crea un hilo
                Thread tr = new Thread() {
                    @Override
                    public void run() {

                        ConnectWs cw = new ConnectWs();

                        //Llamando servicioWeb con enviarDatosLoginGET
                        final String resultado = cw.enviarDatosLoginGET(txtUserName.getText().toString(), txtUserClave.getText().toString());

                        //System.out.println("resultado del servicioWeb = " + resultado);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if (r > 0) {
                                    //para llamar la actividad
                                    Intent i = new Intent(getApplicationContext(), MenuCatchvertd.class);
                                    startActivity(i);
                                } else {
                                    //muestra un mensaje de error en la pantalla
                                    Toast.makeText(getApplicationContext(), "Usuario o Password incorrectos", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                };

                //inicia el hilo
                tr.start();
            } else if (v == lblRegistro) {

                //System.out.println("Ingreso a crear registro");
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);

            } else if (v == lblRecuperarPassword) {
                //muestra un mensaje de en la pantalla
                Toast.makeText(getApplicationContext(), "En construcción", Toast.LENGTH_LONG).show();
            }
        }else{
            //muestra un mensaje de en la pantalla
            Toast.makeText(getApplicationContext(), "No existe conexión a internet", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Valida la existencia de conexión a internet
     * @return
     */
    private boolean existeInternet() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else
            connected = false;

        return  connected;
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



}
