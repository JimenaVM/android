package com.comteco.jimenavidal.controlorden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Jimena Vidal on 07/09/2016.
 */
public class DetalleDanio extends AppCompatActivity {
    ImageView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_danio);
        v=(ImageView) findViewById(R.id.imgVD);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


    }
}
