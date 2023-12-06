package com.example.galagaremake;

import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.opengl.Visibility;
import android.view.View;
import android.widget.ImageView;

public class ShipClass {
    public ImageView shipImage;
    private int shipX;
    private int shipY;

    public Rect shipCollisionBox;
    private GameCounters gameCounter;

    //CREATES THE SHIP WITH THE SHIP COLLISION
    ShipClass(ImageView shipImage, GameCounters gameCounter) {
        this.gameCounter = gameCounter;
        this.shipImage = shipImage;
        shipX = (int) shipImage.getX();
        shipY = (int) shipImage.getY();
        shipCollisionBox = new Rect(shipImage.getWidth(), shipImage.getHeight(), shipImage.getWidth(), shipImage.getHeight());
        shipCollisionBox.set((int) shipImage.getX(), (int) shipImage.getY(), (int) shipImage.getX() + shipImage.getWidth(), (int) shipImage.getY() + shipImage.getHeight());
    }

    public Rect getShipCollisionBox() {
        return shipCollisionBox;
    }

    public float getX() {
        return shipX;
    }

    public float getY() {
        return shipY;
    }

    public int getWidth() {
        return shipImage.getWidth();
    }

    public int getHeight() {
        return shipImage.getHeight();
    }

    //EXPLODES THE SHIP WHEN THE SHIP GETS HIT
    public void removeWithExplosion() {
        playExplosionAnimation();
        shipImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                shipImage.setVisibility(View.GONE);
            }
        }, getExplosionDuration());

        gameCounter.decrementLives();
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

    //CONTROLS THE MOVEMENT OF THE SHIP
    public void move(float y, int screenWidth) {
        //EVALUATE THESE VALUES TO DETERMINE IF THE PHONE IS TILTED LEFT OR RIGHT, IN PORTRAIT MODE
        if (y < -0.01 && shipX > -75) {
            // MOVE THE SHIP LEFT
            shipX = shipX - 75;
            shipImage.setX(shipX);
            //leftText.setVisibility(View.VISIBLE);
            //rightText.setVisibility(View.INVISIBLE);
        }
        if (y > 0.01 && shipX < screenWidth - shipImage.getWidth()) {
            // MOVE THE SHIP RIGHT
            shipX = shipX + 75;
            shipImage.setX(shipX);
            //rightText.setVisibility(View.VISIBLE);
            //leftText.setVisibility(View.INVISIBLE);
        }
        if (y == 0) {
            // KEEP THE SHIP STILL
            //rightText.setVisibility(View.INVISIBLE);
            //leftText.setVisibility(View.INVISIBLE);
        }
        shipImage.setVisibility(View.VISIBLE);
        shipCollisionBox.set((int) shipImage.getX(), (int) shipImage.getY(), (int) shipImage.getX() + shipImage.getWidth(), (int) shipImage.getY() + shipImage.getHeight());
    }
}
