package com.example.androidgame;

public class EnglishConfig implements LanguageConfig {
    public EnglishConfig() {
    }

    public String getScore() {
        return "score";
    }

    public String getHighScore() {
        return "High Score";
    }

    public String getTapToStart() {
        return "Tap To Start";
    }

    public String getGameOver() {
        return "Game Over";
    }

    public String getAnother_try() {
        return "Another Try";
    }

    @Override
    public String getHard() {
        return "Hard";
    }

    @Override
    public String getMedium() {
        return "Medium";
    }

    @Override
    public String getEasy() {
        return "Easy";
    }

    @Override
    public String getLevelDifficulty() {
        return "Difficulty";
    }
}
