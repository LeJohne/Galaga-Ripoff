package com.example.galagaremake;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class gamescreen_page extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private SensorEventListener rotationListener;
    private ImageView shipImage;


    private int shipX;
    private int shipY;
    private boolean startFlag = false;

    private GameCounters gameCounter;
    public ImageView bulletImage;

    public ImageView enemy1Image;
    private BulletClass bullet;

    private EnemyClass Myenemy1;

    private EnemyClass[] enemyArray = new EnemyClass[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bulletImage = findViewById(R.id.bullet);
        setContentView(R.layout.activity_gamescreen_page);

        // Initialize shipImage after setting the content view
        shipImage = findViewById(R.id.ship);
        //rightText = findViewById(R.id.rotationRightText);
        //leftText = findViewById(R.id.rotationLeftText);
        createEnemies();
        gameCounter = new GameCounters(3,1,0, this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {
            rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            rotationListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];

                    int screenWidth = getWindowManager().getDefaultDisplay().getWidth();

                    // Evaluate these values to determine if the phone is tilted left or right, in portrait mode
                    if (y < -0.01 && shipX > -75) {
                        // Move the ship left
                        shipX = shipX - 75;
                        shipImage.setX(shipX);
                        //leftText.setVisibility(View.VISIBLE);
                        //rightText.setVisibility(View.INVISIBLE);
                    }
                    if (y > 0.01 && shipX < screenWidth - shipImage.getWidth()) {
                        // Move the ship right
                        shipX = shipX + 75;
                        shipImage.setX(shipX);
                        //rightText.setVisibility(View.VISIBLE);
                        //leftText.setVisibility(View.INVISIBLE);
                    }
                    if (y == 0) {
                        // Keep the ship still
                        //rightText.setVisibility(View.INVISIBLE);
                        //leftText.setVisibility(View.INVISIBLE);
                    }
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                    // Do something if accuracy changes
                }
            };
        }

        findViewById(R.id.gameScreenLayout).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bullet = new BulletClass((ImageView) findViewById(R.id.bullet), shipImage);
                Myenemy1 = new EnemyClass((ImageView) findViewById(R.id.enemy1));
                enemyArray[0] = Myenemy1;
                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
                bullet.move(screenHeight, enemyArray);
            }
        });

    }

    private void createEnemies() {

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