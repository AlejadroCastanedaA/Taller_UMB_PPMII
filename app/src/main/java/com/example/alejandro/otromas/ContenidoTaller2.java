package com.example.alejandro.otromas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContenidoTaller2 extends AppCompatActivity implements View.OnClickListener{

    Button boton_formulario, boton_consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_taller2);
        boton_formulario = (Button) findViewById(R.id.boton_ir_taller2_base_sqlite);
        boton_consulta = (Button) findViewById(R.id.boton_ir_consultaweb);
        boton_consulta.setOnClickListener(this);
        boton_formulario.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.boton_ir_taller2_base_sqlite:
                intent = new Intent(ContenidoTaller2.this,Main_basedatos_sqlite.class);
                startActivity(intent);
                break;
            case R.id.boton_ir_consultaweb:

                // definir el consulta una vez terminado

                intent = new Intent(ContenidoTaller2.this,ConsultaWeb.class);
                startActivity(intent);
                break;
        }


    }
}
