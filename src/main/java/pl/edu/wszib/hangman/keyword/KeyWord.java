package pl.edu.wszib.hangman.keyword;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class KeyWord {
    private List<String> countriesCapitolsArray = new ArrayList<String>();
    private String keyWord;

    public void getDataFromFile() {
        File countriesFile = new File("countries");
        try {
            Scanner scanner = new Scanner(countriesFile);
            while (scanner.hasNext()) {
                countriesCapitolsArray.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    public void drawKeyWord() {
        int randInt;
        String keyWord;
        Random random = new Random();
        randInt = random.nextInt(countriesCapitolsArray.size());
        keyWord = countriesCapitolsArray.get(randInt);
        this.keyWord= keyWord;
    }

    public void displayHiddenKeyWord(List<String> guessedLettersArray) {
        int i, j;
        char tmpChar;
        String letterFromKeyWord;

        for (i = 0; i < getKeyWord().length(); i++) {
            tmpChar = keyWord.charAt(i);
            letterFromKeyWord = String.valueOf(tmpChar);
            if(guessedLettersArray.size() == 0)
                System.out.print("_ ");
            for(j=0; j< guessedLettersArray.size(); j++){
                if(letterFromKeyWord.equals(guessedLettersArray.get(j))) {
                    System.out.print(guessedLettersArray.get(j) + " ");
                    break;
                } else if (j== guessedLettersArray.size()-1) {
                    System.out.print("_ ");
                }
            }
        }
        System.out.println("\n");
    }



    public List<String> getCountriesCapitolsArray() {
        return countriesCapitolsArray;
    }

    public void setCountriesCapitolsArray(List<String> countriesCapitolsArray) {
        this.countriesCapitolsArray = countriesCapitolsArray;
    }

    public String getKeyWord() {
        return keyWord;
    }
}

