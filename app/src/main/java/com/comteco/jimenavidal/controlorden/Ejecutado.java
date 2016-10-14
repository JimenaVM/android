package com.comteco.jimenavidal.controlorden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Jimena Vidal on 02/09/2016.
 */
public class Ejecutado extends Fragment implements ImageView.OnClickListener {
    ImageView noee,ejecutado;
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.ejecucion_ot, container, false);
        noee = (ImageView) view.findViewById(R.id.imgNoEje);
        noee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                Intent bu = new Intent(getActivity(), NoEjecutado.class);
                startActivity(bu);

            }


        });
        return view;



    }

    @Override
    public void onClick(View v) {

    }
}
