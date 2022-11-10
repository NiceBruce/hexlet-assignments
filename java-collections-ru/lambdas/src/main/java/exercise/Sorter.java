package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> mans) {
        return mans.stream()
                .filter(man -> man.get("gender").equals("male"))
                .sorted((man1, man2) -> LocalDate.parse(man1.get("birthday"))
                        .compareTo(LocalDate.parse(man2.get("birthday"))))
                .map(man -> man.get("name"))
                .collect(Collectors.toList());
    }
}
// END
