package com.example.galagaremake;

public class Counters {
    private int lives;

    public Counters(int initialLives){
        this.lives = initialLives;
    }

    public int getLives(){
        return lives;
    }

    public void decrementLives(){
        if (lives > 0){
            lives--;
        }
    }

}
