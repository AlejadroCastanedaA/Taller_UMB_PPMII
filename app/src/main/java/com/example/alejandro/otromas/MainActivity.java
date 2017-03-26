package com.example.alejandro.otromas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button boton, boton_act_2, boton_act_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (Button) findViewById(R.id.boton_ir_act1);
        boton_act_2 = (Button)findViewById(R.id.boton_ir_act2);
        boton.setOnClickListener(this);
        boton_act_2.setOnClickListener(this);
        boton_act_3 = (Button) findViewById(R.id.boton_ir_act_3);
        boton_act_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.boton_ir_act1:
                intent = new Intent(MainActivity.this,ActividadToast.class);
                startActivity(intent);
                break;

            case R.id.boton_ir_act2:
                intent = new Intent(MainActivity.this, ContenidoTaller2.class);
                startActivity(intent);
                break;

            case R.id.boton_ir_act_3:
                intent = new Intent(MainActivity.this, SensoresTemp.class);
                startActivity(intent);
                break;
        }

    }
}
