package com.example.alejandro.otromas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.SensorManager;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

public class SensoresTemp extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    TextView sensorx, sensory, sensorz;
    Button boton_prueba;

    private SensorManagerSimulator mSensorManager;
    private SensorEventListener mTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores_temp);
        sensorx = (TextView) this.findViewById(R.id.textview_sensorx);
        sensory = (TextView) this.findViewById(R.id.textview_sensory);
        sensorz = (TextView) this.findViewById(R.id.textview_sensorz);
        mSensorManager = SensorManagerSimulator.getSystemService(this,SENSOR_SERVICE);
        mSensorManager.connectSimulator();
        mTemperatura = (SensorEventListener) mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        boton_prueba = (Button) findViewById(R.id.boton_prueba_sensor);
        boton_prueba.setOnClickListener(this);
        //SensorManagerSimulator sms = SensorManagerSimulator.getSystemService(this,SENSOR_SERVICE);
        //sms.connectSimulator();
        initListeners();
    }

    private void initListeners() {
        mTemperatura = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                sensorx.setText("Temperature: " + values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mTemperatura = mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),SensorManager.SENSOR_DELAY_FASTEST);
        //mSensorManager.registerListener(this, (Sensor) mTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
        //sms.registerListener(this, sms.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        //float[] values = event.values;
        //sensorx.setText("Temperatura= "+values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boton_prueba_sensor:
                sensorz.setText("waaaaaaaa");
                //initListeners();
                break;
        }
    }
}
