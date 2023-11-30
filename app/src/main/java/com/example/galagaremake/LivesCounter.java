package com.example.galagaremake;

public class LivesCounter {
    private int lives;

    public LivesCounter(int initialLives){
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
