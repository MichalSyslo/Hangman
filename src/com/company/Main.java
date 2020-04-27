package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private int numberOfLives, startSetLives = 5,  score = 0, series = 0;
    private String keyWord, nickname, userAnswer;
    private boolean isGameFinished = false;
    public ArrayList<String> guessedLettersArray = new ArrayList<String>();

    public Scanner scanner = new Scanner(System.in);
    Timer timer = new Timer();
    ScoresHandling scoresHandling = new ScoresHandling();

    public static void main(String[] args) {
        Main game = new Main();
        game.startHangmanGame();
    }

    private void startHangmanGame() {
        String choice;
        scoresHandling.getScoresFromFile();
        System.out.println("Welcome in Hangman Game!!");
        while (true) {
            printMenu();
            choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Provide your nickname: ");
                    setNickname(scanner.next());
                    while(true) {
                        setNewGame();
                        startNewGame();
                    }
                case "2":
                    setDifficultyLevel();
                    break;
                case "3":
                    scoresHandling.printScores();
                    break;
                case "4":
                    printInstruction();
                    break;
                case "5":
                    scoresHandling.saveScoresToFile();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Provided sign is incorrect. Please chose proper game option");
            }
        }
    }
    private void setDifficultyLevel(){
        String choice;
        do {
            System.out.println("Set the difficulty lvl:\n1. Easy (7 lives)\n2. Normal (5 lives)\n3. Hard (3 lives)\n4. Exit");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    setStartSetLives(7);
                    break;
                case "2":
                    setStartSetLives(5);
                    break;
                case "3":
                    setStartSetLives(3);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Chosen option is incorrect");
                    break;
            }
        }while(!(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")));
    }
    private void gameFinished(){
        //When you won the game this method asks if you want to continue or finish.
        String choice;
        timer.setEstimatedTime();
        setScore(timer.countToScore(getStartSetLives(), getNumberOfLives()));

        do {
            System.out.println("Would you like to continue Hangman Game??\nPress: 1-Continue\n2-Save and finish");
            choice = scanner.next();
            setSeries(1);
            if (choice.equals("1")) {
                break;
            } else if (choice.equals("2")) {
                System.out.println("Your score " + getScore() + " points");
                Score score = new Score(getSeries(),getScore(), getNickname());
                scoresHandling.setBestScores(score);
                scoresHandling.saveScoresToFile();
                System.exit(0);
            } else System.out.println("Chosen option is incorrect.");
        }while (true);
    }
    private void setNewGame(){
        KeyWord key = new KeyWord();
        guessedLettersArray.clear();
        setIsGameFinished(false);
        setNumberOfLives();
        setNumberOfLives(getStartSetLives());
        key.openFileAndGetData();
        setKeyWord(key.drawKeyWord());
    }
    private void startNewGame() {
        // System.out.println(getKeyWord());
        timer.setStartTime();
        while(!getIsGameFinished()) {
            System.out.println("\n");
            displayHiddenKeyWord();
            System.out.println("Lives: " + getNumberOfLives());
            setUserAnswer(getInputFromUser());
            checkUserInput(getUserAnswer(), getKeyWord());

            if(getNumberOfLives()<=0) {
                setIsGameFinished(true);
                System.out.println("You lost");
                System.out.println("The answer is: "+ getKeyWord());
                System.out.println("Your score " + getScore() + " points");

                Score score = new Score(getSeries(),getScore(), getNickname());
                scoresHandling.setBestScores(score);
                scoresHandling.saveScoresToFile();
                System.exit(0);
            }
        }
    }
    private String getInputFromUser() {
        String choice, answer = null;
        char aChar;

        do {
            System.out.println("Would you like guess letter or word? Press: \n1-Letter\n2-Word");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Provide a letter:");
                    aChar = scanner.next().charAt(0);
                    answer = String.valueOf(aChar);
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
        }while(!(choice.equals("1") || choice.equals("2")));
        return answer;
    }
    private void checkUserInput(String userInput, String keyWord){
       int i; char tmpChar; String keyWordLetter; boolean isLetterGuessed = false;

        if(userInput.length()<=1){
            for(i=0; i<keyWord.length(); i++){
                tmpChar = keyWord.charAt(i);
                keyWordLetter = String.valueOf(tmpChar);
                if(userInput.equalsIgnoreCase(keyWordLetter)) {
                    guessedLettersArray.add(keyWordLetter);
                    isLetterGuessed = true;
                    if (keyWord.length() == guessedLettersArray.size()) {
                        setIsGameFinished(true);
                        System.out.println("You won!!!");
                        gameFinished();
                    }
                }
                else if(isLetterGuessed && i==keyWord.length()-1) {
                    break;
                } else if (i==keyWord.length()-1)
                    setNumberOfLives(-1);
            }
        } else{
            if (userInput.equalsIgnoreCase(keyWord)){
                setIsGameFinished(true);
                System.out.println("You won!!!");
                gameFinished();
            } else{
                setNumberOfLives(-2);
            }
       }
    }
    private void displayHiddenKeyWord() {
        // it display dash in the place of unknown letter and letter if it's guessed
        int i, j; char tmpChar; String keyLetter;
        for (i = 0; i < getKeyWord().length(); i++) {
            tmpChar = getKeyWord().charAt(i);
            keyLetter = String.valueOf(tmpChar);
            if(guessedLettersArray.size() == 0)
                System.out.print("_ ");
            for(j=0; j< guessedLettersArray.size(); j++){
                if(keyLetter.equals(guessedLettersArray.get(j))) {
                    System.out.print(guessedLettersArray.get(j) + " ");
                    break;
                } else if (j== guessedLettersArray.size()-1) {
                    System.out.print("_ ");
                }
            }
        }
        System.out.println("\n");
    }
    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. New game");
        System.out.println("2. Settings");
        System.out.println("3. Scores");
        System.out.println("4. Instruction");
        System.out.println("5. Exit");
    }
    private void printInstruction(){
        System.out.println("WELCOME IN HANGMAN GAME!!!");
        System.out.println("Guess hidden country names to win! The result you will gain is based on difficulty level, " +
                "time you need to guess the word, amount of remaining lives \nand guessed words. If yoy make a mistake during " +
                "guessing letter you will lost one of your lives whereas incorrect input of the whole country name takes away \n" +
                "two of your lives. Have fun and try to be in top five! Good luck!\n");
    }


    public void setStartSetLives(int startLiv){ startSetLives = startLiv; }
    public void setSeries(int st){ series += st; }
    public void setScore(int sc){ score += sc; }
    public void setNumberOfLives(int numLiv){ numberOfLives += numLiv; }
    public void setNumberOfLives(){ numberOfLives = 0; }
    public void setIsGameFinished(boolean gameFin){ isGameFinished = gameFin; }
    public void setUserAnswer(String UA){ userAnswer = UA; }
    public void setNickname(String nick){ nickname = nick; }
    public void setKeyWord(String keyW){ keyWord = keyW; }


    public int getStartSetLives() { return startSetLives; }
    public int getSeries(){ return series; }
    public int getScore(){ return score; }
    public int getNumberOfLives(){ return numberOfLives; }
    public boolean getIsGameFinished() { return isGameFinished; }
    public String getNickname() { return nickname; }
    public String getUserAnswer(){ return userAnswer; }
    public String getKeyWord(){ return keyWord; }
}
