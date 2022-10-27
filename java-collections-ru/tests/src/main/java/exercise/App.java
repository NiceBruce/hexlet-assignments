package exercise;

import java.util.ArrayList;
import java.util.List;

class App {
    public static List<Integer> take(List<Integer> elements, int count) {
        String implementation = System.getenv("IMPLEMENTATION") != null
            ? System.getenv("IMPLEMENTATION")
            : "right";

        switch (implementation) {
            case "wrong1":
                return Implementations.wrong1(elements, count);
            case "wrong2":
                return Implementations.wrong2(elements, count);
            case "wrong3":
                return Implementations.wrong3(elements, count);
            default:
                return Implementations.right(elements, count);
        }
    }

    public static void main(String[] args) {
        List<Integer> elements = new ArrayList<>();
        elements.add(1);
        elements.add(2);
        elements.add(3);
        elements.add(4);
        elements.add(5);
        elements.add(6);

        System.out.println(App.take(elements, 0));

    }
}
