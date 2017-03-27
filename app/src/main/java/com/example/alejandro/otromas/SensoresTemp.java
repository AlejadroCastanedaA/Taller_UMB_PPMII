package com.example.alejandro.otromas;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SensoresTemp extends AppCompatActivity implements SensorEventListener{

    TextView sensorx, sensory, sensorz;

    private SensorManagerSimulator mSensorManager = null;
    //private SensorEventListener mEvenListenerTemperatura;
    private Sensor mTemperatura = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores_temp);
        sensorx = (TextView) this.findViewById(R.id.textview_sensorx);
        sensory = (TextView) this.findViewById(R.id.textview_sensory);
        sensorz = (TextView) this.findViewById(R.id.textview_sensorz);
        mSensorManager = SensorManagerSimulator.getSystemService(this,SENSOR_SERVICE);
        mTemperatura = mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);

    }

    protected void onResume() {
        super.onResume();
        //mSensorManager.registerListener(this, mTemperatura, SensorManagerSimulator.SENSOR_DELAY_NORMAL);
        if (mTemperatura == null){
            Toast.makeText(getApplicationContext(), "No hay sensor de Temperatura", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Hay sensor de Temperatura", Toast.LENGTH_SHORT).show();
            //mSensorManager.registerListener(this, mTemperatura, SensorManagerSimulator.SENSOR_DELAY_NORMAL);
            mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),SensorManager.SENSOR_DELAY_FASTEST);
            //mSensorManager.connectSimulator();
        }
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent arg0) {
        float[] masData;
        float x;
        float y;
        float z;
        masData = arg0.values;
        x = masData[0];
        sensorx.setText("Temperatura: " + x );
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }
}
