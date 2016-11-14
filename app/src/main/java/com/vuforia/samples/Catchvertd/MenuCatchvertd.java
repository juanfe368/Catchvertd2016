package com.vuforia.samples.Catchvertd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vuforia.samples.VuforiaSamples.R;
import com.vuforia.samples.VuforiaSamples.app.ImageTargets.ImageTargets;
import com.vuforia.samples.VuforiaSamples.app.UserDefinedTargets.UserDefinedTargets;

public class MenuCatchvertd extends Activity {

    TextView lblTextoUsuario;
    LoginCatchvertd loginCatchvertd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_catchvertd);
        lblTextoUsuario = (TextView) findViewById(R.id.txtUsuarioMenu);
    }

    public void butFood1(View v){
        Intent intent = new Intent(this, ImageTargets.class);
        startActivity(intent);
    }
}
