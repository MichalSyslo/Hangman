package pl.edu.wszib.hangman.player;


public class Player {
    private int numberOfLives, startSetLives = 5,  score = 0, series = 0;
    private String nickname;
    private static final Player instance = new Player();

    private Player(){}

    public static Player getInstance(){
        return instance;
    }

    public void clearSeries(){ this.series = 0; }

    public void clearNumberOfLives() {
        this.numberOfLives = 0;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives += numberOfLives;
    }
    public int getNumberOfLives() {
        return numberOfLives;
    }


    public void setStartSetLives(int startSetLives) {
        this.startSetLives = startSetLives;
    }
    public int getStartSetLives() {
        return startSetLives;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setSeries(int series) {
        this.series += series;
    }
    public int getSeries() {
        return series;
    }
}
