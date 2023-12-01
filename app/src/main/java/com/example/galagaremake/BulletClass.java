package com.example.galagaremake;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BulletClass extends AppCompatActivity {
    private int bulletX;
    private int bulletY;
    private ImageView bulletImage;
    private Rect bulletCollisionBox;

    //create a bullet class that will create a new imageview
    //and move it up the screen until it hits the top of the screen
    //or hits an enemy
    //if it hits an enemy, remove the enemy and the bullet
    //if it hits the top of the screen, remove the bullet
    //if it hits nothing, keep moving it up the screen
    //create a new bullet when the user taps the screen

    public BulletClass(ImageView newBulletImage, ImageView shipImage) {
        this.bulletImage = newBulletImage;
        this.bulletX = (int) shipImage.getX() + (shipImage.getWidth() / 2);
        this.bulletY = (int) shipImage.getY() - shipImage.getHeight();
        this.bulletImage.setX(bulletX);
        this.bulletImage.setY(bulletY);
        this.bulletImage.setVisibility(View.VISIBLE);
        this.bulletCollisionBox = new Rect(bulletImage.getWidth(), bulletImage.getHeight(), bulletImage.getWidth(), bulletImage.getHeight());
        this.bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() + bulletImage.getHeight());
    }

    public void move(TextView rightText, TextView leftText, int screenHeight) {
        rightText.setVisibility(View.VISIBLE);
        leftText.setVisibility(View.VISIBLE);
        //int screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        // Make the bullet move up the screen over the duration of 5 seconds
        for (int i = 1; i < (screenHeight/20); i++) {
            ValueAnimator animator = ValueAnimator.ofFloat(bulletY, screenHeight-(i*20));
            animator.setDuration(300);
            animator.setInterpolator(new LinearInterpolator());
            int finalI = i;
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
                    //bulletImage.setVisibility(View.INVISIBLE);

                }
            });
            animator.start();

        }

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



}
