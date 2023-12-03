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
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class gamescreen_page extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private SensorEventListener rotationListener;

    private boolean startFlag = false;

    private ImageView shipImage;
    private GameCounters gameCounter;
    public ImageView bulletImage;

    public ImageView enemyBulletImage1;
    public ImageView enemyBulletImage2;
    public ImageView enemyBulletImage3;
    public ImageView enemyBulletImage4;
    public ImageView enemyBulletImage5;
    public ImageView enemyBulletImage6;
    public ImageView enemyBulletImage7;
    public ImageView enemyBulletImage8;
    public ImageView enemyBulletImage9;
    public ImageView enemyBulletImage10;


    public ImageView enemy1Image;
    private BulletClass bullet;

    private ShipClass ship;

    private EnemyClass Myenemy1;
    private EnemyClass Myenemy2;
    private EnemyClass Myenemy3;
    private EnemyClass Myenemy4;
    private EnemyClass Myenemy5;
    private EnemyClass Myenemy6;
    private EnemyClass Myenemy7;
    private EnemyClass Myenemy8;
    private EnemyClass Myenemy9;
    private EnemyClass Myenemy10;

    private EnemyClass[] enemyArray = new EnemyClass[10];
    int screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        super.onCreate(savedInstanceState);
        bulletImage = findViewById(R.id.bullet);
        setContentView(R.layout.activity_gamescreen_page);

        // Initialize shipImage after setting the content view
        shipImage = findViewById(R.id.ship);
        ship = new ShipClass(shipImage);
        //rightText = findViewById(R.id.rotationRightText);
        //leftText = findViewById(R.id.rotationLeftText);
        //createEnemies();


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
                    ship.move(y, screenWidth);

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
                Myenemy1 = new EnemyClass((ImageView) findViewById(R.id.enemy1), screenHeight, ship,(ImageView) findViewById(R.id.enemyBullet1));
                Myenemy2 = new EnemyClass((ImageView) findViewById(R.id.enemy2),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet2));
                Myenemy3 = new EnemyClass((ImageView) findViewById(R.id.enemy3),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet3));
                Myenemy4 = new EnemyClass((ImageView) findViewById(R.id.enemy4),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet4));
                Myenemy5 = new EnemyClass((ImageView) findViewById(R.id.enemy5),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet5));
                Myenemy6 = new EnemyClass((ImageView) findViewById(R.id.enemy6),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet6));
                Myenemy7 = new EnemyClass((ImageView) findViewById(R.id.enemy7),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet7));
                Myenemy8 = new EnemyClass((ImageView) findViewById(R.id.enemy8),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet8));
                Myenemy9 = new EnemyClass((ImageView) findViewById(R.id.enemy9),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet9));
                Myenemy10 = new EnemyClass((ImageView) findViewById(R.id.enemy10),screenHeight, ship, (ImageView) findViewById(R.id.enemyBullet10));

                enemyArray[0] = Myenemy1;
                enemyArray[1] = Myenemy2;
                enemyArray[2] = Myenemy3;
                enemyArray[3] = Myenemy4;
                enemyArray[4] = Myenemy5;
                enemyArray[5] = Myenemy6;
                enemyArray[6] = Myenemy7;
                enemyArray[7] = Myenemy8;
                enemyArray[8] = Myenemy9;
                enemyArray[9] = Myenemy10;

                bullet.move(screenHeight, enemyArray);
            }
        });

    }

   /* private void createEnemies() {

        enemyArray[0] = new EnemyClass((ImageView) findViewById(R.id.enemy1),screenHeight, ship, enemyBulletImage1);
        enemyArray[1] = new EnemyClass((ImageView) findViewById(R.id.enemy2),screenHeight, ship, enemyBulletImage2);
        enemyArray[2] = new EnemyClass((ImageView) findViewById(R.id.enemy3),screenHeight, ship, enemyBulletImage3);
        enemyArray[3] = new EnemyClass((ImageView) findViewById(R.id.enemy4),screenHeight, ship, enemyBulletImage4);
        enemyArray[4] = new EnemyClass((ImageView) findViewById(R.id.enemy5),screenHeight, ship, enemyBulletImage5);
        enemyArray[5] = new EnemyClass((ImageView) findViewById(R.id.enemy6),screenHeight, ship, enemyBulletImage6);
        enemyArray[6] = new EnemyClass((ImageView) findViewById(R.id.enemy7),screenHeight, ship, enemyBulletImage7);
        enemyArray[7] = new EnemyClass((ImageView) findViewById(R.id.enemy8),screenHeight, ship, enemyBulletImage8);
        enemyArray[8] = new EnemyClass((ImageView) findViewById(R.id.enemy9),screenHeight, ship, enemyBulletImage9);
        enemyArray[9] = new EnemyClass((ImageView) findViewById(R.id.enemy10),screenHeight, ship, enemyBulletImage10);

    }
    */



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