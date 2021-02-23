package pl.edu.wszib.hangman.score;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ScoresHandler {
    private static List<Score> bestScores = new ArrayList<>();

    public static void showScores(){
        int i=0;
        System.out.println("No. Series  Score    Nickname");
        for(Score score : bestScores)
            System.out.println(i+1 + score.toString());
    }

    public static void getScoresFromFile() {
        int series, points;
        String nickname;
        try {
            File file = new File("Scores.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                series = Integer.parseInt(sc.next());
                points = Integer.parseInt(sc.next());
                nickname = sc.next();
                bestScores.add(new Score(series, points, nickname));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void saveScoresToFile() {
        File file = new File("Scores.txt");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            Collections.sort(bestScores);
            if(bestScores.size() < 5) {
                for (int i = 0; i < bestScores.size(); i++) {
                    printWriter.println(bestScores.get(i).toString());
                }
            } else{
                for (int i = 0; i < 5; i++) {
                    printWriter.println(bestScores.get(i).toString());
                }
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("File can not be created.");
        }
    }


    public static List<Score> getBestScores() {
        return bestScores;
    }

}
