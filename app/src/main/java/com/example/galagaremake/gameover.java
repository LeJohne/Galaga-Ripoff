package com.example.galagaremake;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//CREATE A GAMEOVER CLASS TO ACTIVATE WHEN THE GAME IS OVER
public class gameover extends AppCompatActivity {
    //CREATE A SCORECOUNTER TEXTVIEW
    private TextView scoreCounterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //CHANGE TO THE GAME OVER SCREEN
        setContentView(R.layout.activity_gameover);

        //DISPLAY SCORE AT GAME OVER SCREEN
        scoreCounterTextView = findViewById(R.id.endScoreCounter);

        int score = getIntent().getIntExtra("score", 0);

        if (scoreCounterTextView != null) {
            scoreCounterTextView.setText(String.valueOf(score));
        }

        //START A NEW GAME
        findViewById(R.id.playAgainText).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), gamescreen_page.class));
            }
        });

        //GO BACK TO HOME SCREEN
        findViewById(R.id.HomeText).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}