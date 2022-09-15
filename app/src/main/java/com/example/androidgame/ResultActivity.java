package com.example.androidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.filament.View;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        public void tryAgain (View view){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }


        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0) ;
        scoreLabel.setText(score + " ");

        //Highest score
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE", 0);

        if (score > highScore){
            // Save highest score in context
            SharedPreferences.Editor editor = sharedPreferences.edit() ;
            editor.putInt("HIGH_SCORE", score);
            editor.apply();

            highScoreLabel.setText(" " + score);
        }

        else {
            highScoreLabel.setText(" " + highScore);
        }


    }
}