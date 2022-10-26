package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {

    public static <T> List<Map<T, T>> findWhere(List<Map<T, T>> books, Map<T, T> where) {

        List<Map<T, T>> result = new ArrayList<>();
        int matchCount;

        for (Map<T,T> book: books) {

            matchCount = 0;

            for (Map.Entry<T, T> i : book.entrySet()) {
                if (where.containsKey(i.getKey()) && where.containsValue(i.getValue())) {
                    matchCount += 1;
                }
            }

            if (matchCount == where.size()) {
                result.add(book);
            }
        }

        return result;
    }
}

//END
