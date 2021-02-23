package pl.edu.wszib.hangman.gui;

import pl.edu.wszib.hangman.game.Game;
import pl.edu.wszib.hangman.player.Player;
import pl.edu.wszib.hangman.score.ScoresHandler;

import java.util.Scanner;


public class GUI {
    Scanner scanner = new Scanner(System.in);

    Player player = Player.getInstance();


    public void showMainMenu() {
        String choice;
        Scanner scanner = new Scanner(System.in);

        ScoresHandler.getScoresFromFile();
        System.out.println("Welcome in Hangman Game!!");
        System.out.println("---------------------------");

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. New game");
            System.out.println("2. Settings");
            System.out.println("3. Scores");
            System.out.println("4. Instruction");
            System.out.println("5. Exit");

            choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Provide your nickname: ");
                    this.player.setNickname(scanner.next());

                    Game game = new Game();
                    game.startNewGame();
                    break;
                case "2":
                    setDifficultyLevel();
                    break;
                case "3":
                    ScoresHandler.showScores();
                    break;
                case "4":
                    printInstruction();
                    break;
                case "5":
                    ScoresHandler.saveScoresToFile();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Provided sign is incorrect. Please chose proper game option");
            }
        }
    }

    private void setDifficultyLevel() {
        String choice;
        do {
            System.out.println("Set difficulty lvl:\n" +
                    "1. Easy (7 lives)\n" +
                    "2. Normal (5 lives)\n" +
                    "3. Hard (3 lives)\n" +
                    "4. Exit");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    player.setStartSetLives(7);
                    break;
                case "2":
                    player.setStartSetLives(5);
                    break;
                case "3":
                    player.setStartSetLives(3);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Chosen option is incorrect");
                    break;
            }
        } while (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")));
    }

    private void printInstruction() {
        System.out.println("WELCOME IN HANGMAN GAME!!!");
        System.out.println("Guess hidden country names to win! The result you will gain is based on difficulty level, " +
                "time you need to guess the word, amount of remaining lives \nand guessed words. If yoy make a mistake during " +
                "guessing letter you will lost one of your lives whereas incorrect input of the whole country name takes away \n" +
                "two of your lives. Have fun and try to be in top five! Good luck!\n");
    }
}
