package com.example.galagaremake;

import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

public class ShipClass {
    private ImageView shipImage;
    private int shipX;
    private int shipY;

    private Rect shipCollisionBox;
    ShipClass(ImageView shipImage){
        this.shipImage = shipImage;
        shipX = (int) shipImage.getX();
        shipY = (int) shipImage.getY();
        shipCollisionBox = new Rect(shipImage.getWidth(), shipImage.getHeight(), shipImage.getWidth(), shipImage.getHeight());
        shipCollisionBox.set((int) shipImage.getX(), (int) shipImage.getY(), (int) shipImage.getX() + shipImage.getWidth(), (int) shipImage.getY() + shipImage.getHeight());
    }

    public Rect getShipCollisionBox() {
        return shipCollisionBox;
    }

    public void removeWithExplosion() {
        playExplosionAnimation();
        shipImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                shipImage.setVisibility(View.GONE);
            }
        }, getExplosionDuration());
    }

    private void playExplosionAnimation() {
        shipImage.setBackgroundResource(R.drawable.explosion);

        AnimationDrawable explodeAnimation = (AnimationDrawable) shipImage.getBackground();
        explodeAnimation.start();
    }

    private int getExplosionDuration() {
        AnimationDrawable explodeAnimation = (AnimationDrawable) shipImage.getBackground();
        int duration = 0;
        for (int i = 0; i < explodeAnimation.getNumberOfFrames(); i++) {
            duration += explodeAnimation.getDuration(i);
        }
        return duration;
    }

    public void move(float y, int screenWidth) {
        // Evaluate these values to determine if the phone is tilted left or right, in portrait mode
        if (y < -0.01 && shipX > -75) {
            // Move the ship left
            shipX = shipX - 75;
            shipImage.setX(shipX);
            //leftText.setVisibility(View.VISIBLE);
            //rightText.setVisibility(View.INVISIBLE);
        }
        if (y > 0.01 && shipX < screenWidth - shipImage.getWidth()) {
            // Move the ship right
            shipX = shipX + 75;
            shipImage.setX(shipX);
            //rightText.setVisibility(View.VISIBLE);
            //leftText.setVisibility(View.INVISIBLE);
        }
        if (y == 0) {
            // Keep the ship still
            //rightText.setVisibility(View.INVISIBLE);
            //leftText.setVisibility(View.INVISIBLE);
        }

    }
}
