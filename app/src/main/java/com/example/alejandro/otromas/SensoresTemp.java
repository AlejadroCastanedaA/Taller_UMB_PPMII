package com.example.alejandro.otromas;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SensoresTemp extends AppCompatActivity implements SensorEventListener{

    TextView sensorx, sensory, sensorz;
    private SensorManager mSensorManager = null;
    private Sensor mTemperatura = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores_temp);
        sensorx = (TextView) this.findViewById(R.id.textview_sensorx);
        sensory = (TextView) this.findViewById(R.id.textview_sensory);
        sensorz = (TextView) this.findViewById(R.id.textview_sensorz);
    }

    public SensoresTemp (){

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mTemperatura = mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
        if (mTemperatura == null){
            Toast.makeText(getApplicationContext(), "No hay sensor de Temperatura", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Hay sensor de Temperatura", Toast.LENGTH_SHORT).show();
            mSensorManager.registerListener(this, mTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent arg0) {
        synchronized (this){
            float[] masData;
            float x;
            float y;
            float z;
            // TODO Auto-generated method stub
            switch(arg0.sensor.getType()){

                case Sensor.TYPE_TEMPERATURE:
                    masData = arg0.values;
                    x = masData[0];
                    sensorx.setText("Temperatura: " + x );
                    break;
                //aslss
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }
}
