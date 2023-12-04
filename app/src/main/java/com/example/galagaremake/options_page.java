package com.example.galagaremake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

public class options_page extends AppCompatActivity {

    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public MediaPlayer music;

//    pref = this.getSharedPreferences("MUSIC",0);
//    editor = pref.edit();
//    music = MediaPlayer.create(this, R.raw.galagasound_select);
    // music.setLooping(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_page);

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

//        findViewById(R.id.soundFxSw).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });

    }


}