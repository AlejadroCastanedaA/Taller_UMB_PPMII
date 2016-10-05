package com.example.alejandro.otromas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main_basedatos_sqlite extends AppCompatActivity implements View.OnClickListener{

    Button boton_guardar, boton_consultar;
    EditText entrada_nombre, entrada_documento, entrada_telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_basedatos_sqlite);
        boton_guardar = (Button) findViewById(R.id.boton_guardar_bd);
        boton_consultar = (Button) findViewById(R.id.boton_consultar_bd);
        entrada_documento = (EditText) findViewById(R.id.entrada_bd_documento);
        entrada_nombre = (EditText) findViewById(R.id.entrada_bd_nombre);
        entrada_telefono = (EditText) findViewById(R.id.entrada_bd_telefono);
        boton_guardar.setOnClickListener(this);
        boton_consultar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void guardar(View view) {
        AyudaBaseDatos admin = new AyudaBaseDatos(this);
        SQLiteDatabase bd_Alejandro = admin.getWritableDatabase();
        String tel = entrada_telefono.getText().toString();
        String Nom = entrada_nombre.getText().toString();
        String doc = entrada_documento.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("documento",doc);
        registro.put("telefono", tel);
        registro.put("nombre", Nom);
        bd_Alejandro.insert("tabladatostitulo", null, registro);
        bd_Alejandro.close();
        entrada_telefono.setText("");
        entrada_nombre.setText("");
        entrada_documento.setText("");
        Toast.makeText(this, "Se guardaron los datos Telefonicos", Toast.LENGTH_SHORT).show();
    }

    public void consultarbd (View view){

        AyudaBaseDatos admin = new AyudaBaseDatos(this);
        SQLiteDatabase bd= admin.getReadableDatabase();
        String doc = entrada_documento.getText().toString();
        String Nom = entrada_nombre.getText().toString();
        String tel = entrada_telefono.getText().toString();
        entrada_documento.setText("");
        entrada_nombre.setText("");
        entrada_telefono.setText("");

        Cursor fila = bd.rawQuery( "SELECT nombre, Documento FROM tabladatostitulo WHERE telefono=" + tel, null);


        if (fila.moveToFirst()) {
            entrada_nombre.setText(fila.getString(0));
            //entrada_documento.setText(fila.getString(2));

        } else
            entrada_documento.setText("");
            entrada_nombre.setText("");
            entrada_telefono.setText("");
            Toast.makeText(this, "No existe un telefono asociado", Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void onClick (View view){

        switch (view.getId()){
            case R.id.boton_guardar_bd:
                guardar(view);
                Toast.makeText(this, "hace boton", Toast.LENGTH_SHORT).show();

                break;
            case R.id.boton_consultar_bd:
                consultarbd(view);
                break;
        }
    }
}
