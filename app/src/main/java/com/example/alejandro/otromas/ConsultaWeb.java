package com.example.alejandro.otromas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultaWeb extends AppCompatActivity implements View.OnClickListener {


    TextView entrada_texto_buscar;
    Button buscar_direccion;
    WebView ver_por_defecto;

    private static final String PAGINA_PRUEBA="https://www.google.com.co/";
    private static final String PAGINA_UMB="http://umbvirtual.edu.co/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_web);
        buscar_direccion = (Button) findViewById(R.id.boton_consultar_pagina);
        entrada_texto_buscar = (TextView) findViewById(R.id.entrada_direccion_web);
        ver_por_defecto = (WebView) findViewById(R.id.recuadro_webview);
        buscar_direccion.setOnClickListener(this);
        ver_por_defecto.loadUrl(PAGINA_UMB);

    }

    @Override
    public void onClick(View view) {
        String dir;
        //WebView alejoView = (WebView) findViewById(R.id.recuadro_webview);
        //alejoView.loadUrl(PAGINA_UMB);
        switch (view.getId()){
            case R.id.boton_consultar_pagina:
                dir = entrada_texto_buscar.getText().toString();
                ver_por_defecto.loadUrl(dir);
                //alejoView.loadUrl(dir);
                //Toast.makeText(this," se logra la busqueda",Toast.LENGTH_SHORT).show();
                entrada_texto_buscar.setText(R.string.texto_cabecera_busqueda_http);
                break;
        }
    }
}
