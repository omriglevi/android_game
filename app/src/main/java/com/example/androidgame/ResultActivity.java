package com.example.androidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0) ;
        scoreLabel.setText("Score : " + score);

        //Highest score
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE", 0);

        if (score > highScore){
            SharedPreferences.Editor editor = sharedPreferences.edit() ;
            editor.putInt("HIGH_SCORE", score);
            editor.apply();

            highScoreLabel.setText("Higherst Score : " + score);
        }

        else {
            highScoreLabel.setText("Highest Score : " + highScore);
        }


    }
}