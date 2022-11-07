package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {
    public static int getCountOfFreeEmails(List<String> email) {

        List<String> freeEmail = Arrays.asList("gmail.com", "yandex.ru", "hotmail.com");

        return (int) email.stream()
                .filter(element -> freeEmail.contains(element.substring(element.indexOf('@') + 1)))
                .count();
    }
}
// END
