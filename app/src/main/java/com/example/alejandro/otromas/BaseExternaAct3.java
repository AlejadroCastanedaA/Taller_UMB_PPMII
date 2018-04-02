package com.example.alejandro.otromas;

import android.content.Intent;
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

import javax.net.ssl.HttpsURLConnection;

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
                return downloadURL(urls[0]); /*downloadUrl*/
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

            entrada_id.setText("");
            entrada_actividad.setText("");
            entrada_asunto.setText("");
            entrada_fecha.setText("");

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
                //entrada_fecha.setText(arregloja.getString(1));
                //entrada_asunto.setText(arregloja.getString(2));
                //entrada_actividad.setText(arregloja.getString(3));
                salida_agenda.setText("Fecha: "+arregloja.getString(1)+"\nAsunto: "+arregloja.getString(2)+"\nActividad: "+arregloja.getString(3));
            }catch (JSONException e){
                e.printStackTrace();
            }
            entrada_id.setText("");
            entrada_actividad.setText("");
            entrada_asunto.setText("");
            entrada_fecha.setText("");

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
        String dtfecha, dtactividad, dtasunto;

        switch (view.getId()){
            case R.id.boton_consultar_bd:
                //new consulta_datos().execute("http://localhost/consultaverdos.php?id_agenda=\"+entrada_id.getText().toString()");
                //new consulta_datos().execute("http://10.0.2.2/xampp/htdocs/consultaverdos.php?id_agenda="+entrada_id.getText().toString());
                new consulta_datos().execute("http://10.0.2.2/consulta_nueva.php?id_agenda="+entrada_id.getText().toString());
                break;
            case R.id.boton_guardar_bd:
                dtfecha= entrada_fecha.getText().toString();
                dtasunto=entrada_asunto.getText().toString();
                dtactividad=entrada_actividad.getText().toString();
                new guardar_datos().execute("http://10.0.2.2/registro_nueva.php?fecha="+dtfecha+"&asunto="+dtasunto+"&actividad="+dtactividad);
                //new guardar_datos().execute("http://10.0.2.2/registro_nueva.php?fecha="+entrada_fecha.getText().toString()+"&asunto="+entrada_asunto.getText().toString()+"&actividad="+entrada_actividad.getText().toString());
                salida_agenda.setText(dtasunto+"-"+dtfecha+"-"+dtactividad);
                break;
            case R.id.boton_salir_actividad:
                //Intent intent = new Intent(BaseExternaAct3.this,MainActivity.class);
                //startActivity(intent);
                entrada_id.setText("");
                entrada_actividad.setText("");
                entrada_asunto.setText("");
                entrada_fecha.setText("");
                salida_agenda.setText("");
                break;
        }
    }

    /**
     * Given a URL, sets up a connection and gets the HTTP response body from the server.
     * If the network request is successful, it returns the response body in String form. Otherwise,
     * it will throw an IOException.
     */
    /*
    private String downloadUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            connection.connect();
            publishProgress(DownloadCallback.Progress.CONNECT_SUCCESS);
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readStream(stream, 500);
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
    */


    /*fin*/

}

