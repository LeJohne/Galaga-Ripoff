package com.example.galagaremake;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class gamescreen_page extends AppCompatActivity {
    //set up sensor manager to detect rotation
    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private SensorEventListener rotationListener;
<<<<<<< Updated upstream
    //set up variables for rotation
    private float[] rotationMatrix = new float[9];
    private float[] rotationValues = new float[3];
    //set up variables for the ship
    private ImageView shipImage = (ImageView) findViewById(R.id.ship);
    //private ImageView ship;
=======
    private ImageView shipImage;

    private TextView leftText;

    private TextView rightText;
>>>>>>> Stashed changes
    private int shipX;
    private int shipY;
    //set up variables for the bullet
    /*private ImageView bullet;
    private int bulletX;
    private int bulletY;
    //set up variables for the enemy
    private ImageView enemy;
    private int enemyX;
    private int enemyY;

     */
    //set up variables for the explosion
    // private ImageView explosion;
    // private int explosionX;
    //private int explosionY;
    //set up variables for the score
    //private TextView scoreLabel;
    private int score;
    //set up variables for the start/stop
    // private Button startButton;
    private boolean startFlag = false;

    //set up variables for the handler
    //private Handler handler = new Handler();
    //private Timer timer = new Timer();
    //set up variables for the sound
   /* private SoundPool soundPool;
    private int soundExplosion;
    private int soundShoot;
    private int soundDamage;
    private boolean soundFlag = false;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen_page);

<<<<<<< Updated upstream
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
=======
        // Initialize shipImage after setting the content view
        shipImage = findViewById(R.id.ship);
        leftText = findViewById(R.id.rotationLeftText);
        rightText = findViewById(R.id.rotationRightText);


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
                        rightText.setVisibility(TextView.INVISIBLE);
                        leftText.setVisibility(TextView.VISIBLE);
                    } else if (y > 0.5) {
                        // Move the ship right
                        shipX = shipX + 10;
                        shipImage.setX(shipX);
                        rightText.setVisibility(TextView.VISIBLE);
                        leftText.setVisibility(TextView.INVISIBLE);
                    } else {
                        rightText.setVisibility(TextView.INVISIBLE);
                        leftText.setVisibility(TextView.VISIBLE);
                        // Keep the ship still
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                    // Do something if accuracy changes
                }
            };
>>>>>>> Stashed changes
        }
    }

    protected void onResume() {
        super.onResume();
        if (rotationVectorSensor != null) {
            sensorManager.registerListener(rotationListener, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(rotationListener);
        }
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            //Evaluate these vales to determine if the phone is tilted left or right, in portrait mode
            if (x < -0.5) {
                //move the ship left
                shipX = shipX - 10;
                shipImage.setX(shipX);
            } else if (x > 0.5) {
                //move the ship right
                shipX = shipX + 10;
               shipImage.setX(shipX);
            } else {
                //keep the ship still
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

    };
}