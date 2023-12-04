package com.example.galagaremake;

import static android.graphics.Rect.intersects;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class EnemyBulletClass extends AppCompatActivity {

    private float bulletX;
    private float bulletY;

    private ImageView EnemyImage;
    private float enemyY;
    private ImageView bulletImage;
    private Rect bulletCollisionBox;

    //create a bullet class that will create a new imageview
    //and move it up the screen until it hits the top of the screen
    //or hits an enemy
    //if it hits an enemy, remove the enemy and the bullet
    //if it hits the top of the screen, remove the bullet
    //if it hits nothing, keep moving it up the screen
    //create a new bullet when the user taps the screen

    public EnemyBulletClass(ImageView newBulletImage, ImageView enemyImage) {
        this.bulletImage = newBulletImage;
        this.EnemyImage = enemyImage;
        this.bulletX = (int) enemyImage.getX() + (EnemyImage.getWidth() / 2);
        this.bulletY = (int) enemyImage.getY() - EnemyImage.getHeight();

        this.bulletImage.setX(bulletX);
        this.bulletImage.setY(bulletY);
        //this.bulletImage.setVisibility(View.INVISIBLE);
        this.bulletCollisionBox = new Rect(bulletImage.getWidth(), bulletImage.getHeight(), bulletImage.getWidth(), bulletImage.getHeight());
        this.bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() + bulletImage.getHeight());
    }

    public void shoot(int screenHeight, ShipClass ship) {
        //rightText.setVisibility(View.VISIBLE);
        //leftText.setVisibility(View.VISIBLE);
        //int screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        // Make the bullet move up the screen over the duration of 5 seconds
        this.bulletX = (int) this.EnemyImage.getX() + (EnemyImage.getWidth() / 2);

        Log.d("bulletXA", String.valueOf(bulletX));
        this.bulletY = (int) this.EnemyImage.getY() + EnemyImage.getHeight();
        this.bulletImage.setX(bulletX);
        this.bulletImage.setY(bulletY);
        this.bulletImage.setVisibility(View.VISIBLE);
        int i = 0;
        //Log.d("mymessageE",String.valueOf(myenemy.enemyY));
        AtomicBoolean endAnimation = new AtomicBoolean(false);
        Log.d("mymessageB", String.valueOf(bulletY));
        while((i < (screenHeight/20)) && !endAnimation.get()) {
            ValueAnimator animator1 = ValueAnimator.ofFloat(bulletY, (i*20));
            animator1.setDuration(300);
            animator1.setInterpolator(new LinearInterpolator());
            animator1.addUpdateListener(animation -> {
                float animatedValue = (float) animation.getAnimatedValue();
                bulletY = animatedValue;
                this.bulletImage.setY(bulletY);
                Log.d("bulletY",String.valueOf(bulletY));
                Log.d("bulletX",String.valueOf(bulletX));

                // Update collision box
                //this.bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() + bulletImage.getHeight());
                if(!endAnimation.get()) {
                    this.bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() + bulletImage.getHeight());
                } else {
                    this.bulletCollisionBox.setEmpty();
                }
                //this.bulletCollisionBox.set(-999999999,99999999999,9999999999,-999999999)
                // Refresh the view

                        if (intersects(this.bulletCollisionBox, ship.shipCollisionBox)) {
                            Log.d("COLLISION", "WHOO");
                            ship.removeWithExplosion();
                            this.bulletImage.setVisibility(View.GONE);
                            this.bulletCollisionBox.setEmpty();
                            endAnimation.set(true);
                        }



                this.bulletImage.requestLayout();

            });

            animator1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //bulletImage.setVisibility(View.INVISIBLE);
                    Log.d("mymessageO",String.valueOf(bulletY));
                }
            });
            animator1.start();
            i++;
        }

        this.bulletImage.requestLayout();
        Log.d("mymessageFINAL",String.valueOf(bulletImage.getY()));
    /*
        if(!endAnimation.get()) {
        ValueAnimator animator = ValueAnimator.ofFloat(bulletY, -20);
        animator.setDuration(600);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            this.bulletImage.setY(animatedValue);

            // Update collision box or any other related calculations here

            // Refresh the view
            this.bulletImage.requestLayout();
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                bulletImage.setVisibility(View.INVISIBLE);

            }
        });
        animator.start();
     }

     */
    }



}





