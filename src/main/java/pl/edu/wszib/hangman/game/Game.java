package pl.edu.wszib.hangman.game;

import pl.edu.wszib.hangman.keyword.KeyWord;
import pl.edu.wszib.hangman.player.Player;
import pl.edu.wszib.hangman.score.Score;
import pl.edu.wszib.hangman.score.ScoresHandler;
import pl.edu.wszib.hangman.timer.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    String choice, userAnswer;
    public List<String> guessedLettersArray = new ArrayList<String>();
    Player player = Player.getInstance();
    KeyWord keyWord = new KeyWord();
    private boolean isGameFinished = false;
    Scanner scanner = new Scanner(System.in);

    public void startNewGame() {

        guessedLettersArray.clear();
        isGameFinished=false;

        player.clearNumberOfLives();
        player.setNumberOfLives(player.getStartSetLives());

        keyWord.getDataFromFile();
        keyWord.drawKeyWord();

        Timer.setStartTime();

        while(!isGameFinished) {
            System.out.println("\n");
            keyWord.displayHiddenKeyWord(guessedLettersArray);
            System.out.println("Lives: " + player.getNumberOfLives());
            userAnswer=getInputFromUser();
            checkInputFromUser(userAnswer, keyWord.getKeyWord());

            if(player.getNumberOfLives()<=0) {
                isGameFinished=true;
                System.out.println("You lost");
                System.out.println("The answer is: "+ keyWord.getKeyWord());
                System.out.println("Your score " + player.getScore() + " points");

                ScoresHandler.getBestScores().add(new Score(player.getSeries(),player.getScore(), player.getNickname()));
                ScoresHandler.saveScoresToFile();

                player.setScore(0);
                player.clearSeries();
            }
        }
    }

    public String getInputFromUser() {
        String answer = null;

        do {
            System.out.println("Would you like to1 guess letter or word? Press: \n1-Letter\n2-Word");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Provide a letter:");
                    answer = scanner.next().substring(0,1);
                    break;
                case "2":
                    System.out.println("Provide a word:");
                    answer = scanner.next();
                    if (answer.length() <= 1) {
                        System.out.println("You provided just only one letter instead of word.");
                        break;
                    }
                    break;
                default:
                    System.out.println("Provided choice is incorrect. Please chose proper option\n");
                    break;
            }
        } while (!(choice.equals("1") || choice.equals("2")));
        return answer;
    }

    public void checkInputFromUser(String userInput, String keyWord){
        int i;
        char tmpChar;
        String letterFromKeyWord;
        boolean isLetterGuessed = false;

        if(userInput.length()<=1){
            for(i=0; i<keyWord.length(); i++){
                tmpChar = keyWord.charAt(i);
                letterFromKeyWord = String.valueOf(tmpChar);
                if(userInput.equalsIgnoreCase(letterFromKeyWord)) {
                    guessedLettersArray.add(letterFromKeyWord);
                    isLetterGuessed = true;
                    if (keyWord.length() == guessedLettersArray.size()) {
                        System.out.println("You won!!!");
                        gameFinished();
                    }
                }
                else if(isLetterGuessed && i==keyWord.length()-1) {
                    break;
                } else if (i==keyWord.length()-1) {
                    player.setNumberOfLives(-1);
                }
            }
        } else{
            if (userInput.equalsIgnoreCase(keyWord)){
                System.out.println("You won!!!");
                gameFinished();
            } else{
                player.setNumberOfLives(-2);
            }
        }
    }

    public void gameFinished(){
        Timer.setEstimatedTime();
        player.setScore(Timer.calculateScore(player.getStartSetLives(), player.getNumberOfLives()));

        do {
            System.out.println("Would you like to continue Hangman Game??\nPress: \n1-Continue\n2-Save and finish");
            choice = scanner.next();
            player.setSeries(1);
            if (choice.equals("1")) {
                keyWord.drawKeyWord();
                System.out.println(keyWord.getKeyWord());  //TODO
            } else if (choice.equals("2")) {
                System.out.println("You score " + player.getScore() + " points");
                ScoresHandler.getBestScores().add(new Score(player.getSeries(),player.getScore(), player.getNickname()));
                ScoresHandler.saveScoresToFile();
                isGameFinished=true;
                player.setScore(0);
                player.clearSeries();
            } else System.out.println("Chosen option is incorrect.");
        }while (!(choice.equals("1") || choice.equals("2")));
    }
}
