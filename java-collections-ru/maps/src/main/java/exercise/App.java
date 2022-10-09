package exercise;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String word) {

        HashMap<String, Integer> wordsCount = new HashMap<>();
        String[] wordArr = word.split(" ");

        if (word.length() == 0) {
            return wordsCount;
        } else {
            for(String s : wordArr) {
                wordsCount.put(s, (word.split(s, -1).length - 1));
            }
        }

        return wordsCount;
    }

    public static String toString(Map<String, Integer> wordsCount) {

        String str= "{\n";

        for (Map.Entry<String, Integer> el: wordsCount.entrySet()){
            str += "  " + el.getKey() + ": " + el.getValue() + "\n";
        }

        str += "}";

        return (wordsCount.isEmpty())? "{}" : str;
    }

}
//END
