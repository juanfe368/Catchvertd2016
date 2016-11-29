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
import org.json.JSONException;

public class RegistroActivity extends Activity implements View.OnClickListener{

    private Button btnEnviar;
    private EditText txtUserName, txtUserNameApe, txtPassword, txtRePassword, txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtUserName = (EditText) findViewById(R.id.txtRegistroUsuario);
        txtUserNameApe = (EditText) findViewById(R.id.txtRegistroUsuarioApe);
        txtEmail = (EditText) findViewById(R.id.txtRegistroEmail);
        txtPassword = (EditText) findViewById(R.id.txtRegistroPassword);
        txtRePassword = (EditText) findViewById(R.id.txtRegistroRePassword);

        btnEnviar = (Button) findViewById(R.id.butRegEnviar);
        btnEnviar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if( v == btnEnviar){

            // Valida que las dos contraseñas digitadas sean iguales
            if ((txtPassword.getText().toString().compareTo(txtRePassword.getText().toString())) == 0 ) {
                // Crea un hilo
                Thread tr = new Thread() {
                    @Override
                    public void run() {


                        ConnectWs cw = new ConnectWs();

                        //Llamando servicioWeb con enviarDatosLoginGET
                        final String resultado = cw.enviarDatosRegistroGET(txtUserName.getText().toString(),
                                                                            txtUserNameApe.getText().toString(),
                                                                            txtEmail.getText().toString(),
                                                                            txtPassword.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if (r == 0) {
                                    //para llamar la actividad
                                    Intent i = new Intent(getApplicationContext(), MenuCatchvertd.class);
                                    startActivity(i);
                                } else {
                                    //muestra un mensaje de error en la pantalla
                                    Toast.makeText(getApplicationContext(), "El E-mail se encuentra registrado", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                };

                //inicia el hilo
                tr.start();

            }else{
                //muestra un mensaje de error en la pantalla
                Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }

        }
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
