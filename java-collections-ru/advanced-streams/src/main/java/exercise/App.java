package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;


// BEGIN
class App{
    public static String getForwardedVariables(String data) {

        String[] arr = data.split("\\R");

        String result = Arrays.toString(Arrays.stream(arr)
                .filter(i -> i.contains("environment") && i.contains("X_FORWARDED_"))
                .map(i -> i.substring(13, i.length()-1).split(","))
                .flatMap(i -> Stream.of(i))
                .filter(i -> i.contains("X_FORWARDED_"))
                .map(i -> i.substring(i.indexOf("_", 2)+1, i.length()))
                .toArray(String[] ::new));
        
        return result.substring(1, result.length()-1).replaceAll("\\s","");
    }
}
//END
