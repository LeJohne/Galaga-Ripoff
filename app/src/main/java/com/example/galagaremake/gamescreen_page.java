package com.example.galagaremake;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Rect;

public class gamescreen_page extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private SensorEventListener rotationListener;
    public ImageView shipImage;

    public ImageView bulletImage;
    private TextView rightText;

    private TextView leftText;
    public int shipX;
    private int shipY;
    private int score;


    private boolean startFlag = false;
    private Rect shipCollisionBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bulletImage = findViewById(R.id.bullet);
        setContentView(R.layout.activity_gamescreen_page);

        findViewById(R.id.gameScreenLayout).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bullet Mybullet = new bullet((ImageView) findViewById(R.id.bullet));
                Mybullet.move();




            }
        });

        // Initialize shipImage after setting the content view
        shipImage = findViewById(R.id.ship);
        rightText = findViewById(R.id.rotationRightText);
        leftText = findViewById(R.id.rotationLeftText);

        shipCollisionBox = new Rect(shipImage.getWidth(), shipImage.getHeight(), shipImage.getWidth(), shipImage.getHeight());
        shipCollisionBox.set((int) shipImage.getX(), (int)shipImage.getY(), (int)shipImage.getX() + shipImage.getWidth(), (int)shipImage.getY() - shipImage.getHeight());


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
                        shipCollisionBox.set((int) shipImage.getX(), (int)shipImage.getY(), (int)shipImage.getX() + shipImage.getWidth(), (int)shipImage.getY() - shipImage.getHeight());
                        leftText.setVisibility(View.VISIBLE);
                        rightText.setVisibility(View.INVISIBLE);
                    } else if (y > 0.5) {
                        // Move the ship right
                        shipX = shipX + 10;
                        shipImage.setX(shipX);
                        shipCollisionBox.set((int) shipImage.getX(), (int)shipImage.getY(), (int)shipImage.getX() + shipImage.getWidth(), (int)shipImage.getY() - shipImage.getHeight());
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



  //create a bullet class that will create a new imageview
    //and move it up the screen until it hits the top of the screen
    //or hits an enemy
    //if it hits an enemy, remove the enemy and the bullet
    //if it hits the top of the screen, remove the bullet
    //if it hits nothing, keep moving it up the screen
    //create a new bullet when the user taps the screen

    public class bullet {
        private int bulletX;
        private int bulletY;
        private ImageView bulletImage;
        private Rect bulletCollisionBox;

        public bullet(ImageView newbulletImage) {
            //this.bulletImage = new ImageView(context);
            this.bulletImage = newbulletImage;
            //this.bulletImage.setImageResource(R.drawable.bullet3);
            this.bulletX = (int)shipImage.getX() + (shipImage.getWidth() / 2);
            this.bulletY = (int)shipImage.getY() - shipImage.getHeight();
            this.bulletImage.setX(bulletX);
            this.bulletImage.setY(bulletY);
            this.bulletImage.setVisibility(View.VISIBLE);
            this.bulletCollisionBox = new Rect(bulletImage.getWidth(), bulletImage.getHeight(), bulletImage.getWidth(), bulletImage.getHeight());
            this.bulletCollisionBox.set((int) bulletImage.getX(), (int)bulletImage.getY(), (int)bulletImage.getX() + bulletImage.getWidth(), (int)bulletImage.getY() - bulletImage.getHeight());
        }

        public void move() {
            rightText.setVisibility(View.VISIBLE);
           leftText.setVisibility(View.VISIBLE);


            //Make the bullet move up the screen over the duration of 5 seconds
           /* while(bulletY > 0) {
                bulletY -= 10;

                bulletImage.setY(bulletY);
                bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() - bulletImage.getHeight());
            }*/
            ValueAnimator animator = ValueAnimator.ofFloat(bulletY, 0); // Animate from current Y position to Y = 0 (top of the screen)
            animator.setDuration(5000); // 5 seconds duration
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(animation -> {
                float animatedValue = (float) animation.getAnimatedValue();
                this.bulletImage.setY(animatedValue); // Update Y position of the bullet
                // Update collision box or any other related calculations here

                // Refresh the view
                this.bulletImage.requestLayout();
            });
            animator.start();
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
            enemyCollisionBox.set((int) enemyImage.getX(), (int)enemyImage.getY(), (int)enemyImage.getX() + enemyImage.getWidth(), (int)enemyImage.getY() - enemyImage.getHeight());
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




