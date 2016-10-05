package com.example.alejandro.otromas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ConsultaWeb extends AppCompatActivity implements View.OnClickListener {


    TextView entrada_texto_buscar;
    Button buscar_direccion;

    private static final String PAGINA_PRUEBA="https://www.google.com.co/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_web);
        buscar_direccion = (Button) findViewById(R.id.boton_consultar_pagina);
        entrada_texto_buscar = (TextView) findViewById(R.id.entrada_direccion_web);

        buscar_direccion.setOnClickListener(this);
    }

    public void entradaUsuario (String dir){

        //no se esta implementando este metodo

        dir = entrada_texto_buscar.getText().toString();
        return;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.boton_consultar_pagina:
                WebView alejoView = (WebView) findViewById(R.id.recuadro_webview);

                // falta declarar el modo de retomar lo ingresado por el usuario en el campo de entrada

                alejoView.loadUrl(PAGINA_PRUEBA);
                break;
        }
    }
}
