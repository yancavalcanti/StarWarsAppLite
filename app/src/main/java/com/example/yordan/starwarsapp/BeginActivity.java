package com.example.yordan.starwarsapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class BeginActivity extends AppCompatActivity {

    ImageButton firstButton;
    private static int TIME_OUT = 12000;
    MediaPlayer ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        ring = MediaPlayer.create(BeginActivity.this,R.raw.theme_song);
        ring.start();

        firstButton = (ImageButton) findViewById(R.id.starwarsapp_enter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentPeopleInsert = new Intent(BeginActivity.this, MainActivity.class);
                startActivity(intentPeopleInsert);
                ring.stop();
                finish();
            }
        }, TIME_OUT);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPeopleInsert = new Intent(BeginActivity.this, MainActivity.class);
                startActivity(intentPeopleInsert);
                ring.stop();
            }

          });
        }




    }

