package com.example.galagaremake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class GameCounters extends AppCompatActivity {
    private final Context context;
    boolean isGameOver;
    private int lives;
    private int round;
    private int score;
    private int enemies;
    private TextView livesCounterTextView;
    private TextView roundCounterTextView;
    private TextView scoreCounterTextView;

    //CREATED A CLASS NAMED GAMECOUNTERS THAT HOLDS ALL THE LIVES, ROUNDS,
    //SCORE, AND ENEMIES INFORMATION

    public GameCounters(int initialLives, int initialRounds, int initialScore, int initialEnemies, Context context){
        this.lives = initialLives;
        this.round = initialRounds;
        this.score = initialScore;
        this.enemies = initialEnemies;
        this.isGameOver = false;
        this.context = context;

        this.livesCounterTextView = ((Activity) context).findViewById(R.id.livesCounter);
        this.roundCounterTextView = ((Activity) context).findViewById(R.id.roundCounter);
        this.scoreCounterTextView = ((Activity) context).findViewById(R.id.scoreCounter);
    }

    //GET THE LIVES COUNTER INFORMATION
    public int getLives(){
        return lives;
    }

    //DECREASE THE LIVES
    //IF LIVES IS BELOW 0, THEN END GAME AND GO TO GAMEOVER SCREEN
    public void decrementLives(){
        if (lives > 0){
            lives--;
            updateLivesCounterTextView();
        } else{
            isGameOver = true;
            if (context != null){

                Intent intent = new Intent(context, gameover.class);
                intent.putExtra("score", getScore());
                context.startActivity(intent);
            }
        }
    }

    //GET THE ROUNDS INFORMATION
    public int getRounds(){
        return round;
    }

    //INCREASE THE ROUND COUNTER BY 1 AND UPDATE THE ROUND COUNTER TEXTVIEW
    public void incrementRounds(){
        round++;
        updateRoundCounterTextView();
    }

    //GET THE SCORE INFORMATION
    public int getScore(){
        return score;
    }

    //INCREASE THE SCORE BY A CERTAIN AMOUNT OF POINTS
    //UPDATE THE SCORE COUNTER TEXTVIEW
    public void incrementScore(int points){
        score += points;
        updateScoreCounterTextView();
    }

    //DECREASE THE ENEMIES BY 1
    //IF ENEMIES ARE LESS THAN 0, INCREASE THE ROUND BY 1
    public void decrementEnemies() {
        enemies--;
        if (enemies == 0) {
            incrementRounds();
        }
    }

    //RETURN THE ENEMIES INFORMATION
    public int getEnemies() {
        return enemies;
    }

    //UPDATE THE LIVES COUNTER TEXTVIEW STRING ON SCREEN
    private void updateLivesCounterTextView() {
        if (livesCounterTextView != null) {
            livesCounterTextView.setText(String.valueOf(lives));
        }
    }

    //UPDATE THE ROUNDS COUNTER TEXTVIEW STRING ON SCREEN
    private void updateRoundCounterTextView() {
        if (roundCounterTextView != null) {
            roundCounterTextView.setText(String.valueOf(round));
        }
    }

    //UPDATE THE SCORE COUNTER TEXTVIEW STRING ON SCREEN
    private void updateScoreCounterTextView() {
        if (scoreCounterTextView != null) {
            scoreCounterTextView.setText(String.valueOf(score));
        }
    }

    //BOOLEAN FOR WHETHER THE GAME IS OVER OR NOT
    public boolean isGameOver() {
        return isGameOver;
    }

    //ENEMIES WILL BE EQUAL TO I
    public void setEnemies(int i) {
        enemies = i;
    }
}

