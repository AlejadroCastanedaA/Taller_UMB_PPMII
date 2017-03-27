package com.example.alejandro.otromas;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseExternaAct3 extends AppCompatActivity implements View.OnClickListener{

    Button boton_guardar, boton_consultar, boton_salir;
    EditText entrada_id, entrada_asunto, entrada_actividad, entrada_fecha;
    TextView salida_agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_externa_act3);

        boton_guardar = (Button) findViewById(R.id.boton_consultar_bd);
        boton_consultar = (Button) findViewById(R.id.boton_guardar_bd);
        boton_salir = (Button) findViewById(R.id.boton_salir_actividad);
        entrada_asunto = (EditText) findViewById(R.id.entrada_asunto);
        entrada_actividad = (EditText) findViewById(R.id.entrada_actividad);
        entrada_fecha = (EditText) findViewById(R.id.entrada_fecha);
        entrada_id = (EditText) findViewById(R.id.entrada_id);
        salida_agenda = (TextView) findViewById(R.id.salida_agenda_BD);
        boton_consultar.setOnClickListener(this);
        boton_guardar.setOnClickListener(this);
        boton_salir.setOnClickListener(this);
    }

    private class guardar_datos extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls){
            try {
                return downloadURL(urls[0]);
            } catch (IOException e) {
                return "imposible ingresar a la pagina. URL debe estar invalida.";
            }
        }
        @Override
        protected void onPostExecute (String result){
            JSONArray arregloja = null;
            try{
                arregloja = new JSONArray(result);
                salida_agenda.setText("Fecha: "+arregloja.getString(1)+" Asunto: "+arregloja.getString(2)+" Actividad: "+arregloja.getString(3));
            }catch (JSONException e){
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"se guardaron los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private class consulta_datos extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls){

            try {
                return downloadURL(urls[0]);
            } catch (IOException e) {
                return "imposible ingresar a la pagina. URL debe estar invalida.";
            }
        }
        @Override
        protected void onPostExecute (String result){
            JSONArray arregloja = null;
            try{
                arregloja = new JSONArray(result);
                entrada_fecha.setText(arregloja.getString(1));
                salida_agenda.setText("Fecha: "+arregloja.getString(1)+" Asunto: "+arregloja.getString(2)+" Actividad: "+arregloja.getString(3));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }


    private String downloadURL(String myurl) throws IOException {
        Log.i("URL","" + myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;

        int len = 500;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            int respnse = conn.getResponseCode();
            Log.d("respuesta","La respuesta es= " + respnse);
            is = conn.getInputStream();

            String contentAsString = readIt(is, len);
            return contentAsString;
        } finally {
            if (is!=null){
                is.close();
            }
        }

    }

    public String readIt(InputStream stream, int len) throws  IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boton_consultar_bd:
                new consulta_datos().execute("http://localhost/registro.php?Fecha=\"+entrada_fecha.getText().toString()+\"&Asunto=\"+entrada_asunto.getText().toString()+\"&Actividad=\"+entrada_actividad.getText().toString()");
                break;
            case R.id.boton_guardar_bd:
                new guardar_datos().execute("http://localhost/registro.php?Fecha=\"+entrada_fecha.getText().toString()+\"&Asunto=\"+entrada_asunto.getText().toString()+\"&Actividad=\"+entrada_actividad.getText().toString()");
                break;
            case R.id.boton_salir_actividad:
                Toast.makeText(this,"sin funcion",Toast.LENGTH_SHORT).show();
                break;
        }

    }


}

