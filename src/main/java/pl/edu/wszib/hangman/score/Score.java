package pl.edu.wszib.hangman.score;

public class Score implements Comparable<Score>{
    private int points, series; private String nickname;

    public Score(int sr, int pts, String nick){
        this.points = pts;
        this.series = sr;
        this.nickname = nick;
    }

    @Override
    public int compareTo(Score score){
        if (this.series < score.series)
            return 1;
        else if (this.series > score.series)
            return -1;
        else {
            if (this.points > score.points)
                return 1;
            else if (this.points < score.points)
                return -1;
            else{
                return 0;
            }
        }
    }

    @Override
    public String toString(){
        return String.format("   %03d     %06d   %s", this.series, this.points, this.nickname);
    }
}