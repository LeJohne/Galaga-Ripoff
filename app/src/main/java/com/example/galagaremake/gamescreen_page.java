package com.example.galagaremake;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class gamescreen_page extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private SensorEventListener rotationListener;
    private ImageView shipImage;

    private TextView rightText;

    private TextView leftText;
    private int shipX;
    private int shipY;
    private int score;
    private boolean startFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen_page);

        // Initialize shipImage after setting the content view
        shipImage = findViewById(R.id.ship);
        rightText = findViewById(R.id.rotationRightText);
        leftText = findViewById(R.id.rotationLeftText);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            rotationListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];

                    // Evaluate these values to determine if the phone is tilted left or right, in portrait mode
                    if (y < -0.5) {
                        // Move the ship left
                        shipX = shipX - 10;
                        shipImage.setX(shipX);
                        leftText.setVisibility(View.VISIBLE);
                        rightText.setVisibility(View.INVISIBLE);
                    } else if (y > 0.5) {
                        // Move the ship right
                        shipX = shipX + 10;
                        shipImage.setX(shipX);
                        rightText.setVisibility(View.VISIBLE);
                        leftText.setVisibility(View.INVISIBLE);
                    } else {
                        // Keep the ship still
                        rightText.setVisibility(View.INVISIBLE);
                        leftText.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                    // Do something if accuracy changes
                }
            };
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rotationVectorSensor != null) {
            sensorManager.registerListener(rotationListener, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(rotationListener);
        }
    }
}