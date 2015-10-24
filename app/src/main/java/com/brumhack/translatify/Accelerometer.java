package com.brumhack.translatify;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Vikash Kothary on 24-Oct-15.
 */
public abstract class Accelerometer implements SensorEventListener {

    private Context mContext;
    private Sensor accelerometer;
    private SensorManager sensorManager;

    public Accelerometer(Context context){
        mContext = context;
        sensorManager = (SensorManager) mContext.getSystemService(mContext.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public abstract void onSensorChanged(SensorEvent sensorEvent);

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
