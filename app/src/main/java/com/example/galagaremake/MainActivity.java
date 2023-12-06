package com.example.galagaremake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

//JAVA CLASS FOR THE HOME SCREEN
public class MainActivity extends AppCompatActivity {

    //SETS THE VIEW ON THE HOME SCREEN PAGE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_page);

        //IF YOU CLICK ON THE PLAY BUTTON, SETS THE VIEW TO GAME SCREEN PAGE
        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), gamescreen_page.class));
            }
        });

        //IF YOU CLICK ON THE OPTIONS BUTTON, SETS THE VIEW TO OPTION SCREEN PAGE
        findViewById(R.id.optionsButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), options_page.class));
            }
        });

        //IF YOU CLICK ON EXIT PLAY BUTTON, IT KILLS THE PROGRAM / PROCESS
        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }
}