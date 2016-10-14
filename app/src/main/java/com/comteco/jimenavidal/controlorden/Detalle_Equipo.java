package com.comteco.jimenavidal.controlorden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Jimena Vidal on 02/09/2016.
 */
public class Detalle_Equipo extends AppCompatActivity {
    ImageView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_equipo);

        v=(ImageView) findViewById(R.id.imgVE);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });

    }

}
