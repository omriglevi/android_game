package com.example.androidgame;
import android.app.Application;

import com.example.androidgame.EnglishConfig;

public class AppConfig extends Application {
    private LanguageConfig languge ;
    private int level ;


    public String getScore() {
        return this.languge.getScore() ;
    }

    public void setLanguage(LanguageConfig lang){
        this.languge = lang ;
    }

    public void setLevel(int level){
        this.level = level ;
    }
    public int getLevel(){
        return this.level ;
    }



    public String getHighScore() {
        return this.languge.getHighScore();
    }


    public String getTapToStart() {
        return this.languge.getTapToStart();
    }


    public String getGameOver() {
        return this.languge.getGameOver();
    }


    public String getAnother_try() {
        return this.getAnother_try();
    }


    public String getHard() {
        return this.languge.getHard();
    }


    public String getMedium() {
        return this.languge.getMedium();
    }


    public String getEasy() {
        return this.languge.getEasy();
    }


    public String getLevelDifficulty() {
        return this.languge.getLevelDifficulty();
    }

    public AppConfig() {
        // initial english config
        this.languge = new EnglishConfig();
    }
}
