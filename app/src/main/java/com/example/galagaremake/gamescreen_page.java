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

    private TextView rightText;

    private TextView leftText;
    private int shipX;
    private int shipY;
    private boolean startFlag = false;

    private GameCounters gameCounter;
    public ImageView bulletImage;
    private BulletClass bullet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bulletImage = findViewById(R.id.bullet);
        setContentView(R.layout.activity_gamescreen_page);

        // Initialize shipImage after setting the content view
        shipImage = findViewById(R.id.ship);
        rightText = findViewById(R.id.rotationRightText);
        leftText = findViewById(R.id.rotationLeftText);

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
                        leftText.setVisibility(View.VISIBLE);
                        rightText.setVisibility(View.INVISIBLE);
                    }
                    if (y > 0.01 && shipX < screenWidth - shipImage.getWidth()) {
                        // Move the ship right
                        shipX = shipX + 75;
                        shipImage.setX(shipX);
                        rightText.setVisibility(View.VISIBLE);
                        leftText.setVisibility(View.INVISIBLE);
                    }
                    if (y == 0) {
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

        findViewById(R.id.gameScreenLayout).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bullet = new BulletClass((ImageView) findViewById(R.id.bullet), shipImage);
                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
                bullet.move(rightText, leftText, screenHeight);
            }
        });

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


    public class enemy {
        private int enemyX;
        private int enemyY;
        private ImageView enemyImage;
        private Rect enemyCollisionBox;

        public enemy(ImageView enemyImage) {
            this.enemyImage = enemyImage;
            enemyX = (int)enemyImage.getX();
            enemyY = (int)enemyImage.getY();
            enemyImage.setX(enemyX);
            enemyImage.setY(enemyY);
            enemyImage.setVisibility(View.VISIBLE);
            enemyCollisionBox = new Rect(enemyImage.getWidth(), enemyImage.getHeight(), enemyImage.getWidth(), enemyImage.getHeight());
            enemyCollisionBox.set((int) enemyImage.getX(), (int)enemyImage.getY(), (int)enemyImage.getX() + enemyImage.getWidth(), (int)enemyImage.getY() + enemyImage.getHeight());
        }

        public void move() {
            enemyY = enemyY + 10;
            enemyImage.setY(enemyY);
            enemyCollisionBox.set((int) enemyImage.getX(), (int)enemyImage.getY(), (int)enemyImage.getX() + enemyImage.getWidth(), (int)enemyImage.getY() - enemyImage.getHeight());
        }

        public void remove() {
            enemyImage.setVisibility(View.INVISIBLE);
        }
    }
}