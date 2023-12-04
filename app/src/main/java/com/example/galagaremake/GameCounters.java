package com.example.galagaremake;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameCounters {
    private int lives;
    private int round;
    private int score;

    private int enemies;

    private TextView livesCounterTextView;
    private TextView roundCounterTextView;
    private TextView scoreCounterTextView;

    public GameCounters(int initialLives, int initialRounds, int initialScore, int initialEnemies, Context context){
        this.lives = initialLives;
        this.round = initialRounds;
        this.score = initialScore;
        this.enemies = initialEnemies;

        this.livesCounterTextView = ((Activity) context).findViewById(R.id.livesCounter);
        this.roundCounterTextView = ((Activity) context).findViewById(R.id.roundCounter);
        this.scoreCounterTextView = ((Activity) context).findViewById(R.id.scoreCounter);
    }

    public int getLives(){
        return lives;
    }

    public void decrementLives(){
        if (lives > 0){
            lives--;
            updateLivesCounterTextView();
        }
    }

    public int getRounds(){
        return round;
    }

    public void incrementRounds(){
        round++;
        updateRoundCounterTextView();
    }

    public int getScore(){
        return score;
    }

    public void incrementScore(int points){
        score += points;
        updateScoreCounterTextView();
    }

    public void decrementEnemies() {
        enemies--;
        if (enemies == 0) {
            incrementRounds();
        }
    }
    public int getEnemies() {
        return enemies;
    }

    private void updateLivesCounterTextView() {
        if (livesCounterTextView != null) {
            livesCounterTextView.setText(String.valueOf(lives));
        }
    }

    private void updateRoundCounterTextView() {
        if (roundCounterTextView != null) {
            roundCounterTextView.setText(String.valueOf(round));
        }
    }
    // Update the TextView for score

    private void updateScoreCounterTextView() {
        if (scoreCounterTextView != null) {
            scoreCounterTextView.setText(String.valueOf(score));
        }
    }

}

