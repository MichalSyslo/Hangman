package com.company;

public class Timer {
    private long startTime, estimatedTime;

    public void setStartTime(){
        this.startTime = System.currentTimeMillis();
    }
    public void setEstimatedTime(){
        this.estimatedTime = System.currentTimeMillis() - getStartTime();
    }

    private long getStartTime(){
        return this.startTime;
    }
    private long getEstimatedTime(){
        return this.estimatedTime;
    }
    public int countToScore(int startLives, int numOfLives){
        int timeDivisor, multiplier;
        if(startLives==7){ timeDivisor = 500; multiplier=40;}         //this is easy level
        else if(startLives==3){ timeDivisor = 1000; multiplier=20;}   //this is hard lever
        else { timeDivisor = 750; multiplier=30;}                     //this is normal level, set by default
        int score = (int)getEstimatedTime()/timeDivisor;
        score += (startLives-numOfLives)*multiplier;
        return score;
    }
}
