package com.vuforia.samples.Catchvertd;

import android.app.Activity;
import android.content.Intent;
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

        if( v == btnLogin){

            // Crea un hilo
            Thread tr = new  Thread(){
                @Override
                public void run() {

                    ConnectWs cw = new ConnectWs();

                    //Llamando servicioWeb con enviarDatosLoginGET
                    final String resultado = cw.enviarDatosLoginGET(txtUserName.getText().toString(),txtUserClave.getText().toString());

                    //System.out.println("resultado del servicioWeb = " + resultado);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            int r = obtenerDatosJSON(resultado);
                            if (r > 0){
                                //para llamar la actividad
                                Intent i = new Intent(getApplicationContext(),MenuCatchvertd.class);
                                startActivity(i);
                            }else{
                                //muestra un mensaje de error en la pantalla
                                Toast.makeText(getApplicationContext(),"Usuario o Password incorrectos", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            };

            //inicia el hilo
            tr.start();
        }else if(v == lblRegistro){

            //System.out.println("Ingreso a crear registro");
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);

        }else if(v == lblRecuperarPassword){
            //muestra un mensaje de en la pantalla
            Toast.makeText(getApplicationContext(),"En construcción", Toast.LENGTH_LONG).show();
        }

    }


    /**
     * Llama a el servicio web
     * @param usu
     * @param pass
     * @return
     */
  /*
    public String enviarDatosLoginGET(String usu, String pass) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;

        String url_local = "http://192.168.0.14:8888/Catchvertd/"; // Url del MAC de Oscar
        String url_remota = "http://catchvertd.com.co/";

        try {
            // Prepara la URL con el metodo GET y se asigna el usuario y la contraseña
            url = new URL(url_remota + "valida.php?usu=" + usu + "&pas=" + pass);
            // Abre la conexion a internet
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Prepara la respuesta
            respuesta = connection.getResponseCode();
            // Almacena el resultado que llega del servicio web
            resul = new StringBuilder();
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Retorna el resultado del servicio web que es un JSON
        return resul.toString();
    }
*/

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
