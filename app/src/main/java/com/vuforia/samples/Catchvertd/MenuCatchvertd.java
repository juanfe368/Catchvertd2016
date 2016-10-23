package com.vuforia.samples.Catchvertd;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.vuforia.samples.VuforiaSamples.R;

public class MenuCatchvertd extends Activity {

    TextView lblTextoUsuario;
    LoginCatchvertd loginCatchvertd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_catchvertd);
        lblTextoUsuario = (TextView) findViewById(R.id.txtUsuarioMenu);
        lblTextoUsuario.setText("Usuario: "+getIntent().getStringArrayListExtra("arrayDatosUsuario").get(0));
    }
}
