package com.vuforia.samples.Catchvertd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vuforia.samples.VuforiaSamples.R;

public class Activity_premios2 extends Activity implements View.OnClickListener{

    Button btnRegresar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premios2);

        btnRegresar = (Button) findViewById(R.id.buttonRegresar);
        btnRegresar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == btnRegresar){
            Intent intent = new Intent(this, MenuCatchvertd.class);
            startActivity(intent);
        }

    }
}
