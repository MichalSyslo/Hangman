package com.company;

import java.io.*;
import java.util.*;

public class ScoresHandling {
    private ArrayList<Score> bestScores = new ArrayList<>();


    void setBestScores(Score s){ bestScores.add(s); }
    void printScores(){
        System.out.println("No. Series  Score    Nickname");
        for(int i=0; i<bestScores.size(); i++)
            System.out.println(i+1 + bestScores.get(i).toString());
    }
    void getScoresFromFile(){
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
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    void saveScoresToFile() {
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
}
