package com.example.androidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
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
    private int frameHeight ;
    private int boxSize ;

    //Positions
    private float boxY ;

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

    // Init position
        orange.setX(-80.0f);
        orange.setY(-80.0f);

        pink.setX(-80.0f);
        pink.setY(-80.0f);

        black.setX(-80.0f);
        black.setY(-80.0f);

        //Temp
        boxY = 500.0f ;


    }

    public void changePos(){
        if (action_flag){
            // Touch
            boxY -= 10 ;
        } else {
            //Release
            boxY += 10 ;
        }

        //Keep our pacmen in the main box - set margins for moving
        if (boxY < 0 ) boxY = 0 ;
        if (boxY > frameHeight - boxSize) boxY = frameHeight - boxSize ;
        box.setY(boxY);
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
            },0, 20);

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