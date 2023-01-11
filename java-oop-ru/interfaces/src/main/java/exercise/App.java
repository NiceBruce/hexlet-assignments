package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int n) {

        List<String> sortedList = apartments.stream()
                .sorted((a1, a2) -> {
                    return a1.compareTo(a2);
                })
                .map(apart -> apart.toString())
                .collect(Collectors.toList());

        return (apartments.size() == 0) ? new ArrayList<>() : sortedList.subList(0, n);
    }
}
// END
