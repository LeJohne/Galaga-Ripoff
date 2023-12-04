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
    public int enemyX;
    public int enemyY;


    private ImageView enemyImage;
    public Rect enemyCollisionBox;

    private ImageView BulletImage;

    private ShipClass ship;

    int screenHeight;

    public EnemyBulletClass myBullet;

    public int timesRun = 0;

    GameCounters gamecounter;

    public EnemyClass(ImageView enemyImage,int myHeight, ShipClass ship, ImageView bulletImage, GameCounters gameCounter) {
        this.enemyImage = enemyImage;
        this.screenHeight = myHeight;
        this.BulletImage = bulletImage;
        this.gamecounter = gameCounter;;
        this.myBullet = new EnemyBulletClass(BulletImage,this.enemyImage);
        enemyX = (int) enemyImage.getX();
        enemyY = (int) enemyImage.getY();
        enemyImage.setVisibility(View.VISIBLE);
        enemyCollisionBox = new Rect(enemyImage.getWidth(), enemyImage.getHeight(), enemyImage.getWidth(), enemyImage.getHeight());
        enemyCollisionBox.set((int) enemyImage.getX(), (int) enemyImage.getY(), (int) enemyImage.getX() + enemyImage.getWidth(), (int) enemyImage.getY() + enemyImage.getHeight());
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                EnemyBulletClass.move(screenHeight,ship);
//            }
//        }, 0, 1000);//put here time 1000 milliseconds=1 second


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

        enemyCollisionBox.setEmpty();
        gamecounter.incrementScore(500);
        gamecounter.decrementEnemies();
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
