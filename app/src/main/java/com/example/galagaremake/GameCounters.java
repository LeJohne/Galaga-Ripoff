package com.example.galagaremake;

public class GameCounters {
    private int lives;
    private int rounds;
    private int score;

    public GameCounters(int initialLives, int initialRounds, int initialScore){
        this.lives = initialLives;
        this.rounds = initialRounds;
        this.score = initialScore;
    }

    public int getLives(){
        return lives;
    }

    public void decrementLives(){
        if (lives > 0){
            lives--;
        }
    }

    public int getRounds(){
        return rounds;
    }

    public void incrementRounds(){
        rounds++;
    }

    public int getScore(){
        return score;
    }

    public void incrementScore(int points){
        score += points;
    }
}
