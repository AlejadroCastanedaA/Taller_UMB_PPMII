package com.example.alejandro.otromas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadToast extends AppCompatActivity implements View.OnClickListener{

    Button boton_toast, boton_toast_2;
    TextView entrada_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_toast);
        
        //inicializa el boton

        boton_toast = (Button)findViewById(R.id.boton_hacer_toast);
        boton_toast_2 = (Button)findViewById(R.id.boton_hacer_toast_2);
        entrada_toast = (TextView)findViewById(R.id.entrada_texto_toast);

        boton_toast.setOnClickListener(this);
        boton_toast_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.boton_hacer_toast:
                hacertoast();
                break;
            case R.id.boton_hacer_toast_2:
                cambiatexto(camb);
                break;
        }
    }

    public void hacertoast (){

        Context context = getApplicationContext();
        //CharSequence text = "Hello toast!";
        int text = R.string.texto_toast;
        int duration = Toast.LENGTH_SHORT;
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.para_hacer_toast,(ViewGroup) findViewById(R.id.toast_layout));
        TextView textToast = (TextView) layout.findViewById(R.id.llamar_toast);
        textToast.setText(text);
        Toast.makeText(context, text, duration).show();
    }

    Context camb;
    public void cambiatexto (Context context){
        context = getApplicationContext();
        String text = entrada_toast.getText().toString();
        entrada_toast.setText("");
        int duration = Toast.LENGTH_SHORT;
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.para_hacer_toast,(ViewGroup) findViewById(R.id.toast_layout));
        TextView textToast = (TextView) layout.findViewById(R.id.llamar_toast);
        textToast.setText(text);
        Toast.makeText(context, text, duration).show();
        camb= context;
    }





}
