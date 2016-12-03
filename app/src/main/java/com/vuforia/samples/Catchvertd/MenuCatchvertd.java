package com.vuforia.samples.Catchvertd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vuforia.samples.VuforiaSamples.R;
import com.vuforia.samples.Vuforia.app.ImageTargets.ImageTargets;

public class MenuCatchvertd extends Activity implements View.OnClickListener{

    TextView lblTextoUsuario, lblEventos, lblPremios;
    LoginCatchvertd loginCatchvertd;
    LinearLayout layoutEventos, layoutPremios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_catchvertd);
        //lblTextoUsuario = (TextView) findViewById(R.id.txtUsuarioMenu);

        lblEventos = (TextView) findViewById(R.id.lblEventosMenu);
        lblEventos.setOnClickListener(this);

        lblPremios = (TextView) findViewById(R.id.lblPremiosMenu);
        lblPremios.setOnClickListener(this);

        layoutEventos = (LinearLayout) findViewById(R.id.layoutEventos);
        layoutEventos.setVisibility(LinearLayout.VISIBLE);

        layoutPremios = (LinearLayout) findViewById(R.id.layoutPremios);
        layoutPremios.setVisibility(LinearLayout.GONE);

    }

    public void butFood1(View v){
        Intent intent = new Intent(this, ImageTargets.class);
        startActivity(intent);
    }

    public void butPremios(View v) {
        Intent intent = new Intent(this, PremiosActivity.class);
        startActivity(intent);
    }
    public void butPremios2(View v) {
        Intent intent = new Intent(this, Activity_premios2.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        if (v == lblEventos) {

           layoutEventos.setVisibility(LinearLayout.VISIBLE);
           layoutPremios.setVisibility(LinearLayout.GONE);

        }else if (v == lblPremios){
            layoutEventos.setVisibility(LinearLayout.GONE);
            layoutPremios.setVisibility(LinearLayout.VISIBLE);
        }

    }
}
