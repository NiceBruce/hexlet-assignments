package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test3 {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        l1.add("D");
        l1.add("F");
        l1.add("E");
        l1.add("G");
        l1.add("C");
        l1.add("B");
        l1.add("A");
        System.out.println(l1);
        Collections.sort(l1);
        System.out.println(l1);

    }
}
