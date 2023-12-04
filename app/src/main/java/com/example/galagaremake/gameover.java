package com.example.galagaremake;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class gameover extends AppCompatActivity {
    private TextView scoreCounterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        findViewById(R.id.HomeText).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}