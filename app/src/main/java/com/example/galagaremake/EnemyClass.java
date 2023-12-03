package com.example.galagaremake;

import static android.graphics.Rect.intersects;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class EnemyClass {
    private int enemyX;
    public int enemyY;

    private float bulletY;
    private ImageView enemyImage;
    public Rect enemyCollisionBox;

    private ImageView BulletImage;

    private ShipClass ship;

    int screenHeight;

    private EnemyBulletClass myBullet;

    public EnemyClass(ImageView enemyImage,int myHeight, ShipClass ship, ImageView bulletImage) {
        this.enemyImage = enemyImage;
        this.screenHeight = myHeight;
        this.BulletImage = bulletImage;
        this.myBullet = new EnemyBulletClass(BulletImage,this.enemyImage);
        enemyX = (int) enemyImage.getX();
        enemyY = (int) enemyImage.getY();
        bulletY = enemyY;
        enemyImage.setX(enemyX);
        enemyImage.setY(enemyY);
        enemyImage.setVisibility(View.VISIBLE);
        enemyCollisionBox = new Rect(enemyImage.getWidth(), enemyImage.getHeight(), enemyImage.getWidth(), enemyImage.getHeight());
        enemyCollisionBox.set((int) enemyImage.getX(), (int) enemyImage.getY(), (int) enemyImage.getX() + enemyImage.getWidth(), (int) enemyImage.getY() + enemyImage.getHeight());
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                EnemyBulletClass.move(screenHeight,ship);
//            }
//        }, 0, 1000);//put here time 1000 milliseconds=1 second
        final Handler handler = new Handler();
        final int delay = 5000; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {
                Log.d("AH", "YESSIR");
                myBullet.shoot(screenHeight,ship);
                handler.postDelayed(this, delay);
            }
        }, delay);


    }

    public void move() {
        enemyY = enemyY + 10;
        enemyImage.setY(enemyY);
        enemyCollisionBox.set((int) enemyImage.getX(), (int) enemyImage.getY(), (int) enemyImage.getX() + enemyImage.getWidth(), (int) enemyImage.getY() - enemyImage.getHeight());
    }


    public void remove() {
        enemyImage.setVisibility(View.GONE);

    }

    public void removeWithExplosion() {
        playExplosionAnimation();
        enemyImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                enemyImage.setVisibility(View.GONE);
            }
        }, getExplosionDuration());
    }

    private void playExplosionAnimation() {
        enemyImage.setBackgroundResource(R.drawable.explosion);

        AnimationDrawable explodeAnimation = (AnimationDrawable) enemyImage.getBackground();
        explodeAnimation.start();
    }

    private int getExplosionDuration() {
        AnimationDrawable explodeAnimation = (AnimationDrawable) enemyImage.getBackground();
        int duration = 0;
        for (int i = 0; i < explodeAnimation.getNumberOfFrames(); i++) {
            duration += explodeAnimation.getDuration(i);
        }
        return duration;
    }

}
