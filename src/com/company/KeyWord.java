package com.company;

import java.io.*;
import java.util.*;

public class KeyWord {
    public ArrayList<String> countriesCapitolsArray = new ArrayList<String>();
    public void openFileAndGetData() {
        File countriesFile = new File("./Countries");
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
    public String drawKeyWord() {
        int randInt;
        String keyWord;
        Random random = new Random();
        randInt = random.nextInt(countriesCapitolsArray.size());
        keyWord = countriesCapitolsArray.get(randInt);
        return keyWord;
    }
}
