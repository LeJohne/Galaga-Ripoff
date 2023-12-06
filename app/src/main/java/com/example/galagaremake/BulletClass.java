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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class BulletClass extends AppCompatActivity {
    private float bulletX;
    private float bulletY;
    private ImageView bulletImage;
    private Rect bulletCollisionBox;
    private ImageView shipView;

    //CREATE A BULLET CLASS THAT WILL CREATE A NEW IMAGEVIEW
    //AND MOVE IT UP THE SCREEN UNTIL IT HITS THE TOP OF THE SCREEN
    //OR HITS AN ENEMY
    //IF IT HITS AN ENEMY, REMOVE THE ENEMY AND THE BULLET
    //IF IT HITS THE TOP OF THE SCREEN, REMOVE THE BULLET
    //IF IT HITS NOTHING, KEEP IT MOVING UP THE SCREEN
    //CREATE A NEW BULLET WHEN THE USER TAPS THE SCREEN

    public BulletClass(ImageView newBulletImage, ShipClass ship) {
        this.bulletImage = newBulletImage;
        this.shipView = ship.shipImage;
        this.bulletX = (int) ship.getX() + (ship.getWidth() / 2);
        this.bulletY = (int) ship.getY() - ship.getHeight();
        this.bulletImage.setX(bulletX);
        this.bulletImage.setY(bulletY);
        this.bulletImage.setVisibility(View.VISIBLE);
        this.bulletCollisionBox = new Rect(bulletImage.getWidth(), bulletImage.getHeight(), bulletImage.getWidth(), bulletImage.getHeight());
        this.bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() + bulletImage.getHeight());
    }

    public void move(int screenHeight, EnemyClass[] enemyArray) {
        this.bulletX = (int) this.shipView.getX() + (shipView.getWidth() / 2);

        Log.d("bulletXA", String.valueOf(bulletX));
        this.bulletY = (int) this.shipView.getY() + shipView.getHeight();
        this.bulletImage.setX(bulletX);
        this.bulletImage.setY(bulletY);
        this.bulletImage.setVisibility(View.VISIBLE);
        // Make the bullet move up the screen over the duration of 5 seconds
        int i = 0;
        AtomicBoolean endAnimation = new AtomicBoolean(false);
        Log.d("started","started");
        while((i < ((screenHeight/20)) + 10) && !endAnimation.get()) {
            Log.d("mymessageB", String.valueOf(bulletY));


            ValueAnimator animator = ValueAnimator.ofFloat(bulletY, screenHeight-(i*20));
            animator.setDuration(300);
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(animation -> {
                float animatedValue = (float) animation.getAnimatedValue();
                bulletY = animatedValue;
                this.bulletImage.setY(bulletY);
                Log.d("mymessageU", String.valueOf(bulletY));

                // Update collision box
                if(!endAnimation.get()){
                    this.bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() + bulletImage.getHeight());
                } else{
                    this.bulletCollisionBox.setEmpty();
                }

                // Refresh the view
                for (int k = 0; k < enemyArray.length; k++) {
                    if (enemyArray[k] != null) {
                        if (intersects(this.bulletCollisionBox, enemyArray[k].enemyCollisionBox)) {
                            Log.d("YES", "WHOO");
                            enemyArray[k].removeWithExplosion();
                            this.bulletImage.setVisibility(View.GONE);
                            this.bulletCollisionBox.setEmpty();
                            endAnimation.set(true);
                        }
                    }
                }
                this.bulletImage.requestLayout();
            });

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    Log.d("mymessageO",String.valueOf(bulletY));
                }
            });
            animator.start();
            i++;
        }

        this.bulletImage.requestLayout();
        Log.d("mymessageFINAL",String.valueOf(bulletImage.getY()));
    }
}
