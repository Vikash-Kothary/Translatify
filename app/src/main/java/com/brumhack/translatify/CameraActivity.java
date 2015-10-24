package com.brumhack.translatify;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class CameraActivity extends Activity {

    private final static String TAG = "Translatify";

    private Button mBtnShot;
    private CountDownTimer timer;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);

        TextureView mTextureView = (TextureView) findViewById(R.id.texture);
        if(camera==null){
            camera = new Camera(this, mTextureView);
        }

        mBtnShot = (Button) findViewById(R.id.btn_takepicture);
        mBtnShot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture();
            }
        });

        Accelerometer acc = new Accelerometer(this) {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                onAccelerometerChange(sensorEvent);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        camera.onPause();
    }

    public void onAccelerometerChange(SensorEvent sensorEvent) {
        Float accX = sensorEvent.values[0];
        if (accX <= 0.2) {
            ((ImageView) findViewById(R.id.countdown)).setBackgroundColor(Color.GREEN);
            if (timer == null) {
                timer = new CountDownTimer(3000, 1000) {
                    public void onTick(long millisUntilFinished) {
//                        Log.e(TAG, "seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
//                        Log.e(TAG, ":)");
                        camera.takePicture();
                    }
                }.start();
            }
        } else {
            ((ImageView) findViewById(R.id.countdown)).setBackgroundColor(Color.RED);
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }

    }
}