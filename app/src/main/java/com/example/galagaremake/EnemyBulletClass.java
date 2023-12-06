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

    private GameCounters gamecounter;

    //CREATE A BULLET CLASS THAT WILL CREATE A NEW IMAGEVIEW
    //AND MOVE IT UP THE SCREEN UNTIL IT HITS THE BOTTOM OF THE SCREEN
    //OR HITS THE SHIP
    //IF IT HITS AN ENEMY, REMOVE A LIVE AND THE BULLET
    //IF IT HITS THE BOTTOM OF THE SCREEN, REMOVE THE BULLET
    //IF IT HITS NOTHING, KEEP IT DOWN UP THE SCREEN
    //CREATE A NEW BULLET AT RANDOM

    public EnemyBulletClass(ImageView newBulletImage, ImageView enemyImage, GameCounters gamecounter) {
        this.bulletImage = newBulletImage;
        this.EnemyImage = enemyImage;
        this.gamecounter = gamecounter;
        this.bulletX = (int) enemyImage.getX() + (EnemyImage.getWidth() / 2);
        this.bulletY = (int) enemyImage.getY() - EnemyImage.getHeight();

        this.bulletImage.setX(bulletX);
        this.bulletImage.setY(bulletY);
        this.bulletImage.setVisibility(View.INVISIBLE);
        this.bulletCollisionBox = new Rect(bulletImage.getWidth(), bulletImage.getHeight(), bulletImage.getWidth(), bulletImage.getHeight());
        this.bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() + bulletImage.getHeight());
    }

    public void shoot(int screenHeight, ShipClass ship) {
        //int screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        // Make the bullet move up the screen over the duration of 5 seconds
        this.bulletX = (int) this.EnemyImage.getX() + (EnemyImage.getWidth() / 2);

        Log.d("bulletXA", String.valueOf(bulletX));
        this.bulletY = (int) this.EnemyImage.getY() + EnemyImage.getHeight();
        this.bulletImage.setX(bulletX);
        this.bulletImage.setY(bulletY);
        this.bulletImage.setVisibility(View.VISIBLE);
        int i = 0;
        AtomicBoolean endAnimation = new AtomicBoolean(false);
        Log.d("mymessageB", String.valueOf(bulletY));
        while((i < (screenHeight/20)+10) && !endAnimation.get()) {


            ValueAnimator animator1 = ValueAnimator.ofFloat(bulletY, (i*20));
            animator1.setDuration(3000/gamecounter.getRounds());
            animator1.setInterpolator(new LinearInterpolator());
            animator1.addUpdateListener(animation -> {
                float animatedValue = (float) animation.getAnimatedValue();
                bulletY = animatedValue;
                this.bulletImage.setY(bulletY);
                Log.d("bulletY",String.valueOf(bulletY));
                Log.d("bulletX",String.valueOf(bulletX));

                // Update collision box
                if(!endAnimation.get()){
                    this.bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() + bulletImage.getHeight());
                } else{
                    this.bulletCollisionBox.setEmpty();
                }

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
    }



}





