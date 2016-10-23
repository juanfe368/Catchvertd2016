package com.vuforia.samples.Catchvertd;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vuforia.samples.VuforiaSamples.R;

import java.util.ArrayList;

public class LoginCatchvertd extends Activity {


    private EditText txtUserName;
    private EditText txtUserClave;
    private ClassUsuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_catchvertd);
        txtUserName = (EditText) findViewById(R.id.txtUsuarioLogin);
        txtUserClave = (EditText) findViewById(R.id.txtClaveLogin);
        this.usuario = new ClassUsuario(txtUserName.getText().toString(),txtUserClave.getText().toString(),"","","");
    }

    public void butAceptarLogin(View view){
        ArrayList datosUsuario = usuario.autenticarUsuario(usuario);
        if(datosUsuario.size()>0){
            Intent activMenu = new Intent(this, MenuCatchvertd.class);
            activMenu.putStringArrayListExtra("arrayDatosUsuario", datosUsuario);
            startActivity(activMenu);
        }
        else if(datosUsuario.size()==0) {
            AlertDialog alerta = new AlertDialog.Builder(this).create();
            alerta.setTitle("Autenticación");
            alerta.setMessage("Usuario o Clave incorrecto por favor vuelva a intentarlo");
            alerta.show();
        }
    }

}
