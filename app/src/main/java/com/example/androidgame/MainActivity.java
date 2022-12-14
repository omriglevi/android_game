package com.example.androidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
//Elements
    private TextView scoreLabel, startLabel ;
    private ImageView pink, black, orange, box ;

    //Size
    private int screenHeight, screenWidth;
    private int frameHeight ;
    private int boxSize ;

    //Positions
    private float boxY ;
    private float orangeX, orangeY ;
    private float pinkX, pinkY ;
    private float blackX, blackY ;

    //Score
    private int score ;

    //Timer
    private Timer timer = new Timer() ;
    private  Handler handler = new Handler() ;

    //Status
    private boolean action_flag = false ;
    private boolean start_flag = false ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLabel = findViewById(R.id.scoreLabel) ;
        startLabel = findViewById(R.id.startLabel) ;
        box = findViewById(R.id.box) ;
        black = findViewById(R.id.black) ;
        pink = findViewById(R.id.pink) ;
        orange = findViewById(R.id.orange) ;

        //Screen size
        WindowManager windowManager = getWindowManager() ;
        Display display = windowManager.getDefaultDisplay() ;
        Point size = new Point();
        display.getSize(size);

        screenHeight = size.y ;
        screenWidth = size.x ;

    // Init position
        orange.setX(-80.0f);
        orange.setY(-80.0f);

        pink.setX(-80.0f);
        pink.setY(-80.0f);

        black.setX(-80.0f);
        black.setY(-80.0f);


        scoreLabel.setText("Score : "+ score);

    }

    public void changePos(){

        hitCheck() ;

        //Orange enemy
        orangeX -= 12 ;
        if (orangeX < 0 ){
            orangeX = screenWidth + 20 ;
            orangeY = (float) Math.floor(Math.random() * (frameHeight - orange.getHeight()) ) ;
        }
        orange.setY(orangeY);
        orange.setX(orangeX);

        //Black enemy
        blackX -= 16;
        if(blackX < 0 ){
            blackX = screenWidth + 10 ;
            blackY = (float) Math.floor(Math.random() * (frameHeight - black.getHeight()) ) ;
        }
        black.setX(blackX);
        black.setY(blackY);

        // Pink enemy
        pinkX -= 20 ;
        if (pinkX < 0){
            pinkX = screenWidth + 5000 ;
            pinkY = (float) Math.floor(Math.random() * (frameHeight - pink.getHeight()) ) ;

        }
        pink.setY(pinkY);
        pink.setX(pinkX);


        // Blue Movment
        if (action_flag){
            // Touch
            boxY -= 20 ;
        } else {
            //Release
            boxY += 20 ;
        }

        //Keep our pacmen in the main box - set margins for moving
        if (boxY < 0 ) boxY = 0 ;
        if (boxY > frameHeight - boxSize) boxY = frameHeight - boxSize ;
        box.setY(boxY);

        scoreLabel.setText("Score " + score);
    }


    public void hitCheck() {
        //Orange
        float orangeCenterX = orangeX + orange.getWidth() / 2.0f;
        float orangeCenterY = orangeY + orange.getHeight() / 2.0f;

        if (0 <= orangeCenterX && orangeCenterX <= boxSize &&
                boxY <= orangeCenterY && orangeCenterY <= boxY + boxSize) {
            orangeX = 100.0f;
            score += 10;
        }

        //Pink
        float pinkCenterX = pinkX + pink.getWidth() / 2.0f;
        float pinkCenterY = pinkY + pink.getHeight() / 2.0f;

        if (0 <= pinkCenterX && pinkCenterX <= boxSize &&
                boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize) {
            pinkX = 100.0f;
            score += 30;
        }

        //Black
        float blackCenterX = blackX + black.getWidth() / 2.0f;
        float blackCenterY = blackY + black.getHeight() / 2.0f;

        if (0 <= blackCenterX && blackCenterX <= boxSize &&
                boxY <= blackCenterY && blackCenterY <= boxY + boxSize) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }

            //Show result activity
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("SCORE", score) ;
            startActivity(intent);


        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!start_flag){
        start_flag = true ;

        //FrameHeight
            FrameLayout frameLayout = findViewById(R.id.frame) ;
            frameHeight = frameLayout.getHeight() ;

            //Box
            boxY = box.getY() ;
            boxSize = box.getHeight() ;


        startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos() ;
                        }
                    });
                }
                // enemy movment speed
            },0, 15);

        } else{
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                action_flag = true ;
            }else if (event.getAction() == MotionEvent.ACTION_UP){
                action_flag = false ;
            }
        }

        return super.onTouchEvent(event);
    }
}