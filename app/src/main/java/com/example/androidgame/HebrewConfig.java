package com.example.androidgame;

public class HebrewConfig implements LanguageConfig {

    public String getScore() {
        return "תוצאה";
    }

    public String getHighScore() {
        return "בוא נתחיל";
    }

    public String getTapToStart() {
        return "בוא נשחק !";
    }

    public String getGameOver() {
        return "המשחק נגמר";
    }

    public String getAnother_try() {
        return "משחק נוסף";
    }

    @Override
    public String getHard() {
        return "קל";
    }

    @Override
    public String getMedium() {
        return "בינוני";
    }

    @Override
    public String getEasy() {
        return "קשה";
    }

    @Override
    public String getLevelDifficulty() {
        return "רמת קושי";
    }
}
