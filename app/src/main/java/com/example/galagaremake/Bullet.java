package com.example.galagaremake;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/*
public class Bullet extends gamescreen_page {
        private int bulletX;
        private int bulletY;
        private ImageView bulletImage;
        private Rect bulletCollisionBox;

        public Bullet(Context context) {
            this.bulletImage = new ImageView(context);
            //this.bulletImage = newbulletImage;
            this.bulletImage.setImageResource(R.drawable.bullet3);
            this.bulletX = (int)shipImage.getX() + (shipImage.getWidth() / 2);
            this.bulletY = (int)shipImage.getY() - shipImage.getHeight();
            this.bulletImage.setX(bulletX);
            this.bulletImage.setY(bulletY);
            this.bulletImage.setVisibility(View.VISIBLE);
            this.bulletCollisionBox = new Rect(bulletImage.getWidth(), bulletImage.getHeight(), bulletImage.getWidth(), bulletImage.getHeight());
            this.bulletCollisionBox.set((int) bulletImage.getX(), (int)bulletImage.getY(), (int)bulletImage.getX() + bulletImage.getWidth(), (int)bulletImage.getY() - bulletImage.getHeight());
        }

        public void move() {
            //rightText.setVisibility(View.VISIBLE);
            //leftText.setVisibility(View.VISIBLE);


            //Make the bullet move up the screen over the duration of 5 seconds
            while(bulletY > 0) {
                bulletY -= 10;

                bulletImage.setY(bulletY);
                bulletCollisionBox.set((int) bulletImage.getX(), (int) bulletImage.getY(), (int) bulletImage.getX() + bulletImage.getWidth(), (int) bulletImage.getY() - bulletImage.getHeight());
            }
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
*/