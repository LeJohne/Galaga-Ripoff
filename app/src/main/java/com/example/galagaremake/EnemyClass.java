package com.example.galagaremake;

import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

public class EnemyClass {
    private int enemyX;
    public int enemyY;
    private ImageView enemyImage;
    public Rect enemyCollisionBox;

    public EnemyClass(ImageView enemyImage) {
        this.enemyImage = enemyImage;
        enemyX = (int)enemyImage.getX();
        enemyY = (int)enemyImage.getY();
        enemyImage.setX(enemyX);
        enemyImage.setY(enemyY);
        enemyImage.setVisibility(View.VISIBLE);
        enemyCollisionBox = new Rect(enemyImage.getWidth(), enemyImage.getHeight(), enemyImage.getWidth(), enemyImage.getHeight());
        enemyCollisionBox.set((int) enemyImage.getX(), (int)enemyImage.getY(), (int)enemyImage.getX() + enemyImage.getWidth(), (int)enemyImage.getY() + enemyImage.getHeight());
    }

    public void move() {
        enemyY = enemyY + 10;
        enemyImage.setY(enemyY);
        enemyCollisionBox.set((int) enemyImage.getX(), (int)enemyImage.getY(), (int)enemyImage.getX() + enemyImage.getWidth(), (int)enemyImage.getY() - enemyImage.getHeight());
    }

    public void remove() {
        enemyImage.setVisibility(View.GONE);
    }
}
