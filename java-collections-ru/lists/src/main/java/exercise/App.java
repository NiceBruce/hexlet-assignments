package exercise;

import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
public class App {
    public static boolean scrabble(String setSymbols, String word) {

        ArrayList<String> listSymbols = new ArrayList<>();

        listSymbols.addAll(Arrays.asList(setSymbols.split("")));

        String[] wordArr = word.toLowerCase().split("");

        for (String letter : wordArr) {
            if (!listSymbols.contains(letter)) {
                return false;
            }
            listSymbols.remove(letter);
        }

        return true;
    }
}
//END
