package pl.edu.wszib.hangman.timer;

public class Timer {
    private static long startTime, estimatedTime;

    public static int calculateScore(int startLives, int numOfLives){
        int timeDivisor, multiplier;
        if(startLives==7){ timeDivisor = 500; multiplier=40;}         // easy level
        else if(startLives==3){ timeDivisor = 1000; multiplier=20;}   // hard lever
        else { timeDivisor = 750; multiplier=30;}                     // normal level, set by default
        int score = (int)getEstimatedTime()/timeDivisor;
        score += (startLives-numOfLives)*multiplier;
        return score;
    }


    private static long getStartTime(){
        return startTime;
    }
    public static void setStartTime(){
        startTime = System.currentTimeMillis();
    }

    private static long getEstimatedTime(){
        return estimatedTime;
    }
    public static void setEstimatedTime(){
        estimatedTime = System.currentTimeMillis() - getStartTime();
    }


}
